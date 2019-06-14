package dice.sinanya.dice.manager;

import dice.sinanya.dice.manager.imal.AtQq;
import dice.sinanya.dice.manager.imal.PropList;
import dice.sinanya.dice.manager.imal.Role;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTeamInfo;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.entity.imal.GetDb;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;
import dice.sinanya.tools.Calculator;
import dice.sinanya.tools.CheckSanCheck;
import dice.sinanya.tools.GetRollResultAndStr;
import dice.sinanya.tools.MakeManyRollsStr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByQQ;
import static dice.sinanya.tools.RoleInfo.getRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.RoleInfo.setRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.Sender.sender;
import static dice.sinanya.tools.Team.*;
import static java.lang.Integer.min;
import static java.lang.Math.ceil;
import static java.lang.Math.max;

/**
 * 管理小队
 *
 * @author SitaNya
 */
public class Team extends PropList implements GetDb, Role, AtQq {

    private String regex = "\\[CQ:at,qq=([0-9]+)]";

    private Pattern plus = Pattern.compile("[+*/\\-]");

    private EntityTypeMessages entityTypeMessages;

    public Team(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void set() {
        useRole(entityTypeMessages);

        String tag = TAG_TEAM_SET;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        ArrayList<String> qqList = getAtQqList(msg);
        for (String qq : qqList) {
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]加入小队。可以使用.team查看队伍信息,.team hp/san对成员状态进行强制调整\n其余使用方式请查看.help命令");
        }
        EntityTeamInfo entityTeamInfo = new EntityTeamInfo(entityTypeMessages.getFromGroup(), qqList);
        addIntoTeam(entityTeamInfo);
    }

