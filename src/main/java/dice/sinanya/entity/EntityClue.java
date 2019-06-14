package dice.sinanya.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * 线索类型对象
 *
 * @author SitaNya
 */
public class EntityClue {
    private String groupId;
    private java.sql.Date date;
    private String qqId;

    public EntityClue(String groupId, java.sql.Date date, String qqId) {
        this.groupId = groupId;
        this.date = (Date)date.clone();
        this.qqId = qqId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public java.sql.Date getDate() {
        return this.date;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityClue)) {
            return false;
        }
        EntityClue that = (EntityClue) o;
        return getGroupId().equals(that.getGroupId()) &&
                getDate().equals(that.getDate()) &&
                getQqId().equals(that.getQqId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getDate(), getQqId());
    }
}
