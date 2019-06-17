package dice.sinanya.tools.getinfo;

import dice.sinanya.db.log.tag.InsertLogTag;
import dice.sinanya.db.log.tag.SelectLogTag;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesLog.*;

public class LogTag {
    private static SelectLogTag selectLogTag = new SelectLogTag();
    private static InsertLogTag insertLogTag = new InsertLogTag();

    public static void flushLogTag() {
        selectLogTag.flushLogTagFromDatabase();
    }

    public static boolean checkLogTagExist(EntityTypeMessages entityTypeMessages, String logName) {
        EntityLogTag entityLogTag = new EntityLogTag(entityTypeMessages.getFromGroup(), logName);
        return logNameSwitch.containsKey(entityLogTag);
    }

    public static boolean checkOthorLogTrue(String groupId) {
        if (logSwitchForGroup.containsKey(groupId)) {
            return logSwitchForGroup.get(groupId);
        } else {
            boolean switchLogFroGroup = !selectLogTag.checkOthorLogTrue(groupId);
            logSwitchForGroup.put(groupId, switchLogFroGroup);
            return switchLogFroGroup;
        }
    }

    public static String getOtherLogTrue(String groupId) {
        String tagNotFound = "未找到";
        if (checkOthorLogTrue(groupId) && logNameForGroup.get(groupId) != null) {
            return logNameForGroup.get(groupId);
        } else {
            String name = selectLogTag.getOthorLogTrue(groupId);
            if (!name.equals(tagNotFound)) {
                logNameForGroup.put(groupId, name);
                return name;
            } else {
                return name;
            }
        }
    }

    public static boolean checkLogTagSwitch(EntityTypeMessages entityTypeMessages, String logName) {
        EntityLogTag entityLogTag = new EntityLogTag(entityTypeMessages.getFromGroup(), logName);
        if (checkLogTagExist(entityTypeMessages, logName)) {
            return logNameSwitch.get(entityLogTag);
        } else {
            return false;
        }
    }

    public static void setLogTagSwitch(EntityTypeMessages entityTypeMessages, String logName, boolean logSwitch) {
        EntityLogTag entityLogTag = new EntityLogTag(entityTypeMessages.getFromGroup(), logName);
        insertLogTag.insertLogTag(entityLogTag, logSwitch);
        logNameSwitch.put(entityLogTag, logSwitch);
    }

    public static String getTagList(String groupId) {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("此群中存在以下日志\n");
        ArrayList<String> tagList = selectLogTag.getTagList(groupId);
        for (String tag : tagList) {
            stringBuffer.append(tag)
                    .append("\n");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public static void delLog(EntityLogTag entityLogTag) {
        insertLogTag.deleteLog(entityLogTag);
        logNameSwitch.remove(entityLogTag);
    }
}
