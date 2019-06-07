package dice.sinanya.tools;

import dice.sinanya.entity.EntitySanCheck;
import dice.sinanya.entity.EntityStrManyRolls;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;

import java.util.HashMap;
import java.util.regex.Pattern;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeSkill.makeSkill;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.RoleInfo.getRoleInfoFromChooseByFromQQ;
import static dice.sinanya.tools.RoleInfo.setRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.floor;
import static java.lang.Math.max;

public class CheckSanCheck {
    EntityTypeMessages entityTypeMessages;
    String tagNone = "";
    long qq;
    private static Pattern pContainD = Pattern.compile("(\\d+[dD]\\d+)");

    public CheckSanCheck(EntityTypeMessages entityTypeMessages) {
        qq = Long.parseLong(entityTypeMessages.getFromQQ());
        this.entityTypeMessages = entityTypeMessages;
    }

    public CheckSanCheck(EntityTypeMessages entityTypeMessages, String qqId) {
        qq = Long.parseLong(qqId);
        this.entityTypeMessages = entityTypeMessages;
    }

    public CheckSanCheck(EntityTypeMessages entityTypeMessages, long qq) {
        this.qq = qq;
        this.entityTypeMessages = entityTypeMessages;
    }

    public void addSanCheck(String skill, String strAddValue) throws PlayerSetException {

        if (skill.equals("")) {
            skill = "理智";
        }

        StringBuilder strResult = new StringBuilder();

        String role = "";
        int san;

        int changeValue = 0;
        String changeFuncion = "";
        if (isNumeric(strAddValue)) {
            changeValue = Integer.parseInt(strAddValue);
            changeFuncion = strAddValue;
        } else {
            MakeManyRollsStr makeManyRollsStr = new MakeManyRollsStr(strAddValue);
            changeValue = makeManyRollsStr.getRes();
            changeFuncion = makeManyRollsStr.getStr();
        }

        EntityStrManyRolls entityStrManyRolls = makeSkill(skill, qq);


        HashMap<String, Integer> prop = getRoleInfoFromChooseByFromQQ(entityTypeMessages);
        if (prop != null) {
            role = getRoleChooseByFromQQ(entityTypeMessages);
            if (entityStrManyRolls.getResult() != 0) {
                san = entityStrManyRolls.getResult();
            } else {
                san = prop.get("san");
            }
            prop.put("san", san + changeValue);
        } else {
            if (entityStrManyRolls.getResult() != 0) {
                san = entityStrManyRolls.getResult();
            } else {
                throw new PlayerSetException(entityTypeMessages);
            }
        }
        setRoleInfoFromChooseByQQ(qq, prop);
        sender(entityTypeMessages, "已为" + role + "恢复" + changeFuncion + "=" + changeValue + "点理智值，剩余" + (san + changeValue) + "点");
    }

