package dice.sinanya.tools.getinfo;

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
 * 类说明: 设定默认回复语，并读取配置文件中的回复语
 */
public class GetMessagesSystem {
    private static Logger log = LogManager.getLogger(GetMessagesSystem.class.getName());

    /**
     * 各种回复的默认值，保证配置文件里写错或者删掉了，也不会报错
     */
    public static HashMap<String, String> messagesSystem = new HashMap<String, String>() {{
        put("botStart", "机器人已开启");
        put("botAlreadyStart", "机器人当前处于开启状态");
        put("botStop", "机器人已关闭");
        put("botAlreadyStop", "机器人当前处于关闭状态");
        put("botExit", "正在退出群");

        put("bookCard", "COC七版规则空白卡奈梅斯·西莉亚私人订制版By贝尔巨佬.xlsx\n请使用此链接下载https://pan.baidu.com/s/1M3veskXYFJjwXP1eKHaX4g。有更新更好的版本请随时联系窝。");
        put("bookRP", "角色扮演三百六十五问.zip\n请使用此链接下载https://share1.heiluo.com/share/link/8b93f1f15a974d4a9fda1890863a0af1。有更新更好的版本请随时联系窝。");
        put("bookKp", "克苏鲁的呼唤第七版守秘人规则书 Version1901.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/e88daa8d6565440cbff0a8f7c9c8fe29。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/12XSQc9EEBsfEhhQEesc6nw");
        put("bookMake", "车卡指南.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/0e1083cdb8144b109be07fd4ef09b082。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/1HHo1B1F9kMRIFDB8J9Ulww 提取码：ql5h");

        put("manyRollsFormat", "请按照\".ral 值 次数\"的格式输入");
        put("diceTimesTooBig", "骰点次数过多");

        put("setPropFormat", "请符合格式.st角色名-力量50体质60");
        put("setHelp", ".st命令使用错误，请使用.help make命令查看具体用法");
        put("NotFoundSkill", "您未设置角色卡");

        put("dndInitIsEmtpy", "先攻列表为空");

        put("needKpGroup", "未设置kp群");

        put("can'tInPrivate", "此命令私聊不可用");

        put("alreadyOpen", "日志已经处于开启状态，无法再次开启");
        put("alreadyClose", "日志已经处于关闭状态，无法再次关闭");
        put("notFoundLog", "未找到日志");
        put("readLock", "正在有人取日志，请稍后");
        put("deleteOpenLog", "无法删除打开的日志，请先关闭");

        put("sanCheck", "请符合1/1d3|1d6/1d3|0/1这样的san check格式");

        put("setPropSuccess", "已记录人物卡");

        put("clrDndInit", "清空先攻列表");

        put("antagonizeOver", "对抗结束");
        put("antagonizeFirstSuccess", "先手胜利");
        put("antagonizeSecondSuccess", "后手胜利");
        put("antagonizeAllFailed", "全部失败");
        put("antagonizeDraw", "平手");

        put("symptom", "");

        put("enSuccess", "");
        put("enFailed", "");

        put("hiddenDice", "kp正在进行暗骰");

        put("teamIsEmpty", "您的小队为空");
        put("teamMemberEnIsEmpty", "的技能成功记录为空");


        put("appendLog", "日志已重新开启，将在原基础上追加");
        put("createLog", "日志已创建");

        put("sanCheckFumble", "");
        put("sanCheckCriticalSuccess", "");
        put("sanCheckSuccess", "");
        put("sanCheckFailure", "");

        put("mailUserName", "2730902267@qq.com");
        put("mailPassword", "kktjwuakdafbdcej");
        put("masterMail@qq.com", "450609203@qq.com");

        put("CRITICAL_SUCCESS", "");
        put("EXTREME_SUCCESS", "");
        put("HARD_SUCCESS", "");
        put("SUCCESS", "");
        put("FAILURE", "");
        put("FUMBLE", "");
    }};

    /**
     * 读取配置文件，默认从bin目录的上一层找conf目录，然后找sinanya.properties文件，也就是说如果启动时不在bin目录，可能会找不到文件
     */
    public static void initMessagesSystem() {
        Properties prop = new Properties();
        File file = null;
        try {
            file = new File("../conf/sinanya.properties");
            if (!file.exists()) {
                file = new File("src/main/resources/sinanya.properties");
            }
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(isr);
            prop.load(bufferedReader);
        } catch (IOException e) {
            log.error(file.getAbsolutePath());
            log.error(e.getMessage(), e);
        }

        for (String propertyNames : prop.stringPropertyNames()) {
            if (prop.containsKey(propertyNames)) {
                messagesSystem.put(propertyNames, prop.getProperty(propertyNames));
            }
        }
    }
}
