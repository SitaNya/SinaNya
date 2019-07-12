package dice.sinanya.listener;

import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.exception.TimeTaskException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.TimeJob;
import com.forte.qqrobot.timetask.TimeTaskContext;
import com.forte.qqrobot.utils.CQCodeUtil;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;

/**
 * @author SitaNya
 * 日期: 2019-07-12
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
@CronTask("*/30 * * * * ? *")
public class Prometheus implements TimeJob {
    private Logger log = LogManager.getLogger(Prometheus.class.getName());
    private int port;
    final Counter cpuRequest
            = Counter.build()
            .name("system_load_average").help("average cpu load").register();

    public Prometheus() {
        String tagPrometheus = "PrometheusPort";
        if (isNumeric(MESSAGES_SYSTEM.get(tagPrometheus))) {
            this.port = Integer.parseInt(MESSAGES_SYSTEM.get(tagPrometheus));
        } else {
            this.port = 1234;
        }
        try {
            HTTPServer server = new HTTPServer(port);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        DefaultExports.initialize();
    }

    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
        OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();
        cpuRequest.inc(osMxBean.getSystemLoadAverage());
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