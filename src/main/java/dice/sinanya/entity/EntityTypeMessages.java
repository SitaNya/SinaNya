package dice.sinanya.entity;

import dice.sinanya.entity.imal.MessagesTypes;

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
    private MessagesTypes messagesTypes;
    private long fromQq;
    private long fromGroup;
    private String msg;

    public EntityTypeMessages(MessagesTypes messagesTypes, long fromQq, String msg) {
        this.messagesTypes = messagesTypes;
        this.fromQq = fromQq;
        this.msg = msg;
    }

    public EntityTypeMessages(MessagesTypes messagesTypes, long fromQq, long fromGroup, String msg) {
        this.messagesTypes = messagesTypes;
        this.fromQq = fromQq;
        this.fromGroup = fromGroup;
        this.msg = msg;
    }

    public MessagesTypes getMessagesTypes() {
        return messagesTypes;
    }

    public void setMessagesTypes(MessagesTypes messagesTypes) {
        this.messagesTypes = messagesTypes;
    }

    public String getFromQq() {
        return String.valueOf(fromQq);
    }

    public void setFromQq(long fromQq) {
        this.fromQq = fromQq;
    }

    public String getFromGroup() {
        return String.valueOf(fromGroup);
    }

    public void setFromGroup(long fromGroup) {
        this.fromGroup = fromGroup;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
