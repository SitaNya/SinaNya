package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static dice.sinanya.system.MessagesTag.tagDnd;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;

public class MakeDndCard {

    private EntityTypeMessages entityTypeMessages;

    public MakeDndCard(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void dnd() {
        String tag = tagDnd;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 11));

        int times = 1;

        if (isNumeric(msg)) {
            times = Integer.parseInt(msg);
        }

        String nick = getNickName(entityTypeMessages);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nick)
                .append("的DND英雄做成:")
                .append("\n");

        ExecutorService exec = Executors.newCachedThreadPool();//工头
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();//
        for (int i = 0; i < times; i++) {
            results.add(exec.submit(new dice.sinanya.tools.MakeDndCard()));//submit返回一个Future，代表了即将要返回的结果
        }

        for (Future future : results) {
            while (!future.isDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                stringBuilder.append(future.get());
                stringBuilder.append("\n");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        exec.shutdown();
        sender(entityTypeMessages, stringBuilder.toString().substring(0, stringBuilder.length() - 1));


    }

}
