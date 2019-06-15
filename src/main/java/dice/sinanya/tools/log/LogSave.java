package dice.sinanya.tools.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

public class LogSave {
    private static final Logger Log = LogManager.getLogger(LogSave.class);

    public static void logSave(String groupId, String logName, String info) {
        // 1：利用File类找到要操作的对象
        File file = new File("./saveLogs" + groupId + "/" + logName);

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
