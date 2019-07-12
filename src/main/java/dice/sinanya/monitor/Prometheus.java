package dice.sinanya.monitor;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

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
public class Prometheus {
    private int port;
    public Prometheus(){
        String tagPrometheus = "PrometheusPort";
        if (isNumeric(MESSAGES_SYSTEM.get(tagPrometheus))) {
            this.port = Integer.parseInt(MESSAGES_SYSTEM.get(tagPrometheus));
        } else {
            this.port = 1234;
        }
    }

    public void start(){
        Logger log = LogManager.getLogger(dice.sinanya.listener.Prometheus.class.getName());
        try {
            HTTPServer server = new HTTPServer(port);
            log.info("Prometheus监控系统已在本机" + server.getPort() + "端口启动");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        DefaultExports.initialize();
    }
}
