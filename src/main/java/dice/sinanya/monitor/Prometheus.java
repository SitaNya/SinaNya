package dice.sinanya.monitor;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;

/**
 * @author SitaNya
 * 日期: 2019-07-12
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Prometheus {

    private static Logger log = LogManager.getLogger(Prometheus.class.getName());
    private int port;

    public Prometheus(String port) {
        if (isNumeric(port)) {
            this.port = Integer.parseInt(port);
        } else {
            this.port = 1234;
        }
    }

    public void start() {
        DefaultExports.initialize();

        try {
            HTTPServer server = new HTTPServer(port);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
