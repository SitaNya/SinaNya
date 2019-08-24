package dice.sinanya.system;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: .bot回复静态信息
 */
public interface MessagesSystem {

    String NONE = "";
    String SPACE = " ";

    String VERSIONS = "3.16.23.45Beta";
    StringBuilder UPDATE = new StringBuilder()
            .append("更新日志:\n")
            .append("2019年08月22日 增加了JRRP、WELCOME、NAME功能的可选项支持")
            .append("2019年08月22日 紧急解决被禁言识别问题")
            .append("2019年08月21日 增加更新界面\n")
            .append("2019年08月20日 修改多重投掷的显示方式\n")
            .append("2019年08月20日 增加rp允许多重投掷\n")
            .append("2019年08月20日 增加dnd法术查询\n")
            .append("2019年08月20日 增加admin命令用于远程关闭、开启、退出\n")
            .append("2019年08月20日 调整开关识别范围\n")
            .append("2019年08月14日 增加频度检测\n")
            .append("2019年08月14日 换用JCQ\n")
            .append("2019年08月14日 增加界面\n")
            .append("2019年08月09日 增加配置文件云黑开关\n")
            .append("2019年08月09日 增加心跳连接\n")
            .append("2019年08月08日 支持云黑名单\n")
            .append("2019年08月07日 支持了规则查询rule命令，感谢夏浅秋、秃了秃了的支持");

    StringBuilder STR_BOT_VERSIONS = new StringBuilder()
            .append("Dice made in java\n")
            .append("By SitaNya\n")
            .append("Versions is:\t")
            .append(VERSIONS)
            .append("\n项目文档:\thttps://sitcnya.gitbook.io/sinanya/")
            .append("\n项目地址:\thttps://github.com/sitanya/SinaNya/")
            .append("\n交流及BUG反馈群:\t162279609");

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
