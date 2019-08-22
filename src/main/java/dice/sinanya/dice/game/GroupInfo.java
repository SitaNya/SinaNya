package dice.sinanya.dice.game;

import com.sobte.cqp.jcq.entity.Member;
import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.List;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesBanList.qqBanList;
import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.system.MessagesWelcome.welcomes;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityGame;
import static dice.sinanya.tools.getinfo.GetNickName.getGroupName;
import static dice.sinanya.tools.getinfo.SwitchBot.getBot;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-08-22
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class GroupInfo implements MakeNickToSender {
    private EntityTypeMessages entityTypeMessages;

    public GroupInfo(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        String jrrpEnable = "禁用";
        String welcomeEnable = "禁用";
        String welcomeSet = "未设置";
        String deckEnable = "禁用";
        String botListEnable = "禁用";
        if (entityGame.isJrrpSwitch()) {
            jrrpEnable = "启用";
        }
        if (entityGame.isWelcomeSwitch()) {
            welcomeEnable = "启用";
        }

        if (welcomes.containsKey(Long.parseLong(entityTypeMessages.getFromGroup())) && !welcomes.get(Long.parseLong(entityTypeMessages.getFromGroup())).getText().equals(NONE)) {
            welcomeSet = "已设置";
        }

        if (entityGame.isDeck()) {
            deckEnable = "启用";
        }

        if (entityGame.isBotList()) {
            botListEnable = "启用";
        }

        List<Member> members = CQ.getGroupMemberList(Long.parseLong(entityTypeMessages.getFromGroup()));
        int fishMembers = 0;
        StringBuilder banInGroup = new StringBuilder();
        for (Member member : members) {
            long lastMsgForNow = System.currentTimeMillis() - member.getLastTime().getTime();
            if (lastMsgForNow / 1000 > 30 * 60 * 60 * 24) {
                fishMembers++;
            }
            if (qqBanList.containsKey(String.valueOf(member.getQqId()))) {
                banInGroup.append("\n").append(makeNickToSender(String.valueOf(member.getQqId()))).append("(").append(member.getNick()).append(")");
            }
        }
        StringBuilder info = new StringBuilder();
        info
                .append("群名:\t")
                .append(makeGroupNickToSender(getGroupName(entityTypeMessages.getFromGroup())))
                .append("\n")

                .append("群号:\t")
                .append(entityTypeMessages.getFromGroup())
                .append("\n")

                .append(CQ.getLoginNick()).append("在本群状态:\t")
                .append(getBot(Long.parseLong(entityTypeMessages.getFromGroup())))
                .append("\n")

                .append("jrrp全局开关:\t")
                .append(jrrpEnable)
                .append("\n")

                .append("welcome全局开关:\t")
                .append(welcomeEnable)
                .append("\n")

                .append("welcome信息:\t")
                .append(welcomeSet)
                .append("\n")

                .append("deck全局开关:\t")
                .append(deckEnable)
                .append("\n")

                .append("dicelist全局开关:\t")
                .append(botListEnable)
                .append("\n")

                .append("30天不活跃群员数:\t")
                .append(fishMembers)
                .append("\n")

                .append("群内黑名单成员:\t")
                .append(banInGroup);
        sender(entityTypeMessages, info.toString());
    }
}
