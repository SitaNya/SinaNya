package dice.sinanya.tools.checkdata;

import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;
import dice.sinanya.tools.makedata.GetRollResultAndStr;

import java.util.HashMap;

import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.system.MessagesSystem.SPACE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.RandomInt.random;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByQQ;
import static dice.sinanya.tools.getinfo.RoleInfo.*;
import static dice.sinanya.tools.log.Sender.sender;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class CheckSanCheck {
    EntityTypeMessages entityTypeMessages;
    private long qq;

    public CheckSanCheck(EntityTypeMessages entityTypeMessages) {
        qq = Long.parseLong(entityTypeMessages.getFromQq());
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

    public void addSanCheck(String function) throws PlayerSetException {
        String role = NONE;
        int san = 0;
        int cthulhuMythos = 0;

        int changeValue;
        String changeFunction;
        String strAddValue;


        if (function.contains(SPACE) && !function.split(SPACE)[1].isEmpty()) {
            strAddValue = function.split(SPACE)[0];
            if (isNumeric(function.split(SPACE)[1])) {
                san = Integer.parseInt(function.split(SPACE)[1]);
            }
        } else {
            strAddValue = function;
        }


        if (isNumeric(strAddValue)) {
            changeValue = Integer.parseInt(strAddValue);
            changeFunction = strAddValue;
        } else {
            GetRollResultAndStr getRollResultAndStr = new GetRollResultAndStr(entityTypeMessages, strAddValue);
            changeValue = getRollResultAndStr.getResInt();
            changeFunction = getRollResultAndStr.getResStr();
        }

        HashMap<String, Integer> prop = getRoleInfoFromChooseByFromQQ(entityTypeMessages);
        if (prop != null) {
            role = getRoleChooseByFromQQ(entityTypeMessages);
            if (san == 0) {
                san = prop.get("san");
                cthulhuMythos = prop.get("cthulhuMythos");
            }
            prop.put("san", san + changeValue);
        } else {
            if (san == 0) {
                throw new PlayerSetException(entityTypeMessages);
            }
        }
        setRoleInfoFromChooseByQQ(qq, prop);
        sender(entityTypeMessages, "已为" + role + "恢复" + changeFunction + "=" + changeValue + "点理智值，剩余" + min((san + changeValue), 99 - cthulhuMythos) + "点");
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    public String checkSanCheck(String function) throws SanCheckSetException, PlayerSetException {
        int levelFumbleLine = 100;
        String sanFunctionSeq = "/";

        String strCheckValue;
        String role;
        int san = 0;

        if (function.contains(SPACE) && !function.split(SPACE)[1].isEmpty()) {
            strCheckValue = function.split(SPACE)[0];
            if (isNumeric(function.split(SPACE)[1])) {
                san = Integer.parseInt(function.split(SPACE)[1]);
            }
        } else {
            strCheckValue = function;
        }

        String tagNone = NONE;
        if (!strCheckValue.contains(sanFunctionSeq) || strCheckValue.split(sanFunctionSeq)[0].equals(tagNone) || strCheckValue.split(sanFunctionSeq)[1].equals(tagNone)) {
            throw new SanCheckSetException(entityTypeMessages);
        }

        String strSuccess = strCheckValue.split(sanFunctionSeq)[0];
        String strFail = strCheckValue.split(sanFunctionSeq)[1];

        GetRollResultAndStr mSuccess = new GetRollResultAndStr(entityTypeMessages, strSuccess);
        GetRollResultAndStr mFail = new GetRollResultAndStr(entityTypeMessages, strFail);

        boolean useCard = false;
        HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
        if (prop != null) {
            role = getRoleChooseByQQ(qq);
            if (san == 0) {
                san = prop.get("san");
                useCard = true;
            }
        } else {
            role = getNickName(entityTypeMessages);
            if (san == 0) {
                throw new PlayerSetException(entityTypeMessages);
            }
        }

        StringBuilder strResult = new StringBuilder();
        strResult.append(role)
                .append("的理智检定结果:")
                .append("\n");

        int random = random(1, 100);
        String regexFunctionSeq = "[dD]";
        if (random == levelFumbleLine) {
            int maxSan = 0;
            if (hasFunctionSeq(strFail)) {
                if (isNumeric(strFail.split(regexFunctionSeq)[1])) {
                    maxSan = Integer.parseInt(strFail.split(regexFunctionSeq)[1]);
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
                setCard(newSan, prop, role);
            }
            makeInsane(strResult, newSan, san);
        } else if (random == 1) {
            int minSan = 0;
            if (hasFunctionSeq(strSuccess)) {
                if (isNumeric(strSuccess.split(regexFunctionSeq)[0])) {
                    minSan = Integer.parseInt(strSuccess.split(regexFunctionSeq)[0]);
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
                setCard(newSan, prop, role);
            }
            makeInsane(strResult, newSan, san);
        } else if (random <= san) {
            int newSan = max(0, san - mSuccess.getResInt());
            strResult
                    .append(random)
                    .append("/")
                    .append(san)
                    .append("=成功")
                    .append("\n")
                    .append("你的理智值减少")
                    .append(strSuccess)
                    .append("=")
                    .append(mSuccess.getResInt())
                    .append("点")
                    .append(",当前剩余")
                    .append(newSan)
                    .append("点");
            if (useCard) {
                setCard(newSan, prop, role);
            }
            makeInsane(strResult, newSan, san);
        } else {
            int newSan = max(0, san - mFail.getResInt());
            strResult
                    .append(random)
                    .append("/")
                    .append(san)
                    .append("=失败")
                    .append("\n")
                    .append("你的理智值减少")
                    .append(strFail)
                    .append("=")
                    .append(mFail.getResInt())
                    .append("点")
                    .append(",当前剩余")
                    .append(newSan)
                    .append("点");
            if (useCard) {
                setCard(newSan, prop, role);
            }
            makeInsane(strResult, newSan, san);
        }
        return strResult.toString();
    }

    private void setCard(int newSan, HashMap<String, Integer> prop, String role) {
        prop.put("san", newSan);
        setRoleInfoFromChooseByQQ(qq, prop);
        ROLE_INFO_CACHE.put(new EntityRoleTag(qq, role), prop);
    }

    private void makeInsane(StringBuilder strResult, int newSan, int san) {
        if (newSan == 0) {
            strResult.append("\n已永久疯狂");
        } else if (san - newSan >= 5) {
            strResult.append("\n已进入临时性疯狂");
        } else if (san - newSan >= san / 5) {
            strResult.append("\n已因单次损失值进入不定性疯狂");
        }
    }

    private boolean hasFunctionSeq(String function) {
        return function.contains("D") || function.contains("d");
    }
}
