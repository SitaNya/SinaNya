package dice.sinanya.monitor;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-07-12
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Prometheus {
    private int port;

    public Prometheus() {
    }

    public void start() {
        Logger log = LogManager.getLogger(dice.sinanya.listener.Prometheus.class.getName());
        try {
            HTTPServer server = new HTTPServer(62258);
            log.info("Prometheus监控系统已在本机" + server.getPort() + "端口启动");
        } catch (IOException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
        DefaultExports.initialize();
    }
}
