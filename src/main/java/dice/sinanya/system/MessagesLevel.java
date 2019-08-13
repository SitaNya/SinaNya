package dice.sinanya.system;

import java.util.ArrayList;
import java.util.Arrays;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;

/**
 * @author SitaNya
 * 日期: 2019-07-11
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public enum MessagesLevel {

    /**
     * @param STR_CRITICAL_SUCCESS 大成功
     * @param STR_EXTREME_SUCCESS 极难成功
     * @param STR_HARD_SUCCESS 困难成功
     * @param STR_SUCCESS 成功
     * @param STR_FAILURE 失败
     * @param STR_FUMBLE 大失败
     */
    STR_CRITICAL_SUCCESS("STR_CRITICAL_SUCCESS", new ArrayList<String>(Arrays.asList(entitySystemProperties.getCRITICAL_SUCCESS().split("\\|")))),
    STR_EXTREME_SUCCESS("STR_EXTREME_SUCCESS", new ArrayList<>(Arrays.asList(entitySystemProperties.getEXTREME_SUCCESS().split("\\|")))),
    STR_HARD_SUCCESS("STR_HARD_SUCCESS", new ArrayList<>(Arrays.asList(entitySystemProperties.getHARD_SUCCESS().split("\\|")))),
    STR_SUCCESS("STR_SUCCESS", new ArrayList<>(Arrays.asList(entitySystemProperties.getSUCCESS().split("\\|")))),
    STR_FAILURE("STR_FAILURE", new ArrayList<>(Arrays.asList(entitySystemProperties.getFAILURE().split("\\|")))),
    STR_FUMBLE("STR_FUMBLE", new ArrayList<>(Arrays.asList(entitySystemProperties.getFUMBLE().split("\\|"))));
    private String levelName;
    private ArrayList<String> text;

    private MessagesLevel(String levelName, ArrayList<String> text) {
        this.levelName = levelName;
        this.text = text;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(String input) {
        this.text = new ArrayList<>(Arrays.asList(input.split("\\|")));
    }
}
