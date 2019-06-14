package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityClue;
import dice.sinanya.entity.EntityTypeMessages;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static dice.sinanya.system.MessagesTag.TAG_CLUE_RM;
import static dice.sinanya.system.MessagesTag.TAG_CLUE_SET;
import static dice.sinanya.tools.getinfo.Clue.*;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * 线索集
 *
 * @author SitaNya
 */
public class Clue {
    private EntityTypeMessages entityTypeMessages;

    public Clue(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void set() {
        String tag = TAG_CLUE_SET;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        setClue(new EntityClue(entityTypeMessages.getFromGroup(), new Date(new java.util.Date().getTime()), entityTypeMessages.getFromQq()), msg);
    }

    public void show() {

        String result = getClue(new EntityClue(entityTypeMessages.getFromGroup(), new Date(new java.util.Date().getTime()), entityTypeMessages.getFromQq()));
        sender(entityTypeMessages, result);
    }

    public void rm() {
        String tag = TAG_CLUE_RM;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            delClue(new EntityClue(entityTypeMessages.getFromGroup(), new Date(format.parse(msg).getTime()), entityTypeMessages.getFromQq()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sender(entityTypeMessages, "已删除线索");
    }

    public void clr() {
        delClue(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "清除线索");
    }
}
