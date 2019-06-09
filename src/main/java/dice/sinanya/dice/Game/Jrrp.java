package dice.sinanya.dice.Game;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.Date;

import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.Sender.sender;

public class Jrrp {
    private EntityTypeMessages entityTypeMessages;

    public Jrrp(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        Date date = new Date();
        int tmp = 0;
        char[] b = (entityTypeMessages.getFromQQ() + date.getTime()).toCharArray();
        //转换成响应的ASCLL
        for (char c : b) {
            tmp += (int) c;
        }
        sender(entityTypeMessages, getNickName(entityTypeMessages) + "的今日人品为: " + (tmp % 100));
    }
}
