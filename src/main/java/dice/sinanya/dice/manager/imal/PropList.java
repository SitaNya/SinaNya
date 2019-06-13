package dice.sinanya.dice.manager.imal;

import java.util.ArrayList;

public class PropList {

    protected ArrayList<String> propMain = new ArrayList<String>() {{
        add("hp");
        add("san");
        add("str");
        add("pow");
        add("con");
        add("app");
        add("edu");
        add("siz");
        add("intValue");
        add("luck");
        add("mp");
    }};

    protected ArrayList<String> propInvestigationOfCrimes = new ArrayList<String>() {{
        add("libraryUse");
        add("investigationOfCrimes");
        add("listen");
        add("unlock");
        add("aWonderfulHand");
    }};

    protected ArrayList<String> propTalk = new ArrayList<String>() {{
        add("persuade");
        add("intimidate");
        add("enchantment");
        add("talkingSkill");
        add("psychology");
    }};

    protected ArrayList<String> propFight = new ArrayList<String>() {{
        add("aFistFight");
        add("dodge");
        add("pistol");
        add("firstAid");
        add("medicalScience");
    }};
}
