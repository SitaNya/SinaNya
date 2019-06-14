package dice.sinanya.tools.makedata;

import dice.sinanya.dice.get.imal.MakeCard;

import java.util.concurrent.Callable;

/**
 * 返回一张简易coc7人物卡字符串，可以被多线程调用
 *
 * @author SitaNya
 */
public class ThreadCoc7 implements Callable<String>, MakeCard {

    public ThreadCoc7() {

    }

    @Override
    public String call() {
        return getCoc7CardInfo();
    }


}
