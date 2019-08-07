package dice.sinanya.listener;

import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.exception.TimeTaskException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.TimeJob;
import com.forte.qqrobot.timetask.TimeTaskContext;
import com.forte.qqrobot.utils.CQCodeUtil;
import org.quartz.JobExecutionContext;

import static dice.sinanya.db.system.SelectBot.flushBot;
import static dice.sinanya.tools.getinfo.BanList.flushBanList;
import static dice.sinanya.tools.getinfo.DefaultMaxRolls.flushMaxRolls;
import static dice.sinanya.tools.getinfo.History.flushHistory;
import static dice.sinanya.tools.getinfo.History.setHistory;
import static dice.sinanya.tools.getinfo.Kp.flushKp;
import static dice.sinanya.tools.getinfo.LogTag.flushLogTag;
import static dice.sinanya.tools.getinfo.RoleChoose.flushRoleChoose;
import static dice.sinanya.tools.getinfo.RoleInfo.flushRoleInfoCache;
import static dice.sinanya.tools.getinfo.Team.flushTeamEn;
import static dice.sinanya.tools.getinfo.Team.saveTeamEn;

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
@CronTask("0 */15 * * * ? *")
public class InputHistoryToDataBase implements TimeJob {

    public InputHistoryToDataBase() {
//        初始化时不需要参数
    }

    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
        setHistory();
        saveTeamEn();
        flushTeamEn();
//        从数据库中读取幕间成长到缓存
        flushMaxRolls();
//        从数据库中读取最大默认骰到缓存
        flushBot();
//        从数据库中读取机器人开关到缓存
        flushRoleChoose();
//        从数据库中读取当前已选角色到缓存
        flushRoleInfoCache();
//        从数据库中读取角色信息到缓存
        flushLogTag();
//        从数据库中读取日志开关到缓存
        flushKp();
//        从数据库中读取kp主群设定到缓存
        flushHistory();
//        从数据库中读取骰点历史信息到缓存
        flushBanList();
//        刷写黑名单
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
}