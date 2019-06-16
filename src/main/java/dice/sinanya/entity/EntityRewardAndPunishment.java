package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 惩罚投、奖励投对象
 */
public class EntityRewardAndPunishment {
    /**
     * @param times 惩罚、奖励骰个数
     * @param skill 技能值
     */
    private int times;
    private int skill;

    public EntityRewardAndPunishment(int times, int skill) {
        this.times = times;
        this.skill = skill;
    }

    public int getTimes() {
        return times;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
}
