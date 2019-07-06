package dice.sinanya.dice.get;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dice.sinanya.dice.get.imal.MakeCard;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;
import java.util.concurrent.*;

import static dice.sinanya.system.MessagesTag.TAG_DND;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.makedata.GetFutureToString.getFutureToString;
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
public class MakeDndCard implements MakeCard {

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
        stringBuilder.append(nick)
                .append("的DND英雄做成:")
                .append("\n");

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("dnd-make-card-%d").build();
        ExecutorService exec = new ThreadPoolExecutor(times, times, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            results.add(exec.submit(new dice.sinanya.tools.makedata.MakeDndCard()));
        }

        String resDnd = getFutureToString(stringBuilder, results);
        exec.shutdownNow();
        sender(entityTypeMessages, resDnd.substring(0, resDnd.length() - 1));


    }

}
