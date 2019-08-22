package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-08-21
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class EntityGame {
    boolean jrrpSwitch;
    String jrrpInfo;
    boolean welcomeSwitch;
    boolean botList;
    boolean deck;
    boolean nameSwitch;

    public EntityGame() {
        jrrpSwitch = false;
        jrrpInfo = "%s的今日人品为%s";
        welcomeSwitch = false;
        botList = true;
        deck = true;
        nameSwitch=false;
    }

    public boolean isNameSwitch() {
        return nameSwitch;
    }

    public void setNameSwitch(boolean nameSwitch) {
        this.nameSwitch = nameSwitch;
    }

    public boolean isJrrpSwitch() {
        return jrrpSwitch;
    }

    public void setJrrpSwitch(boolean jrrpSwitch) {
        this.jrrpSwitch = jrrpSwitch;
    }

    public String getJrrpInfo() {
        return jrrpInfo;
    }

    public void setJrrpInfo(String jrrpInfo) {
        this.jrrpInfo = jrrpInfo;
    }

    public boolean isWelcomeSwitch() {
        return welcomeSwitch;
    }

    public void setWelcomeSwitch(boolean welcomeSwitch) {
        this.welcomeSwitch = welcomeSwitch;
    }

    public boolean isBotList() {
        return botList;
    }

    public void setBotList(boolean botList) {
        this.botList = botList;
    }

    public boolean isDeck() {
        return deck;
    }

    public void setDeck(boolean deck) {
        this.deck = deck;
    }
}
