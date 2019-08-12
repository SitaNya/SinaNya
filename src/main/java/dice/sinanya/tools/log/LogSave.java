package dice.sinanya.tools.log;

import org.apache.logging.log4j.LogManager;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import java.util.Arrays;

import java.io.*;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 日志存储落地类
 */
public class LogSave {


    private LogSave() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 将格式化后的全部日志信息存储到相对路径"bin/../saveLogs/${groupId}/${logName}"下
     *
     * @param groupId 群号
     * @param logName 日志名
     * @param info    格式化后的全部日志信息
     */
    public static void logSave(String groupId, String logName, String info) {
        // 1：利用File类找到要操作的对象
        File file = new File("../saveLogs/" + groupId + "/" + logName + ".txt");

        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            CQ.logError("日志存储异常","上层目录未能创建");
        }

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "GBK"));) {
            out.write(info);
        } catch (IOException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }
}
