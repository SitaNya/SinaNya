package dice.sinanya.tools.getinfo;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.getinfo.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByFromQQ;

/**
 * 获取昵称，如果已经设定了人物卡则默认给人物卡名字，没设定的话给QQ昵称
 *
 * @author SitaNya
 */
/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取昵称
 */
public class GetNickName {

    /**
     * 如果已经设定了人物卡则默认给人物卡名字，没设定的话给QQ昵称
     *
     * @param entityTypeMessages 消息包装类
     * @return 昵称
     */
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
