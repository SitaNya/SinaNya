package dice.sinanya.entity;

import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByQQ;

public class EntityRoleTag {
    long qq;
    String role;

    public EntityRoleTag(long qq, String role) {
        this.qq = qq;
        if (role == null) {
            this.role = "未找到";
        } else {
            this.role = role;
        }
    }

    public EntityRoleTag(EntityTypeMessages entityTypeMessages) {
        this.qq = Long.parseLong(entityTypeMessages.getFromQQ());
        if (checkRoleChooseExistByQQ(qq)) {
            this.role = getRoleChooseByQQ(qq);
        } else {
            this.role = "未找到";
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

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EntityRoleTag) {
            EntityRoleTag entityRoleTag = (EntityRoleTag) o;
            return entityRoleTag.qq == qq && entityRoleTag.role.equals(role);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return String.valueOf(qq).hashCode() + role.hashCode();
    }
}
