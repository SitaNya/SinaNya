package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityTypeMessages;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 测试类，并不被任何信息调用
 */
public class Test {

    private EntityTypeMessages entityTypeMessages;

    public Test(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg("450609203", "test");
    }
}
