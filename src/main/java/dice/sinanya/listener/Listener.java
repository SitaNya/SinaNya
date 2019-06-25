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
import com.forte.qqrobot.sender.MsgSender;
import dice.sinanya.dice.system.Bot;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.flow.Flow;

import static dice.sinanya.system.MessagesSystem.ENTITY_LOGINQQ_INFO;
import static dice.sinanya.tools.getinfo.LogTag.checkOthorLogTrue;
import static dice.sinanya.tools.getinfo.LogTag.getOtherLogTrue;
import static dice.sinanya.tools.getinfo.LogText.setLogText;
import static dice.sinanya.tools.getinfo.SwitchBot.getBot;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 总监听入口类，这里是实际上接收到消息的第一个类
 */
public class Listener {
    private String tagBotOn = ".bot on";

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
     * @return 返回值固定为true
     */
    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = "^[.。][ ]*.*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, PrivateMsg msgPrivate) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgPrivate);
        if (entityTypeMessages.getMsgGet().getMsg().contains("bot")) {
            new Bot(entityTypeMessages).info();
        } else {
            new Flow(entityTypeMessages).toPrivate();
        }
        return true;
    }

    /**
     * 群消息入口类
     * 机器人的开启关闭也是在这一层识别的
     *
     * @param msgGet      消息实体
     * @param msgGetTypes 消息来源类型
     * @param msgSender   发送器
     * @param msgGroup    群消息对象
     * @return 返回值固定为true
     */
    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "^[.。][ ]*.*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgGroup);
        changeBotSwitch(entityTypeMessages, msgGroup.getMsg());
        if (getBot(Long.parseLong(msgGroup.getGroupCode()))) {
            new Flow(entityTypeMessages).toGroup();
            return true;
        } else {
            return true;
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
     * @return 返回值固定为true
     */
    @Listen(MsgGetTypes.discussMsg)
    @Filter(value = "^[.。][ ]*.*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, DiscussMsg msgDisGroup) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgDisGroup);
        changeBotSwitch(entityTypeMessages, msgDisGroup.getMsg());
        if (getBot(Long.parseLong(msgDisGroup.getGroupCode()))) {
            new Flow(entityTypeMessages).toDisGroup();
            return true;
        } else {
            return true;
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
     * @return 返回值固定为true
     */
    @Listen(MsgGetTypes.groupMsg)
    public boolean listenerToLog(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, GroupMsg msgGroup) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgGroup);
        String tagMe = "[CQ:at,qq=" + ENTITY_LOGINQQ_INFO.getLoginQQ() + "]";
        if (msgGroup.getMsg().charAt(0) != '.') {
            changeBotSwitch(entityTypeMessages, msgGroup.getMsg());
        }
        if (getBot(Long.parseLong(msgGroup.getGroupCode())) ||
                msgGroup.getMsg().trim().equals(tagBotOn) ||
                (msgGroup.getMsg().trim().contains(tagBotOn) && msgGroup.getMsg().trim().contains(tagMe))) {
            setLogs(entityTypeMessages, msgGet);
            return true;
        } else {
            return true;
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
     * @return 返回值固定为true
     */
    @Listen(MsgGetTypes.discussMsg)
    public boolean listenerToLog(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, DiscussMsg msgDisGroup) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgDisGroup);
        String tagMe = "[CQ:at,qq=" + ENTITY_LOGINQQ_INFO.getLoginQQ() + "]";
        if (msgDisGroup.getMsg().charAt(0) != '.') {
            changeBotSwitch(entityTypeMessages, msgDisGroup.getMsg());
        }
        if (getBot(Long.parseLong(msgDisGroup.getGroupCode())) ||
                msgDisGroup.getMsg().trim().equals(tagBotOn) ||
                (msgDisGroup.getMsg().trim().contains(tagBotOn) && msgDisGroup.getMsg().trim().contains(tagMe))) {
            setLogs(entityTypeMessages, msgGet);
            return true;
        } else {
            return true;
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
        String tagBotOff = "bot off";
        String tagBotInfo = "bot";
        String tagBotExit = "bot exit";
        String tagMe = "[CQ:at,qq=" + ENTITY_LOGINQQ_INFO.getLoginQQ() + "]";

        boolean botOn = messages.trim().contains(tagBotOn) && messages.trim().contains(tagMe) || (messages.trim().contains(tagBotOn) && !messages.trim().contains("[CQ:at"));
        boolean botOff = messages.trim().contains(tagBotOff) && messages.trim().contains(tagMe) || (messages.trim().contains(tagBotOff) && !messages.trim().contains("[CQ:at"));
        boolean botExit = messages.trim().contains(tagBotExit) && messages.trim().contains(tagMe) || (messages.trim().contains(tagBotExit) && !messages.trim().contains("[CQ:at"));
        boolean botInfo = (messages.trim().contains(tagBotInfo) && messages.trim().contains(tagMe) || (messages.trim().contains(tagBotInfo) && !messages.trim().contains("[CQ:at"))) && !botOn && !botOff && !botExit;

        if (botOn) {
            new Bot(entityTypeMessages).on();
        } else if (botOff) {
            new Bot(entityTypeMessages).off();
        } else if (botExit) {
            new Bot(entityTypeMessages).exit();
        } else if (botInfo) {
            new Bot(entityTypeMessages).info();
        }
    }
}
