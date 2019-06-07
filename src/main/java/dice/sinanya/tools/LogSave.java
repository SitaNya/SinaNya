package dice.sinanya.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class LogSave {

    public static void logSave(String groupId, String logName, String info) {
        // 1：利用File类找到要操作的对象
        File file = new File("/Users/zhangxiaozhou/Desktop/" + groupId + "/" + logName);
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
