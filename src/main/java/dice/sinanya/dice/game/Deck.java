package dice.sinanya.dice.game;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotEnableException;

import static dice.sinanya.system.MessagesTag.TAG_DECK;
import static dice.sinanya.tools.getinfo.Deck.getDeck;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityGame;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-08-22
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Deck implements MakeNickToSender {
    private EntityTypeMessages entityTypeMessages;

    public Deck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() throws NotEnableException {
        checkEnable();
        String tag = TAG_DECK;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        String deckType;
        String type;
        if (msg.contains(" ")){
            deckType=msg.split(" ")[0];
            type=msg.split(" ")[1];
        }else{
            deckType=msg;
            type="default";
        }
        String result=getDeck(entityTypeMessages,deckType,type);
        sender(entityTypeMessages,result);
    }

    private void checkEnable() throws NotEnableException {
        if (!entityGame.isBotList()){
            throw new NotEnableException(entityTypeMessages);
        }
    }
}
