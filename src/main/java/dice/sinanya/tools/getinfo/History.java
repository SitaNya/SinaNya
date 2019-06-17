package dice.sinanya.tools.getinfo;

import dice.sinanya.db.history.InsertHistory;
import dice.sinanya.db.history.SelectHistory;
import dice.sinanya.entity.EntityHistory;

import java.util.Map;

import static dice.sinanya.system.MessagesHistory.HISTORY_LIST;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 骰点历史信息的数据库交互类
 */
public class History {

    private static SelectHistory selectHistory = new SelectHistory();
    private static InsertHistory insertHistory = new InsertHistory();

    /**
     * 刷新骰点历史到静态变量，只在程序启动时刷新一次
     */
    public static void flushHistory() {
        selectHistory.flushHistoryFromDatabase();
    }

    /**
     * 将当前静态变量中的骰点历史入库，这个方法被dice.sinanya.listener.InputHistoryToDataBase定时器调用
     */
    public static void setHistory() {
        for (Map.Entry<String, EntityHistory> mapEntry : HISTORY_LIST.entrySet()) {
            insertHistory.insertHistory(mapEntry.getValue());
        }
    }

    /**
     * 获取EntityHistory对象，如果不存在则更新一个对象
     *
     * @param qqId QQ号
     * @return 封装后的EntityHistory对象，包含骰点人，骰娘QQ，各种成功失败次数，骰点次数，均值
     */
    public static EntityHistory changeHistory(String qqId) {
        if (HISTORY_LIST.containsKey(qqId)) {
            return HISTORY_LIST.get(qqId);
        } else {
            HISTORY_LIST.put(qqId, new EntityHistory(qqId));
            return HISTORY_LIST.get(qqId);
        }
    }
}
