package dice.sinanya;

import com.forte.qqrobot.anno.depend.AllBeans;
import com.forte.qqrobot.component.forhttpapi.HttpApp;
import com.forte.qqrobot.component.forhttpapi.HttpApplication;
import com.forte.qqrobot.component.forhttpapi.HttpConfiguration;
import com.forte.qqrobot.exception.RobotRuntionException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;

import static dice.sinanya.db.system.SelectBot.flushBot;
import static dice.sinanya.tools.getinfo.DefaultMaxRolls.flushMaxRolls;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.initMessagesSystem;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.getinfo.History.flushHistory;
import static dice.sinanya.tools.getinfo.Kp.flushKp;
import static dice.sinanya.tools.getinfo.LogTag.flushLogTag;
import static dice.sinanya.tools.getinfo.RoleChoose.flushRoleChoose;
import static dice.sinanya.tools.getinfo.RoleInfo.flushRoleInfoCache;
import static dice.sinanya.tools.getinfo.Team.flushTeamEn;
import static dice.sinanya.tools.log.SendMail.sendMail;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 整个程序的入口类
 *
 * 这里可以修改的是before方法(但我已经改造为配置文件了，因此可以不动这个方法）
 * 此外这里声明了大量服务启动时需要从服务器中获取的缓存数据
 *
 */
@AllBeans(value = "dice.sinanya.listener")
public class RunApplication implements HttpApp {
    public static void main(String[] args) {
        initMessagesSystem();
//        读取配置文件
//        try {
            new HttpApplication().run(new RunApplication());
            flushTeamEn();
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
//        }catch (RobotRuntionException e){
//            sendMail();
//        }
    }

    @Override
    public void before(HttpConfiguration configuration) {
        configuration.setScannerPackage("dice.sinanya.listener");
        configuration.setIp(messagesSystem.get("hostIp"));
        configuration.setServerPort(Integer.parseInt(messagesSystem.get("javaPort")));
        configuration.setJavaPort(Integer.parseInt(messagesSystem.get("serverPort")));
    }

    @Override
    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {

    }
}
