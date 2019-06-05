package dice.sinanya.entity;

import java.util.ArrayList;

public class EntityTeamInfo {
    private String group;
    private ArrayList<Long> qqList=new ArrayList<>();

    public EntityTeamInfo(String group, ArrayList<Long> qqList) {
        this.group = group;
        this.qqList = qqList;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<Long> getQqList() {
        return qqList;
    }

    public void setQqList(ArrayList<Long> qqList) {
        this.qqList = qqList;
    }
}
