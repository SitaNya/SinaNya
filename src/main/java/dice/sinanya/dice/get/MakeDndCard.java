package dice.sinanya.dice.get;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dice.sinanya.dice.get.imal.MakeCard;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;
import java.util.concurrent.*;

import static dice.sinanya.system.MessagesTag.tagDnd;
import static dice.sinanya.tools.GetFutureToString.getFutureToString;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;

/**
 * DND车卡类
 *
 * @author zhangxiaozhou
 */
public class MakeDndCard implements MakeCard {

    private EntityTypeMessages entityTypeMessages;

    public MakeDndCard(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void dnd() {
        String tag = tagDnd;
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
            results.add(exec.submit(new dice.sinanya.tools.MakeDndCard()));
        }

        String resDnd = getFutureToString(stringBuilder, results);
        exec.shutdown();
        sender(entityTypeMessages, resDnd.substring(0, resDnd.length() - 1));


    }

}
