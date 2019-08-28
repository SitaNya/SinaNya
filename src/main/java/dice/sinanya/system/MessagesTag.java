package dice.sinanya.system;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: QQ机器人命令识别标签静态信息
 * <p>
 * 在这里添加标签后去Flow.java中设定导向逻辑，即可导向实际的类
 */
public interface MessagesTag {


    /**
     * @param HEADER 头信息，筛选了.r|. r|。r|。 r等
     */
    String HEADER = "^[.。][ ]*";


    //    R开头的所有骰点标签
    String TAGR = HEADER + "r.*";

    String TAG_RH = HEADER + "rh.*";
    String TAG_RA = HEADER + "ra.*";
    String TAG_RC = HEADER + "rc.*";

    String TAG_RAL = HEADER + "ral.*";
    String TAG_RCL = HEADER + "rcl.*";

    String TAG_RAV = HEADER + "rav.*";
    String TAG_RCV = HEADER + "rcv.*";

    String TAG_RB = HEADER + "rb.*";
    String TAG_RP = HEADER + "rp.*";

    //    SC理智检定标签
    String TAG_SC = HEADER + "sc.*";

    //    EN幕间成长标签
    String TAG_EN = HEADER + "en.*";

    //    set设定最大默认骰标签
    String TAG_SET_ROLL_MAX_VALUE = HEADER + "set.*";

    //    人物卡相关标签
    String HEADER_ST = HEADER + "st[ ]*";
    String TAG_ST_SET = HEADER_ST + ".*";
    String TAG_ST_SHOW = HEADER_ST + "show.*";
    String TAG_ST_LIST = HEADER_ST + "list.*";
    String TAG_ST_RM = HEADER_ST + "rm.*";

    //    小队相关标签
    String HEADER_TEAM = HEADER + "team[ ]*";
    String TAG_TEAM_SHOW = HEADER_TEAM + ".*";
    String TAG_TEAM_SET = HEADER_TEAM + "set.*";
    String TAG_TEAM_CLR = HEADER_TEAM + "clr.*";
    String TAG_TEAM_RM = HEADER_TEAM + "rm.*";
    String TAG_TEAM_CALL = HEADER_TEAM + "call.*";
    String TAG_TEAM_HP = HEADER_TEAM + "hp.*";
    String TAG_TEAM_SAN = HEADER_TEAM + "san.*";
    String TAG_TEAM_DESC = HEADER_TEAM + "desc.*";
    String TAG_TEAM_EN = HEADER_TEAM + "en.*";

    //    机器人相关标签
    String HEADER_BOT = HEADER + "bot[ ]*";
    String TAG_BOT_SHOW = HEADER_BOT + ".*";
    String TAG_BOT_ON = HEADER_BOT + "on.*";
    String TAG_BOT_OFF = HEADER_BOT + "off.*";
    String TAG_BOT_EXIT = HEADER_BOT + "exit.*";

    //    帮助相关标签
    String HEADER_HELP = HEADER + "help[ ]*";
    String TAG_HELP_INFO = HEADER_HELP + ".*";
    String TAG_HELP_NORMAL = HEADER_HELP + "normal.*";
    String TAG_HELP_MAKE = HEADER_HELP + "make.*";
    String TAG_HELP_GROUP = HEADER_HELP + "group.*";
    String TAG_HELP_BOOK = HEADER_HELP + "book.*";
    String TAG_HELP_DND = HEADER_HELP + "dnd.*";

    //    获取资料集相关标签
    String HEADER_BOOK = HEADER + "getbook[ ]*";
    String TAG_BOOK_CARD = HEADER_BOOK + "card.*";
    String TAG_BOOK_RP = HEADER_BOOK + "rp.*";
    String TAG_BOOK_KP = HEADER_BOOK + "kp.*";
    String TAG_BOOK_MAKE = HEADER_BOOK + "make.*";

    //    获取NPC标签
    String TAG_NPC = HEADER + "npc.*";

    //    获取背景标签
    String TAG_BG = HEADER + "bg.*";

    //    获取自定义特质标签
    String TAG_TZ = HEADER + "tz.*";

    //    获取煤气灯特质标签
    String TAG_GAS = HEADER + "gas.*";

    //    获取COC人物卡相关标签，注意这里的正则表达式不同，因为需要匹配后续数字的格式
    String HEADER_COC = HEADER + "[cC][oO][cC][ ]*";
    String TAG_COC7 = HEADER_COC + "[7]{0,1}[ ]*(10|[1-9])*";
    String TAG_COC7D = HEADER_COC + "[7]{0,1}d[ ]*(10|[1-9])*";
    String TAG_COC6 = HEADER_COC + "6[ ]*(10|[1-9])*";
    String TAG_COC6D = HEADER_COC + "6d[ ]*(10|[1-9])*";

