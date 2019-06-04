package dice.sinanya;

import com.forte.qqrobot.component.forlemoc.LemocApp;
import com.forte.qqrobot.component.forlemoc.LemocApplication;
import com.forte.qqrobot.component.forlemoc.LinkConfiguration;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;

/**
 * @author zhangxiaozhou
 */
public class RunApplication implements LemocApp {
    public static void main(String[] args) {
        new LemocApplication().run(new RunApplication());
    }

    @Override
    public void before(LinkConfiguration configuration) {
        configuration.setScannerPackage("dice.sinanya.listener");
        configuration.setLinkIp("123.207.150.160");
        configuration.setPort(25304);
    }

    @Override
    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {

    }
}
