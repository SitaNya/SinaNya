package dice.sinanya.system;

import dice.sinanya.entity.EntityRoleTag;

import java.util.HashMap;

public interface RoleInfoCache {
    HashMap<EntityRoleTag, HashMap<String, Integer>> ROLE_INFO_CACHE = new HashMap<EntityRoleTag, HashMap<String, Integer>>();
    HashMap<Long, String> ROLE_CHOOSE = new HashMap<>();
}
