package dice.sinanya.listener;

import com.forte.qqrobot.anno.Constr;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.beans.messages.msgget.MsgGet;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.beans.types.KeywordMatchType;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgDisGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgGroup;
import com.forte.qqrobot.component.forlemoc.beans.msgget.MsgPrivate;
import com.forte.qqrobot.sender.MsgSender;
import dice.sinanya.entity.EntityTypeMessages;

/**
 * @author zhangxiaozhou
 */
public class Listener {


    private Listener() {

    }

    @Constr
    public static Listener getInstance() {
        return new Listener();
    }

    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = "^\\.[a-z].*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, MsgPrivate msgPrivate) {
        new Flow(new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgPrivate)).toPrivate();
        return true;
    }

    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = "^\\.[a-z].*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, MsgGroup msgGroup) {
        new Flow(new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgGroup)).toGroup();
        return true;
    }

    @Listen(MsgGetTypes.discussMsg)
    @Filter(value = "^\\.[a-z].*", keywordMatchType = KeywordMatchType.TRIM_REGEX)
    public boolean listener(MsgGet msgGet, MsgGetTypes msgGetTypes, MsgSender msgSender, MsgDisGroup msgDisGroup) {
        new Flow(new EntityTypeMessages(msgGetTypes, msgSender, msgGet, msgDisGroup)).toDisGroup();
        return true;
    }
}
