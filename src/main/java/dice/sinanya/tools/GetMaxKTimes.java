package dice.sinanya.tools;

import java.util.ArrayList;
import java.util.Collections;

public class GetMaxKTimes {

    public static ArrayList<Integer> getMaxKTimes(ArrayList<Integer> input, int Ktimes) {
        Collections.sort(input);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = input.size()-1; i >= (input.size()-Ktimes); i--) {
            result.add(input.get(i));
        }
        return result;
    }
}
