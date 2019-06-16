package dice.sinanya.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 线索集对象
 */
public class EntityClue {
    /**
     * @param groupId 群号
     * @param date 日期时间戳，用于删除某一条线索的key
     * @param qqId qq号，记录是谁录入的这条线索
     */
    private String groupId;
    private java.sql.Date date;
    private String qqId;

    public EntityClue(String groupId) {
        this.groupId = groupId;
    }

    public EntityClue(String groupId, java.sql.Date date, String qqId) {
        this.groupId = groupId;
        this.date = (Date) date.clone();
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
