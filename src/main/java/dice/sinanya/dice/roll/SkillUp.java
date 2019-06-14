package dice.sinanya.dice.roll;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesTag.TAG_EN;
import static dice.sinanya.tools.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.Sender.sender;

/**
 * .en的相关类
 *
 * @author SitaNya
 */
public class SkillUp {
    private EntityTypeMessages entityTypeMessages;

    public SkillUp(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void en() {
        String tag = TAG_EN;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        int skill = getSkillValue(entityTypeMessages, msg);
        if (skill == 0) {
            sender(entityTypeMessages, "没有找到您的技能哦");
            return;
        }

        int random = random(1, 100);

        int strFumbleLevel = 100;
        StringBuilder stringBuilder = new StringBuilder();
        if (random > skill || random == strFumbleLevel) {
            int skillUp = random(1, 10);
            stringBuilder
                    .append(getRoleChooseByFromQQ(entityTypeMessages))
                    .append("的技能成长检定:\n")
                    .append(random)
                    .append("/")
                    .append(skill)
                    .append("成功!\n")
                    .append("您的技能增长了")
                    .append(skillUp)
                    .append("点，目前为:")
                    .append(skill + skillUp);
            new InsertRoles().insertRoleInfo(msg + (skill + skillUp), getRoleChooseByFromQQ(entityTypeMessages), entityTypeMessages.getFromQq());
            sender(entityTypeMessages, stringBuilder.toString());
        } else {
            stringBuilder
                    .append(getRoleChooseByFromQQ(entityTypeMessages))
                    .append("的技能成长检定:\n")
                    .append(random)
                    .append("/")
                    .append(skill)
                    .append("失败!");
            sender(entityTypeMessages, stringBuilder.toString());
        }
    }
}
