package dice.sinanya.system;

import dice.sinanya.entity.EntityLogTag;

import java.util.HashMap;

public interface MessagesLog {

    HashMap<EntityLogTag, Boolean> logNameSwitch = new HashMap<EntityLogTag, Boolean>();

    HashMap<String, Boolean> logSwitchForGroup = new HashMap<>();

    HashMap<String, String> logNameForGroup = new HashMap<>();
}
