package dice.sinanya.db.tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


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
            InputStreamReader isr = new InputStreamReader(new FileInputStream("src/main/resources/conf/db.properties"), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(isr);
            Properties prop = new Properties();
            prop.load(bufferedReader);
            dataSource = new ComboPooledDataSource();

            dataSource.setDriverClass(prop.getProperty("jdbcdriver"));
            dataSource.setJdbcUrl(prop.getProperty("url"));
            dataSource.setUser(prop.getProperty("username"));
            dataSource.setPassword(prop.getProperty("password"));
            dataSource.setIdleConnectionTestPeriod(3600);
            Log.info("create DbPool");
        } catch (PropertyVetoException | IOException e) {
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
