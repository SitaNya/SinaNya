package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityClue;
import dice.sinanya.entity.EntityTypeMessages;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static dice.sinanya.system.MessagesTag.tagClueRm;
import static dice.sinanya.system.MessagesTag.tagClueSet;
import static dice.sinanya.tools.Clue.*;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;

public class Clue {
    private EntityTypeMessages entityTypeMessages;

    public Clue(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void set() {
        String tag = tagClueSet;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        setClue(new EntityClue(entityTypeMessages.getFromGroup(), new Date(new java.util.Date().getTime()), entityTypeMessages.getFromQQ()), msg);
    }

    public void show() {

        String result = getClue(new EntityClue(entityTypeMessages.getFromGroup(), new Date(new java.util.Date().getTime()), entityTypeMessages.getFromQQ()));
        sender(entityTypeMessages, result);
    }

    public void rm() {
        String tag = tagClueRm;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            delClue(new EntityClue(entityTypeMessages.getFromGroup(), new Date(format.parse(msg).getTime()), entityTypeMessages.getFromQQ()));
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
