package dice.sinanya.dice.Game;

import dice.sinanya.entity.EntityTypeMessages;

import java.text.SimpleDateFormat;
import java.util.Date;

import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.Sender.sender;

/**
 * 今日人品
 *
 * @author zhangxiaozhou
 */
public class Jrrp {
    private EntityTypeMessages entityTypeMessages;

    public Jrrp(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        String date = toTimestamp(new Date(), "yyyy-MM-dd");
        int tmp = 0;
        char[] b = (entityTypeMessages.getFromQQ() + date).toCharArray();
        //转换成响应的ASCLL
        for (char c : b) {
            tmp += (int) c;
        }
        sender(entityTypeMessages, getNickName(entityTypeMessages) + "的今日人品为: " + (tmp % 100));
    }

    //传入时间
    public static String toTimestamp(Date date, String geshi) {
        SimpleDateFormat df = new SimpleDateFormat(geshi);
        Date s = date;
        String result = null;
        result = df.format(s);
        return result;
    }
}
