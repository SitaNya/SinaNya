package dice.sinanya.tools;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 将多线程的Future列表转化为String字符串结果
 *
 * @author SitaNya
 */
public class GetFutureToString {
    public static String getFutureToString(StringBuilder stringBuilder,ArrayList<Future<String>> results) {
        for (Future future : results) {
            while (!future.isDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                stringBuilder.append(future.get());
                stringBuilder.append("\n");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
