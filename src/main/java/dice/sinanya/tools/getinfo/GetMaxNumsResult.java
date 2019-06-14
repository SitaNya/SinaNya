package dice.sinanya.tools.getinfo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author SitaNya
 */
public class GetMaxNumsResult {

    public static ArrayList<Integer> getMaxNumsResult(ArrayList<Integer> input, int maxNums) {
        Collections.sort(input);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = input.size() - 1; i >= (input.size() - maxNums); i--) {
            result.add(input.get(i));
        }
        return result;
    }
}
