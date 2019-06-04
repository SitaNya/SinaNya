package dice.sinanya.tools;

import dice.sinanya.entity.EntityTypeMessages;

/**
 * @author zhangxiaozhou
 */
public class Sender {
    public static void sender(EntityTypeMessages entityTypeMessages, String messages) {
        switch (entityTypeMessages.getMsgGetTypes()) {
            case privateMsg:
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getMsgPrivate().getFromQQ(), messages);
                break;
            case groupMsg:
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(entityTypeMessages.getMsgGroup().getFromGroup(), messages);
                break;
            case discussMsg:
                entityTypeMessages.getMsgSender().SENDER.sendDiscussMsg(entityTypeMessages.getMsgDisGroup().getFromDiscuss(), messages);
                break;
            default:
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg("450609203", entityTypeMessages + "\nmessages: " + messages);
        }
    }
}
