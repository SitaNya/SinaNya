package dice.sinanya.entity;

import com.forte.qqrobot.beans.messages.msgget.MsgGet;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgDisGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgPrivate;
import com.forte.qqrobot.sender.MsgSender;

import static dice.sinanya.tools.checkdata.CheckValue.checkValue;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: API信息包装对象，非常重要。各种程序里都会带着它传来传去
 */
public class EntityTypeMessages {
    /**
     * @param msgGetTypes 获取类型对象，可以从里面拿到这条消息是从私聊、群、讨论组还是什么渠道来的
     * @param msgSender 发送器对象，通过它可以把消息发送出去
     * @param msgGet 消息get对象，从这里面可以实际拿到消息
     * @param msgGroup 群组对象，可以拿到比如群号之类的信息
     * @param msgDisGroup 讨论组对象，可以拿到比如讨论组号之类的信息
     * @param msgPrivate 私聊对象，可以拿到私聊昵称之类的消息
     */
    private MsgGetTypes msgGetTypes;
    private MsgSender msgSender;
    private MsgGet msgGet;
    private MsgGroup msgGroup;
    private MsgDisGroup msgDisGroup;
    private MsgPrivate msgPrivate;


    public EntityTypeMessages(MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGet msgGet, MsgPrivate msgPrivate) {
        this.msgGetTypes = msgGetTypes;
        this.msgSender = msgSender;
        this.msgGet = msgGet;
        this.msgPrivate = msgPrivate;
    }

    public EntityTypeMessages(MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGet msgGet, MsgGroup msgGroup) {
        this.msgGetTypes = msgGetTypes;
        this.msgSender = msgSender;
        this.msgGet = msgGet;
        this.msgGroup = msgGroup;
    }

    public EntityTypeMessages(MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGet msgGet, MsgDisGroup msgDisGroup) {
        this.msgGetTypes = msgGetTypes;
        this.msgSender = msgSender;
        this.msgGet = msgGet;
        this.msgDisGroup = msgDisGroup;
    }

    /**
     * @return 获取对方QQ号，需要根据消息来源，分别从三种不同对象中取得，这里我默认写了自己的QQ号，遇到问题自己可以知道
     */
    public String getFromQq() {
        switch (msgGetTypes) {
            case groupMsg:
                return msgGroup.getFromQQ();
            case discussMsg:
                return msgDisGroup.getFromQQ();
            case privateMsg:
                return msgPrivate.getFromQQ();
            default:
                return "450609203";
        }
    }

    /**
     * @return 获取来源群号，需要根据消息来源，分别从2中不同渠道取得，这里我默认写了0
     */
    public String getFromGroup() {
        switch (msgGetTypes) {
            case groupMsg:
                return msgGroup.getFromGroup();
            case discussMsg:
                return msgDisGroup.getFromDiscuss();
            default:
                return "0";
        }
    }

    public MsgGetTypes getMsgGetTypes() {
        return msgGetTypes;
    }

    public MsgSender getMsgSender() {
        return msgSender;
    }

    public MsgGet getMsgGet() {
        return msgGet;
    }

    public MsgGroup getMsgGroup() {
        return msgGroup;
    }

    public MsgDisGroup getMsgDisGroup() {
        return msgDisGroup;
    }

    public MsgPrivate getMsgPrivate() {
        return msgPrivate;
    }

    @Override
    public String toString() {
        String result = "";
        result += "msgGetTypes: " + checkValue(msgGetTypes) + "\n";
        result += "msgSender: " + checkValue(msgSender) + "\n";
        result += "msgGet: " + checkValue(msgGet) + "\n";
        result += "msgGroup: " + checkValue(msgGroup) + "\n";
        result += "msgDisGroup: " + checkValue(msgDisGroup) + "\n";
        result += "msgPrivate: " + checkValue(msgPrivate) + "\n";
        return result;
    }
}
