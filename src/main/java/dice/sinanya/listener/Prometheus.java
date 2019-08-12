package dice.sinanya.listener;

import dice.sinanya.tools.getinfo.CPUMonitorCalc;
import io.prometheus.client.Gauge;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @author SitaNya
 * 日期: 2019-07-12
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Prometheus implements Job {

    private static final Gauge CPU_REQUEST
            = Gauge.build()
            .name("system_load_average").help("average cpu load").register();

    public Prometheus() {
//        由于构造方法每次任务周期都会被调用，因此很多东西无法写在里面
    }

    @Override
    public void execute(JobExecutionContext context) {
        CPU_REQUEST.set(CPUMonitorCalc.getInstance().getProcessCpu());
    }
}