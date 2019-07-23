package dice.sinanya.dice.system;

import com.forte.qqrobot.sender.MsgSender;
import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

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

    Logger log = LogManager.getLogger(Test.class.getName());

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
            try {
                int type = msgSender.GETTER.getGroupInfo(offBotGroupId).getTypeId();
                msgSender.SENDER.sendPrivateMsg("450609203", "type: " + type + "名称: " + makeGroupNickToSender(getGroupName(msgSender, offBotGroupId) + offBotGroupId));
            } catch (NullPointerException e) {
                log.error(e.getMessage(), e);
                log.error(offBotGroupId == null);
                log.error(msgSender.GETTER == null);
                log.error(ENTITY_LOGINQQ_INFO.getLoginQQ());
                log.error(offBotGroupId);
                log.error(msgSender.GETTER.getGroupInfo(offBotGroupId)==null);
            }
        }
    }
}
