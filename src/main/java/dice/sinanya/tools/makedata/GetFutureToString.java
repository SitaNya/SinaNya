package dice.sinanya.tools.makedata;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 将多线程的Future列表转化为String字符串结果
 *
 * @author SitaNya
 */
public class GetFutureToString {
    private static Logger log = LogManager.getLogger(GetFutureToString.class.getName());
    public static String getFutureToString(StringBuilder stringBuilder, ArrayList<Future<String>> results) {
        for (Future future : results) {
            while (!future.isDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            try {
                stringBuilder.append(future.get());
                stringBuilder.append("\n");
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage(), e);
            }
        }
        return stringBuilder.toString();
    }
}
