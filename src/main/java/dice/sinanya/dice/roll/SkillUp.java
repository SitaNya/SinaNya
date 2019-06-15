package dice.sinanya.dice.roll;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotFoundSkillException;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.system.MessagesTag.TAG_EN;
import static dice.sinanya.tools.getinfo.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.getinfo.RandomInt.random;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.log.Sender.sender;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;

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

    public void en() throws NotFoundSkillException {
        String tag = TAG_EN;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        int skill = getSkillValue(entityTypeMessages, msg);
        if (skill == 0) {
            throw new NotFoundSkillException(entityTypeMessages);
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
                    .append(skill + skillUp)
                    .append(messagesSystem.get("enSuccess"));
            new InsertRoles().insertRoleInfo(msg + (skill + skillUp), getRoleChooseByFromQQ(entityTypeMessages), entityTypeMessages.getFromQq());
            sender(entityTypeMessages, stringBuilder.toString());
        } else {
            stringBuilder
                    .append(getRoleChooseByFromQQ(entityTypeMessages))
                    .append("的技能成长检定:\n")
                    .append(random)
                    .append("/")
                    .append(skill)
                    .append("失败!")
                    .append(messagesSystem.get("enFailed"));
            sender(entityTypeMessages, stringBuilder.toString());
        }
    }
}
