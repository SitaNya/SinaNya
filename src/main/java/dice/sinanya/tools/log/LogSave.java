package dice.sinanya.tools.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

import static dice.sinanya.system.MessagesSystem.*;

public class LogSave {
    private static final Logger Log = LogManager.getLogger(LogSave.class);

    public static void logSave(String groupId, String logName, String info) {
        // 1：利用File类找到要操作的对象
        File file = null;
        if (OSX_MODEL) {
            if (new File("/Users/SitaNya/Desktop/").exists()) {
                file = new File("/Users/SitaNya/Desktop/" + groupId + "/" + logName);
            }else{
                file=new File("/Users/zhangxiaozhou//Desktop/" + groupId + "/" + logName);
            }
        } else if (WIN_MODEL) {
            file = new File("C:/Files/" + groupId + "/" + logName);
        } else if (LINUX_MODEL) {
            file = new File("/tmp/" + groupId + "/" + logName);
        }

        assert file != null;
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
