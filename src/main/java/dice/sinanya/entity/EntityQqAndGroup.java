package dice.sinanya.entity;

import java.util.Objects;

/**
 * @author SitaNya
 * 日期: 2019-06-16
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class EntityQqAndGroup {
    private String groupId;
    private String qqId;

    public EntityQqAndGroup(String groupId, String qqId) {
        this.groupId = groupId;
        this.qqId = qqId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityQqAndGroup)) return false;
        EntityQqAndGroup that = (EntityQqAndGroup) o;
        return getGroupId().equals(that.getGroupId()) &&
                getQqId().equals(that.getQqId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getQqId());
    }
}
