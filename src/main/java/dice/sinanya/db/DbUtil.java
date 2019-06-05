package dice.sinanya.db;

import java.sql.Connection;

/**
 * @author zhangxiaozhou
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
