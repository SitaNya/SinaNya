package dice.sinanya.tools.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger Log = LogManager.getLogger(LogSave.class);

    /**
     * 将格式化后的全部日志信息存储到相对路径"bin/../saveLogs/${groupId}/${logName}"下
     *
     * @param groupId 群号
     * @param logName 日志名
     * @param info    格式化后的全部日志信息
     */
    public static void logSave(String groupId, String logName, String info) {
        // 1：利用File类找到要操作的对象
        File file = new File("../saveLogs/" + groupId + "/" + logName);

        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                Log.error("上层目录未能创建");
            }
        }

        //2：准备输出流
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "GBK"));
            out.write(info);
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }
}
