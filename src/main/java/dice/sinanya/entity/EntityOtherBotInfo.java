package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-08-14
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class EntityOtherBotInfo {
    String botName;
    String botId;

    public EntityOtherBotInfo(String botName, String botId) {
        this.botName = botName;
        this.botId = botId;
    }

    public EntityOtherBotInfo() {
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }
}
