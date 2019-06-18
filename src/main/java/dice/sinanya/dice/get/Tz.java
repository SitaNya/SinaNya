package dice.sinanya.dice.get;

import dice.sinanya.dice.get.imal.GetRandomList;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesTZ.TZ_LIST;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取自定义特质，这些特质是不符合规则书的
 */
public class Tz implements GetRandomList {
    private EntityTypeMessages entityTypeMessages;

    public Tz(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        sender(entityTypeMessages, randomNestList(TZ_LIST));
    }
}
