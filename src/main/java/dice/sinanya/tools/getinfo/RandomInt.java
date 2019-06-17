package dice.sinanya.tools.getinfo;

import dice.sinanya.tools.makedata.MersenneTwister;

/**
 * @author SitaNya
 */
public class RandomInt {
    public static int random(int lowest, int highest) {
        int result = 0;
        while (result == 0) {
            MersenneTwister mersenneTwister=new MersenneTwister();
            result = mersenneTwister.nextInt(highest + 1 - lowest) + lowest;
        }
        return result;
    }
}
