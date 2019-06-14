package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesBG.*;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

/**
 * 获取人物背景
 *
 * @author SitaNya
 */
public class Bj {

    private EntityTypeMessages entityTypeMessages;

    public Bj(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void bg() {

        String stringBuilder = "个人描述:\t\t" +
                randomFromList(Info) +
                "\n" +
                "思想信念:\t\t" +
                randomFromList(Persuasion) +
                "\n" +
                "重要之人:\t\t" +
                randomFromList(ImportantPersonr) +
                "\n" +
                "重要之人理由:\t" +
                randomFromList(ImportantPersonrInfo) +
                "\n" +
                "意义非凡之地:\t" +
                randomFromList(ImportantMap) +
                "\n" +
                "宝贵之物:\t\t" +
                randomFromList(Precious) +
                "\n" +
                "调查员特点:\t\t" +
                randomFromList(speciality) +
                "\n" +
                "既然决定了背景，就一定要好好扮演不要出戏哦！";
        sender(entityTypeMessages, stringBuilder);
    }

    private String randomFromList(ArrayList<String> infoList) {
        return infoList.get(random(0, infoList.size() - 1));
    }
}
