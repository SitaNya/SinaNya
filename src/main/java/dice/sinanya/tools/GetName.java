package dice.sinanya.tools;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesName.*;
import static dice.sinanya.tools.RandomInt.random;

/**
 * 作者: SitaNya
 * 日期: 2019-06-14
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 接口说明:
 */
public class GetName {

    private static String getChineseName() {
        return getFromList(ChineseSurname) + getFromList(ChineseFirstName);
    }

    private static String getEnglishName() {
        return getFromList(EnglishFirstName) + getFromList(EnglishLastName) + "(" + getFromList(EnglishFirstNameChineseTranslation) + getFromList(EnglishLastNameChineseTranslation) + ")";
    }

    private static String getJapaneseName() {
        return getFromList(JapaneseSurname) + getFromList(JapaneseFirstName);
    }

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

    private static String getFromList(ArrayList<String> list) {
        return list.get(random(0, list.size() - 1));
    }
}
