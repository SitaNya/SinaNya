package dice.sinanya.tools.log;

import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.getinfo.LogTag.checkOthorLogTrue;
import static dice.sinanya.tools.getinfo.LogTag.getOtherLogTrue;
import static dice.sinanya.tools.getinfo.LogText.setLogText;

/**
 * @author SitaNya
 */
public class Sender {
    public static void sender(EntityTypeMessages entityTypeMessages, String messages) {
        if (checkOthorLogTrue(entityTypeMessages.getFromGroup())) {
            setLogText(new EntityLogTag(entityTypeMessages.getFromGroup(), getOtherLogTrue(entityTypeMessages.getFromGroup())), messages);
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