    public EntitySanCheck checkSanCheck(String skill, String strCheckValue) throws SanCheckSetException, PlayerSetException {

        if (skill.equals("")) {
            skill = "理智";
        }
        int level;

        StringBuilder strResult = new StringBuilder();

        String role = "";
        int san;

        if (!strCheckValue.contains("/") || strCheckValue.split("/")[0].equals(tagNone) || strCheckValue.split("/")[1].equals(tagNone)) {
            throw new SanCheckSetException(entityTypeMessages);
        }

        String strSuccess = strCheckValue.split("/")[0];
        String strFail = strCheckValue.split("/")[1];

        MakeManyRollsStr mSuccess = new MakeManyRollsStr(strSuccess);
        MakeManyRollsStr mFail = new MakeManyRollsStr(strFail);

        EntityStrManyRolls entityStrManyRolls = makeSkill(skill, qq);


        boolean useCard = false;
        HashMap<String, Integer> prop = getRoleInfoFromChooseByFromQQ(entityTypeMessages);
        if (prop != null) {
            role = getRoleChooseByFromQQ(entityTypeMessages);
            if (!skill.equals("理智")) {
                san = entityStrManyRolls.getResult();
            } else {
                san = prop.get("san");
                useCard = true;
            }
        } else {
            role = getNickName(entityTypeMessages);
            if (entityStrManyRolls.getResult() != 0) {
                san = entityStrManyRolls.getResult();
            } else {
                throw new PlayerSetException(entityTypeMessages);
            }
        }

        strResult.append(role)
                .append("的理智检定结果:")
                .append("\n");

        int random = random(1, 100);
        if (random == 100) {
            int maxSan = 0;
            if (strFail.contains("D") || strFail.contains("d")) {
                if (isNumeric(strFail.split("[dD]")[1])) {
                    maxSan = Integer.parseInt(strFail.split("[dD]")[1]);
                } else {
                    sender(entityTypeMessages, "sc格式错误");
                }
            } else {
                if (isNumeric(strFail)) {
                    maxSan = Integer.parseInt(strFail);
                } else {
                    sender(entityTypeMessages, "sc格式错误");
                }
            }
            int newSan = max(0, san - maxSan);

            strResult.append(random)
                    .append("/")
                    .append(san)
                    .append("=大失败")
                    .append("\n")
                    .append("你的理智值减少")
                    .append(strFail)
                    .append("=")
                    .append(maxSan)
                    .append("点")
                    .append(",当前剩余")
                    .append(newSan)
                    .append("点");
            if (useCard) {
                prop.put("san", newSan);
                setRoleInfoFromChooseByQQ(qq, prop);
            }
            if (newSan == 0) {
                strResult.append("\n已永久疯狂");
            } else if (maxSan >= 5) {
                strResult.append("\n已进入临时性疯狂");
            } else if (maxSan >= floor(san / 5)) {
                strResult.append("\n已因单次损失值进入不定性疯狂");
            }
            level = 3;
        } else if (random == 1) {
            int minSan = 0;
            if (strSuccess.contains("D") || strSuccess.contains("d")) {
                if (isNumeric(strSuccess.split("[dD]")[0])) {
                    minSan = Integer.parseInt(strSuccess.split("[dD]")[0]);
                } else {
                    sender(entityTypeMessages, "sc格式错误");
                }
            } else {
                if (isNumeric(strSuccess)) {
                    minSan = Integer.parseInt(strSuccess);
                } else {
                    sender(entityTypeMessages, "sc格式错误");
                }
            }
            int newSan = max(0, san - minSan);

            strResult.append(random)
                    .append("/")
                    .append(san)
                    .append("=大成功")
                    .append("\n")
                    .append("你的理智值减少")
                    .append(strSuccess)
                    .append("=")
                    .append(minSan)
                    .append("点")
                    .append(",当前剩余")
                    .append(newSan)
                    .append("点");

            if (useCard) {
                prop.put("san", newSan);
                setRoleInfoFromChooseByQQ(qq, prop);
            }
            if (newSan == 0) {
                strResult.append("\n已永久疯狂");
            } else if (minSan >= 5) {
                strResult.append("\n已进入临时性疯狂");
            } else if (minSan >= floor(san / 5)) {
                strResult.append("\n已因单次损失值进入不定性疯狂");
            }
            level = 0;
        } else if (random <= san) {
            int newSan = max(0, san - mSuccess.getRes());
            strResult
                    .append(random)
                    .append("/")
                    .append(san)
                    .append("=成功")
                    .append("\n")
                    .append("你的理智值减少")
                    .append(strSuccess)
                    .append("=")
                    .append(mSuccess.getRes())
                    .append("点")
                    .append(",当前剩余")
                    .append(newSan)
                    .append("点");
            if (useCard) {
                prop.put("san", newSan);
                setRoleInfoFromChooseByQQ(qq, prop);
            }
            if (newSan == 0) {
                strResult.append("\n已永久疯狂");
            } else if (mSuccess.getRes() >= 5) {
                strResult.append("\n已进入临时性疯狂");
            } else if (mSuccess.getRes() >= floor(san / 5)) {
                strResult.append("\n已因单次损失值进入不定性疯狂");
            }
            level = 1;
        } else {
            int tmp = mFail.getRes();
            int newSan = max(0, san - mFail.getRes());
            strResult
                    .append(random)
                    .append("/")
                    .append(san)
                    .append("=失败")
                    .append("\n")
                    .append("你的理智值减少")
                    .append(strFail)
                    .append("=")
                    .append(mFail.getRes())
                    .append("点")
                    .append(",当前剩余")
                    .append(newSan)
                    .append("点");
            if (useCard) {
                prop.put("san", newSan);
                setRoleInfoFromChooseByQQ(qq, prop);
            }
            if (newSan == 0) {
                strResult.append("\n已永久疯狂");
            } else if (mFail.getRes() >= 5) {
                strResult.append("\n已进入临时性疯狂");
            } else if (mFail.getRes() >= floor(san / 5)) {
                strResult.append("\n已因单次损失值进入不定性疯狂");
            }
            level = 2;
        }
        return new EntitySanCheck(level, strResult.toString());
    }
}
