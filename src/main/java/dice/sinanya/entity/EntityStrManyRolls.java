package dice.sinanya.entity;

public class EntityStrManyRolls {
    private String strManyRolls;
    private int result;

    public EntityStrManyRolls(String strManyRolls, int result) {
        this.strManyRolls = strManyRolls;
        this.result = result;
    }

    public String getStrManyRolls() {
        return strManyRolls;
    }

    public void setStrManyRolls(String strManyRolls) {
        this.strManyRolls = strManyRolls;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
