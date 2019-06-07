package dice.sinanya.entity;

public class EntityLevelResult {
    int level;
    int result;

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
