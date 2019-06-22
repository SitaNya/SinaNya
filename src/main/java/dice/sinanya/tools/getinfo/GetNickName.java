package dice.sinanya.tools.getinfo;

import com.forte.qqrobot.beans.messages.RootBean;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.getinfo.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByFromQQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取昵称
 */
public class GetNickName implements RootBean {

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
        return entityTypeMessages.getMsgSender().getPersonInfoByCode(entityTypeMessages.getFromQq()).getOtherParam("result").toString();
    }

    /**
     * 返回群或讨论组名
     *
     * @param entityTypeMessages 消息包装类
     * @return 昵称
     */
    public static String getGroupName(EntityTypeMessages entityTypeMessages) {
        switch (entityTypeMessages.getMsgGetTypes()) {
            case groupMsg:
                return entityTypeMessages.getMsgGroup().getOtherParam("result.name").toString();
            case discussMsg:
//                return entityTypeMessages.getMsgDisGroup().getNick();
            default:
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg("450609203", entityTypeMessages.toString());
                return entityTypeMessages.toString();
        }
    }
}
