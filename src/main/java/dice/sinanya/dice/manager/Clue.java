package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityClue;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;

import static dice.sinanya.system.MessagesTag.TAG_CLUE_RM;
import static dice.sinanya.system.MessagesTag.TAG_CLUE_SET;
import static dice.sinanya.tools.getinfo.Clue.*;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.MakeTimeStamp.getTime;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 线索集
 */
public class Clue {
    private static Logger log = LogManager.getLogger(Clue.class.getName());

    private EntityTypeMessages entityTypeMessages;

    public Clue(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 设定线索集，注意为了跑团流畅，这里不予以回显
     * 这里记录了一个时间戳，用于删除
     */
    public void set() {
        String tag = TAG_CLUE_SET;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        setClue(new EntityClue(entityTypeMessages.getFromGroup(), new Timestamp(System.currentTimeMillis()), entityTypeMessages.getFromQq()), msg);
    }

    /**
     * 显示线索集，同一个群内所有人的都会被打印出来
     */
    public void show() {
        String result = getClue(new EntityClue(entityTypeMessages.getFromGroup()));
        sender(entityTypeMessages, result);
    }

    /**
     * 删除线索集中的某一条信息，这里除了用信息记录人和群号做过滤，还是用了上面记录的时间戳做key来确保不会删错
     */
    public void rm() {
        String tag = TAG_CLUE_RM;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        delClue(new EntityClue(entityTypeMessages.getFromGroup(), getTime(msg), entityTypeMessages.getFromQq()));
        sender(entityTypeMessages, "已删除线索");
    }

    /**
     * 清除一个群内的全部线索
     */
    public void clr() {
        clrClue(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "清除线索");
    }
}
