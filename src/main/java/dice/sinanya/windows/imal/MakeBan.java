package dice.sinanya.windows.imal;

import dice.sinanya.tools.windows.Lable;
import dice.sinanya.tools.windows.RadioButton;
import dice.sinanya.tools.windows.Text;

import javax.swing.*;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;

/**
 * @author SitaNya
 * 日期: 2019-08-13
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class MakeBan extends MessagesWindows {
    Text text = new Text();
    Lable lable = new Lable();
    RadioButton radioButton = new RadioButton();

    public void info(JPanel jPanel) {
        lable.createLable(jPanel, "你当前登录的骰娘QQ为:\t");
        lable.createLable(jPanel, String.valueOf(CQ.getLoginQQ()));

        lable.createLable(jPanel, "你当前登录的骰娘昵称为:\t");
        lable.createLable(jPanel, CQ.getLoginNick());
    }


    public void monitor(JPanel jPanel) {
        Prometheus = radioButton.createRadioButton(jPanel, "普罗米修斯监控上报");
        Prometheus.setSelected(entityBanProperties.isPrometheus());
        heap = radioButton.createRadioButton(jPanel, "心跳报告");
        heap.setSelected(entityBanProperties.isHeap());
    }

    public void master(JPanel jPanel) {
        lable.createLable(jPanel, "管理员");
        master = text.createText(jPanel);
        master.setText(entityBanProperties.getMaster());

        lable.createLable(jPanel, "非master拒绝命令");
        notMaster = text.createText(jPanel);
        notMaster.setText(entityBanProperties.getNotMaster());

        lable.createLable(jPanel, "输入的不是QQ号或群号");
        banListInputNotId = text.createText(jPanel);
        banListInputNotId.setText(entityBanProperties.getBanListInputNotId());

        lable.createLable(jPanel, "普罗米修斯监控端口");
        prometheusPort = text.createText(jPanel);
        prometheusPort.setText(String.valueOf(entityBanProperties.getPrometheusPort()));
    }

    public void cloudBan(JPanel jPanel) {
        cloudBan = radioButton.createRadioButton(jPanel, "开启云黑");
        cloudBan.setSelected(entityBanProperties.isCloudBan());

        ignoreBanUser = radioButton.createRadioButton(jPanel, "当黑名单用户处于白名单群中时，忽略此用户");
        ignoreBanUser.setSelected(entityBanProperties.isIgnoreBanUser());

        leaveByBanUser = radioButton.createRadioButton(jPanel, "当黑名单用户处于白名单群中时，因此而退群");
        leaveByBanUser.setSelected(entityBanProperties.isLeaveByBanUser());

        leaveGroupByBan = radioButton.createRadioButton(jPanel, "自动退出黑名单群");
        leaveGroupByBan.setSelected(entityBanProperties.isLeaveGroupByBan());
        banGroupBecauseBan = radioButton.createRadioButton(jPanel, "退出并拉黑被禁言的群");
        banGroupBecauseBan.setSelected(entityBanProperties.isBanGroupBecauseBan());
        banGroupBecauseReduce = radioButton.createRadioButton(jPanel, "拉黑被踢出的群");
        banGroupBecauseReduce.setSelected(entityBanProperties.isBanGroupBecauseReduce());
        banUserBecauseReduce = radioButton.createRadioButton(jPanel, "拉黑提出自己的管理员");
        banUserBecauseReduce.setSelected(entityBanProperties.isBanUserBecauseReduce());
    }

    public void cloudBanInfo(JPanel jPanel) {
        lable.createLable(jPanel, "入群词");
        addGroup = text.createTextArea(jPanel);
        addGroup.setText(entityBanProperties.getAddGroup());

        lable.createLable(jPanel, "加好友欢迎词");
        addFriend = text.createTextArea(jPanel);
        addFriend.setText(entityBanProperties.getAddFriend());

        lable.createLable(jPanel, "拒绝黑名单群用语");
        refuseGroupByBan = text.createTextArea(jPanel);
        refuseGroupByBan.setText(entityBanProperties.getRefuseGroupByBan());

        lable.createLable(jPanel, "拒绝黑名单好友用语");
        refuseFriendByBan = text.createTextArea(jPanel);
        refuseFriendByBan.setText(entityBanProperties.getRefuseFriendByBan());
    }

    public void clearInfo(JPanel jPanel) {
        lable.createLable(jPanel, "关闭群清理期限");
        clearGroupByOff = text.createText(jPanel);
        clearGroupByOff.setText(String.valueOf(entityBanProperties.getClearGroupByOff()));

        lable.createLable(jPanel, "关闭群清理用语");
        clearGroupByOffInfo = text.createText(jPanel);
        clearGroupByOffInfo.setText(String.valueOf(entityBanProperties.getClearGroupByOffInfo()));

        lable.createLable(jPanel, "废弃群清理期限");
        clearGroup = text.createText(jPanel);
        clearGroup.setText(String.valueOf(entityBanProperties.getClearGroup()));

        lable.createLable(jPanel, "废弃群清理用语");
        clearGroupInfo = text.createText(jPanel);
        clearGroupInfo.setText(String.valueOf(entityBanProperties.getClearGroupInfo()));

        lable.createLable(jPanel, "警告刷屏频度");
        alterFrequentness = text.createText(jPanel);
        alterFrequentness.setText(String.valueOf(entityBanProperties.getAlterFrequentness()));

        lable.createLable(jPanel, "拉黑退群刷屏频度");
        banFrequentness = text.createText(jPanel);
        banFrequentness.setText(String.valueOf(entityBanProperties.getBanFrequentness()));

        lable.createLable(jPanel, "警告刷屏用语句");
        frequentnessAlterInfo = text.createText(jPanel);
        frequentnessAlterInfo.setText(String.valueOf(entityBanProperties.getFrequentnessAlterInfo()));

        lable.createLable(jPanel, "拉黑退群刷屏用语");
        frequentnessBanInfo = text.createText(jPanel);
        frequentnessBanInfo.setText(String.valueOf(entityBanProperties.getFrequentnessBanInfo()));
    }
}
