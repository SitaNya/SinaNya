package dice.sinanya.listener;

import com.forte.qqrobot.anno.Constr;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.beans.types.KeywordMatchType;
import com.forte.qqrobot.sender.MsgSender;

/**
 * @author zhangxiaozhou
 */
public class TestListener {

    private int number;

    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = ".r", keywordMatchType = KeywordMatchType.CONTAINS)
    public boolean listen(MsgGetTypes msgGetTypes, MsgSender msgSender) {
        msgSender.SENDER.sendPrivateMsg("450609203", "number");
        return true;
    }

    /**
     * 这是构造函数
     */
    public TestListener(int number) {
        this.number = number;
    }

    @Constr
    public static TestListener getInstance() {
        return new TestListener(20);
    }
}
