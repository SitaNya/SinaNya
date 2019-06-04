package dice.sinanya.entity;

import com.forte.qqrobot.beans.messages.msgget.MsgGet;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgDisGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgPrivate;
import com.forte.qqrobot.sender.MsgSender;

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

    public MsgGetTypes getMsgGetTypes() {
        return msgGetTypes;
    }

    public void setMsgGetTypes(MsgGetTypes msgGetTypes) {
        this.msgGetTypes = msgGetTypes;
    }

    public MsgSender getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public MsgGet getMsgGet() {
        return msgGet;
    }

    public void setMsgGet(MsgGet msgGet) {
        this.msgGet = msgGet;
    }

    public MsgGroup getMsgGroup() {
        return msgGroup;
    }

    public void setMsgGroup(MsgGroup msgGroup) {
        this.msgGroup = msgGroup;
    }

    public MsgDisGroup getMsgDisGroup() {
        return msgDisGroup;
    }

    public void setMsgDisGroup(MsgDisGroup msgDisGroup) {
        this.msgDisGroup = msgDisGroup;
    }

    public MsgPrivate getMsgPrivate() {
        return msgPrivate;
    }

    public void setMsgPrivate(MsgPrivate msgPrivate) {
        this.msgPrivate = msgPrivate;
    }

    @Override
    public String toString() {
        String result = "";
        result += "msgGetTypes: " + msgGetTypes.toString() + "\n";
        result += "msgSender: " + msgSender.toString() + "\n";
        result += "msgGet: " + msgGet.toString() + "\n";
        result += "msgGroup: " + msgGroup.toString() + "\n";
        result += "msgDisGroup: " + msgDisGroup.toString() + "\n";
        result += "msgPrivate: " + msgPrivate.toString() + "\n";
        return result;
    }
}
