package dice.sinanya.dice.get;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.dice.get.imal.MakeCard;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static dice.sinanya.system.MessagesTag.TAG_DND;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.makedata.MakeDndCardInfo.makeDndCardInfo;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: DND车卡
 */
public class MakeDndCard implements MakeCard, MakeNickToSender {

    private EntityTypeMessages entityTypeMessages;

    public MakeDndCard(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * DND简易车卡，可以根据参数生成多张
     */
    public void dnd() {
        String tag = TAG_DND;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 11));

        int times = getTime(msg);

        String nick = getNickName(entityTypeMessages);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeNickToSender(nick))
                .append("的DND英雄做成:");

        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            results.add("");
        }

        results = (ArrayList<String>) results.stream().parallel().map(s -> makeDndCardInfo()).collect(Collectors.toList());
        for (String dndText : results) {
            stringBuilder.append("\n")
                    .append(dndText);
        }
        sender(entityTypeMessages, stringBuilder.toString());


    }

}
