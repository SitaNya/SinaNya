package dice.sinanya.tools.makedata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 将多线程的Future列表转化为String字符串结果
 *
 * @author SitaNya
 */

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 将多线程的Future列表转化为String字符串结果
 */
public class GetFutureToString {
    private static Logger log = LogManager.getLogger(GetFutureToString.class.getName());

    /**
     * 从Future列表中拿到结果并拼装成结果字符串
     *
     * @param stringBuilder StringBuilder对象，用于拼装结果字符串
     * @param results       包含Future的列表，这个Future是一种异步多线程对象，每个Future代表了一个还没执行完的线程，可以从里面拿到结果
     * @return 格式化完毕的字符串
     */
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
