package dice.sinanya.entity;

import java.util.Objects;

/**
 * 对抗类型对象
 *
 * @author SitaNya
 */
public class EntityAntagonize {
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
