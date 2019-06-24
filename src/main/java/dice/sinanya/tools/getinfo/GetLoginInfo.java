package dice.sinanya.tools.getinfo;

import com.forte.qqrobot.sender.MsgSender;

import static dice.sinanya.system.MessagesSystem.entityLoginQQInfo;

public class GetLoginInfo {

    public static void getLoginInfo(MsgSender sender){
        entityLoginQQInfo.setLoginQQ(Long.parseLong(sender.GETTER.getLoginQQInfo().getQQ()));
        entityLoginQQInfo.setLoginQQNick(sender.GETTER.getLoginQQInfo().getName());
    }
}
