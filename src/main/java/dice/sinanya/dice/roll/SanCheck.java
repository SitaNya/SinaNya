package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityManyRolls;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.tools.Calculator;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.ManyRolls.manyRollsForInt;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByQQ;
import static dice.sinanya.tools.RoleInfo.*;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.*;

public class SanCheck {
    private static Pattern p = Pattern.compile("[+-/*]");

    private EntityTypeMessages entityTypeMessages;

    public SanCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void sc() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".sc");
        String strCheckValue = "";
        int skill = 0;
        if (msg.contains(" ")) {
            strCheckValue = msg.split(" ")[0].trim();

            String tmpMsg = msg.split(" ")[1].trim();
            Matcher m = p.matcher(tmpMsg);
            if (m.find()) {
                skill = (int) ceil(Calculator.conversion(tmpMsg));
            } else if (isNumeric(tmpMsg)) {
                skill = Integer.parseInt(tmpMsg);
            } else {
                sender(entityTypeMessages, "sc格式错误");
                return;
            }
        } else {
            strCheckValue = msg;
        }
        String strSuc = "";
        String strFail = "";
        if (!strCheckValue.contains("/")) {
            sender(entityTypeMessages, "sc格式错误");
            return;
        } else {
            strSuc = strCheckValue.split("/")[0];
            strFail = strCheckValue.split("/")[1];
        }

        int intSuc = 0;
        int intFail = 0;

        if (isNumeric(strSuc)) {
            intSuc = Integer.parseInt(strSuc);
        } else if (strSuc.contains("D") || strSuc.contains("d")) {
            EntityManyRolls entityManyRolls;
            try {
                entityManyRolls = new EntityManyRolls(strSuc).check(entityTypeMessages);
                intSuc = manyRollsForInt(entityManyRolls.getTimes(), entityManyRolls.getRolls());
            } catch (PlayerSetException e) {
                return;
            }
        }

        if (isNumeric(strFail)) {
            intFail = Integer.parseInt(strFail);
        } else if (strFail.contains("D") || strFail.contains("d")) {
            EntityManyRolls entityManyRolls;
            try {
                entityManyRolls = new EntityManyRolls(strFail).check(entityTypeMessages);
                intFail = manyRollsForInt(entityManyRolls.getTimes(), entityManyRolls.getRolls());
            } catch (PlayerSetException e) {
                return;
            }
        }

        String role;
        int san;
        HashMap<String, Integer> prop = getRoleInfoFromChooseByFromQQ(entityTypeMessages);
        if (prop != null) {
            role = getRoleChooseByFromQQ(entityTypeMessages);
            if (skill != 0) {
                san = skill;
            } else {
                san = prop.get("san");
            }


            int valuePowCheck = random(1, 100);

            StringBuilder stringBuilder = new StringBuilder();

            if (valuePowCheck == 100) {
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
                if (skill == 0) {
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByFromQQ(entityTypeMessages, prop);
                }
                stringBuilder.append(role)
                        .append("的理智检定结果:")
                        .append("\n")
                        .append(valuePowCheck)
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
                if (newSan == 0) {
                    stringBuilder.append("\n已永久疯狂");
                } else if (maxSan >= 5) {
                    stringBuilder.append("\n已进入临时性疯狂");
                } else if (maxSan >= floor(san / 5)) {
                    stringBuilder.append("\n已因单次损失值进入不定性疯狂");
                }
                sender(entityTypeMessages, stringBuilder.toString());
            } else if (valuePowCheck == 1) {
                int minSan = 0;
                if (strSuc.contains("D") || strSuc.contains("d")) {
                    if (isNumeric(strSuc.split("[dD]")[1])) {
                        minSan = Integer.parseInt(strSuc.split("[dD]")[1]);
                    } else {
                        sender(entityTypeMessages, "sc格式错误");
                    }
                } else {
                    if (isNumeric(strSuc)) {
                        minSan = Integer.parseInt(strSuc);
                    } else {
                        sender(entityTypeMessages, "sc格式错误");
                    }
                }
                int newSan = max(0, san - minSan);
                if (skill == 0) {
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByFromQQ(entityTypeMessages, prop);
                }
                stringBuilder.append(role)
                        .append("的理智检定结果:")
                        .append("\n")
                        .append(valuePowCheck)
                        .append("/")
                        .append(san)
                        .append("=大成功")
                        .append("\n")
                        .append("你的理智值减少")
                        .append(strSuc)
                        .append("=")
                        .append(minSan)
                        .append("点")
                        .append(",当前剩余")
                        .append(newSan)
                        .append("点");
                if (newSan == 0) {
                    stringBuilder.append("\n已永久疯狂");
                } else if (minSan >= 5) {
                    stringBuilder.append("\n已进入临时性疯狂");
                } else if (minSan >= floor(san / 5)) {
                    stringBuilder.append("\n已因单次损失值进入不定性疯狂");
                }
                sender(entityTypeMessages, stringBuilder.toString());
            } else if (valuePowCheck <= san) {
                int newSan = max(0, san - intSuc);
                if (skill == 0) {
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByFromQQ(entityTypeMessages, prop);
                }
                stringBuilder.append(role)
                        .append("的理智检定结果:")
                        .append("\n")
                        .append(valuePowCheck)
                        .append("/")
                        .append(san)
                        .append("=成功")
                        .append("\n")
                        .append("你的理智值减少")
                        .append(strSuc)
                        .append("=")
                        .append(intSuc)
                        .append("点")
                        .append(",当前剩余")
                        .append(newSan)
                        .append("点");
                if (newSan == 0) {
                    stringBuilder.append("\n已永久疯狂");
                } else if (intSuc >= 5) {
                    stringBuilder.append("\n已进入临时性疯狂");
                } else if (intSuc >= floor(san / 5)) {
                    stringBuilder.append("\n已因单次损失值进入不定性疯狂");
                }
                sender(entityTypeMessages, stringBuilder.toString());
            } else {
                int newSan = max(0, san - intFail);
                if (skill == 0) {
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByFromQQ(entityTypeMessages, prop);
                }
                stringBuilder.append(role)
                        .append("的理智检定结果:")
                        .append("\n")
                        .append(valuePowCheck)
                        .append("/")
                        .append(san)
                        .append("=失败")
                        .append("\n")
                        .append("你的理智值减少")
                        .append(strFail)
                        .append("=")
                        .append(intFail)
                        .append("点")
                        .append(",当前剩余")
                        .append(newSan)
                        .append("点");
                if (newSan == 0) {
                    stringBuilder.append("\n已永久疯狂");
                } else if (intFail >= 5) {
                    stringBuilder.append("\n已进入临时性疯狂");
                } else if (intFail >= floor(san / 5)) {
                    stringBuilder.append("\n已因单次损失值进入不定性疯狂");
                }
                sender(entityTypeMessages, stringBuilder.toString());
            }
        } else {
            sender(entityTypeMessages, "[CQ:at,qq=" + entityTypeMessages.getFromQQ() + "] 未选择人物卡");
        }
    }


    public void sc(long qq, String inputMsg) {
        String msg = deleteTag(inputMsg, ".sc");
        String strCheckValue = "";
        int skill = 0;
        if (msg.contains(" ")) {
            strCheckValue = msg.split(" ")[0].trim();

            String tmpMsg = msg.split(" ")[1].trim();
            Matcher m = p.matcher(tmpMsg);
            if (m.find()) {
                skill = (int) ceil(Calculator.conversion(tmpMsg));
            } else if (isNumeric(tmpMsg)) {
                skill = Integer.parseInt(tmpMsg);
            } else {
                sender(entityTypeMessages, "sc格式错误");
                return;
            }
        } else {
            strCheckValue = msg;
        }
        String strSuc = "";
        String strFail = "";
        if (!strCheckValue.contains("/")) {
            sender(entityTypeMessages, "sc格式错误");
            return;
        } else {
            strSuc = strCheckValue.split("/")[0];
            strFail = strCheckValue.split("/")[1];
        }

        int intSuc = 0;
        int intFail = 0;

        if (isNumeric(strSuc)) {
            intSuc = Integer.parseInt(strSuc);
        } else if (strSuc.contains("D") || strSuc.contains("d")) {
            EntityManyRolls entityManyRolls;
            try {
                entityManyRolls = new EntityManyRolls(strSuc).check(entityTypeMessages);
                intSuc = manyRollsForInt(entityManyRolls.getTimes(), entityManyRolls.getRolls());
            } catch (PlayerSetException e) {
                return;
            }
        }

        if (isNumeric(strFail)) {
            intFail = Integer.parseInt(strFail);
        } else if (strFail.contains("D") || strFail.contains("d")) {
            EntityManyRolls entityManyRolls;
            try {
                entityManyRolls = new EntityManyRolls(strFail).check(entityTypeMessages);
                intFail = manyRollsForInt(entityManyRolls.getTimes(), entityManyRolls.getRolls());
            } catch (PlayerSetException e) {
                return;
            }
        }

        String role;
        int san;
        HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
        if (prop != null) {
            role = getRoleChooseByQQ(qq);
            if (skill != 0) {
                san = skill;
            } else {
                san = prop.get("san");
            }


            int valuePowCheck = random(1, 100);

            StringBuilder stringBuilder = new StringBuilder();

            if (valuePowCheck == 100) {
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
                if (skill == 0) {
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByQQ(qq, prop);
                }
                stringBuilder.append(role)
                        .append("的理智检定结果:")
                        .append("\n")
                        .append(valuePowCheck)
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
                if (newSan == 0) {
                    stringBuilder.append("\n已永久疯狂");
                } else if (maxSan >= 5) {
                    stringBuilder.append("\n已进入临时性疯狂");
                } else if (maxSan >= floor(san / 5)) {
                    stringBuilder.append("\n已因单次损失值进入不定性疯狂");
                }
                sender(entityTypeMessages, stringBuilder.toString());
            } else if (valuePowCheck == 1) {
                int minSan = 0;
                if (strSuc.contains("D") || strSuc.contains("d")) {
                    if (isNumeric(strSuc.split("[dD]")[1])) {
                        minSan = Integer.parseInt(strSuc.split("[dD]")[1]);
                    } else {
                        sender(entityTypeMessages, "sc格式错误");
                    }
                } else {
                    if (isNumeric(strSuc)) {
                        minSan = Integer.parseInt(strSuc);
                    } else {
                        sender(entityTypeMessages, "sc格式错误");
                    }
                }
                int newSan = max(0, san - minSan);
                if (skill == 0) {
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByFromQQ(entityTypeMessages, prop);
                }
                stringBuilder.append(role)
                        .append("的理智检定结果:")
                        .append("\n")
                        .append(valuePowCheck)
                        .append("/")
                        .append(san)
                        .append("=大成功")
                        .append("\n")
                        .append("你的理智值减少")
                        .append(strSuc)
                        .append("=")
                        .append(minSan)
                        .append("点")
                        .append(",当前剩余")
                        .append(newSan)
                        .append("点");
                if (newSan == 0) {
                    stringBuilder.append("\n已永久疯狂");
                } else if (minSan >= 5) {
                    stringBuilder.append("\n已进入临时性疯狂");
                } else if (minSan >= floor(san / 5)) {
                    stringBuilder.append("\n已因单次损失值进入不定性疯狂");
                }
                sender(entityTypeMessages, stringBuilder.toString());
            } else if (valuePowCheck <= san) {
                int newSan = max(0, san - intSuc);
                if (skill == 0) {
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByQQ(qq, prop);
                }
                stringBuilder.append(role)
                        .append("的理智检定结果:")
                        .append("\n")
                        .append(valuePowCheck)
                        .append("/")
                        .append(san)
                        .append("=成功")
                        .append("\n")
                        .append("你的理智值减少")
                        .append(strSuc)
                        .append("=")
                        .append(intSuc)
                        .append("点")
                        .append(",当前剩余")
                        .append(newSan)
                        .append("点");
                if (newSan == 0) {
                    stringBuilder.append("\n已永久疯狂");
                } else if (intSuc >= 5) {
                    stringBuilder.append("\n已进入临时性疯狂");
                } else if (intSuc >= floor(san / 5)) {
                    stringBuilder.append("\n已因单次损失值进入不定性疯狂");
                }
                sender(entityTypeMessages, stringBuilder.toString());
            } else {
                int newSan = max(0, san - intFail);
                if (skill == 0) {
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByQQ(qq, prop);
                }
                stringBuilder.append(role)
                        .append("的理智检定结果:")
                        .append("\n")
                        .append(valuePowCheck)
                        .append("/")
                        .append(san)
                        .append("=失败")
                        .append("\n")
                        .append("你的理智值减少")
                        .append(strFail)
                        .append("=")
                        .append(intFail)
                        .append("点")
                        .append(",当前剩余")
                        .append(newSan)
                        .append("点");
                if (newSan == 0) {
                    stringBuilder.append("\n已永久疯狂");
                } else if (intFail >= 5) {
                    stringBuilder.append("\n已进入临时性疯狂");
                } else if (intFail >= floor(san / 5)) {
                    stringBuilder.append("\n已因单次损失值进入不定性疯狂");
                }
                sender(entityTypeMessages, stringBuilder.toString());
            }
        } else {
            sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
        }
    }
}
