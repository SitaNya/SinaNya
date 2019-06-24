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
        if (instance == null) {
            instance = new DbPool();
        }
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
            dataSource.setJdbcUrl("jdbc:mysql://123.207.150.160:3306/roles?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull&useSSL=false");
            dataSource.setUser("root");
            dataSource.setPassword(messagesSystem.get("dbPassword"));

            connPoolConfig(dataSource);
            connAgeConfig(dataSource);
            connTestConfig(dataSource);
            reconnConfig(dataSource);
            statementConfig(dataSource);
            threadConfig(dataSource);
            tranConfig(dataSource);


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
        try {
            Exception e = new Exception("this is a log");
            Log.info("当前线程池中链接数为: " + dataSource.getNumConnections() + " 调用信息为: \n");
            e.printStackTrace();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return conn;
    }


    /**
     * 连接池的配置 initialPoolSize:连接池的初始值 maxPoolSize:连接池的最大值 minPoolSize:连接池的最小值
     *
     * @param dataSource
     */
    private static void connPoolConfig(ComboPooledDataSource dataSource) {
        // 连接池的初始值，默认值为3
        dataSource.setInitialPoolSize(10);
        // 连接池的最大值,默认值为0
        dataSource.setMaxPoolSize(5000);
        // 连接池的最小值，最小值为3
        dataSource.setMinPoolSize(1);
        // 连接池的递增值,默认值为3
        dataSource.setAcquireIncrement(100);
        dataSource.setCheckoutTimeout(60 * 1000);
    }

    /**
     * 连接池生命周期配置,连接池首先从数据库中获取连接，用户请求时从连接池中获取连接。默认值为0，表示永不过期。 maxConnectionAge:
     * 连接对象生命的最大值，超过此时间，连接池会销毁连接对象，连接池变小。单位为秒，建议设置1800或更多 maxIdleTime:
     * 空闲连接在连接池中的超时时间，超过此时间，连接池将会销毁连接对象。单位为秒，建议设置1800或更多
     * maxIdleTimeExcessConnections：当连接池不处于过载状态时，空闲连接对象生命的最大值。
     * unreturnedConnectionTimeout:当连接对象在一定时间内无法回收，则创建新连接对象，销毁旧连接对象
     *
     * @param dataSource
     */
    private static void connAgeConfig(ComboPooledDataSource dataSource) {
        // 连接对象生命的最大值,它指绝对时间。从创建开始时计算,默认值为0
        dataSource.setMaxConnectionAge(10 * 60 * 60);
        // 空闲连接的超时时间，从连接池变为空闲状态开始计算
        dataSource.setMaxIdleTime(1800);
        // 空闲连接对象生命的最大值
        dataSource.setMaxIdleTimeExcessConnections(60);
        // 连接对象的最大使用时间,设置为2小时
        dataSource.setUnreturnedConnectionTimeout(2 * 60 * 60);
    }

    /**
     * 连接测试配置。 automaticTestTable:测试连接时使用的数据库表 ,默认值为null。connectionTesterClassName:测试连接时使用的类名称
     * idleConnectionTestPeriod:测试连接间隔时间。在此段时间内不进行连接测试。 preferredTestQuery:连接测试使用的SQL语句。默认语句为select
     * 1 from dual。 testConnectionOnCheckin:从连接池回收连接对象时测试连接。默认值为false
     * testConnectionOnCheckOut:从连接池取出连接对象时测试连接。默认值为false。
     * forceSynchronousCheckins:连接池回收连接对象时是同步，还是异步，默认是异步。默认值为false
     *
     * @param dataSource
     * @throws PropertyVetoException
     */
    private static void connTestConfig(ComboPooledDataSource dataSource)
            throws PropertyVetoException {
        // 连接测试使用的数据库表,默认值为Null
        dataSource.setAutomaticTestTable("SELECT * FROM `dual`");
        // 从连接池取出连接时测试连接，默认值为false
        dataSource.setTestConnectionOnCheckout(true);
        // 从连接池回收连接时测试连接，默认值为false。
        dataSource.setTestConnectionOnCheckin(true);
        // 测试连接的间隔时间，默认值为0
        dataSource.setIdleConnectionTestPeriod(60);
    }

    /**
     * 当连接失败后，重新连接的配置。 acquireRetryAttempts:重连的次数。 acquireRetryDelay:重连的时间间隔。单位为毫秒
     * breakAfterAcquireFailure:重连失败后，如果此值设置为false,数据源对象不会销毁，设置为false。数据源被销毁。
     * checkoutTimeout:等待连接响应的时间。
     */
    private static void reconnConfig(ComboPooledDataSource dataSource) {
        // 设置重连次数为3，默认值为30
        dataSource.setAcquireRetryAttempts(3);
        // 设置重连的时间间隔为2秒，默认值为1000
        dataSource.setAcquireRetryDelay(2000);
        // 等待连接响应的超时时间。默认值为0表示永远不超时
        dataSource.setCheckoutTimeout(60*1000);
        // 重连失败后，销毁数据源。默认值为false
        dataSource.setBreakAfterAcquireFailure(true);
    }

    /**
     * 连接池中PreparedStatement对象的配置 PreparedStatement对象的配置。 maxStatements:连接池拥有PreparedStatement对象的总数。
     * maxStatementsPerConnections:每个连接拥有PreparedStatement对象的数目。
     *
     * @param dataSource
     */
    private static void statementConfig(ComboPooledDataSource dataSource) {
        // 设置PreparedStatement对象的总数，默认值为0，表示关闭
        dataSource.setMaxStatements(100);
        // 设置每个连接拥有Statement对象的数目，默认值为0，表示关闭。
        dataSource.setMaxStatementsPerConnection(15);
    }

    /**
     * 连接池的线程设置 numHelperThread:连接池拥有的线程数量 maxAdministrativeTaskTime:线程执行的超时时间。
     *
     * @param dataSource
     */
    private static void threadConfig(ComboPooledDataSource dataSource) {
        // 设置线程数量为10，默认值为3
        dataSource.setNumHelperThreads(10);
        // 设置线程的超时时间，默认值为0，设置为5分钟
        dataSource.setMaxAdministrativeTaskTime(5 * 60);
    }

    /**
     * 连接对象的事务配置 autoCommitOnClose：是否自动提交事务，true为是，false为否，默认为否 forceIgnoreUnresolvedTransactions
     * 回收连接时，是否强制回滚或提交事务，默认为false。一般不设置此值， 例如由Spring来管理事务
     *
     * @param dataSource
     */
    private static void tranConfig(ComboPooledDataSource dataSource) {
        // 关闭自动提交事务，默认值为false
        dataSource.setAutoCommitOnClose(false);
        // 回收连接时，是否强制回滚或提交事务，设置为false。
        dataSource.setForceIgnoreUnresolvedTransactions(false);

    }

    /**
     * 调试模式的设置 debugUnreturnedConnectionStackTraces:从连接池获取连接对象时，打印所有信息

     *
     * @param dataSource
     */
    private static void debugMode(ComboPooledDataSource dataSource) {
        // 从连接池获取连接对象时，打印所有信息
        dataSource.setDebugUnreturnedConnectionStackTraces(true);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
