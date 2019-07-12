package dice.sinanya.listener;

import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.exception.TimeTaskException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.TimeJob;
import com.forte.qqrobot.timetask.TimeTaskContext;
import com.forte.qqrobot.utils.CQCodeUtil;
import io.prometheus.client.Gauge;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

/**
 * @author SitaNya
 * 日期: 2019-07-12
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
@CronTask("* * * * * ? *")
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
        CPU_REQUEST.set(getProcessCpuRate(Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0].trim())));
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

    private float getProcessCpuRate(int pid) // 获得应用cpu占用率
    {
        float cpu = 0;
        try {
            float totalCpuTime1 = getTotalCpuTime();
            float processCpuTime1 = getAppCpuTime(pid);
            try {
//                获取2次之间休眠0.3秒
                Thread.sleep(300);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            float totalCpuTime2 = getTotalCpuTime();
            float processCpuTime2 = getAppCpuTime(pid);
            float cpuRate = 100 * (processCpuTime2 - processCpuTime1)
                    / (totalCpuTime2 - totalCpuTime1);
            String str = new DecimalFormat("0.00").format(cpuRate);
            cpu = Float.parseFloat(str);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return cpu;
    }

    private long getTotalCpuTime() { // 获取系统总CPU使用时间
        String[] cpuInfos = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("/proc/stat")), 1000);
            String load = reader.readLine();
            reader.close();
            cpuInfos = load.split(" ");
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        assert cpuInfos != null;
        return Long.parseLong(cpuInfos[2])
                + Long.parseLong(cpuInfos[3]) + Long.parseLong(cpuInfos[4])
                + Long.parseLong(cpuInfos[6]) + Long.parseLong(cpuInfos[5])
                + Long.parseLong(cpuInfos[7]) + Long.parseLong(cpuInfos[8]);
    }


    private long getAppCpuTime(int pid) { // 获取应用占用的CPU时间
        String[] cpuInfos = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("/proc/" + pid + "/stat")), 1000);
            String load = reader.readLine();
            reader.close();
            cpuInfos = load.split(" ");
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        assert cpuInfos != null;
        return Long.parseLong(cpuInfos[13])
                + Long.parseLong(cpuInfos[14]) + Long.parseLong(cpuInfos[15])
                + Long.parseLong(cpuInfos[16]);
    }
}