package dice.sinanya.system;

import dice.sinanya.entity.EntityRoleTag;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 人物卡角色的静态对象
 */
public class RoleInfoCache {

    /**
     * @param ROLE_INFO_CACHE 当前人物卡信息
     * key为包含qq号与角色名的EntityRoleTag对象
     * value为HashMap。其中key为技能名，value为技能值
     * @param ROLE_CHOOSE 当前选择人物卡对象，key为QQ号，value为角色名
     */
    public static final Map<EntityRoleTag, HashMap<String, Integer>> ROLE_INFO_CACHE = new HashMap<>();
    public static final Map<String, String> ROLE_CHOOSE = new HashMap<>();

    private RoleInfoCache() {
        throw new IllegalStateException("Utility class");
    }
}
