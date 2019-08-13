package dice.sinanya.db.tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import java.util.Arrays;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 数据库连接池定义类，没有特殊必要不需要改动
 */
class DbPool {

    private static DbPool instance;

    static {
        instance = new DbPool();
    }

    private ComboPooledDataSource dataSource;

    /**
     * 初始化信息类，这里声明了驱动、用户名、密码等各种信息，其中密码是从配置文件中取得的
     */
    private DbPool() {
        try {
            dataSource = new ComboPooledDataSource();

            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://123.207.150.160:3306/roles?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull&useSSL=false");
            dataSource.setUser("root");
            dataSource.setPassword("rong");
        } catch (PropertyVetoException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
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
            CQ.logDebug("数据库","获取连接");
        } catch (SQLException e) {
            CQ.logDebug("数据库数据源",dataSource.toString());
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
        try {
            Exception e = new Exception("线程池调用流程为");
            CQ.logDebug("当前线程池中链接数 " , String.valueOf(dataSource.getNumConnections()));
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
        return conn;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
