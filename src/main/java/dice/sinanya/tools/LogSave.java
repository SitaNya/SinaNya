package dice.sinanya.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static dice.sinanya.system.MessagesSystem.*;

public class LogSave {

    public static void logSave(String groupId, String logName, String info) {
        // 1：利用File类找到要操作的对象
        File file = null;
        if (OSX_MODEL) {
            file = new File("/Users/zhangxiaozhou/Desktop/" + groupId + "/" + logName);
        } else if (WIN_MODEL) {
            file = new File("C:/Files/" + groupId + "/" + logName);
        } else if (LINUX_MODEL) {
            file = new File("/tmp/" + groupId + "/" + logName);
        }

        assert file != null;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //2：准备输出流
        Writer out;
        try {
            out = new FileWriter(file);
            out.write(info);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
