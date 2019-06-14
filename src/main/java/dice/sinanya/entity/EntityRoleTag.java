package dice.sinanya.entity;

import java.util.Objects;

/**
 * 角色标签对象，这个不同于角色信息，是用来标注此qq当前激活角色的
 *
 * @author SitaNya
 */
public class EntityRoleTag {
    private long qq;
    private String role;

    public EntityRoleTag(long qq, String role) {
        this.qq = qq;
        if (role == null) {
            this.role = "未找到";
        } else {
            this.role = role;
        }
    }

    public long getQq() {
        return qq;
    }

    public void setQq(long qq) {
        this.qq = qq;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        ;
        if (!(o instanceof EntityRoleTag)) {
            return false;
        }
        ;
        EntityRoleTag that = (EntityRoleTag) o;
        return getQq() == that.getQq() &&
                getRole().equals(that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQq(), getRole());
    }
}
