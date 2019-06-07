package dice.sinanya.tools;

import dice.sinanya.db.team.InsertTeam;
import dice.sinanya.db.team.SelectTeam;
import dice.sinanya.entity.EntityTeamInfo;

import java.util.ArrayList;

public class Team {
    private static InsertTeam insertTeam = new InsertTeam();
    private static SelectTeam selectTeam = new SelectTeam();

    public static void addIntoTeam(EntityTeamInfo entityTeamInfo) {
        insertTeam.insertTeamInfo(entityTeamInfo, true);
    }

    public static void removeFromTeam(EntityTeamInfo entityTeamInfo) {
        insertTeam.insertTeamInfo(entityTeamInfo, false);
    }

    public static void clearTeam(String group) {
        insertTeam.deleteGroup(group);
    }

    public static ArrayList<String> queryTeam(String group) {
        return selectTeam.selectTeamInfo(group);
    }
}
