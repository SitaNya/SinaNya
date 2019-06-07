package dice.sinanya.tools;

import dice.sinanya.db.log.info.InsertLogInfo;
import dice.sinanya.db.log.info.SelectLogInfo;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.MakeLogInfo.makeLogInfo;

public class LogText {

    static SelectLogInfo selectLogInfo = new SelectLogInfo();
    static InsertLogInfo insertLogInfo = new InsertLogInfo();

    public static void setLogText(EntityTypeMessages entityTypeMessages,EntityLogTag entityLogTag, String info) {
        info = makeLogInfo(entityTypeMessages,info);
        insertLogInfo.insertLogTag(entityLogTag, info);
    }

    public static void setLogText(EntityLogTag entityLogTag, String info) {
        info = makeLogInfo(info);
        insertLogInfo.insertLogTag(entityLogTag, info);
    }

    public static String getLogText(EntityLogTag entityLogTag) {
        return selectLogInfo.selectLogInfo(entityLogTag);
    }
}
