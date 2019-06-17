package dice.sinanya.listener;

import com.forte.qqrobot.anno.Constr;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.beans.messages.msgget.MsgGet;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.beans.types.KeywordMatchType;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgDisGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgPrivate;
import com.forte.qqrobot.sender.MsgSender;
import dice.sinanya.dice.system.Bot;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;
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
    private String tagBotOff = ".bot off";
    private String tagMe = "[CQ:at,qq=" + messagesSystem.get("loginQQ") + "]";

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
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, MsgPrivate msgPrivate) {
        new Flow(new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgPrivate)).toPrivate();
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
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGroup msgGroup) {
        if (getBot(Long.parseLong(msgGroup.getFromGroup()))) {
            new Flow(new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgGroup)).toGroup();
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
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, MsgDisGroup msgDisGroup) {
        if (getBot(Long.parseLong(msgDisGroup.getFromDiscuss()))) {
            new Flow(new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgDisGroup)).toDisGroup();
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
    public boolean listenerToLog(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGroup msgGroup) {
        if (getBot(Long.parseLong(msgGroup.getFromGroup())) ||
                msgGroup.getMsg().trim().equals(tagBotOn) ||
                (msgGroup.getMsg().trim().contains(tagBotOn) && msgGroup.getMsg().trim().contains(tagMe))) {
            EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgGroup);
            setLogs(entityTypeMessages, msgGet);
            changeBotSwitch(entityTypeMessages, msgGroup.getMsg());
            return true;
        } else if (msgGroup.getMsg().trim().equals(tagBotOff) || (msgGroup.getMsg().trim().contains(tagBotOff) && msgGroup.getMsg().trim().contains(tagMe))) {
            msgSender.SENDER.sendGroupMsg(msgGroup.getFromGroup(), messagesSystem.get("botAlreadyStop"));
            EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgGroup);
            changeBotSwitch(entityTypeMessages, msgGroup.getMsg());
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
    public boolean listenerToLog(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, MsgDisGroup msgDisGroup) {
        if (getBot(Long.parseLong(msgDisGroup.getFromDiscuss())) ||
                msgDisGroup.getMsg().trim().equals(tagBotOn) ||
                (msgDisGroup.getMsg().trim().contains(tagBotOn) && msgDisGroup.getMsg().trim().contains(tagMe))) {
            EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgDisGroup);
            setLogs(entityTypeMessages, msgGet);
            changeBotSwitch(entityTypeMessages, msgDisGroup.getMsg());
            return true;
        } else if (msgDisGroup.getMsg().trim().equals(tagBotOff) || (msgDisGroup.getMsg().trim().contains(tagBotOff) && msgDisGroup.getMsg().trim().contains(tagMe))) {
            msgSender.SENDER.sendGroupMsg(msgDisGroup.getFromDiscuss(), messagesSystem.get("botAlreadyStop"));
            EntityTypeMessages entityTypeMessages = new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgDisGroup);
            changeBotSwitch(entityTypeMessages, msgDisGroup.getMsg());
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
        if ((messages.trim().contains(tagBotOn) && messages.trim().contains(tagMe)) || (messages.trim().contains(tagBotOn) && !messages.trim().contains("[CQ:at"))) {
            new Bot(entityTypeMessages).on();
        } else if (messages.trim().contains(tagBotOff) && messages.trim().contains(tagMe)) {
            new Bot(entityTypeMessages).off();
        }
    }
}
