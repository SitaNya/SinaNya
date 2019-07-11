package dice.sinanya.tools.makedata;

import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.getinfo.LogTag.checkOthorLogTrue;
import static dice.sinanya.tools.getinfo.LogTag.getOtherLogTrue;
import static dice.sinanya.tools.getinfo.LogText.setLogText;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 很重要的消息发送包装类
 * <p>
 * 用这个静态方法可以直接发出消息而不用在乎消息来源
 */
public class Sender {

    private Sender() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 用这个静态方法可以直接发出消息而不用在乎消息来源
     *
     * @param entityTypeMessages 消息包装类
     * @param messages           要发出的消息
     */
    public static void sender(EntityTypeMessages entityTypeMessages, String messages) {
        if (checkOthorLogTrue(entityTypeMessages.getFromGroup())) {
            setLogText(new EntityLogTag(entityTypeMessages.getFromGroup(), getOtherLogTrue(entityTypeMessages.getFromGroup())), messages);
        }
        switch (entityTypeMessages.getMsgGetTypes()) {
            case privateMsg:
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getMsgPrivate().getQQCode(), messages);
                break;
            case groupMsg:
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(entityTypeMessages.getMsgGroup().getGroupCode(), messages);
                break;
            case discussMsg:
                entityTypeMessages.getMsgSender().SENDER.sendDiscussMsg(entityTypeMessages.getMsgDisGroup().getGroupCode(), messages);
                break;
            default:
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg("450609203", entityTypeMessages + "\nmessages: " + messages);
        }
    }
}
