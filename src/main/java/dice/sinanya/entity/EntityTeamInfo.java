package dice.sinanya.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 小队信息对象，包装了ArrayList列表形式的QQ列表与群号
 */
public class EntityTeamInfo {
    private String group;
    private ArrayList<String> qqList;

    public EntityTeamInfo(String group, List<String> qqList) {
        this.group = group;
        this.qqList = (ArrayList<String>) qqList;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getQqList() {
        return qqList;
    }

}
