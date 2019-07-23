package dice.sinanya.dice.system;

import com.forte.qqrobot.sender.MsgSender;
import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.db.system.InsertBot.deleteBot;
import static dice.sinanya.db.system.SelectBot.selectOffBotList;
import static dice.sinanya.system.MessagesLoginInfo.ENTITY_LOGINQQ_INFO;
import static dice.sinanya.tools.getinfo.GetNickName.getGroupName;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 测试类，并不被任何信息调用
 */
public class Test implements MakeNickToSender {

    private EntityTypeMessages entityTypeMessages;

    public Test(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        autoClean(entityTypeMessages.getMsgSender());
    }

    private void autoClean(MsgSender msgSender) {
        ArrayList<String> offBotList = selectOffBotList();
        for (String offBotGroupId : offBotList) {
            long lastMsg = msgSender.GETTER.getGroupMemberInfo(offBotGroupId, String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ())).getLastTime();
            if (lastMsg > 21600) {
                msgSender.SENDER.sendGroupMsg("162279609", "已清理15日未使用，且已关闭本骰的群: " + makeGroupNickToSender(getGroupName(msgSender, offBotGroupId) + offBotGroupId));
                deleteBot(offBotGroupId);
                String type = msgSender.GETTER.getGroupInfo(offBotGroupId).getType();
                if ("discuss".equals(type)) {
                    msgSender.SENDER.sendDiscussMsg(offBotGroupId, "已在群: " + offBotGroupId + "中超过15日未响应且处于关闭状态，即将退群。");
                    msgSender.SETTER.setDiscussLeave(offBotGroupId);
                } else {
                    msgSender.SENDER.sendGroupMsg(offBotGroupId, "已在群: " + offBotGroupId + "中超过15日未响应且处于关闭状态，即将退群。");
                    msgSender.SETTER.setGroupLeave(offBotGroupId);
                }
                return;
            }
        }
    }
}
