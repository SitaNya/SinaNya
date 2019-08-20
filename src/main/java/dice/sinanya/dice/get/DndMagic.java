package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesDndMagic.dndMagic;
import static dice.sinanya.system.MessagesTag.TAG_MAGIC;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-08-20
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class DndMagic {
    private EntityTypeMessages entityTypeMessages;

    public DndMagic(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 获取煤气灯特质并发送
     */
    public void get() {
        String tag = TAG_MAGIC;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        sender(entityTypeMessages, dndMagic.getOrDefault(msg, "不存在此法术:\t" + msg));
    }
}
