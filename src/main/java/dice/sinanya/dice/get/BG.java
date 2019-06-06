package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesBG.*;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

public class BG {

    private EntityTypeMessages entityTypeMessages;

    public BG(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void bg() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("个人描述:\t\t")
                .append(randomFromList(Info))
                .append("\n")

                .append("思想信念:\t\t")
                .append(randomFromList(Persuasion))
                .append("\n")

                .append("重要之人:\t\t")
                .append(randomFromList(ImportantPersonr))
                .append("\n")

                .append("重要之人理由:\t")
                .append(randomFromList(ImportantPersonrInfo))
                .append("\n")

                .append("意义非凡之地:\t")
                .append(randomFromList(ImportantMap))
                .append("\n")

                .append("宝贵之物:\t\t")
                .append(randomFromList(Precious))
                .append("\n")

                .append("调查员特点:\t\t")
                .append(randomFromList(speciality))
                .append("\n")
                .append("既然决定了背景，就一定要好好扮演不要出戏哦！");

        sender(entityTypeMessages, stringBuilder.toString());
    }

    private String randomFromList(ArrayList<String> infoList) {
        return infoList.get(random(0, infoList.size() - 1));
    }
}
