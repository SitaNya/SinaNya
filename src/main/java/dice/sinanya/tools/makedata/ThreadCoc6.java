package dice.sinanya.tools.makedata;

import dice.sinanya.dice.get.imal.MakeCard;

import java.util.concurrent.Callable;

/**
 * 返回一张简易coc6人物卡字符串，可以被多线程调用
 *
 * @author SitaNya
 */
public class ThreadCoc6 implements Callable<String>, MakeCard {

    public ThreadCoc6() {

    }

    @Override
    public String call() {
        return getCoc6CardInfo();
    }
}
