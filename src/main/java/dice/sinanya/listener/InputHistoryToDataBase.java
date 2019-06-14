package dice.sinanya.listener;

import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.exception.TimeTaskException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.TimeJob;
import com.forte.qqrobot.timetask.TimeTaskContext;
import com.forte.qqrobot.utils.CQCodeUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static dice.sinanya.tools.History.setHistory;

@CronTask("* 0/1 * * * ? *")
public class InputHistoryToDataBase implements TimeJob {
    /**
     * 这是一个每5秒给qq：1234567890发送一句 'hi!'的定时任务
     */
    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
        setHistory();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            CQCodeUtil cqCodeUtil = TimeTaskContext.getCQCodeUtil(context);
            MsgSender msgSender = TimeTaskContext.getMsgSender(context);
            execute(msgSender, cqCodeUtil);
        } catch (Exception e) {
            throw new TimeTaskException(e);
        }
    }
}