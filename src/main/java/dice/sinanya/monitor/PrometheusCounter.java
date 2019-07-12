package dice.sinanya.monitor;

import io.prometheus.client.Counter;

/**
 * @author SitaNya
 * 日期: 2019-07-12
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class PrometheusCounter {

    static final Counter requests = Counter.build()
            .name("requests_total").help("Total requests.").register();

    void processRequest() {
        requests.inc();
        // Your code here.
    }
}
