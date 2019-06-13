package dice.sinanya.entity.imal;

/**
 * 获取Coc7版信息卡的附加信息，比如伤害加值或体型等
 * 
 * @author zhangxiaozhou
 */
public interface GetCoc7Info extends GetDb{

    /**
     * 获取体型加值
     *
     * @param a 传入str与siz之和
     * @return  返回体型值
     */
    default int buildGetter(int a) {
        if (a >= SIZE_LEVEL1 && a <= SIZE_LEVEL2) {
            return -2;
        } else if (a <= SIZE_LEVEL3) {
            return -1;
        } else if (a <= SIZE_LEVEL4) {
            return 0;
        } else if (a <= SIZE_LEVEL5) {
            return 1;
        } else if (a <= SIZE_LEVEL6) {
            return 2;
        } else {
            return -100;
        }
    }

    /**
     * 根据传入的参数计算移动值
     *
     * @param str   力量
     * @param siz   体型
     * @param dex   敏捷
     * @return  移动值
     */
    default int movGetter(int str, int siz, int dex) {
        if (dex < siz && str < siz) {
            return 7;
        } else if (dex > siz && str > siz) {
            return 9;
        } else {
            return 8;
        }
    }
}
