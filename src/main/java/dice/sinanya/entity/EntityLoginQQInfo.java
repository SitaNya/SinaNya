package dice.sinanya.entity;

public class EntityLoginQQInfo {
    String loginQQNick;
    long loginQQ;

    public EntityLoginQQInfo() {
//        因为初始化给静态变量时可能无数据，所以这里没有方法体
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
