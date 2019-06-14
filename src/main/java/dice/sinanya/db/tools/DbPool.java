package dice.sinanya.db.tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * 数据库连接池
 */
class DbPool {
    private static final Logger Log = LogManager.getLogger(DbPool.class.getName());
    private static DbPool instance;

    /*
     * 根据本地db.properties文件中的内容创建连接池
     */

    static {
        instance = new DbPool();
    }

    private ComboPooledDataSource dataSource;

    private DbPool() {
        Log.info("Begin create DbPool");
        try {
            dataSource = new ComboPooledDataSource();

            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://123.207.150.160:3306/roles?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull");
            dataSource.setUser("root");
            dataSource.setPassword("rong");
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
