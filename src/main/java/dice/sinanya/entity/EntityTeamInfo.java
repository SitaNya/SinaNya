package dice.sinanya.entity;

import java.util.ArrayList;

public class EntityTeamInfo {
    private String group;
    private ArrayList<String> qqList=new ArrayList<>();

    public EntityTeamInfo(String group, ArrayList<String> qqList) {
        this.group = group;
        this.qqList = qqList;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<String> getQqList() {
        return qqList;
    }

    public void setQqList(ArrayList<String> qqList) {
        this.qqList = qqList;
    }
}
