package dice.sinanya.tools;

import dice.sinanya.entity.EntityCoc6CardInfo;
import dice.sinanya.entity.imal.GetDb;

import java.util.concurrent.Callable;

/**
 * 返回一张简易coc6人物卡字符串，可以被多线程调用
 *
 * @author zhangxiaozhou
 */
public class ThreadCoc6 implements Callable<String>, GetDb {

    public ThreadCoc6() {

    }

    @Override
    public String call() {
        EntityCoc6CardInfo coc6CardInfo = new EntityCoc6CardInfo();

        return "力量:" +
                coc6CardInfo.getStr() +
                " " +
                "体质:" +
                coc6CardInfo.getCon() +
                " " +
                "体型:" +
                coc6CardInfo.getSiz() +
                " " +
                "敏捷:" +
                coc6CardInfo.getDex() +
                " " +
                "魅力:" +
                coc6CardInfo.getApp() +
                " " +
                "智力:" +
                coc6CardInfo.getInt() +
                " " +
                "意志:" +
                coc6CardInfo.getPow() +
                " " +
                "教育:" +
                coc6CardInfo.getEdu() +
                " " +
                "血量:" +
                coc6CardInfo.getHp() +
                " " +
                "理智:" +
                coc6CardInfo.getSan() +
                " " +
                "灵感:" +
                coc6CardInfo.getIdea() +
                " " +
                "幸运:" +
                coc6CardInfo.getLuck() +
                " " +
                "知识:" +
                coc6CardInfo.getKnow() +
                " " +
                "DB:" +
                coc6CardInfo.getDb() +
                "\n总值:" +
                coc6CardInfo.getNotLuck() +
                " " +
                "占总点数的:" +
                String.valueOf((coc6CardInfo.getNotLuck() * 1.0 / 147) * 100).substring(0, 5) + "%";
    }
}
