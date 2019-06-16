package dice.sinanya.tools.getinfo;

import dice.sinanya.dice.get.imal.GetRandomList;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesName.*;
import static dice.sinanya.tools.getinfo.RandomInt.random;

/**
 * @author  SitaNya
 * 日期: 2019-06-14
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明: 获取人名，不是指玩家，而是NPC名称
 */
public class GetName {

    /**
     * @return 中文人名
     */
    private static String getChineseName() {
        return getFromList(ChineseSurname) + getFromList(ChineseFirstName);
    }

    /**
     * @return 英文人名（含有中文翻译）
     */
    private static String getEnglishName() {
        return getFromList(EnglishFirstName) + "·" + getFromList(EnglishLastName) + "(" + getFromList(EnglishFirstNameChineseTranslation) + "·" + getFromList(EnglishLastNameChineseTranslation) + ")";
    }

    /**
     * @return 日本人名（中间带有空格）
     */
    private static String getJapaneseName() {
        return getFromList(JapaneseSurname) + " " + getFromList(JapaneseFirstName);
    }

    /**
     * @return 随机人名
     */
    public static String getRandomName() {
        int random = random(1, 3);
        switch (random) {
            case 1:
                return getChineseName();
            case 3:
                return getJapaneseName();
            default:
                return getEnglishName();
        }
    }

    /**
     * 随机返回字符串列表中的一个元素，其实和GetRandomList接口的方法一样，但毕竟这里是静态的
     *
     * @param list 输入的字符串列表
     * @return 随机返回字符串列表中的一个元素
     */
    private static String getFromList(ArrayList<String> list) {
        return list.get(random(0, list.size() - 1));
    }
}
