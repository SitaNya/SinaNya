package dice.sinanya.entity;

public class EntityRoleTag {
    long qq;
    String role;

    public EntityRoleTag(long qq, String role) {
        this.qq = qq;
        this.role = role;
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
        return String.valueOf(qq).hashCode()+role.hashCode();
    }
}
