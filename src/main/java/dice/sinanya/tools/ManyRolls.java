package dice.sinanya.tools;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static dice.sinanya.tools.GetMaxKTimes.getMaxKTimes;
import static dice.sinanya.tools.RandomInt.random;

public class ManyRolls {

    public static String manyRollsProcess(int times, int rolls, int Ktimes) {
        if (Ktimes == 0) {
            Ktimes = times;
        }
        int maxTimesRolls = 10;
        StringBuilder stringBuilder = new StringBuilder(200);
        int intResult = 0;
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > maxTimesRolls) {
            ExecutorService exec = Executors.newCachedThreadPool();//工头
            ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();//
            for (int i = 0; i < times; i++) {
                results.add(exec.submit(new MakeManyRollsInThread(rolls)));//submit返回一个Future，代表了即将要返回的结果
            }
            ArrayList<Integer> tmp = new ArrayList<>();
            for (Future future : results) {
                while (!future.isDone()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    tmp.add((int) future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            for (int intTmp : getMaxKTimes(tmp, Ktimes)) {
                intResult += intTmp;
            }
            exec.shutdown();
            stringBuilder.append(intResult);
        } else if (times > 1) {
            stringBuilder.append("(");
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < times; i++) {
                tmp.add(random(1, rolls));
            }
            int i = 0;
            for (int tmpRandom : getMaxKTimes(tmp, Ktimes)) {
                stringBuilder.append(tmpRandom);
                if (i != (Ktimes - 1)) {
                    stringBuilder.append("+");
                }
                intResult += tmpRandom;
                i++;
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    public static int manyRollsForInt(int times, int rolls, int Ktimes) {
        if (Ktimes == 0) {
            Ktimes = times;
        }
        int maxTimesRolls = 10;
        int intResult = 0;
        if (times == 1) {
            return random(1, rolls);
        } else if (times > maxTimesRolls) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < times; i++) {
                tmp.add(random(1, rolls));
            }
            for (int tmpRandom : getMaxKTimes(tmp, Ktimes)) {
                intResult += tmpRandom;
            }
            return intResult;
        } else {
            return 0;
        }
    }
}
