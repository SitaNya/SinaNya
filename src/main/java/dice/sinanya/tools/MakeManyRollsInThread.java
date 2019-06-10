package dice.sinanya.tools;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.DBAndSize.dbGetter6;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;
import static dice.sinanya.tools.RandomInt.random;
import static java.lang.Math.ceil;

public class MakeManyRollsInThread implements Callable<Integer> {

    int maxValue;
    public MakeManyRollsInThread(int maxValue) {
        this.maxValue=maxValue;
    }

    @Override
    public Integer call() {
        return random(1, maxValue);
    }
}
