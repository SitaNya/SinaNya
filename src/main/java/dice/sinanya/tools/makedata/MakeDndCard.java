package dice.sinanya.tools.makedata;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.makedata.MakeDndCardInfo.makeDndCardInfo;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: DND多线程车卡
 */
public class MakeDndCard implements Callable<String> {

    public MakeDndCard() {

    }

    @Override
    public String call() {
        return makeDndCardInfo();
    }
}