package dice.sinanya.entity;

/**
 * 骰点过程包装类，包含最终的结果和骰点表达式
 * @author SitaNya
 */
public class EntityStrManyRolls {
    private String strFunction;
    private String strResult;
    private int result;

    public EntityStrManyRolls(String strFunction, String strResult, int result) {
        this.strFunction = strFunction;
        this.strResult = strResult;
        this.result = result;
    }

    public String getStrFunction() {
        return strFunction;
    }

    public void setStrFunction(String strFunction) {
        this.strFunction = strFunction;
    }

    public String getStrResult() {
        return strResult;
    }

    public void setStrResult(String strResult) {
        this.strResult = strResult;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
