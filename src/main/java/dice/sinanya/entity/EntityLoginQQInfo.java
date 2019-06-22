package dice.sinanya.entity;

public class EntityLoginQQInfo {
    String loginQQNick;
    long loginQQ;

    public EntityLoginQQInfo(String loginQQNick, long loginQQ) {
        this.loginQQNick = loginQQNick;
        this.loginQQ = loginQQ;
    }

    public String getLoginQQNick() {
        return loginQQNick;
    }

    public void setLoginQQNick(String loginQQNick) {
        this.loginQQNick = loginQQNick;
    }

    public long getLoginQQ() {
        return loginQQ;
    }

    public void setLoginQQ(long loginQQ) {
        this.loginQQ = loginQQ;
    }
}
