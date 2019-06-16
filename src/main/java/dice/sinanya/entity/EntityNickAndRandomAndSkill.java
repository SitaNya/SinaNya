package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 包装骰点信息对象，包含骰点人昵称、骰点结果、技能值
 */
public class EntityNickAndRandomAndSkill {
    /**
     * @param nick 骰点人昵称
     * @param random 骰点结果值
     * @param skill 技能值
     */
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
