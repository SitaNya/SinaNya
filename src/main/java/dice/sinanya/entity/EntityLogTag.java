package dice.sinanya.entity;

import java.util.Objects;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 日志标签对象，包含群号和日志名
 */
public class EntityLogTag {
    /**
     * @param groupId 群号
     * @param logName 日志名称
     */
    private String groupId;
    private String logName;

    public EntityLogTag(String groupId, String logName) {
        this.groupId = groupId;
        this.logName = logName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getLogName() {
        return logName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityLogTag)) {
            return false;
        }
        EntityLogTag that = (EntityLogTag) o;
        return getGroupId().equals(that.getGroupId()) &&
                getLogName().equals(that.getLogName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getLogName());
    }

    @Override
    public String toString() {
        return "EntityLogTag{" +
                "groupId='" + groupId + '\'' +
                ", logName='" + logName + '\'' +
                '}';
    }
}
