package dice.sinanya.dice.game;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;

import java.text.SimpleDateFormat;
import java.util.Date;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityGame;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 今日人品类，其实不是很想做……
 */
public class Jrrp implements MakeNickToSender {
    private EntityTypeMessages entityTypeMessages;

    public Jrrp(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 将系统信息Date转化为毫秒时间戳字符串
     *
     * @param date 系统日期
     * @return 系统日期的毫秒时间戳
     */
    private static String toTimestamp(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String result;
        result = df.format(date);
        return result;
    }

    /**
     * 将结果发送出去，里面使用了对方的QQ号和时间戳作为种子
     */
    public void get() {
        String date = toTimestamp(new Date());
        int tmp = 0;
        char[] b = (entityTypeMessages.getFromQq() + date).toCharArray();
        //转换成响应的ASCLL
        for (char c : b) {
            tmp += (int) c;
        }
        sender(entityTypeMessages, String.format(entityGame.getJrrpInfo(), makeNickToSender(getNickName(entityTypeMessages)), (tmp % 100)));
    }
}
