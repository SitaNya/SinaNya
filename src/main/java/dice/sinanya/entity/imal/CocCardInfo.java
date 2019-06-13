package dice.sinanya.entity.imal;

import dice.sinanya.tools.Calculator;

import static dice.sinanya.tools.ManyRolls.manyRollsProcess;
import static java.lang.Math.ceil;

/**
 * 获取coc人物卡的一些共有数据
 *
 * @author zhangxiaozhou
 */
public class CocCardInfo {

    protected int str = get3d6multiply();
    protected int con = get3d6multiply();
    protected int siz = get2d6plus6multiply();
    protected int dex = get3d6multiply();
    protected int app = get3d6multiply();
    protected int intValue = get2d6plus6multiply();
    protected int pow = get3d6multiply();
    protected int edu = get2d6plus6multiply();

    protected int notLuck = str + con + siz + dex + app + intValue + pow + edu;

    private int get2d6plus6multiply() {
        return (int) (ceil(Calculator.conversion(manyRollsProcess(2, 6, 0))) + 6);
    }

    protected int get3d6multiply() {
        return (int) ceil(Calculator.conversion(manyRollsProcess(3, 6, 0)));
    }

    public int getStr() {
        return str;
    }

    public int getCon() {
        return con;
    }

    public int getSiz() {
        return siz;
    }

    public int getDex() {
        return dex;
    }

    public int getApp() {
        return app;
    }

    public int getInt() {
        return intValue;
    }

    public int getPow() {
        return pow;
    }

    public int getEdu() {
        return edu;
    }

    public int getNotLuck() {
        return notLuck;
    }
}
