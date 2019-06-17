package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-06-17
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 用于传递群统计信息
 */
public class EntityGroupCensus {
    private int groupNum;
    private int onNum;

    public EntityGroupCensus(int groupNum, int onNum) {
        this.groupNum = groupNum;
        this.onNum = onNum;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public int getOnNum() {
        return onNum;
    }
}
