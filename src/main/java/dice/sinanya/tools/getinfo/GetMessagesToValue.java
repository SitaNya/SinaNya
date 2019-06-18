package dice.sinanya.tools.getinfo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static dice.sinanya.system.MessagesSystem.NONE;
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
class GetMessagesToValue {
    private static final Logger Log = LogManager.getLogger(GetMessagesToValue.class);

    /**
     * 将输入的属性值字符串转变为HashMap形式
     *
     * @param properties 属性值的HashMap列表，包含了所有技能和技能值。如果是新卡则所有技能都是默认值
     * @param msg 属性值字符串
     * @return 新生成的HashMap属性值列表
     */
    static HashMap<String, Integer> getMessagesToValue(HashMap<String, Integer> properties, String msg) {
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
                if (!strSkillValue.toString().equals(NONE)) {
                    properties.put(makeSkillName(strSkillName.toString()), Integer.parseInt(strSkillValue.toString()));
                    strSkillName = new StringBuilder();
                    strSkillValue = new StringBuilder();
                }
            } catch (NumberFormatException e) {
                Log.error(e.getMessage(), e);
            }
        }
        return properties;
    }
}
