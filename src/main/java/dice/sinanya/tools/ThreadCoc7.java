package dice.sinanya.tools;

import dice.sinanya.entity.EntityCoc7CardInfo;

import java.util.concurrent.Callable;

/**
 * 返回一张简易coc7人物卡字符串，可以被多线程调用
 *
 * @author zhangxiaozhou
 */
public class ThreadCoc7 implements Callable<String> {

    public ThreadCoc7() {

    }

    @Override
    public String call() {
        EntityCoc7CardInfo coc7CardInfo = new EntityCoc7CardInfo();

        return "力量:" +
                coc7CardInfo.getStr() +
                " " +
                "体质:" +
                coc7CardInfo.getCon() +
                " " +
                "体型:" +
                coc7CardInfo.getSiz() +
                " " +
                "敏捷:" +
                coc7CardInfo.getDex() +
                " " +
                "魅力:" +
                coc7CardInfo.getApp() +
                " " +
                "智力:" +
                coc7CardInfo.getInt() +
                " " +
                "意志:" +
                coc7CardInfo.getPow() +
                " " +
                "教育:" +
                coc7CardInfo.getEdu() +
                " " +
                "幸运:" +
                coc7CardInfo.getLuck() +
                " " +
                "血量:" +
                coc7CardInfo.getHp() +
                " " +
                "DB:" +
                coc7CardInfo.getDb() +
                " " +
                "总值:" +
                coc7CardInfo.getNotLuck() +
                "/" +
                coc7CardInfo.getHasLuck() +
                " " +
                "占总点数的:" + String.valueOf((coc7CardInfo.getHasLuck() * 1.0 / 630) * 100).substring(0, 5) + "%";
    }


}
