package dice.sinanya;

import com.forte.qqrobot.anno.depend.AllBeans;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.component.forhttpapi.HttpApp;
import com.forte.qqrobot.component.forhttpapi.HttpApplication;
import com.forte.qqrobot.component.forhttpapi.HttpConfiguration;
import com.forte.qqrobot.exception.RobotRuntionException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static dice.sinanya.db.system.SelectBot.flushBot;
import static dice.sinanya.system.MessagesLoginInfo.ENTITY_LOGINQQ_INFO;
import static dice.sinanya.tools.getinfo.DefaultMaxRolls.flushMaxRolls;
import static dice.sinanya.tools.getinfo.GetLoginInfo.getLoginInfo;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.initMessagesSystem;
import static dice.sinanya.tools.getinfo.History.flushHistory;
import static dice.sinanya.tools.getinfo.Kp.flushKp;
import static dice.sinanya.tools.getinfo.LogTag.flushLogTag;
import static dice.sinanya.tools.getinfo.RoleChoose.flushRoleChoose;
import static dice.sinanya.tools.getinfo.RoleInfo.flushRoleInfoCache;
import static dice.sinanya.tools.log.SendMail.sendMail;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 整个程序的入口类
 * <p>
 * 这里可以修改的是before方法(但我已经改造为配置文件了，因此可以不动这个方法）
 * 此外这里声明了大量服务启动时需要从服务器中获取的缓存数据
 */
@AllBeans(value = "dice.sinanya.listener", beans = @Beans(allDepend = true, single = false))
public class RunApplication implements HttpApp {
    private static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        initMessagesSystem();
//        读取配置文件
        try {
            new HttpApplication().run(new RunApplication());
        } catch (RobotRuntionException e) {
            sendMail(e.getMessage());
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void before(HttpConfiguration configuration) {
        configuration.setScannerPackage("dice.sinanya.listener");
        configuration.setIp(MESSAGES_SYSTEM.get("hostIp"));
        configuration.setServerPath("/coolq/demo.php");
        configuration.setJavaPort(Integer.parseInt(MESSAGES_SYSTEM.get("javaPort")));
        configuration.setServerPort(Integer.parseInt(MESSAGES_SYSTEM.get("serverPort")));

    }

    @Override
    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {
        int times = 0;
        do {
            getLoginInfo(sender);
            times++;
        } while (ENTITY_LOGINQQ_INFO.getLoginQQNick() == null && times < 20);
        if (times >= 20) {
            sender.SENDER.sendPrivateMsg("450609203", "获取昵称超过20次失败");
        }
//        从数据库中读取幕间成长到缓存
        flushMaxRolls();
//        从数据库中读取最大默认骰到缓存
        flushBot();
//        从数据库中读取机器人开关到缓存
        flushRoleChoose();
//        从数据库中读取当前已选角色到缓存
        flushRoleInfoCache();
//        从数据库中读取角色信息到缓存
        flushLogTag();
//        从数据库中读取日志开关到缓存
        flushKp();
//        从数据库中读取kp主群设定到缓存
        flushHistory();
//        从数据库中读取骰点历史信息到缓存
    }
}
