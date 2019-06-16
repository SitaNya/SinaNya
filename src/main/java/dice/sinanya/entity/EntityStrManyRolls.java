package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 骰点过程包装类，包含最终的结果和骰点表达式
 */
public class EntityStrManyRolls {
    /**
     * @param strFunction 字符串表达式，如:3d6k2+4d6*3+d4/2-6d
     * @param strResult 数字结果表达式，如:(1+2)+(1+3+4+6)*3+(2)/2-(32+35+12+54)
     * @param result 实际结果，如:-87
     */
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
