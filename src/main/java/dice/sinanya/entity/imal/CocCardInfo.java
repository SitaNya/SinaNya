package dice.sinanya.entity.imal;

import dice.sinanya.tools.makedata.Calculator;

import static dice.sinanya.tools.makedata.ManyRolls.manyRollsProcess;
import static java.lang.Math.ceil;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取coc卡的一些共有数据，注意这里获取的数据是未经*5的coc6版本
 */
public class CocCardInfo {

    /**
     * @param str 力量
     * @param siz 体型
     * @param dex 敏捷
     * @param app 外貌
     * @param intValue 智力
     * @param pow 意志
     * @param edu 教育
     * @param notLuck 不含幸运总和
     */
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
