package dice.sinanya.listener;

import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.exception.TimeTaskException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.TimeJob;
import com.forte.qqrobot.timetask.TimeTaskContext;
import com.forte.qqrobot.utils.CQCodeUtil;
import dice.sinanya.dice.MakeNickToSender;
import org.quartz.JobExecutionContext;

import java.util.ArrayList;

import static dice.sinanya.db.system.InsertBot.deleteBot;
import static dice.sinanya.db.system.SelectBot.selectOffBotList;
import static dice.sinanya.system.MessagesLoginInfo.ENTITY_LOGINQQ_INFO;
import static dice.sinanya.tools.getinfo.GetNickName.getGroupName;
import static dice.sinanya.tools.getinfo.GetTime.getNowString;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 定时任务类，会根据设定的时间每隔多久自动执行一次
 * <p>
 * 这里的“0 * * * * ? *”表示每分钟执行一次，具体怎么写请去查crontab的使用
 * 前5位应该是:分 时 日 月 周
 */
@CronTask("0 0 * * * ? *")
public class TestRunningTime implements TimeJob, MakeNickToSender {

    public TestRunningTime() {
//        定时任务按照接口无逻辑
    }

    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
        msgSender.SENDER.sendPrivateMsg("450609203", getNowString());
        autoClean(msgSender);
    }

    @Override
    public void execute(JobExecutionContext context) {
        try {
            CQCodeUtil cqCodeUtil = TimeTaskContext.getCQCodeUtil(context);
            MsgSender msgSender = TimeTaskContext.getMsgSender(context);
            execute(msgSender, cqCodeUtil);
        } catch (Exception e) {
            throw new TimeTaskException(e);
        }
    }

    private void autoClean(MsgSender msgSender) {
        ArrayList<String> offBotList = selectOffBotList();
        for (String offBotGroupId : offBotList) {
            if (msgSender.GETTER.getGroupInfo(offBotGroupId).getTypeId() == null) {
                deleteBot(offBotGroupId);
                msgSender.SENDER.sendPrivateMsg("450609203", "删除不存在群： " + offBotGroupId);
                continue;
            }
            long lastMsg = msgSender.GETTER.getGroupMemberInfo(offBotGroupId, String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ())).getLastTime();
            if (lastMsg > 21600) {
                msgSender.SENDER.sendGroupMsg("162279609", "已清理15日未使用，且已关闭本骰的群: " + makeGroupNickToSender(getGroupName(msgSender, offBotGroupId)) + offBotGroupId);
                deleteBot(offBotGroupId);
                int type = msgSender.GETTER.getGroupInfo(offBotGroupId).getTypeId();
                if (type == 1) {
                    msgSender.SENDER.sendDiscussMsg(offBotGroupId, "已在群: " + makeGroupNickToSender(offBotGroupId) + offBotGroupId + "中超过15日未响应且处于关闭状态，即将退群。");
                    msgSender.SETTER.setDiscussLeave(offBotGroupId);
                } else {
                    msgSender.SENDER.sendGroupMsg(offBotGroupId, "已在群: " + makeGroupNickToSender(offBotGroupId) + offBotGroupId + "中超过15日未响应且处于关闭状态，即将退群。");
                    msgSender.SETTER.setGroupLeave(offBotGroupId);
                }
                return;
            }
        }
    }
}