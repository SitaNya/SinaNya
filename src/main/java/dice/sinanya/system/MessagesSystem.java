package dice.sinanya.system;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public interface MessagesSystem {
    HashMap<String, Integer> ROLL_MAX_VALUE = new HashMap<>();

    Boolean OSX_MODEL = Objects.equals(System.getProperty("os.name").toLowerCase(Locale.US), "mac os x");
    Boolean WIN_MODEL = Objects.equals(System.getProperty("os.name").toLowerCase(Locale.US), "Windows 10");
    Boolean LINUX_MODEL = !OSX_MODEL && !WIN_MODEL;

    String NONE = "";
    String SPACE = " ";

    String VERSIONS = "0.1.0.0 beta";
    String STR_SUCCESSFULLY_ENABLED_NOTICE = "啊~您需要奈梅斯了吗？我一定会好好努力的";
    String STR_ALREADY_ENABLED_ERR = "嗯！我在的呢，您尽管吩咐吧";
    String STR_SUCCESSFULLY_DISABLED_NOTICE = "这样啊……你找到更好的骰子了对吗？奈梅斯明白的，会好好安静下来的……";
    String STR_ALREADY_DISABLED_ERR = "奈梅斯还不够安静吗？您……您需要我离开吗？";
    String STR_EXIT_INFO = "离开群";


    StringBuilder STR_BOT_VERSIONS = new StringBuilder()
            .append("Dice made in java\n")
            .append("By SitaNya\n")
            .append("Versions is:\t")
            .append(VERSIONS);

    StringBuilder STR_BOT_HELP = new StringBuilder()
            .append("命令索引:\n")
            .append(".bot\t\t")
            .append("查看骰子版本信息\n")
            .append(".bot on\t\t")
            .append("开启骰子\n")
            .append(".bot off\t\t")
            .append("关闭骰子\n")
            .append(".bot exit\t\t")
            .append("命令骰子主动退群")
            .append("\n-----------------------------------------------\n")
            .append(".help normal\t\t")
            .append("常用骰点指令:由于是全新底层的骰子，可能许多命令和原版不同\n")
            .append(".help book\t\t")
            .append("资料集:包含规则书、rp自问合集等\n")
            .append(".help make\t\t")
            .append("车卡增强:本骰不再支持传统人物卡，直接使用多人物卡档位。附带煤气灯、自定义特质生成。还具备获取最新版人物卡与车卡指南功能\n")
            .append(".help group\t\t")
            .append("带团增强:以team命令为主打的队伍管理机制，附带有NPC生成，log日志记录，简易对抗骰掷等功能\n")
            .append(".help dnd\t\t")
            .append("dnd骰点命令集:先攻命令与生成卡片");

    StringBuilder STR_BOT_INFO = new StringBuilder()
            .append(STR_BOT_VERSIONS.toString())
            .append("\n\n")
            .append(STR_BOT_HELP);
}
