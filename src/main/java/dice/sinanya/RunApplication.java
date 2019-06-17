package dice.sinanya;

import com.forte.qqrobot.component.forlemoc.LemocApp;
import com.forte.qqrobot.component.forlemoc.LemocApplication;
import com.forte.qqrobot.component.forlemoc.LinkConfiguration;
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

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 整个程序的入口类，这里主要需要修改的是服务器类，下一版会改为读取配置文件
 */
public class RunApplication implements LemocApp {
    public static void main(String[] args) {
        initMessagesSystem();
//        读取配置文件
        new LemocApplication().run(new RunApplication());
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
    }

    @Override
    public void before(LinkConfiguration configuration) {
        configuration.setScannerPackage("dice.sinanya.listener");
        configuration.setLinkIp(messagesSystem.get("hostIp"));
        configuration.setPort(Integer.parseInt(messagesSystem.get("hostPort")));
        configuration.setLocalQQCode(messagesSystem.get("loginQQ"));
    }

    @Override
    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {

    }
}
