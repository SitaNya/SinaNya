package dice.sinanya.tools.makedata;

import static dice.sinanya.tools.makedata.RandomInt.random;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: ral、rcl使用的多次骰点多线程类
 */
public class MakeManyRollsInThread {

    private int maxValue;

    MakeManyRollsInThread(int maxValue) {
        this.maxValue = maxValue;
    }

    public Integer call() {
        return random(1, maxValue);
    }
}
