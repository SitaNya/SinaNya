package dice.sinanya.tools;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static dice.sinanya.tools.RandomInt.random;

public class ManyRolls {

    public static String manyRolls(int times, int rolls) {
        int maxTimesRolls = 10;
        StringBuilder stringBuilder = new StringBuilder(200);
        int intResult = 0;
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > maxTimesRolls) {
            for (int i = 0; i < times; i++) {
                intResult += random(1, rolls);
            }
            stringBuilder.append(intResult);
        } else if (times > 1) {
            for (int i = 0; i < times; i++) {
                int tmpRandom = random(1, rolls);
                stringBuilder.append(tmpRandom);
                if (i != (times - 1)) {
                    stringBuilder.append("+");
                }
                intResult += tmpRandom;
            }
            stringBuilder.append("=").append(intResult);
        }
        return stringBuilder.toString();
    }

    public static String manyRollsProcess(int times, int rolls) {
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
            for (Future future : results) {
                while (!future.isDone()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    intResult += (int) future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            exec.shutdown();
            stringBuilder.append(intResult);
        } else if (times > 1) {
            stringBuilder.append("(");
            for (int i = 0; i < times; i++) {
                int tmpRandom = random(1, rolls);
                stringBuilder.append(tmpRandom);
                if (i != (times - 1)) {
                    stringBuilder.append("+");
                }
                intResult += tmpRandom;
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    public static int manyRollsForInt(int times, int rolls) {
        int maxTimesRolls = 10;
        int intResult = 0;
        if (times == 1) {
            return random(1, rolls);
        } else if (times > maxTimesRolls) {
            for (int i = 0; i < times; i++) {
                intResult += random(1, rolls);
            }
            return intResult;
        } else if (times > 1) {
            for (int i = 0; i < times; i++) {
                intResult += random(1, rolls);
            }
            return intResult;
        } else {
            return 0;
        }
    }
}