    //    获取日志相关标签
    String HEADER_LOG = HEADER + "log[ ]*";
    String TAG_LOG_ON = HEADER_LOG + "on.*";
    String TAG_LOG_OFF = HEADER_LOG + "off.*";
    String TAG_LOG_GET = HEADER_LOG + "get.*";
    String TAG_LOG_LIST = HEADER_LOG + "list.*";
    String TAG_LOG_RM = HEADER_LOG + "rm.*";

    //    获取疯狂症状相关标签
    String TAG_TI = HEADER + "ti[ ]*.*";
    String TAG_LI = HEADER + "li[ ]*.*";

    //    获取DND骰点相关标签
    String HEADER_DND = HEADER + "[dD][nN][dD][ ]*";
    String TAG_DND = HEADER_DND + "(10|[1-9])*";
    String TAG_RI = HEADER + "ri.*";
    String TAG_INIT = HEADER + "init.*";
    String TAG_INIT_CLR = HEADER + "init[ ]*clr.*";

    //    获取线索集相关标签
    String HEADER_CLUE = HEADER + "clue[ ]*";
    String TAG_CLUE_SET = HEADER_CLUE + ".*";
    String TAG_CLUE_SHOW = HEADER_CLUE + "show.*";
    String TAG_CLUE_RM = HEADER_CLUE + "rm.*";
    String TAG_CLUE_CLR = HEADER_CLUE + "clr.*";

    //    获取云黑相关标签
    String HEADER_BAN = HEADER + "ban[ ]*";
    String TAG_BAN_USER = HEADER_BAN + "user[ ]*\\d+";
    String TAG_BAN_GROUP = HEADER_BAN + "group[ ]*\\d+";
    String TAG_RM_BAN_USER = HEADER_BAN + "rm[ ]*user[ ]*\\d+";
    String TAG_RM_BAN_GROUP = HEADER_BAN + "rm[ ]*group[ ]*\\d+";
    String TAG_BAN_USER_LIST = HEADER_BAN + "user[ ]*list.*";
    String TAG_BAN_GROUP_LIST = HEADER_BAN + "group[ ]*list.*";

    //    获取云黑相关标签
    String HEADER_WHITE = HEADER + "white[ ]*";
    String TAG_WHITE_USER = HEADER_WHITE + "user[ ]*\\d+";
    String TAG_WHITE_GROUP = HEADER_WHITE + "group[ ]*\\d+";
    String TAG_RM_WHITE_USER = HEADER_WHITE + "rm[ ]*user[ ]*\\d+";
    String TAG_RM_WHITE_GROUP = HEADER_WHITE + "rm[ ]*group[ ]*\\d+";
    String TAG_WHITE_USER_LIST = HEADER_WHITE + "user[ ]*list.*";
    String TAG_WHITE_GROUP_LIST = HEADER_WHITE + "group[ ]*list.*";

    //    获取云黑相关标签
    String HEADER_ADMIN = HEADER + "admin[ ]*";
    String TAG_ADMIN_ON = HEADER_ADMIN + "on[ ]*\\d+";
    String TAG_ADMIN_OFF = HEADER_ADMIN + "off[ ]*\\d+";
    String TAG_ADMIN_EXIT = HEADER_ADMIN + "exit[ ]*\\d+";
    String TAG_ADMIN_SEARCH = HEADER_ADMIN + "search[ ]*\\d+";

    //    获取骰点历史标签
    String TAG_HIY = HEADER + "hiy[ ]*.*";

    //    获取kp主群标签
    String TAG_KP = HEADER + "kp[ ]*.*";

    //    获取今日人品标签
    String TAG_JRRP = HEADER + "jrrp[ ]*.*";

    //    获取测试标签
    String TAG_TEST = HEADER + "test[ ]*.*";

    String TAG_RULES = HEADER + "rule[ ]*.*";

    String TAG_MAGIC = HEADER + "magic[ ]*.*";

    String TAG_WELCOME = HEADER + "welcome[ ]*.*";

//    随即起名
    String HEADER_NAME = HEADER + "name[ ]*";
    String TAG_NAME = HEADER_NAME + "\\d*";
    String TAG_NAME_EN = HEADER_NAME + "en[ ]*\\d*";
    String TAG_NAME_CH = HEADER_NAME + "ch[ ]*\\d*";
    String TAG_NAME_JP = HEADER_NAME + "jp[ ]*\\d*";

    String TAG_DICE_LIST = HEADER + "dice[ ]*list.*";
    String TAG_GROUP_INFO = HEADER + "group[ ]*info.*";

    String TAG_DECK = HEADER + "deck.*";
}
