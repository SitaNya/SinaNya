package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesNPC.Character;
import static dice.sinanya.system.MessagesNPC.*;
import static dice.sinanya.tools.MakeCocCardInfo.makeCardInfo;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.ceil;

public class NPC {

    private EntityTypeMessages entityTypeMessages;
    String genderInfo;
    int trueAge;

    public NPC(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void npc() {
        forPC();
        state();
        prop();
    }

    private void forPC() {
        StringBuilder stringBuilder = new StringBuilder();
        String gender = randomFromList(Gender);
        int age = random(1, 100);
        String ageInfo = "";

        if (age > 95) {
            ageInfo = "70岁左右";
            trueAge = random(70, 80);
        } else if (age > 90) {
            ageInfo = "50-60岁上下";
            trueAge = random(45, 69);
        } else if (age > 70) {
            ageInfo = "40岁左右";
            trueAge = random(35, 45);
        } else if (age > 50) {
            ageInfo = "30岁上下";
            trueAge = random(25, 35);
        } else if (age > 5) {
            ageInfo = "20岁左右";
            trueAge = random(18, 25);
        } else if (age > 0) {
            ageInfo = "15岁上下";
            trueAge = random(10, 18);
        }

        if (gender.equals("男")) {
            genderInfo = "他";
        } else {
            genderInfo = "她";
        }

        stringBuilder.append("这是一名大约").append(ageInfo).append("的").append(randomFromListSmall(Shape)).append(gender).append("性\n");
        stringBuilder.append(genderInfo).append("有着一头").append(randomFromList(HairColor)).append("的");
        if (gender.equals("男")) {
            stringBuilder.append(randomFromList(HairLengthMan));
        } else {
            stringBuilder.append(randomFromList(HairLengthWomen));
        }

        stringBuilder.append("并且有一双").append(randomFromList(EyeColor)).append("的").append(randomFromList(EyeShape))
                .append("\n而").append(genderInfo).append("的皮肤").append(randomFromListSmall(SkinColor))
                .append(",此外还穿着一身有些").append(randomFromListSmall(Clothing)).append("的衣服")
                .append("\n并且你们一看到").append(genderInfo).append("就觉得").append(genderInfo).append(randomFromListSmall(Temperament));

        sender(entityTypeMessages, stringBuilder.toString());
    }

    private void state() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("以下是NPC的基本信息:\n")
                .append("姓名:\t")
                .append("\n")
                .append("年龄\t")
                .append(trueAge)
                .append("\n")
                .append("来自:\t")
                .append(randomFromList(strNational))
                .append("\n")
                .append("职业是:\t")
                .append(randomFromList(job))
                .append("\n\n")
                .append("三个可选特点分别是:")
                .append("\n")
                .append("1.\t")
                .append(randomFromList(TZ))
                .append("\n")
                .append("2.\t")
                .append(randomFromList(TZ))
                .append("\n")
                .append("3.\t")
                .append(randomFromList(TZ));
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), stringBuilder.toString());
    }

    private void prop() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("以下是NPC的性格可选信息:\n")
                .append("实际性格:\t")
                .append(randomFromList(Character))
                .append("\n")
                .append("当前状态\t")
                .append(randomFromList(Manner))
                .append("\n")
                .append("特长技能:\t")
                .append(randomFromList(SpeTag))
                .append("\n")
                .append("此技能大约有")
                .append(random(60, 80))
                .append("点之高")
                .append("\n\n")
                .append("其余属性为:\n\n")
                .append(makeCardInfo());
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), stringBuilder.toString());
    }

    private String randomFromList(ArrayList<String> infoList) {
        return infoList.get(random(0, infoList.size() - 1));
    }

    private String randomFromListSmall(ArrayList<String> infoList) {
        int random = random(1, 3);
        return infoList.get(random(0, (int) ceil((infoList.size() - 1) / random)));
    }
}
