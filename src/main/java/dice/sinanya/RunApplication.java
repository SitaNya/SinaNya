package dice.sinanya;

import com.forte.qqrobot.component.forlemoc.LemocApp;
import com.forte.qqrobot.component.forlemoc.LemocApplication;
import com.forte.qqrobot.component.forlemoc.LinkConfiguration;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;

import static dice.sinanya.tools.getinfo.GetMessagesSystem.initMessagesSystem;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;
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
        flushTeamEn();
//        从数据库中读取幕间成长的缓存
        new LemocApplication().run(new RunApplication());
    }

    @Override
    public void before(LinkConfiguration configuration) {
        configuration.setScannerPackage("dice.sinanya.listener");
        configuration.setLinkIp(messagesSystem.get("hostIp"));
        configuration.setPort(Integer.parseInt(messagesSystem.get("hostPort")));
    }

    @Override
    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {

    }
}
