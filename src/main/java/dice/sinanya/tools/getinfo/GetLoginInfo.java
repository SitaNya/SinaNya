package dice.sinanya.tools.getinfo;

import com.forte.qqrobot.sender.MsgSender;

import static dice.sinanya.system.MessagesSystem.ENTITY_LOGINQQ_INFO;

public class GetLoginInfo {

    public static void getLoginInfo(MsgSender sender){
        ENTITY_LOGINQQ_INFO.setLoginQQ(Long.parseLong(sender.GETTER.getLoginQQInfo().getQQ()));
        ENTITY_LOGINQQ_INFO.setLoginQQNick(sender.GETTER.getLoginQQInfo().getName());
    }
}
