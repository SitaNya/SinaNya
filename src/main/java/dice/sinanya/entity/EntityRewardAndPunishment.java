package dice.sinanya.entity;

/**
 * 惩罚投、奖励投对象
 *
 * @author SitaNya
 */
public class EntityRewardAndPunishment {
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
