package dice.sinanya.entity;

/**
 * 包装骰点信息对象，包含骰点人昵称、骰点结果、技能值
 *
 * @author SitaNya
 */
public class EntityNickAndRandomAndSkill {
    private String nick;
    private int random;
    private int skill;

    public EntityNickAndRandomAndSkill(String nick, int random, int skill) {
        this.nick = nick;
        this.random = random;
        this.skill = skill;
    }

    public String getNick() {
        return nick;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
}
