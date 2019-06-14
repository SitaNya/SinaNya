package dice.sinanya.entity;

/**
 * 骰点等级对象，包含骰点值与成功等级
 *
 * @author SitaNya
 */
public class EntityLevelResult {
    private int level;
    private int result;

    public EntityLevelResult(int level, int result) {
        this.level = level;
        this.result = result;
    }

    public int getLevel() {
        return level;
    }

    public int getResult() {
        return result;
    }
}
