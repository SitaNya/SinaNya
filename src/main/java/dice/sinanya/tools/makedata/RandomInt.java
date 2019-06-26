package dice.sinanya.tools.makedata;

import java.util.Random;

import static java.lang.Math.ceil;
import static java.lang.Math.max;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 最重要的随机数生成类，骰娘的所有随机数都取决于这里
 * <p>
 * 这里为了效果，没有使用单纯的随机数，而是微正态分布
 * <p>
 * 也就是说，不是以绝对公平的随机为准
 * 而是以千次骰点成功率符合跑团预期为准
 */
public class RandomInt {
    /**
     * 尝试了系统随机数和梅森旋转
     * 梅森旋转就他娘的是个憨憨！
     * 当然也可能是我没调好
     * 但最终系统随机数+高斯分布的均值+2方差结果较为满意
     * <p>
     * rc的千次骰掷大成功/大失败概率稳定在0.8%-1.2%左右
     * ra的千次骰掷大成功/大失败概率稳定在4-6%左右
     * <p>
     * 由于需要用到均值，采用最高减最低向上取整并最低为1的计算方式
     *
     * @param lowest  最低值
     * @param highest 最高值
     * @return 随机数结果
     */
    public static int random(int lowest, int highest) {
        int result;
        int times = 0;
        do {
            Random r = new Random();
            int mean = max((int) ceil((highest - lowest) / 2.0), 1);
            result = (int) ((mean + 2) * r.nextGaussian() + mean);
            times++;
        } while ((result > highest || result < lowest) && times < 50);
        if (times >= 50) {
            result = overRandom(lowest, highest);
        }
        return result;
    }

    private static int overRandom(int lowest, int highest) {
        int result = 0;
        while (result == 0) {
            result = new Random().nextInt(highest + 1 - lowest) + lowest;
        }
        return result;
    }
}
