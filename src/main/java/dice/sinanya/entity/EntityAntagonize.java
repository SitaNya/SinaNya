package dice.sinanya.entity;

import java.util.Objects;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 对抗对象
 */
public class EntityAntagonize {

    /**
     * @param random 随机数结果
     * @param level 成功等级
     * @param skill 技能等级
     */
    private int random;
    private int level;
    private int skill;

    public EntityAntagonize(int random, int level, int skill) {
        this.random = random;
        this.level = level;
        this.skill = skill;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public int getLevel() {
        return level;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityAntagonize)) {
            return false;
        }
        EntityAntagonize that = (EntityAntagonize) o;
        return getRandom() == that.getRandom() &&
                getLevel() == that.getLevel() &&
                getSkill() == that.getSkill();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRandom(), getLevel(), getSkill());
    }
}
