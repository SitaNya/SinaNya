package dice.sinanya.entity;

import com.forte.qqrobot.beans.messages.msgget.MsgGet;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgDisGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgPrivate;
import com.forte.qqrobot.sender.MsgSender;

import static dice.sinanya.tools.checkdata.CheckValue.checkValue;

/**
 * @author SitaNya
 */
public class EntityTypeMessages {
    private MsgGetTypes msgGetTypes;
    private MsgSender msgSender;
    private MsgGet msgGet;
    private MsgGroup msgGroup;
    private MsgDisGroup msgDisGroup;
    private MsgPrivate msgPrivate;


    public EntityTypeMessages(MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGet msgGet, MsgPrivate msgPrivate) {
        this.msgGetTypes = msgGetTypes;
        this.msgSender = msgSender;
        this.msgGet = msgGet;
        this.msgPrivate = msgPrivate;
    }

    public EntityTypeMessages(MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGet msgGet, MsgGroup msgGroup) {
        this.msgGetTypes = msgGetTypes;
        this.msgSender = msgSender;
        this.msgGet = msgGet;
        this.msgGroup = msgGroup;
    }

    public EntityTypeMessages(MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGet msgGet, MsgDisGroup msgDisGroup) {
        this.msgGetTypes = msgGetTypes;
        this.msgSender = msgSender;
        this.msgGet = msgGet;
        this.msgDisGroup = msgDisGroup;
    }

    public String getFromQq() {
        switch (msgGetTypes) {
            case groupMsg:
                return msgGroup.getFromQQ();
            case discussMsg:
                return msgDisGroup.getFromQQ();
            case privateMsg:
                return msgPrivate.getFromQQ();
            default:
                return "450609203";
        }
    }

    public String getFromGroup() {
        switch (msgGetTypes) {
            case groupMsg:
                return msgGroup.getFromGroup();
            case discussMsg:
                return msgDisGroup.getFromDiscuss();
            default:
                return "0";
        }
    }

    public MsgGetTypes getMsgGetTypes() {
        return msgGetTypes;
    }

    public MsgSender getMsgSender() {
        return msgSender;
    }

    public MsgGet getMsgGet() {
        return msgGet;
    }

    public MsgGroup getMsgGroup() {
        return msgGroup;
    }

    public MsgDisGroup getMsgDisGroup() {
        return msgDisGroup;
    }

    public MsgPrivate getMsgPrivate() {
        return msgPrivate;
    }

    @Override
    public String toString() {
        String result = "";
        result += "msgGetTypes: " + checkValue(msgGetTypes) + "\n";
        result += "msgSender: " + checkValue(msgSender) + "\n";
        result += "msgGet: " + checkValue(msgGet) + "\n";
        result += "msgGroup: " + checkValue(msgGroup) + "\n";
        result += "msgDisGroup: " + checkValue(msgDisGroup) + "\n";
        result += "msgPrivate: " + checkValue(msgPrivate) + "\n";
        return result;
    }
}
