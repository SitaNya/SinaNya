package dice.sinanya.system;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 骰点回复的静态信息
 * <p>
 * 用列表保存后可以实现随机回复词
 */
public interface MessagesLevel {


    ArrayList<String> STR_CRITICAL_SUCCESS = new ArrayList<>(Arrays.asList(messagesSystem.get("CRITICAL_SUCCESS").split("\\|")));

    ArrayList<String> STR_EXTREME_SUCCESS = new ArrayList<>(Arrays.asList(messagesSystem.get("EXTREME_SUCCESS").split("\\|")));

    ArrayList<String> STR_HARD_SUCCESS = new ArrayList<>(Arrays.asList(messagesSystem.get("HARD_SUCCESS").split("\\|")));

    ArrayList<String> STR_SUCCESS = new ArrayList<>(Arrays.asList(messagesSystem.get("SUCCESS").split("\\|")));

    ArrayList<String> STR_FAILURE = new ArrayList<>(Arrays.asList(messagesSystem.get("FAILURE").split("\\|")));

    ArrayList<String> STR_FUMBLE = new ArrayList<>(Arrays.asList(messagesSystem.get("FUMBLE").split("\\|")));

    HashMap<String, ArrayList<String>> LEVEL_MAP = new HashMap<String, ArrayList<String>>(6) {{
        put("STR_CRITICAL_SUCCESS", STR_CRITICAL_SUCCESS);
        put("STR_EXTREME_SUCCESS", STR_EXTREME_SUCCESS);
        put("STR_HARD_SUCCESS", STR_HARD_SUCCESS);
        put("STR_SUCCESS", STR_SUCCESS);
        put("STR_FAILURE", STR_FAILURE);
        put("STR_FUMBLE", STR_FUMBLE);
    }};
}
