package dice.sinanya.dice.get;

import dice.sinanya.dice.get.imal.GetRandomList;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesGas.gasList;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取煤气灯特质
 */
public class Gas implements GetRandomList {
    private EntityTypeMessages entityTypeMessages;

    public Gas(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 获取煤气灯特质并发送
     */
    public void get() {
        sender(entityTypeMessages, randomNestList(gasList));
    }
}
