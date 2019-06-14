package dice.sinanya.tools.getinfo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static dice.sinanya.tools.getinfo.MakeSkillName.makeSkillName;

/**
 * @author SitaNya
 * 日期: 2019-06-14
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:将传入的msg人物属性信息整理包装为HashMap格式
 */
public class GetMessagesToValue {
    private static final Logger Log = LogManager.getLogger(GetMessagesToValue.class);

    public static HashMap<String, Integer> getMessagesToValue(HashMap<String, Integer> propertiesDefault, String msg) {
        StringBuilder strSkillValue = new StringBuilder();
        StringBuilder strSkillName = new StringBuilder();
        for (int i = 0; i < msg.length(); ) {
            while (i < msg.length() && !Character.isSpaceChar(msg.charAt(i)) &&
                    !Character.isDigit(msg.charAt(i)) &&
                    msg.charAt(i) != ':' &&
                    msg.charAt(i) != '=') {
                strSkillName.append(msg.charAt(i));
                i++;
            }

            while (i < msg.length() && Character.isDigit(msg.charAt(i))) {
                strSkillValue.append(msg.charAt(i));
                i++;
            }

            try {
                propertiesDefault.put(makeSkillName(strSkillName.toString()), Integer.parseInt(strSkillValue.toString()));
                strSkillName = new StringBuilder();
                strSkillValue = new StringBuilder();
            } catch (NumberFormatException e) {
                Log.error(e.getMessage(), e);
            }
        }
        return propertiesDefault;
    }
}
