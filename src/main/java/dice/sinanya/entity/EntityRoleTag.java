package dice.sinanya.entity;

import java.util.Objects;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 角色标签对象，这个不同于角色信息，是用来标注此qq当前激活角色的
 */
public class EntityRoleTag {
    /**
     * @param qq QQ号
     * @param role 角色名
     */
    private long qq;
    private String role;

    /**
     * @param qq   qq号
     * @param role 角色名，如果这个值输入为空则改为“未找到”
     */
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
