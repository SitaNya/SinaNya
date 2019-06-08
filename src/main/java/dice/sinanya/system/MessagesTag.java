package dice.sinanya.system;

public interface MessagesTag {
    String header = "^[.。][ ]*";


    String tagR = header + "r.*";

    String tagRH = header + "rh.*";
    String tagRA = header + "ra.*";
    String tagRC = header + "rc.*";

    String tagRAL = header + "ral.*";
    String tagRCL = header + "rcl.*";

    String tagRAV = header + "rav.*";
    String tagRCV = header + "rcv.*";

    String tagRB = header + "rb.*";
    String tagRP = header + "rp.*";

    String tagSC = header + "sc.*";

    String tagEN = header + "en.*";

    String headerSt = header + "st[ ]*";
    String tagStSet = headerSt + ".*";
    String tagStShow = headerSt + "show.*";
    String tagStList = headerSt + "list.*";
    String tagStMove = headerSt + "move.*";

    String headerTeam = header + "team[ ]*";
    String tagTeamShow = headerTeam + ".*";
    String tagTeamSet = headerTeam + "set.*";
    String tagTeamClr = headerTeam + "clr.*";
    String tagTeamMove = headerTeam + "rm.*";
    String tagTeamCall = headerTeam + "call.*";
    String tagTeamHp = headerTeam + "hp.*";
    String tagTeamSan = headerTeam + "san.*";
    String tagTeamDesc = headerTeam + "desc.*";

    String headerBot = header + "bot[ ]*";
    String tagBotShow = headerBot + ".*";
    String tagBotOn = headerBot + "on.*";
    String tagBotOff = headerBot + "off.*";
    String tagBotExit = headerBot + "exit.*";

    String headerHelp = header + "help[ ]*";
    String tagHelpNormal = headerHelp + "normal.*";
    String tagHelpMake = headerHelp + "make.*";
    String tagHelpGroup = headerHelp + "group.*";
    String tagHelpBook = headerHelp + "book.*";
    String tagHelpDnd = headerHelp + "dnd.*";

    String headerBook = header + "getbook[ ]*";
    String tagBookCard = headerBook + "card.*";
    String tagBookRP = headerBook + "rp.*";
    String tagBookKP = headerBook + "kp.*";
    String tagBookMake = headerBook + "make.*";

    String tagNPC = header + "npc.*";

    String tagBG = header + "bg.*";

    String tagTZ = header + "tz.*";

    String tagGas = header + "gas.*";

    String headerCoc = header + "coc[ ]*";
    String tagCoc7 = headerCoc + "[7]{0,1}[ ]*(10|[1-9])*";
    String tagCoc7d = headerCoc + "[7]{0,1}d[ ]*(10|[1-9])*";
    String tagCoc6 = headerCoc + "6[ ]*(10|[1-9])*";
    String tagCoc6d = headerCoc + "6d[ ]*(10|[1-9])*";

    String headerLog = header + "log[ ]*";
    String tagLogOn = headerLog + "on.*";
    String tagLogOff = headerLog + "off.*";
    String tagLogGet = headerLog + "get.*";
    String tagLogList = headerLog + "list.*";
    String tagLogDel = headerLog + "del.*";

    String tagTi = header + "ti[ ]*.*";
    String tagLi = header + "li[ ]*.*";

    String headerDnd = header + "dnd[ ]*";
    String tagDnd = headerDnd + "(10|[1-9])*";
    String tagRi = header + "ri.*";
    String tagInit = header + "init.*";
    String tagInitClr = header + "init clr.*";

    String headerClue = header + "clue[ ]*";
    String tagClueSet = headerClue + ".*";
    String tagClueShow = headerClue + "show.*";
    String tagClueRm = headerClue + "rm.*";
    String tagClueClr = headerClue + "clr.*";

    String tagHiy = header + "hiy[ ]*.*";

    String tagKp = header + "kp[ ]*.*";
}
