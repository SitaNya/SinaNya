package dice.sinanya.tools.getinfo;

import dice.sinanya.db.log.info.InsertLogInfo;
import dice.sinanya.db.log.info.SelectLogInfo;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.log.MakeLogInfo;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 日志信息存储数据库交互类
 */
public class LogText {

    private static SelectLogInfo selectLogInfo = new SelectLogInfo();
    private static InsertLogInfo insertLogInfo = new InsertLogInfo();

    /**
     * 将某个具体信息（通常是PC发言或行动的话）插入数据库
     *
     * @param entityTypeMessages 消息封装类
     * @param entityLogTag       日志标志对象
     * @param info               具体信息
     */
    public static void setLogText(EntityTypeMessages entityTypeMessages, EntityLogTag entityLogTag, String info) {
        info = MakeLogInfo.makeLogInfo(entityTypeMessages, info);
        insertLogInfo.insertLogTag(entityLogTag, info);
    }

    /**
     * 将骰娘回复的骰点结果信息插入数据库
     *
     * @param entityLogTag 日志标志对象
     * @param info 具体信息
     */
    public static void setLogText(EntityLogTag entityLogTag, String info) {
        info = MakeLogInfo.makeLogInfo(info);
        insertLogInfo.insertLogTag(entityLogTag, info);
    }

    /**
     * 获取格式化好的所有日志内容，用于存入文件并使用邮箱发送
     *
     * @param entityLogTag 日志标志对象
     * @return 格式化好的所有日志内容
     */
    public static String getLogText(EntityLogTag entityLogTag) {
        return selectLogInfo.selectLogInfo(entityLogTag);
    }
}
