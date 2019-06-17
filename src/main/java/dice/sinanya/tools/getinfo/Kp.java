package dice.sinanya.tools.getinfo;

import dice.sinanya.db.kp.InsertKp;
import dice.sinanya.db.kp.SelectKp;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotSetKpGroupException;

import static dice.sinanya.system.MessagesKP.KP_GROUP;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: KP主群数据库交互类
 */
public class Kp {
    private static SelectKp selectKp = new SelectKp();
    private static InsertKp insertKp = new InsertKp();

    /**
     * 从数据库读取kp主群数据刷写到静态变量，这个方法只在启动时调用一次
     */
    public static void flushKp() {
        selectKp.flushKpFromDatabase();
    }

    /**
     * 将新增的kp主群信息插入数据库
     *
     * @param entityTypeMessages 消息封装类
     * @param groupId            群号
     */
    public static void setKpGroup(EntityTypeMessages entityTypeMessages, String groupId) {
        insertKp.insertKp(entityTypeMessages.getFromQq(), groupId);
    }

    /**
     * 获取KP主群群号
     *
     * @param entityTypeMessages 消息封装类
     * @return KP主群群号
     * @throws NotSetKpGroupException 未设定KP主群报错，会在打印日志的同时回复消息
     */
    public static String getKpGroup(EntityTypeMessages entityTypeMessages) throws NotSetKpGroupException {
        if (KP_GROUP.containsKey(entityTypeMessages.getFromQq())) {
            return KP_GROUP.get(entityTypeMessages.getFromQq());
        } else {
            throw new NotSetKpGroupException(entityTypeMessages);
        }
    }
}
