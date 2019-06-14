package dice.sinanya.tools;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.concurrent.*;

import static dice.sinanya.tools.GetMaxNumsResult.getMaxNumsResult;
import static dice.sinanya.tools.RandomInt.random;

public class ManyRolls {

    public static String manyRollsProcess(int times, int rolls, int maxNums) {
        if (maxNums == 0) {
            maxNums = times;
        }
        int maxTimesRolls = 10;
        StringBuilder stringBuilder = new StringBuilder(200);
        int intResult = 0;
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > maxTimesRolls) {
            ArrayList<Future<Integer>> results = new ArrayList<>();

            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("manyRolls-%d").build();
            ExecutorService exec = new ThreadPoolExecutor(times, times, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
            for (int i = 0; i < times; i++) {
                results.add(exec.submit(new MakeManyRollsInThread(rolls)));
                //submit返回一个Future，代表了即将要返回的结果
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
            for (int intTmp : getMaxNumsResult(tmp, maxNums)) {
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
            for (int tmpRandom : getMaxNumsResult(tmp, maxNums)) {
                stringBuilder.append(tmpRandom);
                if (i != (maxNums - 1)) {
                    stringBuilder.append("+");
                }
                intResult += tmpRandom;
                i++;
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    static int manyRollsForInt(int times, int rolls, int maxNums) {
        if (maxNums == 0) {
            maxNums = times;
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
            for (int tmpRandom : getMaxNumsResult(tmp, maxNums)) {
                intResult += tmpRandom;
            }
            return intResult;
        } else {
            return 0;
        }
    }
}
