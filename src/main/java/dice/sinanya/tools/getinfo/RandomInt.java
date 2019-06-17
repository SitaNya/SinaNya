package dice.sinanya.tools.getinfo;

import java.util.Random;

import static java.lang.Math.ceil;
import static java.lang.Math.max;

/**
 * @author SitaNya
 */
public class RandomInt {
    /**
     * 尝试了系统随机数和梅森旋转
     * 梅森旋转就他娘的是个憨憨！
     * 当然也可能是我没调好
     * 但最终系统随机数+高斯分布的均值+2方差结果较为满意
     *
     * rc的千次骰掷大成功/大失败概率稳定在0.8%-1.2%左右
     * ra的千次骰掷大成功/大失败概率稳定在4-6%左右
     *
     * 由于需要用到均值，采用最高减最低向上取整并最低为1的计算方式
     *
     * @param lowest  最低值
     * @param highest 最高值
     * @return 随机数结果
     */
    public static int random(int lowest, int highest) {
        int result;
        do {
            Random r = new Random();
            int mean = max((int) ceil((highest - lowest) / 2.0), 1);
            result = (int) ((mean+2) * r.nextGaussian() + mean);
        } while (result > highest || result < lowest);
        return result;
    }
}
