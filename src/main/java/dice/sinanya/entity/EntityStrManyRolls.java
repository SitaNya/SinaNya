package dice.sinanya.entity;

/**
 * 骰点过程包装类，包含最终的结果和骰点表达式
 * @author SitaNya
 */
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
