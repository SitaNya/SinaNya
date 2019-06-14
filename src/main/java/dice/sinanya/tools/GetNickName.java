package dice.sinanya.tools;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;

/**
 * @author SitaNya
 */
public class GetNickName {

    public static String getNickName(EntityTypeMessages entityTypeMessages) {
        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            return getRoleChooseByFromQQ(entityTypeMessages);
        }

        switch (entityTypeMessages.getMsgGetTypes()) {
            case privateMsg:
                return entityTypeMessages.getMsgPrivate().getNick();
            case groupMsg:
                return entityTypeMessages.getMsgGroup().getUsername();
            case discussMsg:
                return entityTypeMessages.getMsgDisGroup().getNick();
            default:
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg("450609203", entityTypeMessages.toString());
                return entityTypeMessages.toString();
        }
    }
}
