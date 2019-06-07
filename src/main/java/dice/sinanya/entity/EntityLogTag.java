package dice.sinanya.entity;

import java.util.Objects;

public class EntityLogTag {
    String groupId;
    String logName;

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

    public void setLogName(String logName) {
        this.logName = logName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityLogTag)) return false;
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
