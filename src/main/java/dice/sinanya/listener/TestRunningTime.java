package dice.sinanya.listener;

import com.sobte.cqp.jcq.entity.Group;
import dice.sinanya.db.heap.InsertHeap;
import dice.sinanya.dice.MakeNickToSender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.db.system.InsertBot.deleteBot;
import static dice.sinanya.db.system.SelectBot.selectOffBotList;
import static dice.sinanya.tools.getinfo.GetNickName.getGroupName;
import static dice.sinanya.tools.getinfo.GetTime.getNowString;
import static dice.sinanya.tools.makedata.RandomInt.random;

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
public class TestRunningTime implements Job, MakeNickToSender {
    private static Logger log = LogManager.getLogger(TestRunningTime.class.getName());

    private static long groupManager = 162279609;
    public TestRunningTime() {
//        定时任务按照接口无逻辑
    }

    @Override
    public void execute(JobExecutionContext context) {
        long heapGroupId = 825848066;
        if (checkHasGroup(heapGroupId)) {
            CQ.sendGroupMsg(heapGroupId, getNowString());
        }
        if (checkHasGroup(groupManager)) {
            autoClean();
            try {
                Thread.sleep(random(2000, 12000));
            } catch (InterruptedException e) {
                CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
            }
            autoCleanNotPlay();
        }
        try {
            CQ.getGroupList();
            new InsertHeap().updateHeap();
        } catch (Exception e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
            log.error("获取群列表失败，停止心跳");
        }
    }

    private void autoClean() {
        ArrayList<String> offBotList = selectOffBotList();
        for (String offBotGroupIdString : offBotList) {
            long offBotGroupId = Long.parseLong(offBotGroupIdString);
            if (!checkHasGroup(offBotGroupId)) {
                deleteBot(offBotGroupId);
                CQ.sendGroupMsg(groupManager, "删除已不存在群： " + offBotGroupId);
                continue;
            }
            long lastMsgForNow = System.currentTimeMillis() - CQ.getGroupMemberInfo(offBotGroupId, CQ.getLoginQQ()).getLastTime().getTime();
            if (lastMsgForNow/1000 > 432000) {
                try {
                    CQ.setDiscussLeave(offBotGroupId);
                    CQ.sendDiscussMsg(offBotGroupId, "已在讨论组: " + makeGroupNickToSender(getGroupName(offBotGroupId)) + offBotGroupId + "中超过5日未响应且处于关闭状态，即将退群。\n此次退群不会记录黑名单，如遇到问题请至群162279609进行反馈或使用退群命令缓解问题");
                    CQ.sendGroupMsg(groupManager, "已清理" + lastMsgForNow / 1000 / 60 / 60 / 24 + "日未使用，且已关闭本骰的讨论组: " + makeGroupNickToSender(getGroupName(offBotGroupId)) + offBotGroupId);
                } catch (Exception e) {
                    CQ.setGroupLeave(offBotGroupId, false);
                    CQ.sendGroupMsg(groupManager, "已清理" + lastMsgForNow / 60 / 60 / 24 + "日未使用，且已关闭本骰的群: " + makeGroupNickToSender(getGroupName(offBotGroupId)) + offBotGroupId);
                    CQ.sendGroupMsg(offBotGroupId, "已在群: " + makeGroupNickToSender(getGroupName(offBotGroupId)) + offBotGroupId + "中超过5日未响应且处于关闭状态，即将退群。\n此次退群不会记录黑名单，如遇到问题请至群162279609进行反馈或使用退群命令缓解问题");
                }
                deleteBot(offBotGroupId);
                return;
            }
        }
    }

    private void autoCleanNotPlay() {
        List<Group> groupLists = CQ.getGroupList();
        for (Group group : groupLists) {
            long notPlayBotGroupId = group.getId();
            if (!checkHasGroup(notPlayBotGroupId)) {
                deleteBot(notPlayBotGroupId);
                CQ.sendGroupMsg(groupManager, "删除已不存在群： " + notPlayBotGroupId);
                continue;
            }
            long lastMsgForNow = System.currentTimeMillis() - CQ.getGroupMemberInfo(notPlayBotGroupId, CQ.getLoginQQ()).getLastTime().getTime();
            if (lastMsgForNow / 1000 > 1296000) {
                try {
                    CQ.setDiscussLeave(notPlayBotGroupId);
                    CQ.sendDiscussMsg(notPlayBotGroupId, "已在讨论组: " + makeGroupNickToSender(getGroupName(notPlayBotGroupId)) + notPlayBotGroupId + "中超过15日未响应且处于关闭状态，即将退群。\n此次退群不会记录黑名单，如遇到问题请至群162279609进行反馈或使用退群命令缓解问题");
                    CQ.sendGroupMsg(groupManager, "已清理" + lastMsgForNow / 1000 / 60 / 60 / 24 + "日未使用，且已关闭本骰的讨论组: " + makeGroupNickToSender(getGroupName(notPlayBotGroupId)) + notPlayBotGroupId);
                } catch (Exception e) {
                    CQ.setGroupLeave(notPlayBotGroupId, false);
                    CQ.sendGroupMsg(groupManager, "已清理" + lastMsgForNow / 60 / 60 / 24 + "日未使用，且已关闭本骰的群: " + makeGroupNickToSender(getGroupName(notPlayBotGroupId)) + notPlayBotGroupId);
                    CQ.sendGroupMsg(notPlayBotGroupId, "已在群: " + makeGroupNickToSender(getGroupName(notPlayBotGroupId)) + notPlayBotGroupId + "中超过15日未响应且处于关闭状态，即将退群。\n此次退群不会记录黑名单，如遇到问题请至群162279609进行反馈或使用退群命令缓解问题");
                }
                deleteBot(notPlayBotGroupId);
                return;
            }
        }
    }

    private boolean checkHasGroup(long groupId) {
        List<Group> groupList = CQ.getGroupList();
        ArrayList<Long> groupListCode = new ArrayList<>();
        for (Group group : groupList) {
            groupListCode.add(group.getId());
        }
        return groupListCode.contains(groupId);
    }
}