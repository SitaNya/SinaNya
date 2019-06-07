package dice.sinanya.system;

public interface MessagesTag {
    String header = "^[.。][ ]*";


    String tagR = header + "r.*";

    String tagRH = header + "rh.*";
    String tagRA = header + "ra.*";
    String tagRC = header + "rc.*";

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
    String tagTeamMove = headerTeam + "move.*";
    String tagTeamCall = headerTeam + "call.*";
    String tagTeamHp = headerTeam + "hp.*";
    String tagTeamSan = headerTeam + "san.*";

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

    String headerBook = header + "getbook[ ]*";
    String tagBookCard = headerBook + "card.*";
    String tagBookRP = headerBook + "rp.*";
    String tagBookKP = headerBook + "kp.*";
    String tagBookMake = headerBook + "make.*";

    String tagNPC = header + "npc.*";

    String tagBG = header + "bg.*";

    String headerCoc = header + "coc[ ]*";
    String tagCoc7 = headerCoc + "[7]{0,1}[ ]*(10|[1-9])*";
    String tagCoc7d = headerCoc + "[7]{0,1}d[ ]*(10|[1-9])*";
    String tagCoc6 = headerCoc + "6[ ]*(10|[1-9])*";
    String tagCoc6d = headerCoc + "6d[ ]*(10|[1-9])*";

    String tagTi = header + "ti[ ]*.*";
    String tagLi = header + "li[ ]*.*";

}
