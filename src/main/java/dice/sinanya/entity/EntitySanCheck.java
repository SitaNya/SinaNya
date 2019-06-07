package dice.sinanya.entity;

public class EntitySanCheck {
    int level;
    String strSanCheck;
    int changeValue;

    public int getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(int changeValue) {
        this.changeValue = changeValue;
    }

    public EntitySanCheck(int level, String strSanCheck, int changeValue) {
        this.level = level;
        this.strSanCheck = strSanCheck;
        this.changeValue = changeValue;
    }

    public EntitySanCheck(int level, String strSanCheck) {
        this.level = level;
        this.strSanCheck = strSanCheck;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getStrSanCheck() {
        return strSanCheck;
    }

    public void setStrSanCheck(String strSanCheck) {
        this.strSanCheck = strSanCheck;
    }
}
