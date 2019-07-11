package dice.sinanya.entity.imal;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 获取DB加值，默认方法为coc7版
 */
public interface GetDb {
    int SIZE_LEVEL1 = 2;
    int SIZE_LEVEL2 = 64;
    int SIZE_LEVEL3 = 84;
    int SIZE_LEVEL4 = 124;
    int SIZE_LEVEL5 = 164;
    int SIZE_LEVEL6 = 204;

    /**
     * 获取db加值字符串
     *
     * @param a 传入数字a为力量与体型之和
     * @return 返回DB加值字符串
     */
    default String dbGetter(int a) {

        if (a >= SIZE_LEVEL1 && a <= SIZE_LEVEL2) {
            return "-2";
        } else if (a <= SIZE_LEVEL3) {
            return "-1";
        } else if (a <= SIZE_LEVEL4) {
            return "0";
        } else if (a <= SIZE_LEVEL5) {
            return "1d4";
        } else if (a <= SIZE_LEVEL6) {
            return "1d6";
        } else {
            return "计算错误";
        }
    }
}
