package dice.sinanya.entity;

import java.util.Objects;

/**
 * @author SitaNya
 * 日期: 2019-08-26
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class EntityDeckBack {
    long qqId;
    String deck;
    String deckType;

    public EntityDeckBack() {
    }

    public EntityDeckBack(long qqId, String deck, String deckType) {
        this.qqId = qqId;
        this.deck = deck;
        this.deckType = deckType;
    }

    public long getQqId() {
        return qqId;
    }

    public void setQqId(long qqId) {
        this.qqId = qqId;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getDeckType() {
        return deckType;
    }

    public void setDeckType(String deckType) {
        this.deckType = deckType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityDeckBack that = (EntityDeckBack) o;
        return qqId == that.qqId &&
                Objects.equals(deck, that.deck) &&
                Objects.equals(deckType, that.deckType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qqId, deck, deckType);
    }
}
