package dice.sinanya.tools;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author SitaNya
 */
class GetMaxNumsResult {

    static ArrayList<Integer> getMaxNumsResult(ArrayList<Integer> input, int maxNums) {
        Collections.sort(input);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = input.size() - 1; i >= (input.size() - maxNums); i--) {
            result.add(input.get(i));
        }
        return result;
    }
}
