package dice.sinanya.tools;

import java.util.Random;

/**
 * @author zhangxiaozhou
 */
public class RandomInt {
    public static int random(int lowest, int highest) {
        int result = 0;
        while (result == 0) {
            result = new Random().nextInt(highest + 1 - lowest) + lowest;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
