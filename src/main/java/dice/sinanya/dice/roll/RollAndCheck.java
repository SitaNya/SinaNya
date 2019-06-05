package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.CheckResultLevel;

import static dice.sinanya.system.GetRole.getRole;
import static dice.sinanya.system.GetRole.getRoleInfo;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class RollAndCheck {

    private EntityTypeMessages entityTypeMessages;

    public RollAndCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void ra() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".ra");
        EntityRollAndCheck entityRollAndCheck = makeResult(msg);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), false).getResultLevel();
        sender(entityTypeMessages, stringBuilder);
    }

    public void rc() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".rc");

        EntityRollAndCheck entityRollAndCheck = makeResult(msg);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), true).getResultLevel();
        sender(entityTypeMessages, stringBuilder);
    }

    private EntityRollAndCheck makeResult(String msg) {
        int random = random(1, 100);

        StringBuilder skillName = new StringBuilder();
        int i = 0;
        while (i < msg.length() && !Character.isSpaceChar(msg.charAt(i)) &&
                !Character.isDigit(msg.charAt(i)) &&
                msg.charAt(i) != ':' &&
                msg.charAt(i) != '=') {
            skillName.append(msg.charAt(i));
            i++;
        }

        String nick = "";
        int skill = 0;

        if (getRole(entityTypeMessages)) {
            nick = getRoleInfo(entityTypeMessages);
        } else {
            nick = getNickName(entityTypeMessages);
        }

        if (!skillName.toString().equals("")) {
            skill = getSkillValue(entityTypeMessages, skillName.toString());
        }
        if (skill == 0) {
            if (isNumeric(msg.substring(i).trim())) {
                skill = Integer.parseInt(msg.substring(i).trim());
            }
        }
        return new EntityRollAndCheck(nick, random, skill);
    }


}
