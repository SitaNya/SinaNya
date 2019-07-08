package dice.sinanya.dice.get;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dice.sinanya.dice.get.imal.MakeCard;
import dice.sinanya.entity.EntityCoc6CardInfo;
import dice.sinanya.entity.EntityCoc7CardInfo;
import dice.sinanya.entity.EntityTypeMessages;


import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static dice.sinanya.system.MessagesTag.TAG_COC6;
import static dice.sinanya.system.MessagesTag.TAG_COC7;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: COC车卡
 */
public class MakeCocCard implements MakeCard {

    private EntityTypeMessages entityTypeMessages;

    public MakeCocCard(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * COC7版详细车卡
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    public void coc7d() {
        String nick = getNickName(entityTypeMessages);

        EntityCoc7CardInfo cocCardInfo = new EntityCoc7CardInfo();

        String stringBuilder = nick +
                "的人物作成:" +
                "\n" +
                "力量STR=3D6*5=" +
                cocCardInfo.getStr() + "/" +
                cocCardInfo.getStr() / 2 + "/" +
                cocCardInfo.getStr() / 5 + "\n" +
                "体质CON=3D6*5=" +
                cocCardInfo.getCon() + "/" +
                cocCardInfo.getCon() / 2 + "/" +
                cocCardInfo.getCon() / 5 + "\n" +
                "体型SIZ=(2D6+6)*5=" +
                cocCardInfo.getSiz() + "/" +
                cocCardInfo.getSiz() / 2 + "/" +
                cocCardInfo.getSiz() / 5 + "\n" +
                "敏捷DEX=3D6*5=" +
                cocCardInfo.getDex() + "/" +
                cocCardInfo.getDex() / 2 + "/" +
                cocCardInfo.getDex() / 5 + "\n" +
                "外貌APP=3D6*5=" +
                cocCardInfo.getApp() + "/" +
                cocCardInfo.getApp() / 2 + "/" +
                cocCardInfo.getApp() / 5 + "\n" +
                "智力INT=(2D6+6)*5=" +
                cocCardInfo.getInt() + "/" +
                cocCardInfo.getInt() / 2 + "/" +
                cocCardInfo.getInt() / 5 + "\n" +
                "意志POW=3D6*5=" +
                cocCardInfo.getPow() + "/" +
                cocCardInfo.getPow() / 2 + "/" +
                cocCardInfo.getPow() / 5 + "\n" +
                "教育EDU=(2D6+6)*5=" +
                cocCardInfo.getEdu() + "/" +
                cocCardInfo.getEdu() / 2 + "/" +
                cocCardInfo.getEdu() / 5 + "\n" +
                "幸运LUCK=3D6*5=" +
                cocCardInfo.getLuck() + "/" +
                cocCardInfo.getLuck() / 2 + "/" +
                cocCardInfo.getLuck() / 5 + "\n" + "\n" +
                "共计:\n" +
                "不带幸运为:\t" +
                cocCardInfo.getNotLuck() + "\t大约为 " + String.valueOf((cocCardInfo.getNotLuck() * 1.0 / 540) * 100).substring(0, 5) + "% 强度(越高越好)\n" +
                "带幸运为:\t" +
                cocCardInfo.getHasLuck() + "\t大约为 " + String.valueOf((cocCardInfo.getHasLuck() * 1.0 / 630) * 100).substring(0, 5) + "% 强度(越高越好)" +
                "\n" + "\n" +
                "生命值HP=(SIZ+CON)/10(向下取整)=" +
                cocCardInfo.getHp() +
                "\n" +
                "理智SAN=POW=" +
                cocCardInfo.getSan() +
                "\n" +
                "魔法值MP=POW/5=" +
                cocCardInfo.getMp() +
                "\n" +
                "伤害奖励DB=" +
                cocCardInfo.getDb() +
                "\n" +
                "体格BUILD=" +
                cocCardInfo.getBuild() +
                "\n" +
                "移动力MOV=" +
                cocCardInfo.getMov();
        sender(entityTypeMessages, stringBuilder);
    }

    /**
     * COC6版详细车卡
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    public void coc6d() {
        String nick = getNickName(entityTypeMessages);

        EntityCoc6CardInfo cocCardInfo = new EntityCoc6CardInfo();

        String stringBuilder = nick +
                "的人物作成:" +
                "\n" +
                "力量STR=3D6=" +
                cocCardInfo.getStr() + "/" +
                cocCardInfo.getStr() / 2 + "/" +
                cocCardInfo.getStr() / 5 + "\n" +
                "体质CON=3D6=" +
                cocCardInfo.getCon() + "/" +
                cocCardInfo.getCon() / 2 + "/" +
                cocCardInfo.getCon() / 5 + "\n" +
                "体型SIZ=2D6+6=" +
                cocCardInfo.getSiz() + "/" +
                cocCardInfo.getSiz() / 2 + "/" +
                cocCardInfo.getSiz() / 5 + "\n" +
                "敏捷DEX=3D6=" +
                cocCardInfo.getDex() + "/" +
                cocCardInfo.getDex() / 2 + "/" +
                cocCardInfo.getDex() / 5 + "\n" +
                "外貌APP=3D6=" +
                cocCardInfo.getApp() + "/" +
                cocCardInfo.getApp() / 2 + "/" +
                cocCardInfo.getApp() / 5 + "\n" +
                "智力INT=2D6+6=" +
                cocCardInfo.getInt() + "/" +
                cocCardInfo.getInt() / 2 + "/" +
                cocCardInfo.getInt() / 5 + "\n" +
                "意志POW=3D6=" +
                cocCardInfo.getPow() + "/" +
                cocCardInfo.getPow() / 2 + "/" +
                cocCardInfo.getPow() / 5 + "\n" +
                "教育EDU=3D6+3=" +
                cocCardInfo.getEdu() + "/" +
                cocCardInfo.getEdu() / 2 + "/" +
                cocCardInfo.getEdu() / 5 + "\n" +
                "共计:\n" +
                cocCardInfo.getNotLuck() + "为 " + String.valueOf((cocCardInfo.getNotLuck() * 1.0 / 147) * 100).substring(0, 5) + "% 强度(越高越好)\n" +
                "\n" + "\n" +
                "生命值HP=(SIZ+CON)/2(向上取整)=" +
                cocCardInfo.getHp() +
                "\n" +
                "理智SAN=POW*5=" +
                cocCardInfo.getSan() +
                "\n" +
                "灵感IDEA=INT*5=" +
                cocCardInfo.getIdea() +
                "\n" +
                "幸运LUCK=POW*5=" +
                cocCardInfo.getLuck() +
                "\n" +
                "知识KNOW=EDU*5=" +
                cocCardInfo.getKnow() +
                "\n" +
                "伤害奖励DB=" +
                cocCardInfo.getDb();
        sender(entityTypeMessages, stringBuilder);
    }

    /**
     * COC7版简易车卡，可以根据参数生成多张
     */
    public void coc7() {
        String tag = TAG_COC7;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 11));
        msg = deleteTag(msg, ".coc");

        int times = getTime(msg);

        String nick = getNickName(entityTypeMessages);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(nick)
                .append("的7版人物做成:");

        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            results.add("");
        }

        results = (ArrayList<String>) results.stream().parallel().map(s -> getCoc7CardInfo()).collect(Collectors.toList());
        for (String cocText:results){
            stringBuilder.append("\n")
                    .append(cocText);
        }
        sender(entityTypeMessages, stringBuilder.substring(0, stringBuilder.length() - 1));
    }

    /**
     * COC6版简易车卡，可以根据参数生成多张
     */
    public void coc6() {
        String tag = TAG_COC6;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 11));

        int times = getTime(msg);

        String nick = getNickName(entityTypeMessages);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nick)
                .append("的6版人物做成:");

        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            results.add("");
        }

        results = (ArrayList<String>) results.stream().parallel().map(s -> getCoc6CardInfo()).collect(Collectors.toList());
        for (String cocText:results){
            stringBuilder.append("\n")
                    .append(cocText);
        }
        sender(entityTypeMessages, stringBuilder.substring(0, stringBuilder.length() - 1));
    }
}
