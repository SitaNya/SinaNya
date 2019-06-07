package dice.sinanya.tools;

import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.LogTag.checkOthorLogTrue;
import static dice.sinanya.tools.LogTag.getOthorLogTrue;
import static dice.sinanya.tools.LogText.setLogText;

/**
 * @author zhangxiaozhou
 */
public class Sender {
    public static void sender(EntityTypeMessages entityTypeMessages, String messages) {
        if (checkOthorLogTrue(entityTypeMessages.getFromGroup())) {
            setLogText(new EntityLogTag(entityTypeMessages.getFromGroup(), getOthorLogTrue(entityTypeMessages.getFromGroup())), messages);
        }
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
