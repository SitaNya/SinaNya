package dice.sinanya.tools.makedata;

import dice.sinanya.dice.get.imal.MakeCard;

import java.util.concurrent.Callable;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:返回一张简易coc7人物卡字符串，可以被多线程调用
 */
public class ThreadCoc7 implements Callable<String>, MakeCard {

    public ThreadCoc7() {

    }

    @Override
    public String call() {
        return getCoc7CardInfo();
    }


}
