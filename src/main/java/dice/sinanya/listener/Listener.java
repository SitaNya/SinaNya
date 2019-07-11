package dice.sinanya.listener;

import com.forte.qqrobot.anno.Constr;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.beans.messages.msgget.DiscussMsg;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.msgget.MsgGet;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.beans.types.KeywordMatchType;
import com.forte.qqrobot.component.forhttpapi.beans.response.Resp_getGroupMemberInfo;
import com.forte.qqrobot.sender.MsgSender;
import dice.sinanya.dice.system.Bot;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.flow.Flow;

import static com.forte.qqrobot.beans.messages.types.MsgGetTypes.discussMsg;
import static dice.sinanya.system.MessagesLoginInfo.ENTITY_LOGINQQ_INFO;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.getinfo.LogTag.checkOthorLogTrue;
import static dice.sinanya.tools.getinfo.LogTag.getOtherLogTrue;
import static dice.sinanya.tools.getinfo.LogText.setLogText;
import static dice.sinanya.tools.getinfo.SwitchBot.getBot;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 总监听入口类，这里是实际上接收到消息的第一个类
 */
public class Listener {
    private String tagBotOn = ".*[.。][ ]*bot[ ]*on.*";
    private String atTag = "[cq:at,qq=?]";
    private String tagMe = String.format(atTag, ENTITY_LOGINQQ_INFO.getLoginQQ());

    private Listener() {
    }

    @Constr
    public static Listener getInstance() {
        return new Listener();
    }

