package dice.sinanya.system;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class GetMessagesSystem {
    private static Logger log = LogManager.getLogger(GetMessagesSystem.class.getName());

    public static HashMap<String, String> messagesSystem = new HashMap<String, String>() {{
        put("botStart", "机器人已开启");
        put("botAlreadyStart", "机器人当前处于开启状态");
        put("botStop", "机器人已关闭");
        put("botAlreadyStop", "机器人当前处于关闭状态");
        put("botExit", "正在退出群");
    }};

    public static void initMessagesSystem() {
        Properties prop = new Properties();
        try {
            File file = new File("./sinanya.properties");
            if (!file.exists()) {
                file = new File("src/main/resources/sinanya.properties");
            }
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(isr);
            prop.load(bufferedReader);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        for (String propertyNames : prop.stringPropertyNames()) {
            if (prop.containsKey(propertyNames)) {
                messagesSystem.put(propertyNames, prop.getProperty(propertyNames));
            }
        }
    }
}
