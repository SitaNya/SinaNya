package dice.sinanya.tools.getinfo;

import dice.sinanya.db.log.info.InsertLogInfo;
import dice.sinanya.db.log.info.SelectLogInfo;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.log.MakeLogInfo;

public class LogText {

    private static SelectLogInfo selectLogInfo = new SelectLogInfo();
    private static InsertLogInfo insertLogInfo = new InsertLogInfo();

    public static void setLogText(EntityTypeMessages entityTypeMessages, EntityLogTag entityLogTag, String info) {
        info = MakeLogInfo.makeLogInfo(entityTypeMessages, info);
        insertLogInfo.insertLogTag(entityLogTag, info);
    }

    public static void setLogText(EntityLogTag entityLogTag, String info) {
        info = MakeLogInfo.makeLogInfo(info);
        insertLogInfo.insertLogTag(entityLogTag, info);
    }

    public static String getLogText(EntityLogTag entityLogTag) {
        return selectLogInfo.selectLogInfo(entityLogTag);
    }
}
