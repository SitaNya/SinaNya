package dice.sinanya.tools;

import dice.sinanya.entity.EntityTypeMessages;

/**
 * @author zhangxiaozhou
 */
public class GetNickName {

    public static String getNickName(EntityTypeMessages entityTypeMessages) {
        switch (entityTypeMessages.getMsgGetTypes()) {
            case privateMsg:
                return entityTypeMessages.getMsgPrivate().getNick();
            case groupMsg:
                return entityTypeMessages.getMsgGroup().getNick();
            case discussMsg:
                return entityTypeMessages.getMsgDisGroup().getNick();
            default:
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg("450609203", entityTypeMessages.toString());
                return entityTypeMessages.toString();
        }
    }
}
