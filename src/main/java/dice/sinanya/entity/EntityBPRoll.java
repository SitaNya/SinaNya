package dice.sinanya.entity;

public class EntityBPRoll {
    int times;
    int skill;

    public EntityBPRoll(int times, int skill) {
        this.times = times;
        this.skill = skill;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
}
