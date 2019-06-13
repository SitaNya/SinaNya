package dice.sinanya.dice.get;

import dice.sinanya.dice.get.imal.MakeCard;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesNPC.Character;
import static dice.sinanya.system.MessagesNPC.*;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.ceil;

/**
 * 获取NPC
 *
 * @author zhangxiaozhou
 */
public class Npc implements MakeCard {

    private EntityTypeMessages entityTypeMessages;
    private int trueAge;

    public Npc(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void npc() {
        forPC();
        state();
        prop();
    }

    private void forPC() {
        int ageLevel1 = 95;
        int ageLevel2 = 90;
        int ageLevel3 = 70;
        int ageLevel4 = 50;
        int ageLevel5 = 5;
        int ageLevel6 = 0;

        String tagMan = "男";

        StringBuilder stringBuilder = new StringBuilder();
        String gender = randomFromList(Gender);
        int age = random(1, 100);
        String ageInfo = "";

        if (age > ageLevel1) {
            ageInfo = "70岁左右";
            trueAge = random(70, 80);
        } else if (age > ageLevel2) {
            ageInfo = "50-60岁上下";
            trueAge = random(45, 69);
        } else if (age > ageLevel3) {
            ageInfo = "40岁左右";
            trueAge = random(35, 45);
        } else if (age > ageLevel4) {
            ageInfo = "30岁上下";
            trueAge = random(25, 35);
        } else if (age > ageLevel5) {
            ageInfo = "20岁左右";
            trueAge = random(18, 25);
        } else if (age > ageLevel6) {
            ageInfo = "15岁上下";
            trueAge = random(10, 18);
        }

        String genderInfo;
        if (gender.equals(tagMan)) {
            genderInfo = "他";
        } else {
            genderInfo = "她";
        }

        stringBuilder.append("这是一名大约").append(ageInfo).append("的").append(randomFromListSmall(Shape)).append(gender).append("性\n");
        stringBuilder.append(genderInfo).append("有着一头").append(randomFromList(HairColor)).append("的");
        if (gender.equals(tagMan)) {
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
        String stringBuilder = "以下是NPC的基本信息:\n" +
                "姓名:\t" +
                "\n" +
                "年龄\t" +
                trueAge +
                "\n" +
                "来自:\t" +
                randomFromList(strNational) +
                "\n" +
                "职业是:\t" +
                randomFromList(job) +
                "\n\n" +
                "三个可选特点分别是:" +
                "\n" +
                "1.\t" +
                randomFromList(TZ) +
                "\n" +
                "2.\t" +
                randomFromList(TZ) +
                "\n" +
                "3.\t" +
                randomFromList(TZ);
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), stringBuilder);
    }

    private void prop() {
        String stringBuilder = "以下是NPC的性格可选信息:\n" +
                "实际性格:\t" +
                randomFromList(Character) +
                "\n" +
                "当前状态\t" +
                randomFromList(Manner) +
                "\n" +
                "特长技能:\t" +
                randomFromList(SpeTag) +
                "\n" +
                "此技能大约有" +
                random(60, 80) +
                "点之高" +
                "\n\n" +
                "其余属性为:\n\n" +
                getCoc7CardInfo();
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), stringBuilder);
    }

    private String randomFromList(ArrayList<String> infoList) {
        return infoList.get(random(0, infoList.size() - 1));
    }

    private String randomFromListSmall(ArrayList<String> infoList) {
        int random = random(1, 3);
        return infoList.get(random(0, (int) ceil((infoList.size() - 1) / random)));
    }
}
