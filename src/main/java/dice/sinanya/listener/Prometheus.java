package dice.sinanya.listener;

import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.exception.TimeTaskException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.TimeJob;
import com.forte.qqrobot.timetask.TimeTaskContext;
import com.forte.qqrobot.utils.CQCodeUtil;
import dice.sinanya.tools.getinfo.CPUMonitorCalc;
import io.prometheus.client.Gauge;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

/**
 * @author SitaNya
 * 日期: 2019-07-12
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
@CronTask("0 * * * * ? *")
public class Prometheus implements TimeJob {

    static Logger log = LogManager.getLogger(dice.sinanya.listener.Prometheus.class.getName());

    private static final Gauge CPU_REQUEST
            = Gauge.build()
            .name("system_load_average").help("average cpu load").register();

    public Prometheus() {
//        由于构造方法每次任务周期都会被调用，因此很多东西无法写在里面
    }

    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
        CPU_REQUEST.set(CPUMonitorCalc.getInstance().getProcessCpu());
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