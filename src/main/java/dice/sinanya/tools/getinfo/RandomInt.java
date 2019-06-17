package dice.sinanya.tools.getinfo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * @author SitaNya
 */
public class RandomInt {
    private static Logger log = LogManager.getLogger(RandomInt.class.getName());
    public static int random(int lowest, int highest) {
        int result = 0;
        while (result == 0) {
            result = new Random().nextInt(highest + 1 - lowest) + lowest;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
