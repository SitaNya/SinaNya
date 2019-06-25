package dice.sinanya.tools.makedata;

import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;

import java.util.HashMap;

import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.system.MessagesSystem.SPACE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByQQ;
import static dice.sinanya.tools.getinfo.RoleInfo.getRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.getinfo.RoleInfo.setRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.makedata.RandomInt.random;
import static dice.sinanya.tools.makedata.Sender.sender;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 进行SanCheck检定
 */
public class MakeSanCheck {
    private EntityTypeMessages entityTypeMessages;
    private long qq;

    public MakeSanCheck(EntityTypeMessages entityTypeMessages) {
        qq = Long.parseLong(entityTypeMessages.getFromQq());
        this.entityTypeMessages = entityTypeMessages;
    }

    public MakeSanCheck(EntityTypeMessages entityTypeMessages, String qqId) {
        qq = Long.parseLong(qqId);
        this.entityTypeMessages = entityTypeMessages;
    }

    public MakeSanCheck(EntityTypeMessages entityTypeMessages, long qq) {
        this.qq = qq;
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 恢复san值方法，用于team命令
     *
     * @param function 恢复的字符串，可以是1D3|1D3+5等
     * @throws PlayerSetException 如果无法解析，则返回玩家输入值报错
     */
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
//        如果字符串中含有空格且第二位不为空，则认为是指定了san值。否则整段都是表达式


        if (isNumeric(strAddValue)) {
            changeValue = Integer.parseInt(strAddValue);
            changeFunction = strAddValue;
        } else {
            GetRollResultAndStr getRollResultAndStr = new GetRollResultAndStr(entityTypeMessages, strAddValue);
            changeValue = getRollResultAndStr.getResInt();
            changeFunction = getRollResultAndStr.getResStr();
        }
//        如果表达式是数字，那么直接恢复即可。如果不是则需要过一下GetRollResultAndStr方法计算最终的值

        HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
        if (san == 0 && prop != null) {
            role = getRoleChooseByQQ(qq);
            san = prop.get("san");
            cthulhuMythos = prop.get("cthulhuMythos");
            prop.put("san", san + changeValue);
            setRoleInfoFromChooseByQQ(qq, prop);
//            计算新的值后更新人物卡
        } else if (san == 0) {
            throw new PlayerSetException(entityTypeMessages);
        }
//        如果san为0，也就是玩家没有输入san值的同时，人物卡还没取到的话就报错。否则优先用玩家输入的san，然后用人物卡。
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
//        如果字符串中含有空格且第二位不为空，则认为是指定了san值。否则整段都是表达式

        String tagNone = NONE;
        if (!strCheckValue.contains(sanFunctionSeq) || strCheckValue.split(sanFunctionSeq)[0].equals(tagNone) || strCheckValue.split(sanFunctionSeq)[1].equals(tagNone)) {
            throw new SanCheckSetException(entityTypeMessages);
        }
//        确认表达式合规

        String strSuccess = strCheckValue.split(sanFunctionSeq)[0];
        String strFail = strCheckValue.split(sanFunctionSeq)[1];
//        分别取得成功与失败的表达式

        GetRollResultAndStr mSuccess = new GetRollResultAndStr(entityTypeMessages, strSuccess);
        GetRollResultAndStr mFail = new GetRollResultAndStr(entityTypeMessages, strFail);
//        分别计算成功与失败的表达式

        boolean useCard = false;
//        默认为未使用人物卡
        HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
        if (san == 0 && prop != null) {
            role = getRoleChooseByQQ(qq);
            san = prop.get("san");
            useCard = true;
//            如果读到了人物卡，将使用人物卡标志位记为打开
        } else if (san == 0) {
            throw new PlayerSetException(entityTypeMessages);
        } else {
            role = getNickName(entityTypeMessages);
        }
//        如果san为0，也就是玩家没有输入san值的同时，人物卡还没取到的话就报错。否则优先用玩家输入的san，然后用人物卡。

        StringBuilder strResult = new StringBuilder();
        strResult.append(role)
                .append("的理智检定结果:")
                .append("\n");
//        初始化回复字符串

        int random = random(1, 100);
//        骰点
        String regexFunctionSeq = "[dD]";
        if (random == levelFumbleLine) {
//            如果大失败，默认掉最大值
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
                    .append("点")
                    .append(MESSAGES_SYSTEM.get("sanCheckFumble"));
            if (useCard) {
                setCard(newSan, prop, role);
            }
            makeInsane(strResult, newSan, san);
        } else if (random == 1) {
//            如果大成功，默认掉最小值
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
                    .append("点")
                    .append(MESSAGES_SYSTEM.get("sanCheckCriticalSuccess"));

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
                    .append("点")
                    .append(MESSAGES_SYSTEM.get("sanCheckSuccess"));
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
                    .append("点")
                    .append(MESSAGES_SYSTEM.get("sanCheckFailure"));
            if (useCard) {
                setCard(newSan, prop, role);
            }
            makeInsane(strResult, newSan, san);
        }
        return strResult.toString();
    }

    /**
     * 更新人物卡信息，不过这里必须注意，人工输入san值是不会更新任务卡信息
     *
     * @param newSan 新的san值
     * @param prop   包含所有属性值的HashMap列表
     * @param role   角色名
     */
    private void setCard(int newSan, HashMap<String, Integer> prop, String role) {
        prop.put("san", newSan);
        setRoleInfoFromChooseByQQ(qq, prop);
        ROLE_INFO_CACHE.put(new EntityRoleTag(qq, role), prop);
    }

    /**
     * 根据san值变化判断是否疯狂
     *
     * @param strResult StringBuilder对象，用于整合回复字符串
     * @param newSan    新的san值
     * @param san       原本的san值
     */
    private void makeInsane(StringBuilder strResult, int newSan, int san) {
        if (newSan == 0) {
            strResult.append("\n已永久疯狂")
                    .append(MESSAGES_SYSTEM.get("symptom"));
        } else if (san - newSan >= 5) {
            strResult.append("\n已进入临时性疯狂")
                    .append(MESSAGES_SYSTEM.get("symptom"));
        } else if (san - newSan >= san / 5) {
            strResult.append("\n已因单次损失值进入不定性疯狂")
                    .append(MESSAGES_SYSTEM.get("symptom"));
        }
    }

    /**
     * 判断输入字符串是否为表达式，表达式一定带有d或D
     *
     * @param function 输入字符串
     * @return 是否正常
     */
    private boolean hasFunctionSeq(String function) {
        return function.contains("D") || function.contains("d");
    }
}
