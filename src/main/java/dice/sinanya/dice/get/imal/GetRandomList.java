package dice.sinanya.dice.get.imal;

import java.util.ArrayList;
import java.util.HashMap;

import static dice.sinanya.tools.getinfo.RandomInt.random;
import static java.lang.Math.min;

/**
 * @author SitaNya
 * 日期: 2019-06-16
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 随机取列表的接口，实现此接口便可以随机从一个静态的字符串列表中取出一个字符串元素
 */
public interface GetRandomList {

    /**
     * 随机取字符串列表中的某一个字符串元素返回
     *
     * @param infoList 信息字符串
     * @return 字符串中的某一个字符串元素
     */
    default String randomFromList(ArrayList<String> infoList) {
        return infoList.get(random(0, infoList.size() - 1));
    }

    /**
     * 随机取字符串列表中的某一个字符串元素返回，这和上面那个方法的不同是，会有一定几率取到靠前的元素
     *
     * @param infoList 信息字符串
     * @return 字符串中的某一个字符串元素
     */
    default String randomFromListSmall(ArrayList<String> infoList) {
        int random = random(1, 3);
        return infoList.get(random(0, min(1, (infoList.size() - random))));
    }

    /**
     * 随机取字符串列表中的某一个字符串元素返回，这和上面那个方法的不同是，会有一定几率取到靠前的元素
     *
     * @param infoList 信息字符串
     * @return 字符串中的某一个字符串元素
     */
    default String randomNestList(HashMap<Integer, ArrayList<String>> infoList) {
        int random = random(0, infoList.size() - 1);
        ArrayList<String> detail = infoList.get(random);
        return detail.get(random(0, detail.size() - 1));
    }
}
