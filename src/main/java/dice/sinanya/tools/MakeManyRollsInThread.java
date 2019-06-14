package dice.sinanya.tools;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.RandomInt.random;

public class MakeManyRollsInThread implements Callable<Integer> {

    private int maxValue;

    MakeManyRollsInThread(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public Integer call() {
        return random(1, maxValue);
    }
}
