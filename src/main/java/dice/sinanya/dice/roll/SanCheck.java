package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;
import dice.sinanya.tools.makedata.MakeSanCheck;

import static dice.sinanya.system.MessagesTag.TAG_SC;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 理智检定
 */
public class SanCheck {

    private EntityTypeMessages entityTypeMessages;

    public SanCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * @throws PlayerSetException   可能因为用户输入格式错误而报错
     * @throws SanCheckSetException 用户可能输入无法识别的sc表达式
     */
    public void sc() throws PlayerSetException, SanCheckSetException {
        String tag = TAG_SC;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String result = new MakeSanCheck(entityTypeMessages).checkSanCheck(msg);
        sender(entityTypeMessages, result);
    }
}
