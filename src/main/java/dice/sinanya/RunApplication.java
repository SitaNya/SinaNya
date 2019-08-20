package dice.sinanya;

import com.sobte.cqp.jcq.entity.ICQVer;
import com.sobte.cqp.jcq.entity.IMsg;
import com.sobte.cqp.jcq.entity.IRequest;
import com.sobte.cqp.jcq.event.JcqAppAbstract;
import dice.sinanya.db.properties.ban.SelectBanProperties;
import dice.sinanya.db.properties.system.SelectSystemProperties;
import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.dice.system.Bot;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.entity.imal.MessagesTypes;
import dice.sinanya.exceptions.OnlyManagerException;
import dice.sinanya.flow.Flow;
import dice.sinanya.listener.InputHistoryToDataBase;
import dice.sinanya.listener.Prometheus;
import dice.sinanya.listener.TestRunningTime;
import dice.sinanya.windows.Setting;
import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.regex.Pattern;

import static dice.sinanya.db.system.SelectBot.flushBot;
import static dice.sinanya.tools.getinfo.BanList.*;
import static dice.sinanya.tools.getinfo.DefaultMaxRolls.flushMaxRolls;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.*;
import static dice.sinanya.tools.getinfo.GetNickName.getGroupName;
import static dice.sinanya.tools.getinfo.GetNickName.getUserName;
import static dice.sinanya.tools.getinfo.History.flushHistory;
import static dice.sinanya.tools.getinfo.History.setHistory;
import static dice.sinanya.tools.getinfo.Kp.flushKp;
import static dice.sinanya.tools.getinfo.LogTag.*;
import static dice.sinanya.tools.getinfo.LogText.setLogText;
import static dice.sinanya.tools.getinfo.RoleChoose.flushRoleChoose;
import static dice.sinanya.tools.getinfo.RoleInfo.flushRoleInfoCache;
import static dice.sinanya.tools.getinfo.SwitchBot.getBot;
import static dice.sinanya.tools.getinfo.Team.flushTeamEn;
import static dice.sinanya.tools.getinfo.Team.saveTeamEn;
import static dice.sinanya.tools.makedata.Sender.sender;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author SitaNya 日期: 2019-06-15 电子邮箱: sitanya@qq.com 维护群(QQ): 162279609
 * 有任何问题欢迎咨询 类说明: 整个程序的入口类
 * <p>
 * 这里可以修改的是before方法(但我已经改造为配置文件了，因此可以不动这个方法）
 * 此外这里声明了大量服务启动时需要从服务器中获取的缓存数据
 */
public class RunApplication extends JcqAppAbstract implements ICQVer, IMsg, IRequest, MakeNickToSender {
    Scheduler scheduler;
    private Pattern commandHeader = Pattern.compile("^[ ]*[.。][ ]*.*");
    private Pattern chineseRegex = Pattern.compile("([\\u4e00-\\u9fa5]+)");
    private String atTag = "[cq:at,qq=?]";
    private String tagMe = "";

    public static void main(String[] args) {
        RunApplication runApplication = new RunApplication();
        runApplication.startup();
        runApplication.enable();
        runApplication.disable();
    }

    /**
     * 酷Q启动 (Type=1001)<br>
     * 本方法会在酷Q【主线程】中被调用。<br>
     * 请在这里执行插件初始化代码。<br>
     * 请务必尽快返回本子程序，否则会卡住其他插件以及主程序的加载。
     *
     * @return 请固定返回0
     */
    @Override
    public int startup() {
        initMessagesSystemProperties();
        initMessagesBanProperties();
        entitySystemProperties.setSystemDir(CQ.getAppDirectory());
        return 0;
    }

    @Override
    public int exit() {
        setHistory();
        saveTeamEn();
        flushTeamEn();
        // 从数据库中读取幕间成长到缓存
        flushMaxRolls();
        // 从数据库中读取最大默认骰到缓存
        flushBot();
        // 从数据库中读取机器人开关到缓存
        flushRoleChoose();
        // 从数据库中读取当前已选角色到缓存
        flushRoleInfoCache();
        // 从数据库中读取角色信息到缓存
        flushLogTag();
        // 从数据库中读取日志开关到缓存
        flushKp();
        // 从数据库中读取kp主群设定到缓存
        flushHistory();
        // 从数据库中读取骰点历史信息到缓存
        flushBanList();
        // 刷写黑名单
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
        return 0;
    }

    @Override
    public int enable() {
        tagMe = String.format(atTag, CQ.getLoginQQ());
        new SelectSystemProperties().flushProperties();
        new SelectBanProperties().flushProperties();
        CQ.logInfo("系统日志", "开始刷写数据库");
        flushTeamEn();
        CQ.logInfo("数据库", "读取幕间成长到缓存");
        flushMaxRolls();
        CQ.logInfo("数据库", "读取最大默认骰到缓存");
        flushBot();
        CQ.logInfo("数据库", "读取机器人开关到缓存");
        flushRoleChoose();
        CQ.logInfo("数据库", "读取当前已选角色到缓存");
        flushRoleInfoCache();
        CQ.logInfo("数据库", "读取角色信息到缓存");
        flushLogTag();
        CQ.logInfo("数据库", "读取日志开关到缓存");
        flushKp();
        CQ.logInfo("数据库", "读取kp主群设定到缓存");
        flushHistory();
        CQ.logInfo("数据库", "读取骰点历史信息到缓存");
        if (entityBanProperties.isCloudBan()) {
            flushBanList();
            CQ.logInfo("数据库", "读取云黑列表到缓存");
        }
        if (entityBanProperties.isPrometheus()) {
            new dice.sinanya.monitor.Prometheus().start();
            CQ.logInfo("监控", "开启普罗米修斯监控");
        }

        try {
            // 从Scheduler工厂获取一个Scheduler的实例
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();
            JobDetail jobDetail1 = newJob(Prometheus.class).withIdentity("Prometheus", "monitor").build();
            jobDetail1.getJobDataMap().put("CONTENT", String.valueOf(System.currentTimeMillis()));
            Trigger trigger1 = newTrigger().withIdentity("trigger1", "monitor").startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 * * * ?")).build();
            scheduler.scheduleJob(jobDetail1, trigger1);

            JobDetail jobDetail2 = newJob(InputHistoryToDataBase.class).withIdentity("flushDatabase", "data").build();
            jobDetail2.getJobDataMap().put("CONTENT", String.valueOf(System.currentTimeMillis()));
            Trigger trigger2 = newTrigger().withIdentity("trigger2", "data").startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 * * * ?")).build();
            scheduler.scheduleJob(jobDetail2, trigger2);

            JobDetail jobDetail3 = newJob(TestRunningTime.class).withIdentity("cleanGroup", "clean").build();
            jobDetail3.getJobDataMap().put("CONTENT", String.valueOf(System.currentTimeMillis()));
            Trigger trigger3 = newTrigger().withIdentity("trigger3", "clean").startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 * * * ?")).build();
            scheduler.scheduleJob(jobDetail3, trigger3);

        } catch (SchedulerException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
        return 0;
    }

    @Override
    public int disable() {
        setHistory();
        saveTeamEn();
        flushTeamEn();
        // 从数据库中读取幕间成长到缓存
        flushMaxRolls();
        // 从数据库中读取最大默认骰到缓存
        flushBot();
        // 从数据库中读取机器人开关到缓存
        flushRoleChoose();
        // 从数据库中读取当前已选角色到缓存
        flushRoleInfoCache();
        // 从数据库中读取角色信息到缓存
        flushLogTag();
        // 从数据库中读取日志开关到缓存
        flushKp();
        // 从数据库中读取kp主群设定到缓存
        flushHistory();
        // 从数据库中读取骰点历史信息到缓存
        flushBanList();
        // 刷写黑名单
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
        return 0;
    }

    /**
     * 私聊消息 (Type=21)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType 子类型，11/来自好友 1/来自在线状态 2/来自群 3/来自讨论组
     * @param msgId   消息ID
     * @param fromQq  来源QQ
     * @param msg     消息内容
     * @param font    字体
     * @return 返回值*不能*直接返回文本 如果要回复消息，请调用api发送<br>
     * 这里 返回 {@link IMsg#MSG_INTERCEPT MSG_INTERCEPT} - 截断本条消息，不再继续处理<br>
     * 注意：应用优先级设置为"最高"(10000)时，不得使用本返回值<br>
     * 如果不回复消息，交由之后的应用/过滤器处理，这里 返回 {@link IMsg#MSG_IGNORE MSG_IGNORE} -
     * 忽略本条消息
     */
    @Override
    public int privateMsg(int subType, int msgId, long fromQq, String msg, int font) {
        if (!msg.matches(commandHeader.toString())) {
            return MSG_IGNORE;
        }
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(MessagesTypes.PRIVATE_MSG, fromQq, msg);
        if (checkQqInBanList(entityTypeMessages.getFromQq())) {
            return MSG_INTERCEPT;
        }
        if (msg.contains("bot") && !msg.contains("update")) {
            new Bot(entityTypeMessages).info();
        } else if (msg.contains("bot update")) {
            new Bot(entityTypeMessages).update();
        } else {
            new Flow(entityTypeMessages).toPrivate();
        }
        return MSG_IGNORE;
    }

    /**
     * 群消息 (Type=2)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType       子类型，目前固定为1
     * @param msgId         消息ID
     * @param fromGroup     来源群号
     * @param fromQq        来源QQ号
     * @param fromAnonymous 来源匿名者
     * @param msg           消息内容
     * @param font          字体
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int groupMsg(int subType, int msgId, long fromGroup, long fromQq, String fromAnonymous, String msg,
                        int font) {
        try {
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(MessagesTypes.GROUP_MSG, fromQq, fromGroup, msg);
        if ((checkBeBanOrInBan(entityTypeMessages) == MSG_INTERCEPT)) {
            return MSG_INTERCEPT;
        }

        try {
            if (checkBotSwitch(entityTypeMessages) == MSG_INTERCEPT) {
                return MSG_INTERCEPT;
            }
        } catch (OnlyManagerException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }

        if (!getBot(fromGroup)) {
            return MSG_INTERCEPT;
        }

        if (msg.matches(commandHeader.toString())) {
            new Flow(entityTypeMessages).toGroup();
            setLogsForText(entityTypeMessages);
            return MSG_IGNORE;
        } else {
            setLogsForText(entityTypeMessages);
            return MSG_IGNORE;
        }
    }

    /**
     * 讨论组消息 (Type=4)<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype     子类型，目前固定为1
     * @param msgId       消息ID
     * @param fromDiscuss 来源讨论组
     * @param fromQq      来源QQ号
     * @param msg         消息内容
     * @param font        字体
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int discussMsg(int subtype, int msgId, long fromDiscuss, long fromQq, String msg, int font) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(MessagesTypes.DISCUSS_MSG, fromQq, fromDiscuss,
                msg);
        if (checkBeBanOrInBan(entityTypeMessages) == MSG_INTERCEPT) {
            return MSG_INTERCEPT;
        }

        try {
            if (checkBotSwitch(entityTypeMessages) == MSG_INTERCEPT) {
                return MSG_INTERCEPT;
            }
        } catch (OnlyManagerException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }

        if (!getBot(fromDiscuss)) {
            return MSG_INTERCEPT;
        }

        if (msg.matches(commandHeader.toString())) {
            new Flow(entityTypeMessages).toDisGroup();
            setLogsForText(entityTypeMessages);
            return MSG_IGNORE;
        } else {
            setLogsForText(entityTypeMessages);
            return MSG_IGNORE;
        }
    }

    /**
     * 群文件上传事件 (Type= )<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subType   子类型，目前固定为1
     * @param sendTime  发送时间(时间戳)// 10位时间戳
     * @param fromGroup 来源群号
     * @param fromQQ    来源QQ号
     * @param file      上传文件信息
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int groupUpload(int subType, int sendTime, long fromGroup, long fromQQ, String file) {
        return 0;
    }

    /**
     * 群事件-管理员变动 (Type= )<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/被取消管理员 2/被设置管理员
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param beingOperateQQ 被操作QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int groupAdmin(int subtype, int sendTime, long fromGroup, long beingOperateQQ) {
        return 0;
    }

    /**
     * 群事件-群成员减少 (Type= )<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/群员离开 2/群员被踢
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param fromQQ         操作者QQ(仅子类型为2时存在)
     * @param beingOperateQQ 被操作QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int groupMemberDecrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        if (subtype == 2 && beingOperateQQ == CQ.getLoginQQ() && entityBanProperties.isBanGroupBecauseReduce()) {
            if (entityBanProperties.isBanUserBecauseReduce()) {
                CQ.sendGroupMsg(162279609, "已被移出群" + "(" + fromGroup + ")中，将群和操作者"
                        + getUserName(fromQQ) + "(" + fromQQ + ")拉黑");
                insertQqBanList(String.valueOf(beingOperateQQ), "被踢出群" + fromGroup);
                insertGroupBanList(String.valueOf(fromGroup), "被" + fromQQ + "踢出");
            } else {
                CQ.sendGroupMsg(162279609, "已被移出群" + "(" + fromGroup + ")中，将群拉黑");
                insertGroupBanList(String.valueOf(fromGroup), "被" + fromQQ + "踢出");
            }
        }
        return 0;
    }

    /**
     * 群事件-群成员增加 (Type= )<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype        子类型，1/管理员已同意 2/管理员邀请
     * @param sendTime       发送时间(时间戳)
     * @param fromGroup      来源群号
     * @param fromQQ         操作者QQ(即管理员QQ)
     * @param beingOperateQQ 被操作QQ(即加群的QQ)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int groupMemberIncrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        return 0;
    }

    /**
     * 好友事件-好友已添加 (Type= )<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype  子类型，目前固定为1
     * @param sendTime 发送时间(时间戳)
     * @param fromQQ   来源QQ
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int friendAdd(int subtype, int sendTime, long fromQQ) {
        return 0;
    }

    /**
     * 请求-好友添加 (Type= )<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype      子类型，目前固定为1
     * @param sendTime     发送时间(时间戳)
     * @param fromQQ       来源QQ
     * @param msg          附言
     * @param responseFlag 反馈标识(处理请求用)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int requestAddFriend(int subtype, int sendTime, long fromQQ, String msg, String responseFlag) {
        if (!checkQqInBanList(String.valueOf(fromQQ))) {
            CQ.sendGroupMsg(162279609, "收到" + getUserName(fromQQ) + "(" + fromQQ + ")的好友邀请，已同意");
            CQ.setFriendAddRequest(responseFlag, REQUEST_ADOPT, "");
            CQ.sendPrivateMsg(fromQQ, entityBanProperties.getAddFriend());
        } else {
            CQ.sendPrivateMsg(162279609, "收到" + fromQQ + "的好友邀请，处于黑名单中已拒绝");
            CQ.setFriendAddRequest(responseFlag, REQUEST_REFUSE, "您处于黑名单中");
        }
        return MSG_INTERCEPT;
    }

    /**
     * 请求-群添加 (Type= )<br>
     * 本方法会在酷Q【线程】中被调用。<br>
     *
     * @param subtype      子类型，1/他人申请入群 2/自己(即登录号)受邀入群
     * @param sendTime     发送时间(时间戳)
     * @param fromGroup    来源群号
     * @param fromQQ       来源QQ
     * @param msg          附言
     * @param responseFlag 反馈标识(处理请求用)
     * @return 关于返回值说明, 见 {@link #privateMsg 私聊消息} 的方法
     */
    @Override
    public int requestAddGroup(int subtype, int sendTime, long fromGroup, long fromQQ, String msg,
                               String responseFlag) {
        if (subtype == 2) {
            if (!checkQqInBanList(String.valueOf(fromQQ)) && !checkGroupInBanList(String.valueOf(fromGroup))) {
                CQ.setGroupAddRequestV2(responseFlag, REQUEST_GROUP_INVITE, REQUEST_ADOPT, "");
                CQ.sendGroupMsg(162279609, "收到" + getUserName(fromQQ) + "(" + fromQQ + ")的群" + getGroupName(fromGroup) + "("
                        + fromGroup + ")邀请，已同意");
                CQ.sendGroupMsg(fromGroup, entityBanProperties.getAddGroup());
            } else {
                CQ.sendGroupMsg(162279609,
                        "收到" + getUserName(fromQQ) + "(" + fromQQ + ")的群" + fromGroup + "邀请，处于黑名单中已拒绝");
                CQ.setGroupAddRequestV2(responseFlag, REQUEST_GROUP_INVITE, REQUEST_REFUSE, "群或邀请人处于黑名单内");
            }
        }
        return MSG_INTERCEPT;
    }

    private int checkBeBanOrInBan(EntityTypeMessages entityTypeMessages) {
        if (checkQqInBanList(entityTypeMessages.getFromQq()) && entityBanProperties.isIgnoreBanUser()) {
            return MSG_INTERCEPT;
        }

        if (checkGroupInBanList(entityTypeMessages.getFromGroup()) && entityBanProperties.isLeaveGroupByBan()) {
            sender(entityTypeMessages, "检测到处于黑名单群中，正在退群");
            CQ.sendGroupMsg(162279609, "检测到处于黑名单群" + makeGroupNickToSender(getGroupName(entityTypeMessages)) + "("
                    + entityTypeMessages.getFromGroup() + ")中，正在退群");
            leave(entityTypeMessages.getMessagesTypes(), entityTypeMessages.getFromGroup());
            return MSG_INTERCEPT;
        }

        if (checkBeBan(entityTypeMessages.getMsg()) && entityBanProperties.isBanGroupBecauseBan()) {
            sender(entityTypeMessages, "于群" + makeGroupNickToSender(getGroupName(entityTypeMessages)) + "("
                    + entityTypeMessages.getFromGroup() + ")中被禁言，已退出并拉黑");
            CQ.sendGroupMsg(162279609, "于群" + entityTypeMessages.getFromGroup() + "中被禁言，已退出并拉黑");
            leave(entityTypeMessages.getMessagesTypes(), entityTypeMessages.getFromGroup());
            insertGroupBanList(entityTypeMessages.getFromGroup(), "被禁言");
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }

    private boolean checkBeBan(String msg) {
        return msg.contains("禁言") && msg.contains(String.valueOf(CQ.getLoginQQ()));
    }

    private void leave(MessagesTypes messagesTypes, String groupId) {
        switch (messagesTypes) {
            case GROUP_MSG:
                CQ.setGroupLeave(Long.parseLong(groupId), false);
                break;
            case DISCUSS_MSG:
                CQ.setDiscussLeave(Long.parseLong(groupId));
                break;
            default:
                break;
        }
    }

    private int checkBotSwitch(EntityTypeMessages entityTypeMessages) throws OnlyManagerException {
        String messages = entityTypeMessages.getMsg().toLowerCase();

        if (!messages.contains("bot")) {
            return MSG_IGNORE;
        }

        String tagBotOff = ".*[.。][ ]*bot[ ]*off.*";
        String tagBotInfo = ".*[.。][ ]*bot.*";
        String tagBotExit = ".*[.。][ ]*bot[ ]*exit.*";
        String tagBotUpdate = ".*[.。][ ]*bot[ ]*update.*";

        String tagBotOn = ".*[.。][ ]*bot[ ]*on.*";
        boolean botOn = messagesContainsAtMe(messages, tagBotOn, tagMe) || messagesBotForAll(messages, tagBotOn)
                || messagesContainsQqId(messages, tagBotOn);
        boolean botOff = messagesContainsAtMe(messages, tagBotOff, tagMe) || messagesBotForAll(messages, tagBotOff)
                || messagesContainsQqId(messages, tagBotOff);
        boolean botExit = messagesContainsAtMe(messages, tagBotExit, tagMe) || messagesBotForAll(messages, tagBotExit)
                || messagesContainsQqId(messages, tagBotExit);
        boolean botUpdate = (messagesContainsAtMe(messages, tagBotUpdate, tagMe)
                || messagesBotForAll(messages, tagBotUpdate) || messagesContainsQqId(messages, tagBotUpdate));
        boolean botInfo = (messagesContainsAtMe(messages, tagBotInfo, tagMe) || messagesBotForAll(messages, tagBotInfo)
                || messagesContainsQqId(messages, tagBotInfo)) && !botOn && !botOff && !botExit && !botUpdate;

        if (botOn) {
            checkAudit(entityTypeMessages);
            new Bot(entityTypeMessages).on();
        } else if (botOff) {
            checkAudit(entityTypeMessages);
            new Bot(entityTypeMessages).off();
        } else if (botExit) {
            checkAudit(entityTypeMessages);
            new Bot(entityTypeMessages).exit();
        } else if (botUpdate) {
            new Bot(entityTypeMessages).update();
        } else if (botInfo) {
            new Bot(entityTypeMessages).info();
        }
        return MSG_INTERCEPT;
    }

    private boolean messagesContainsAtMe(String messages, String tagBotSwitch, String tagMe) {
        boolean atMe = messages.trim().matches(tagBotSwitch) && messages.trim().contains(tagMe);
        CQ.logDebug("返回机器人开关值", "at自身: " + atMe);
        return atMe;
    }

    private boolean messagesBotForAll(String messages, String tagBotSwitch) {
        boolean forAll = messages.trim().matches(tagBotSwitch) && !messages.trim().contains("[cq:at")
                && !messages.matches(".*[0-9]+.*") && !messages.matches(tagBotSwitch.substring(2, tagBotSwitch.length() - 2));
        CQ.logDebug("判断信息为", messages);
        CQ.logDebug("返回机器人开关值", "全体: " + forAll);
        return forAll;
    }

    private boolean messagesContainsQqId(String messages, String tagBotSwitch) {
        String qqId = String.valueOf(CQ.getLoginQQ());
        boolean containsQqId = messages.trim().matches(tagBotSwitch)
                && (messages.trim().contains(qqId) || messages.trim().contains(qqId.substring(qqId.length() - 5)));
        CQ.logDebug("返回机器人开关值", "QQId: " + containsQqId);
        return containsQqId;
    }

    private void checkAudit(EntityTypeMessages entityTypeMessages) throws OnlyManagerException {
        int power = CQ.getGroupMemberInfo(Long.parseLong(entityTypeMessages.getFromGroup()),
                Long.parseLong(entityTypeMessages.getFromQq())).getAuthority();

        boolean boolIsAdmin = power != 1;
        boolean boolIsAdminOrInDiscuss = boolIsAdmin
                || entityTypeMessages.getMessagesTypes() == MessagesTypes.DISCUSS_MSG;
        if (!boolIsAdminOrInDiscuss) {
            throw new OnlyManagerException(entityTypeMessages);
        }
    }

    private void setLogsForText(EntityTypeMessages entityTypeMessages) {
        if (checkOthorLogTrue(entityTypeMessages.getFromGroup())) {
            setLogText(entityTypeMessages, new EntityLogTag(entityTypeMessages.getFromGroup(),
                    getOtherLogTrue(entityTypeMessages.getFromGroup())), entityTypeMessages.getMsg());
        }
    }

    public int setting() {
        new Setting();
        return 0;
    }

    /**
     * 返回应用的ApiVer、Appid，打包后将不会调用<br>
     * 本函数【禁止】处理其他任何代码，以免发生异常情况。<br>
     * 如需执行初始化代码请在 startup 事件中执行（Type=1001）。<br>
     *
     * @return 应用信息
     */
    @Override
    public String appInfo() {
        // 应用AppID,规则见 http://d.cqp.me/Pro/开发/基础信息#appid
        String AppID = "com.sinanya.dice";// 记住编译后的文件和json也要使用appid做文件名
        /**
         * 本函数【禁止】处理其他任何代码，以免发生异常情况。 如需执行初始化代码请在 startup 事件中执行（Type=1001）。
         */
        return CQAPIVER + "," + AppID;
    }
}
