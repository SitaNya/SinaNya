package dice.sinanya.dice.get;

import dice.sinanya.dice.get.imal.GetRandomList;
import dice.sinanya.dice.get.imal.MakeCard;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesBG.*;
import static dice.sinanya.tools.getinfo.RandomInt.random;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取人物背景
 */
public class Bj implements GetRandomList {

    private EntityTypeMessages entityTypeMessages;

    public Bj(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 拼装人物背景并发送，里面用到了很多dice.sinanya.system包中的静态列表信息
     */
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
}
