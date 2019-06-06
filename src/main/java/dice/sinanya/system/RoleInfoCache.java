package dice.sinanya.system;

import dice.sinanya.entity.EntityRoleTag;

import java.util.HashMap;

public interface RoleInfoCache {
    HashMap<EntityRoleTag, HashMap<String, Integer>> ROLE_INFO_CACHE = new HashMap<>();
    HashMap<Long, String> ROLE_CHOOSE = new HashMap<>();
}
