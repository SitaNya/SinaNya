package dice.sinanya.dice.roll;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotFoundSkillException;

import static dice.sinanya.system.MessagesTag.TAG_EN;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.getinfo.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.RandomInt.random;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 幕间成长
 */
public class SkillUp {
    private EntityTypeMessages entityTypeMessages;

    public SkillUp(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 若未设定技能，则无法进行en，而单纯的.en 60从自然逻辑上讲是无意义的
     *
     * @throws NotFoundSkillException 可能因未找到技能而无法成长
     */
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
                    .append(MESSAGES_SYSTEM.get("enSuccess"));
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
                    .append(MESSAGES_SYSTEM.get("enFailed"));
            sender(entityTypeMessages, stringBuilder.toString());
        }
    }
}