    /**
     * 私聊入口类
     * 机器人的开启关闭也是在这一层识别的
     *
     * @param msgGet      消息实体
     * @param msgGetTypes 消息来源类型
     * @param msgSender   发送器
     * @param msgPrivate  私聊消息对象
     */
    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = "^[.。][ ]*.*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public void listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, PrivateMsg msgPrivate) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgPrivate);
        if (entityTypeMessages.getMsgGet().getMsg().contains("bot")) {
            new Bot(entityTypeMessages).info();
        } else {
            new Flow(entityTypeMessages).toPrivate();
        }
    }

    /**
     * 群消息入口类
     * 机器人的开启关闭也是在这一层识别的
     *
     * @param msgGet      消息实体
     * @param msgGetTypes 消息来源类型
     * @param msgSender   发送器
     * @param msgGroup    群消息对象
     */
    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "^[.。][ ]*.*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public void listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgGroup);
        changeBotSwitch(entityTypeMessages, msgGroup.getMsg());
        if (getBot(Long.parseLong(msgGroup.getGroupCode()))) {
            new Flow(entityTypeMessages).toGroup();
            setLogs(entityTypeMessages, msgGet);
        }
    }

    /**
     * 讨论组入口类
     * 机器人的开启关闭也是在这一层识别的
     *
     * @param msgGet      消息实体
     * @param msgGetTypes 消息来源类型
     * @param msgSender   发送器
     * @param msgDisGroup 讨论组消息对象
     */
    @Listen(discussMsg)
    @Filter(value = "^[.。][ ]*.*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public void listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, DiscussMsg msgDisGroup) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgDisGroup);
        changeBotSwitch(entityTypeMessages, msgDisGroup.getMsg());
        if (getBot(Long.parseLong(msgDisGroup.getGroupCode()))) {
            new Flow(entityTypeMessages).toDisGroup();
            setLogs(entityTypeMessages, msgGet);
        }
    }

    /**
     * 群无过滤入口类，用于日志消息的捕捉
     * 机器人的开启关闭也是在这一层识别的
     *
     * @param msgGet      消息实体
     * @param msgGetTypes 消息来源类型
     * @param msgSender   发送器
     * @param msgGroup    群消息对象
     */
    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "^[^.。].*")
    public void listenerToLog(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgGroup);
        if (msgGroup.getMsg().charAt(0) != '.') {
            changeBotSwitch(entityTypeMessages, msgGroup.getMsg());
        }
        if (getBot(Long.parseLong(msgGroup.getGroupCode())) ||
                msgGroup.getMsg().trim().equals(tagBotOn) ||
                (msgGroup.getMsg().trim().contains(tagBotOn) && msgGroup.getMsg().trim().contains(tagMe))) {
            setLogs(entityTypeMessages, msgGet);
        }
    }

    /**
     * 讨论组无过滤入口类，用于日志消息的捕捉
     * 机器人的开启关闭也是在这一层识别的
     *
     * @param msgGet      消息实体
     * @param msgGetTypes 消息来源类型
     * @param msgSender   发送器
     * @param msgDisGroup 讨论组消息对象
     */
    @Listen(discussMsg)
    @Filter(value = "^[^.。].*")
    public void listenerToLog(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, DiscussMsg msgDisGroup) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgDisGroup);
        if (msgDisGroup.getMsg().charAt(0) != '.') {
            changeBotSwitch(entityTypeMessages, msgDisGroup.getMsg());
        }
        if (getBot(Long.parseLong(msgDisGroup.getGroupCode())) ||
                msgDisGroup.getMsg().trim().equals(tagBotOn) ||
                (msgDisGroup.getMsg().trim().contains(tagBotOn) && msgDisGroup.getMsg().trim().contains(tagMe))) {
            setLogs(entityTypeMessages, msgGet);
        }
    }

    /**
     * 判断群中是否有其它日志打开，因为是check所以没有的话则返回true，并将当前群的锁置为true
     * 内容为将某一句消息插入log数据库
     *
     * @param entityTypeMessages 消息封装类
     * @param msgGet             消息对象
     */
    private void setLogs(EntityTypeMessages entityTypeMessages, MsgGet msgGet) {
        if (checkOthorLogTrue(entityTypeMessages.getFromGroup())) {
            setLogText(entityTypeMessages, new EntityLogTag(entityTypeMessages.getFromGroup(), getOtherLogTrue(entityTypeMessages.getFromGroup())), msgGet.getMsg());
        }
    }

    /**
     * 根据消息信息和是否包含at自己更改机器人开关
     *
     * @param entityTypeMessages 消息封装类
     * @param messages           消息字符串
     */
    private void changeBotSwitch(EntityTypeMessages entityTypeMessages, String messages) {
        messages = messages.toLowerCase();
        String tagBotOff = ".*[.。][ ]*bot[ ]*off.*";
        String tagBotInfo = ".*[.。][ ]*bot.*";
        String tagBotExit = ".*[.。][ ]*bot[ ]*exit.*";
        String onlyManagerError = "onlyManager";

        boolean botOn = messagesContainsAtMe(messages, tagBotOn, tagMe) || messagesBotForAll(messages, tagBotOn) || messagesContainsQqId(messages, tagBotOn);
        boolean botOff = messagesContainsAtMe(messages, tagBotOff, tagMe) || messagesBotForAll(messages, tagBotOff) || messagesContainsQqId(messages, tagBotOff);
        boolean botExit = messagesContainsAtMe(messages, tagBotExit, tagMe) || messagesBotForAll(messages, tagBotExit) || messagesContainsQqId(messages, tagBotExit);
        boolean botInfo = (messagesContainsAtMe(messages, tagBotInfo, tagMe) || messagesBotForAll(messages, tagBotInfo) || messagesContainsQqId(messages, tagBotInfo)) && !botOn && !botOff && !botExit;

        if (!messages.contains("bot")) {
            return;
        }
        Resp_getGroupMemberInfo.GroupMemberInfo isAdmin = entityTypeMessages.getMsgSender().GETTER.getGroupMemberInfo(entityTypeMessages.getFromGroup(), entityTypeMessages.getFromQq()).getOtherParam("result", Resp_getGroupMemberInfo.GroupMemberInfo.class);

        boolean notIsAdmin = isAdmin.getPower() != 1;
        boolean notIsAdminInDiscuss = notIsAdmin || entityTypeMessages.getMsgGetTypes() == discussMsg;
        if (botOn) {
            new Bot(entityTypeMessages).on();
        } else if (botOff && notIsAdmin) {
            new Bot(entityTypeMessages).off();
        } else if (botExit && notIsAdminInDiscuss) {
            new Bot(entityTypeMessages).exit();
        } else if (botInfo) {
            new Bot(entityTypeMessages).info();
        } else {
            sender(entityTypeMessages, MESSAGES_SYSTEM.get(onlyManagerError));
        }
    }

    private boolean messagesContainsAtMe(String messages, String tagBotSwitch, String tagMe) {
        return messages.trim().matches(tagBotSwitch) && messages.trim().contains(tagMe);
    }

    private boolean messagesBotForAll(String messages, String tagBotSwitch) {
        return messages.trim().matches(tagBotSwitch) && !messages.trim().contains("[cq:at") && !messages.matches(".*[0-9]+.*");
    }

    private boolean messagesContainsQqId(String messages, String tagBotSwitch) {
        String qqId = String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ());
        return messages.trim().matches(tagBotSwitch) && (messages.trim().contains(qqId) || messages.trim().contains(qqId.substring(qqId.length() - 5)));
    }
}
