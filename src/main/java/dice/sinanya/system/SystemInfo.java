package dice.sinanya.system;

import java.util.HashMap;

public interface SystemInfo {

    /**
     * 编译时的target环境路径
     */
    String BUILD_CLASS_TARGET_DIR = "/target/classes";
    /**
     * 编译时的BOOT-INF环境路径
     */
    String BUILD_CLASS_BOOT_INF_DIR = "!/BOOT-INF/classes!";

    HashMap<Long,Boolean> SWITCH_BOT =new HashMap<>();
}
