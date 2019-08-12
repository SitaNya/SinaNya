package dice.sinanya.tools.getinfo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 设定默认回复语，并读取配置文件中的回复语
 */
public class GetMessagesSystem {

    /**
     * 各种回复的默认值，保证配置文件里写错或者删掉了，也不会报错
     */
    public final static Map<String, String> MESSAGES_SYSTEM = new HashMap<>();
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager.getLogger(GetMessagesSystem.class.getName());

    static {

    }

    private GetMessagesSystem() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 读取配置文件，默认从bin目录的上一层找conf目录，然后找sinanya.properties文件，也就是说如果启动时不在bin目录，可能会找不到文件
     */
    public static void initMessagesSystem() {
        Properties prop = new Properties();
        File file = new File(MESSAGES_SYSTEM.get("system_dir")+"/saveLogs/sinanya.properties");
        try (
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(isr);
        ) {
            prop.load(bufferedReader);
        } catch (IOException e) {
            log.error(file.getAbsolutePath());
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }

        for (String propertyNames : prop.stringPropertyNames()) {
            if (prop.containsKey(propertyNames)) {
                MESSAGES_SYSTEM.put(propertyNames, prop.getProperty(propertyNames));
            }
        }
    }
}
