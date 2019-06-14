package dice.sinanya.entity;

import java.util.ArrayList;

/**
 * 小队信息对象，包装了ArrayList列表形式的QQ列表与群号
 *
 * @author SitaNya
 */
public class EntityTeamInfo {
    private String group;
    private ArrayList<String> qqList;

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

}
