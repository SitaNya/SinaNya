package dice.sinanya.system;

/**
 * QQ机器人命令识别标签集，在这里添加标签后去Flow.java中设定导向逻辑，即可导向实际的类
 *
 * @author zhangxiaozhou
 */
public interface MessagesTag {
    String HEADER = "^[.。][ ]*";


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

    String TAG_SC = HEADER + "sc.*";

    String TAG_EN = HEADER + "en.*";

    String TAG_SET_ROLL_MAX_VALUE = HEADER + "set.*";

    String HEADER_ST = HEADER + "st[ ]*";
    String TAG_ST_SET = HEADER_ST + ".*";
    String TAG_ST_SHOW = HEADER_ST + "show.*";
    String TAG_ST_LIST = HEADER_ST + "list.*";
    String TAG_ST_RM = HEADER_ST + "rm.*";

    String HEADER_TEAM = HEADER + "team[ ]*";
    String TAG_TEAM_SHOW = HEADER_TEAM + ".*";
    String TAG_TEAM_SET = HEADER_TEAM + "set.*";
    String TAG_TEAM_CLR = HEADER_TEAM + "clr.*";
    String TAG_TEAM_RM = HEADER_TEAM + "rm.*";
    String TAG_TEAM_CALL = HEADER_TEAM + "call.*";
    String TAG_TEAM_HP = HEADER_TEAM + "hp.*";
    String TAG_TEAM_SAN = HEADER_TEAM + "san.*";
    String TAG_TEAM_DESC = HEADER_TEAM + "desc.*";

    String HEADER_BOT = HEADER + "bot[ ]*";
    String TAG_BOT_SHOW = HEADER_BOT + ".*";
    String TAG_BOT_ON = HEADER_BOT + "on.*";
    String TAG_BOT_OFF = HEADER_BOT + "off.*";
    String TAG_BOT_EXIT = HEADER_BOT + "exit.*";

    String HEADER_HELP = HEADER + "help[ ]*";
    String TAG_HELP_INFO = HEADER_HELP + ".*";
    String TAG_HELP_NORMAL = HEADER_HELP + "normal.*";
    String TAG_HELP_MAKE = HEADER_HELP + "make.*";
    String TAG_HELP_GROUP = HEADER_HELP + "group.*";
    String TAG_HELP_BOOK = HEADER_HELP + "book.*";
    String TAG_HELP_DND = HEADER_HELP + "dnd.*";

    String HEADER_BOOK = HEADER + "getbook[ ]*";
    String TAG_BOOK_CARD = HEADER_BOOK + "card.*";
    String TAG_BOOK_RP = HEADER_BOOK + "rp.*";
    String TAG_BOOK_KP = HEADER_BOOK + "kp.*";
    String TAG_BOOK_MAKE = HEADER_BOOK + "make.*";

    String TAG_NPC = HEADER + "npc.*";

    String TAG_BG = HEADER + "bg.*";

    String TAG_TZ = HEADER + "tz.*";

    String TAG_GAS = HEADER + "gas.*";

    String HEADER_COC = HEADER + "coc[ ]*";
    String TAG_COC7 = HEADER_COC + "[7]{0,1}[ ]*(10|[1-9])*";
    String TAG_COC7D = HEADER_COC + "[7]{0,1}d[ ]*(10|[1-9])*";
    String TAG_COC6 = HEADER_COC + "6[ ]*(10|[1-9])*";
    String TAG_COC6D = HEADER_COC + "6d[ ]*(10|[1-9])*";

    String HEADER_LOG = HEADER + "log[ ]*";
    String TAG_LOG_ON = HEADER_LOG + "on.*";
    String TAG_LOG_OFF = HEADER_LOG + "off.*";
    String TAG_LOG_GET = HEADER_LOG + "get.*";
    String TAG_LOG_LIST = HEADER_LOG + "list.*";
    String TAG_LOG_RM = HEADER_LOG + "rm.*";

    String TAG_TI = HEADER + "ti[ ]*.*";
    String TAG_LI = HEADER + "li[ ]*.*";

    String HEADER_DND = HEADER + "dnd[ ]*";
    String TAG_DND = HEADER_DND + "(10|[1-9])*";
    String TAG_RI = HEADER + "ri.*";
    String TAG_INIT = HEADER + "init.*";
    String TAG_INIT_CLR = HEADER + "init clr.*";

    String HEADER_CLUE = HEADER + "clue[ ]*";
    String TAG_CLUE_SET = HEADER_CLUE + ".*";
    String TAG_CLUE_SHOW = HEADER_CLUE + "show.*";
    String TAG_CLUE_RM = HEADER_CLUE + "rm.*";
    String TAG_CLUE_CLR = HEADER_CLUE + "clr.*";

    String TAG_HIY = HEADER + "hiy[ ]*.*";

    String TAG_KP = HEADER + "kp[ ]*.*";

    String TAG_JRRP = HEADER + "jrrp[ ]*.*";
}
