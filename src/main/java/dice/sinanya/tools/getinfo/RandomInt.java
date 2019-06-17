package dice.sinanya.tools.getinfo;

import dice.sinanya.tools.makedata.MersenneTwister;
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
            MersenneTwister mersenneTwister=new MersenneTwister();
            result = mersenneTwister.nextInt(highest + 1 - lowest) + lowest;
        }
        return result;
    }
}