    public void remove() {
        useRole(entityTypeMessages);

        String tag = TAG_TEAM_RM;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        ArrayList<String> qqList = getAtQqList(msg);
        EntityTeamInfo entityTeamInfo = new EntityTeamInfo(entityTypeMessages.getFromGroup(), qqList);
        removeFromTeam(entityTeamInfo);
        for (String qq : qqList) {
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]移出小队,其在这期间损失的血量和san值不会还原。");
        }
    }

    public void clr() {
        clearTeam(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已清空本群小队");
    }

    public void call() {
        useRole(entityTypeMessages);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("kp正在呼叫以下成员:");
        for (String qq : queryTeam(entityTypeMessages.getFromGroup())
        ) {
            stringBuilder.append("\n[CQ:at,qq=")
                    .append(qq)
                    .append("]");
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }

    public void hp() {
        useRole(entityTypeMessages);

        String tag = TAG_TEAM_HP;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        ArrayList<String> qqList = getAtQqList(msg);

        msg = msg.replaceAll(regex, "").trim();
        for (String qq : qqList) {
            boolean add = false;
            if (msg.charAt(0) == '+') {
                msg = msg.replaceAll("\\+", "");
                add = true;
            }

            String[] everyFunction = msg.split(plus.toString());

            String strResult = msg;
            String strFunction = msg;
            for (String function : everyFunction) {
                if (!isNumeric(function)) {
                    GetRollResultAndStr resRollResultAndStr = new GetRollResultAndStr(entityTypeMessages, function);

                    strResult = strResult.replaceFirst(function, resRollResultAndStr.getResStr());
                    strFunction = strFunction.replaceFirst(function, resRollResultAndStr.getFunction());
                }
            }
//            将原3d6替换为(5+5+1)，塞回原字符串里。
//            如原本是3d6+3d6，替换后是（5+5+1）+（4+3+6）
//            其中strResult存储了数学表达式如（5+5+1）+（4+3+6）
//            而strFunction存储了最初的字符表达式，如3d6+3d6

            int result;
            if (isNumeric(strResult)) {
                result = Integer.parseInt(strResult);
            } else {
                result = (int) ceil(Calculator.conversion(strResult));
            }

            String role;
            int hp;
            HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
            if (prop != null) {

                role = getRoleChooseByQQ(qq);
                hp = prop.get("hp");
                int con = prop.get("con");
                int siz = prop.get("siz");
                if (add) {
                    int maxHp = (con + siz) / 10;
                    int newHp = min(hp + result, maxHp);
                    prop.put("hp", newHp);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    sender(entityTypeMessages, "已为" + role + "恢复" + strFunction + "=" + result + "点血量，剩余" + newHp + "点");
                } else {
                    int newHp = max(0, hp - result);
                    prop.put("hp", newHp);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    ROLE_INFO_CACHE.put(new EntityRoleTag(Long.parseLong(qq), role), prop);
                    if (newHp == 0) {
                        sender(entityTypeMessages, role + "损失" + strFunction + "=" + result + "点血量，已死亡");
                    } else if (result >= hp / 2) {
                        sender(entityTypeMessages, "已为" + role + "降低" + strFunction + "=" + result + "点血量，剩余" + newHp + "点,已进入重伤状态");
                    } else {
                        sender(entityTypeMessages, "已为" + role + "降低" + strFunction + "=" + result + "点血量，剩余" + newHp + "点");
                    }
                }
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        }
    }

    public void san() {
        useRole(entityTypeMessages);

        String tag = TAG_TEAM_SAN;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        ArrayList<String> qqList = getAtQqList(msg);

        msg = msg.replaceAll(regex, "").trim();
        for (String qq : qqList) {
            boolean add = false;
            if (msg.contains("+")) {
                msg = msg.replaceAll("\\+", "");
                add = true;
            }

            CheckSanCheck checkSanCheck = new CheckSanCheck(entityTypeMessages, qq);

            if (add) {
                try {
                    checkSanCheck.addSanCheck(msg);
                } catch (PlayerSetException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (msg.contains("/")) {
                        String result = checkSanCheck.checkSanCheck(msg);
                        sender(entityTypeMessages, result);
                    } else {
                        int changeValue;
                        if (isNumeric(msg)) {
                            changeValue = Integer.parseInt(msg);
                        } else {
                            changeValue = new MakeManyRollsStr(msg).getRes();
                        }
                        HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
                        if (prop != null) {
                            StringBuilder strResult = new StringBuilder();
                            int newSan = max(0, prop.get("san") - changeValue);
                            String role = getRoleChooseByQQ(qq);
                            strResult.append("已为").append(role).append("减少").append(changeValue).append("点理智值，剩余").append(newSan).append("点");
                            if (newSan == 0) {
                                strResult.append("\n已永久疯狂");
                            } else if (changeValue >= 5) {
                                strResult.append("\n已进入临时性疯狂");
                            } else if (changeValue >= prop.get("san") / 5) {
                                strResult.append("\n已因单次损失值进入不定性疯狂");
                            }
                            prop.put("san", newSan);
                            setRoleInfoFromChooseByQQ(qq, prop);
                            ROLE_INFO_CACHE.put(new EntityRoleTag(Long.parseLong(qq), role), prop);
                            sender(entityTypeMessages, strResult.toString());
                        } else {
                            sender(entityTypeMessages, "未找到[CQ:at,qq=" + qq + "]的人物卡");
                        }
                    }

                } catch (SanCheckSetException | PlayerSetException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public void show() {
        useRole(entityTypeMessages);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您的小队情况目前为: ");
        ArrayList<String> qqList = queryTeam(entityTypeMessages.getFromGroup());
        if (qqList == null) {
            sender(entityTypeMessages, "小队成员为空");
            return;
        }
        for (String qq : qqList) {
            HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
            if (prop != null) {
                String role = getRoleChooseByQQ(qq);
                int str = prop.get("str");
                int pow = prop.get("pow");
                int con = prop.get("con");
                int siz = prop.get("siz");
                int san = prop.get("san");
                int hp = prop.get("hp");
                int cthulhuMythos = prop.get("cthulhuMythos");

                stringBuilder
                        .append("\n")
                        .append(role)
                        .append("  ")
                        .append("血量=")
                        .append(hp)
                        .append("/")
                        .append((con + siz) / 10)
                        .append("  ")
                        .append("san值=")
                        .append(san)
                        .append("/")
                        .append(99 - cthulhuMythos)
                        .append("  ")
                        .append("(初始值:")
                        .append(pow)
                        .append(")  ")
                        .append("DB:")
                        .append(dbGetter(siz + str));
            } else {
                stringBuilder.append("\n[CQ:at,qq=").append(qq).append("] 未选择人物卡");
            }
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }

    public void desc() {
        useRole(entityTypeMessages);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您小队内成员的属性值为:\n");

        ArrayList<String> qqList = queryTeam(entityTypeMessages.getFromGroup());
        if (qqList == null) {
            sender(entityTypeMessages, "小队成员为空");
            return;
        }
        for (String qq : qqList) {
            stringBuilder = new Roles(entityTypeMessages).showProp(entityTypeMessages, qq);
        }
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQq(), stringBuilder.toString());
    }
}
