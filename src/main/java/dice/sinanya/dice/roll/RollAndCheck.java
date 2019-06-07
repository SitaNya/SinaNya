package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityHistory;
import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.CheckResultLevel;
import dice.sinanya.tools.MakeRal;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.History.changeHistory;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.MakeRollCheckResult.makeResult;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class RollAndCheck {

    private EntityTypeMessages entityTypeMessages;

    public RollAndCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void ra() {
        String tag = tagRA;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), false);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                checkResultLevel.getResultLevel();
        changeHistory(entityTypeMessages.getFromQQ()).update(checkResultLevel.getLevelResult());
        sender(entityTypeMessages, stringBuilder);
    }

    public void rc() {
        String tag = tagRC;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), true);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                checkResultLevel.getResultLevel();
        changeHistory(entityTypeMessages.getFromQQ()).update(checkResultLevel.getLevelResult());
        sender(entityTypeMessages, stringBuilder);
    }

    public void ral() {
        String tag = tagRAL;
        EntityHistory entityHistory = new EntityHistory("0");
        StringBuilder stringBuilder = new StringBuilder();
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        if (!msg.contains(" ") || msg.split(" ").length != 2 || !isNumeric(msg.split(" ")[0]) || !isNumeric(msg.split(" ")[1])) {
            sender(entityTypeMessages, "请按照\".ral 值 次数\"的格式输入");
            return;
        }

        if (Integer.parseInt(msg.split(" ")[1]) > 1000) {
            sender(entityTypeMessages, "次数不能超过1000");
            return;
        }
        ExecutorService exec = Executors.newCachedThreadPool();//工头
        ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();//
        for (int i = 0; i < Integer.parseInt(msg.split(" ")[1]); i++) {
            results.add(exec.submit(new MakeRal(entityTypeMessages, msg.split(" ")[0])));//submit返回一个Future，代表了即将要返回的结果
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
                entityHistory.update((int) future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        exec.shutdown();
        stringBuilder
                .append("大成功:\t")
                .append(entityHistory.getCriticalSuccess())
                .append("次")
                .append("\n")

                .append("极难成功:\t")
                .append(entityHistory.getExtremeSuccess())
                .append("次")
                .append("\n")

                .append("困难成功:\t")
                .append(entityHistory.getHardSuccess())
                .append("次")
                .append("\n")

                .append("成功:\t")
                .append(entityHistory.getSuccess())
                .append("次")
                .append("\n")

                .append("失败:\t")
                .append(entityHistory.getFailure())
                .append("次")
                .append("\n")

                .append("大失败:\t")
                .append(entityHistory.getFumble())
                .append("次");
        sender(entityTypeMessages, stringBuilder.toString());
    }

    public void rcl() {
        String tag = tagRCL;
        EntityHistory entityHistory = new EntityHistory("0");
        StringBuilder stringBuilder = new StringBuilder();
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        if (!msg.contains(" ") || msg.split(" ").length != 2 || !isNumeric(msg.split(" ")[0]) || !isNumeric(msg.split(" ")[1])) {
            sender(entityTypeMessages, "请按照\".ral 值 次数\"的格式输入");
            return;
        }

        if (Integer.parseInt(msg.split(" ")[1]) > 1000) {
            sender(entityTypeMessages, "次数不能超过1000");
            return;
        }
        ExecutorService exec = Executors.newCachedThreadPool();//工头
        ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();//
        for (int i = 0; i < Integer.parseInt(msg.split(" ")[1]); i++) {
            results.add(exec.submit(new MakeRal(entityTypeMessages, msg.split(" ")[0])));//submit返回一个Future，代表了即将要返回的结果
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
                entityHistory.update((int) future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        exec.shutdown();
        stringBuilder
                .append("大成功:\t")
                .append(entityHistory.getCriticalSuccess())
                .append("次")
                .append("\n")

                .append("极难成功:\t")
                .append(entityHistory.getExtremeSuccess())
                .append("次")
                .append("\n")

                .append("困难成功:\t")
                .append(entityHistory.getHardSuccess())
                .append("次")
                .append("\n")

                .append("成功:\t")
                .append(entityHistory.getSuccess())
                .append("次")
                .append("\n")

                .append("失败:\t")
                .append(entityHistory.getFailure())
                .append("次")
                .append("\n")

                .append("大失败:\t")
                .append(entityHistory.getFumble())
                .append("次");
        sender(entityTypeMessages, stringBuilder.toString());
    }

}
