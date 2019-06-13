package dice.sinanya.dice.get.imal;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;

public class MakeCard {
    int times = 1;

    protected int getTime(String msg) {
        if (isNumeric(msg)) {
            this.times = Integer.parseInt(msg);
        }
        return times;
    }
}
