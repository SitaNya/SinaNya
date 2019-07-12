package dice.sinanya.listener;

import com.forte.qqrobot.anno.timetask.CronTask;
import com.forte.qqrobot.exception.TimeTaskException;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.timetask.TimeJob;
import com.forte.qqrobot.timetask.TimeTaskContext;
import com.forte.qqrobot.utils.CQCodeUtil;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
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
@CronTask("*/1 * * * * ? *")
public class Prometheus implements TimeJob {

    private final Gauge cpuRequest
            = Gauge.build()
            .name("system_load_average").help("average cpu load").register();

    public Prometheus() {
        String tagPrometheus = "PrometheusPort";
        int port;
        if (isNumeric(MESSAGES_SYSTEM.get(tagPrometheus))) {
            port = Integer.parseInt(MESSAGES_SYSTEM.get(tagPrometheus));
        } else {
            port = 1234;
        }
        Logger log = LogManager.getLogger(Prometheus.class.getName());
        try {
            HTTPServer server = new HTTPServer(port);
            log.info("Prometheus监控系统已在本机" + server.getPort() + "端口启动");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        DefaultExports.initialize();
    }

    @Override
    public void execute(MsgSender msgSender, CQCodeUtil cqCodeUtil) {
        cpuRequest.inc(ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage());
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