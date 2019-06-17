package dice.sinanya.tools.getinfo;

import dice.sinanya.db.team.InsertTeam;
import dice.sinanya.db.team.SelectTeam;
import dice.sinanya.entity.EntityQqAndGroup;
import dice.sinanya.entity.EntityTeamInfo;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static dice.sinanya.system.MessagesTeamEn.TEAM_EN;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.getinfo.RoleChoose.checkRoleChooseExistByQQ;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByQQ;

public class Team {
    private static InsertTeam insertTeam = new InsertTeam();
    private static SelectTeam selectTeam = new SelectTeam();

    public static void addIntoTeam(EntityTeamInfo entityTeamInfo) {
        insertTeam.changeTeamInfo(entityTeamInfo, true);
    }

    public static void removeFromTeam(EntityTeamInfo entityTeamInfo) {
        insertTeam.changeTeamInfo(entityTeamInfo, false);
    }

    public static void clearTeam(String group) {
        insertTeam.deleteGroup(group);
    }

    public static ArrayList<String> queryTeam(String group) {
        return selectTeam.selectTeamInfo(group);
    }

    public static void saveTeamEn() {
        for (Map.Entry<EntityQqAndGroup, ArrayList<String>> mapEntry : TEAM_EN.entrySet()) {
            insertTeam.saveTeamEnToDatabase(mapEntry);
        }
    }

    public static void flushTeamEn() {
        selectTeam.flushTeamEnFromDatabase();
    }

    public static String getTeamEn(EntityTypeMessages entityTypeMessages, String qqId) {
        EntityQqAndGroup entityQqAndGroup = new EntityQqAndGroup(entityTypeMessages.getFromGroup(), qqId);
        HashMap<EntityQqAndGroup, ArrayList<String>> tmp = TEAM_EN;
        if (TEAM_EN.containsKey(entityQqAndGroup)) {
            String role;
            if (checkRoleChooseExistByQQ(qqId)) {
                role = getRoleChooseByQQ(qqId) + ": ";
            } else {
                role = "[CQ:at,qq=" + qqId + "]: ";
            }
            return role + StringUtils.join(TEAM_EN.get(entityQqAndGroup), ",");
        } else {
            return String.format(messagesSystem.get("teamMemberEnIsEmpty"), qqId);
        }
    }

    public static void clrTeamEn(String groupId) {
        insertTeam.clrTeamEnToDatabase(groupId);
    }

    public static void rmTeamEn(String groupId) {
        insertTeam.clrTeamEnToDatabase(groupId);
    }
}
