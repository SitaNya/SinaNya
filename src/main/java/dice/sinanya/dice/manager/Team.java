package dice.sinanya.dice.manager;

import dice.sinanya.dice.manager.imal.AtQq;
import dice.sinanya.dice.manager.imal.Role;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityStrManyRolls;
import dice.sinanya.entity.EntityTeamInfo;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.entity.imal.GetDb;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;
import dice.sinanya.exceptions.TeamIsEmptyException;
import dice.sinanya.tools.makedata.GetRollResultAndStr;
import dice.sinanya.tools.makedata.MakeSanCheck;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByQQ;
import static dice.sinanya.tools.getinfo.RoleInfo.getRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.getinfo.RoleInfo.setRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.getinfo.Team.*;
import static dice.sinanya.tools.makedata.GetRollResultAndStr.getResFunctionAndResultInt;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;
import static java.lang.Integer.min;
import static java.lang.Math.max;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 管理小队
 */
public class Team implements GetDb, Role, AtQq {

    private static Logger log = LogManager.getLogger(Team.class.getName());

    private String regex = "\\[CQ:at,qq=([0-9]+)]";

    private Pattern plus = Pattern.compile("[+*/\\-]");

    private EntityTypeMessages entityTypeMessages;

    public Team(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 将@到的成员加入小队
     */
    public void set() {
        String tag = TAG_TEAM_SET;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        ArrayList<String> qqList = getAtQqList(msg);
        for (String qq : qqList) {
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]加入小队。可以使用.team查看队伍信息,.team hp/san对成员状态进行强制调整\n其余使用方式请查看.help命令");
        }
        EntityTeamInfo entityTeamInfo = new EntityTeamInfo(entityTypeMessages.getFromGroup(), qqList);
        addIntoTeam(entityTeamInfo);
    }

    /**
     * 将@到的成员移出小队，也会清空该队员的技能成功记录
     */
    public void remove() {
        String tag = TAG_TEAM_RM;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        ArrayList<String> qqList = getAtQqList(msg);
        EntityTeamInfo entityTeamInfo = new EntityTeamInfo(entityTypeMessages.getFromGroup(), qqList);
        removeFromTeam(entityTeamInfo);
        for (String qq : qqList) {
            rmTeamEn(qq, entityTypeMessages.getFromGroup());
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]移出小队,其在这期间损失的血量和san值不会还原。");
        }
    }

    /**
     * 将本群小队清空，也会清空队员的技能成功记录
     */
    public void clr() {
        clearTeam(entityTypeMessages.getFromGroup());
        clrTeamEn(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已清空本群小队");
    }

    /**
     * 自动@本群小队中所有成员
     */
    public void call() {
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

    /**
     * 调整@到的小队成员的血量，默认为减少，可以使用1D3或1D3+20之类的表达式，每个小队成员都会单独计算表达式
     * 使用+为前缀则恢复血量
     */
    public void hp() {
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


            EntityStrManyRolls entityStrManyRolls = getResFunctionAndResultInt(entityTypeMessages, msg, everyFunction);

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
                    int newHp = min(hp + entityStrManyRolls.getResult(), maxHp);
                    prop.put("hp", newHp);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    sender(entityTypeMessages, "已为" + role + "恢复" + entityStrManyRolls.getStrFunction() + "=" + entityStrManyRolls.getResult() + "点血量，剩余" + newHp + "点");
                } else {
                    int newHp = max(0, hp - entityStrManyRolls.getResult());
                    prop.put("hp", newHp);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    ROLE_INFO_CACHE.put(new EntityRoleTag(Long.parseLong(qq), role), prop);
                    if (newHp == 0) {
                        sender(entityTypeMessages, role + "损失" + entityStrManyRolls.getStrFunction() + "=" + entityStrManyRolls.getResult() + "点血量，已死亡");
                    } else if (entityStrManyRolls.getResult() >= hp / 2) {
                        sender(entityTypeMessages, "已为" + role + "降低" + entityStrManyRolls.getStrFunction() + "=" + entityStrManyRolls.getResult() + "点血量，剩余" + newHp + "点,已进入重伤状态");
                    } else {
                        sender(entityTypeMessages, "已为" + role + "降低" + entityStrManyRolls.getStrFunction() + "=" + entityStrManyRolls.getResult() + "点血量，剩余" + newHp + "点");
                    }
                }
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        }
    }

    /**
     * 调整@到的小队成员的理智值，默认为减少，可以使用1D3/1d6或1D3+20之类的表达式，每个小队成员都会单独计算表达式
     * 使用+为前缀则恢复理智值
     */
    public void san() {
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

            MakeSanCheck makeSanCheck = new MakeSanCheck(entityTypeMessages, qq);

            if (add) {
                try {
                    makeSanCheck.addSanCheck(msg);
                } catch (PlayerSetException e) {
                    log.error(e.getMessage(), e);
                }
            } else {
                try {
                    if (msg.contains("/")) {
                        String result = makeSanCheck.checkSanCheck(msg);
                        sender(entityTypeMessages, result);
                    } else {
                        int changeValue;
                        if (isNumeric(msg)) {
                            changeValue = Integer.parseInt(msg);
                        } else {
                            changeValue = new GetRollResultAndStr(entityTypeMessages, msg).getResInt();
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
                    log.error(e.getMessage(), e);
                }
            }


        }
    }

    /**
     * 显示当前小队的情况，会根据小队成员的当前激活人物自动计算得出
     */
    public void show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您的小队情况目前为: ");
        ArrayList<String> qqList = queryTeam(entityTypeMessages.getFromGroup());
        if (qqList == null) {
            try {
                throw new TeamIsEmptyException(entityTypeMessages);
            } catch (TeamIsEmptyException e) {
                log.error(e.getMessage(), e);
                return;
            }
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

    /**
     * 显示当前小队所有成员当前激活角色的技能情况，私聊发送给命令触发人
     */
    public void desc() {
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> qqList = queryTeam(entityTypeMessages.getFromGroup());
        if (qqList == null) {
            try {
                throw new TeamIsEmptyException(entityTypeMessages);
            } catch (TeamIsEmptyException e) {
                log.error(e.getMessage(), e);
                return;
            }
        } else {
            stringBuilder.append("您小队内成员的属性值为:\n");
        }
        for (String qq : qqList) {
            stringBuilder.append(new Roles(entityTypeMessages).showProp(entityTypeMessages, qq));
        }
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQq(), stringBuilder.toString());
    }

    /**
     * 显示当前小队所有成员当前激活角色的技能成功情况
     */
    public void en() {
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> qqList = queryTeam(entityTypeMessages.getFromGroup());
        if (qqList == null) {
            try {
                throw new TeamIsEmptyException(entityTypeMessages);
            } catch (TeamIsEmptyException e) {
                log.error(e.getMessage(), e);
                return;
            }
        } else {
            stringBuilder.append("以下是您小队中成员的技能成功情况:\n");
        }
        for (String qq : qqList) {
            stringBuilder.append(getTeamEn(entityTypeMessages, qq)).append("\n");
        }
        sender(entityTypeMessages, stringBuilder.substring(0, stringBuilder.length() - 1));
    }
}
