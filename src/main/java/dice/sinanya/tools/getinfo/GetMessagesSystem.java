package dice.sinanya.tools.getinfo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        MESSAGES_SYSTEM.put("botStart", "机器人已开启");
        MESSAGES_SYSTEM.put("botAlreadyStart", "机器人当前处于开启状态");
        MESSAGES_SYSTEM.put("botStop", "机器人已关闭");
        MESSAGES_SYSTEM.put("botAlreadyStop", "机器人当前处于关闭状态");
        MESSAGES_SYSTEM.put("botExit", "正在退出群");
        MESSAGES_SYSTEM.put("botInfo", "");

        MESSAGES_SYSTEM.put("bookCard", "COC七版规则空白卡奈梅斯·西莉亚私人订制版By贝尔巨佬.xlsx\n请使用此链接下载https://pan.baidu.com/s/1M3veskXYFJjwXP1eKHaX4g。有更新更好的版本请随时联系窝。");
        MESSAGES_SYSTEM.put("bookRP", "角色扮演三百六十五问.zip\n请使用此链接下载https://share1.heiluo.com/share/link/8b93f1f15a974d4a9fda1890863a0af1。有更新更好的版本请随时联系窝。");
        MESSAGES_SYSTEM.put("bookKp", "克苏鲁的呼唤第七版守秘人规则书 Version1901.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/e88daa8d6565440cbff0a8f7c9c8fe29。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/12XSQc9EEBsfEhhQEesc6nw");
        MESSAGES_SYSTEM.put("bookMake", "车卡指南.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/0e1083cdb8144b109be07fd4ef09b082。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/1HHo1B1F9kMRIFDB8J9Ulww 提取码：ql5h");

        MESSAGES_SYSTEM.put("manyRollsFormat", "请按照\".ral 值 次数\"的格式输入");
        MESSAGES_SYSTEM.put("diceTimesTooBig", "骰点次数过多");

        MESSAGES_SYSTEM.put("setPropFormat", "请符合格式.st角色名-力量50体质60");
        MESSAGES_SYSTEM.put("setHelp", ".st命令使用错误，请使用.help make命令查看具体用法");
        MESSAGES_SYSTEM.put("NotFoundSkill", "您未设置角色卡");

        MESSAGES_SYSTEM.put("dndInitIsEmtpy", "先攻列表为空");

        MESSAGES_SYSTEM.put("needKpGroup", "未设置kp群");

        MESSAGES_SYSTEM.put("can'tInPrivate", "此命令私聊不可用");
        MESSAGES_SYSTEM.put("onlyManager", "此命令仅群主或管理员可以使用");

        MESSAGES_SYSTEM.put("alreadyOpen", "日志已经处于开启状态，无法再次开启");
        MESSAGES_SYSTEM.put("alreadyClose", "日志已经处于关闭状态，无法再次关闭");
        MESSAGES_SYSTEM.put("notFoundLog", "未找到日志");
        MESSAGES_SYSTEM.put("readLock", "正在有人取日志，请稍后");
        MESSAGES_SYSTEM.put("deleteOpenLog", "无法删除打开的日志，请先关闭");

        MESSAGES_SYSTEM.put("sanCheck", "请符合1/1d3|1d6/1d3|0/1这样的san check格式");

        MESSAGES_SYSTEM.put("setPropSuccess", "已记录人物卡");

        MESSAGES_SYSTEM.put("clrDndInit", "清空先攻列表");

        MESSAGES_SYSTEM.put("antagonizeOver", "对抗结束");
        MESSAGES_SYSTEM.put("antagonizeFirstSuccess", "先手胜利");
        MESSAGES_SYSTEM.put("antagonizeSecondSuccess", "后手胜利");
        MESSAGES_SYSTEM.put("antagonizeAllFailed", "全部失败");
        MESSAGES_SYSTEM.put("antagonizeDraw", "平手");

        MESSAGES_SYSTEM.put("symptom", "");

        MESSAGES_SYSTEM.put("enSuccess", "");
        MESSAGES_SYSTEM.put("enFailed", "");

        MESSAGES_SYSTEM.put("hiddenDice", "kp正在进行暗骰");

        MESSAGES_SYSTEM.put("teamIsEmpty", "您的小队为空");
        MESSAGES_SYSTEM.put("teamMemberEnIsEmpty", "的技能成功记录为空");


        MESSAGES_SYSTEM.put("appendLog", "日志已重新开启，将在原基础上追加");
        MESSAGES_SYSTEM.put("createLog", "日志已创建");
        MESSAGES_SYSTEM.put("CantEmptyLogName", "不支持空日志名");

        MESSAGES_SYSTEM.put("sanCheckFumble", "");
        MESSAGES_SYSTEM.put("sanCheckCriticalSuccess", "");
        MESSAGES_SYSTEM.put("sanCheckSuccess", "");
        MESSAGES_SYSTEM.put("sanCheckFailure", "");

        MESSAGES_SYSTEM.put("mailUserName", "2730902267@qq.com");
        MESSAGES_SYSTEM.put("mailPassword", "kktjwuakdafbdcej");
        MESSAGES_SYSTEM.put("masterMail", "450609203@qq.com");
        MESSAGES_SYSTEM.put("serverPort", "80");
        MESSAGES_SYSTEM.put("javaPort", "9999");
        MESSAGES_SYSTEM.put("hostIp", "127.0.0.1");

        MESSAGES_SYSTEM.put("CRITICAL_SUCCESS", "");
        MESSAGES_SYSTEM.put("EXTREME_SUCCESS", "");
        MESSAGES_SYSTEM.put("HARD_SUCCESS", "");
        MESSAGES_SYSTEM.put("SUCCESS", "");
        MESSAGES_SYSTEM.put("FAILURE", "");
        MESSAGES_SYSTEM.put("FUMBLE", "");

        MESSAGES_SYSTEM.put("PrometheusPort", "1234");
    }

    private GetMessagesSystem() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 读取配置文件，默认从bin目录的上一层找conf目录，然后找sinanya.properties文件，也就是说如果启动时不在bin目录，可能会找不到文件
     */
    public static void initMessagesSystem() {
        Properties prop = new Properties();
        File file = new File("../conf/sinanya.properties");
        if (!file.exists()) {
            file = new File("src/main/resources/sinanya.properties");
        }
        try (
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(isr);
        ) {
            prop.load(bufferedReader);
        } catch (IOException e) {
            log.error(file.getAbsolutePath());
            log.error(e.getMessage(), e);
        }

        for (String propertyNames : prop.stringPropertyNames()) {
            if (prop.containsKey(propertyNames)) {
                MESSAGES_SYSTEM.put(propertyNames, prop.getProperty(propertyNames));
            }
        }
    }
}
