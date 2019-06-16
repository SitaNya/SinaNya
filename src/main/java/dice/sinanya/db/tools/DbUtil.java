package dice.sinanya.db.tools;

import java.sql.Connection;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 数据库连接池初始化类，没有特殊需要无需改动
 */
public class DbUtil {
    /**
     * @return 将从连接池中取连接的动作封装一层
     */
    public static Connection getConnection() {
        DbPool pool = DbPool.getInstance();

        return pool.getConnection();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
