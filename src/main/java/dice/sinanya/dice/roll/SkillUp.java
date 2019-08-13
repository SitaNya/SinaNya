package dice.sinanya.dice.roll;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotFoundSkillException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesTag.TAG_EN;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.getinfo.RoleChoose.checkRoleChooseExistByFromQQ;
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

    private static Pattern numbers = Pattern.compile("(\\d+)");

    /**
     * 若未设定技能，则无法进行en，而单纯的.en 60从自然逻辑上讲是无意义的
     *
     * @throws NotFoundSkillException 可能因未找到技能而无法成长
     */
    public void en() throws NotFoundSkillException {
        String tag = TAG_EN;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        String roleName;
        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            roleName = getRoleChooseByFromQQ(entityTypeMessages);
        } else {
            roleName = getNickName(entityTypeMessages);
        }
        int skill;
        Matcher skillNumber = numbers.matcher(msg);
        boolean useRoleCard;
        if (skillNumber.find()) {
            skill = Integer.parseInt(skillNumber.group(1));
            useRoleCard = false;
        } else {
            skill = getSkillValue(entityTypeMessages, msg);
            useRoleCard = true;
        }

        if (skill == 0) {
            throw new NotFoundSkillException(entityTypeMessages);
        }

        int random = random(1, 100);

        int strFumbleLevel = 95;
        StringBuilder stringBuilder = new StringBuilder();
        if (random > skill || random > strFumbleLevel) {
            int skillUp = random(1, 10);
            stringBuilder
                    .append("[")
                    .append(roleName)
                    .append("]")
                    .append("的技能成长检定:\n")
                    .append(random)
                    .append("/")
                    .append(skill)
                    .append("成功!\n")
                    .append("您的技能增长了1D10=")
                    .append(skillUp)
                    .append("点，目前为:")
                    .append(skill + skillUp);
            if (useRoleCard) {
                new InsertRoles().insertRoleInfo(msg + (skill + skillUp), roleName, entityTypeMessages.getFromQq());
                stringBuilder.append("\n本次结果会自动更新到人物卡中");
            }else{
                stringBuilder.append("\n本次结果不会自动更新到人物卡中，请使用.st<角色名>-<属性><新属性值>的格式进行更新录入，没有更改的属性不需要再次录入\n.en <技能>这种格式可以自动录入人物卡，无需再次st");
            }
            stringBuilder.append(entitySystemProperties.getEnSuccess());
            sender(entityTypeMessages, stringBuilder.toString());
        } else {
            stringBuilder
                    .append("[")
                    .append(roleName)
                    .append("]")
                    .append("的技能成长检定:\n")
                    .append(random)
                    .append("/")
                    .append(skill)
                    .append("失败!")
                    .append(entitySystemProperties.getEnFailed());
            sender(entityTypeMessages, stringBuilder.toString());
        }
    }
}
