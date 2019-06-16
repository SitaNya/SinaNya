package dice.sinanya.db.tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 数据库连接池定义类，没有特殊必要不需要改动
 */
class DbPool {
    private static final Logger Log = LogManager.getLogger(DbPool.class.getName());
    private static DbPool instance;

    static {
        instance = new DbPool();
    }

    private ComboPooledDataSource dataSource;

    /**
     * 初始化信息类，这里声明了驱动、用户名、密码等各种信息，其中密码是从配置文件中取得的
     */
    private DbPool() {
        Log.info("Begin create DbPool");
        try {
            dataSource = new ComboPooledDataSource();

            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://123.207.150.160:3306/roles?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull");
            dataSource.setUser("root");
            dataSource.setPassword(messagesSystem.get("dbPassword"));
            dataSource.setIdleConnectionTestPeriod(3600);
            Log.info("create DbPool");
        } catch (PropertyVetoException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * @return 返回连接池
     */
    static DbPool getInstance() {
        return instance;
    }


    /**
     * @return 返回连接
     */
    Connection getConnection() {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            Log.debug("get Connection");
        } catch (SQLException e) {
            Log.error("get Connection error: \n" + dataSource.toString() + e.getMessage(), e);
        }

        return conn;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
