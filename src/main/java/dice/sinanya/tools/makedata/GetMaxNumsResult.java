package dice.sinanya.tools.makedata;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取指定个数的最大骰点结果（XDXK3中的3）
 */
class GetMaxNumsResult {

    /**
     * 给K参数的方法，取一系列骰点结果中的最大值
     *
     * @param input 所有骰点结果，如4d6可以投出1，2，5，4
     * @param maxNums 取其中最大几个值
     * @return 最终取出来的最大值列表
     */
    static ArrayList<Integer> getMaxNumsResult(ArrayList<Integer> input, int maxNums) {
        Collections.sort(input);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = input.size() - 1; i >= (input.size() - maxNums); i--) {
            result.add(input.get(i));
        }
        return result;
    }
}
