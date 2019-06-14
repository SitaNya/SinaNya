package dice.sinanya.dice.roll;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dice.sinanya.entity.EntityAntagonize;
import dice.sinanya.entity.EntityHistory;
import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.ManyRollsFormatError;
import dice.sinanya.exceptions.ManyRollsTimesTooMore;
import dice.sinanya.exceptions.NotSetKpGroupException;
import dice.sinanya.tools.CheckResultLevel;
import dice.sinanya.tools.MakeRal;
import dice.sinanya.tools.MakeRcl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.*;

import static dice.sinanya.system.MessagesAntagonize.Antagonize;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.History.changeHistory;
import static dice.sinanya.tools.Kp.getKpGroup;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.MakeRollCheckResult.makeResult;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author SitaNya
 */
public class RollAndCheck {
    private static final Logger Log = LogManager.getLogger(RollAndCheck.class);

    private EntityTypeMessages entityTypeMessages;

    public RollAndCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void ra() {
        String tag = TAG_RA;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String result = check(msg, false);
        sender(entityTypeMessages, result);
    }

    public void rc() {
        String tag = TAG_RC;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String result = check(msg, true);
        sender(entityTypeMessages, result);
    }


    public void rav() {
        String tag = TAG_RAV;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), false);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                checkResultLevel.getLevelResultStr();
        changeHistory(entityTypeMessages.getFromQQ()).update(checkResultLevel.getLevelAndRandom());
        String groupId;
        if (entityTypeMessages.getFromGroup().equals("0")) {
            try {
                groupId = getKpGroup(entityTypeMessages);
                sender(entityTypeMessages, "本次对抗将用于群" + groupId);
            } catch (NotSetKpGroupException e) {
                e.printStackTrace();
                groupId = "0";
            }
        } else {
            groupId = entityTypeMessages.getFromGroup();

        }
        if (Antagonize.containsKey(groupId) && !groupId.equals("0")) {
            EntityAntagonize entityAntagonize = Antagonize.get(groupId);
            EntityAntagonize thisEntityAntagonize = checkResultLevel.getAntagonize();

            sender(entityTypeMessages, stringBuilder);
            checkAntagonize(entityTypeMessages, thisEntityAntagonize, entityAntagonize, groupId);
            Antagonize.remove(groupId);
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, "对抗结束");
        } else if (!groupId.equals("0")) {
            sender(entityTypeMessages, stringBuilder);
            Antagonize.put(groupId, checkResultLevel.getAntagonize());
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, getNickName(entityTypeMessages) + "发起一次对抗");
        } else {
            sender(entityTypeMessages, "您需要使用.kp设定kp群后才可以在私聊中回应或进行对抗");
        }
    }

    public void rcv() {
        String tag = TAG_RCV;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), true);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                checkResultLevel.getLevelResultStr();
        changeHistory(entityTypeMessages.getFromQQ()).update(checkResultLevel.getLevelAndRandom());
        String groupId;
        if (entityTypeMessages.getFromGroup().equals("0")) {
            try {
                groupId = getKpGroup(entityTypeMessages);

            } catch (NotSetKpGroupException e) {
                e.printStackTrace();
                groupId = "0";
            }
        } else {
            groupId = entityTypeMessages.getFromGroup();
        }
        if (Antagonize.containsKey(groupId) && !groupId.equals("0")) {
            EntityAntagonize entityAntagonize = Antagonize.get(groupId);
            EntityAntagonize thisEntityAntagonize = checkResultLevel.getAntagonize();
            sender(entityTypeMessages, stringBuilder);
            checkAntagonize(entityTypeMessages, thisEntityAntagonize, entityAntagonize, groupId);
            Antagonize.remove(groupId);
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, "对抗结束");
        } else if (!groupId.equals("0")) {
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, getNickName(entityTypeMessages) + "发起一次对抗");
            sender(entityTypeMessages, stringBuilder);
            Antagonize.put(groupId, checkResultLevel.getAntagonize());
        } else {
            sender(entityTypeMessages, "您需要使用.kp设定kp群后才可以在私聊中回应或进行对抗");
        }
    }

    public void ral() {
        String tag = TAG_RAL;
        EntityHistory entityHistory = new EntityHistory("0");
        StringBuilder stringBuilder = new StringBuilder();
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        try {
            checkManyRollsError(msg);
        } catch (ManyRollsTimesTooMore | ManyRollsFormatError manyRollsTimesTooMore) {
            Log.error(manyRollsTimesTooMore.getMessage(), manyRollsTimesTooMore);
        }
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("coc-ral-%d").build();
        ExecutorService exec = new ThreadPoolExecutor(Integer.parseInt(msg.split(" ")[1]), Integer.parseInt(msg.split(" ")[1]), 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(msg.split(" ")[1]); i++) {
            results.add(exec.submit(new MakeRal(entityTypeMessages, msg.split(" ")[0])));
            //submit返回一个Future，代表了即将要返回的结果
        }

        try {
            updateHistory(entityHistory, results);
        } catch (InterruptedException | ExecutionException e) {
            Log.error(e.getMessage(), e);
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
        String tag = TAG_RCL;
        EntityHistory entityHistory = new EntityHistory("0");
        StringBuilder stringBuilder = new StringBuilder();
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        try {
            checkManyRollsError(msg);
        } catch (ManyRollsTimesTooMore | ManyRollsFormatError manyRollsTimesTooMore) {
            Log.error(manyRollsTimesTooMore.getMessage(), manyRollsTimesTooMore);
        }
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("coc-rcl-%d").build();
        ExecutorService exec = new ThreadPoolExecutor(Integer.parseInt(msg.split(" ")[1]), Integer.parseInt(msg.split(" ")[1]), 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(msg.split(" ")[1]); i++) {
            results.add(exec.submit(new MakeRcl(entityTypeMessages, msg.split(" ")[0])));
            //submit返回一个Future，代表了即将要返回的结果
        }

        try {
            updateHistory(entityHistory, results);
        } catch (InterruptedException | ExecutionException e) {
            Log.error(e.getMessage(), e);
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

    private String check(String msg, Boolean ruleBook) {
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), ruleBook);
        String result = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                checkResultLevel.getLevelResultStr();
        changeHistory(entityTypeMessages.getFromQQ()).update(checkResultLevel.getLevelAndRandom());
        return result;
    }

    private void checkAntagonize(EntityTypeMessages entityTypeMessages, EntityAntagonize thisAntagonize, EntityAntagonize lastAntagonize, String groupId) {
        if (lastAntagonize.getLevel() > thisAntagonize.getLevel()) {
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, "先手胜利");
        } else if (lastAntagonize.getLevel() == thisAntagonize.getLevel()) {
            if (lastAntagonize.getLevel() < 2 && thisAntagonize.getLevel() < 2) {
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, "全部失败，平手");
            } else if (lastAntagonize.getRandom() < thisAntagonize.getRandom()) {
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, "先手胜利");
            } else if (lastAntagonize.getSkill() > thisAntagonize.getSkill()) {
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, "先手胜利");
            } else if (lastAntagonize.getSkill() == thisAntagonize.getSkill()) {
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, "平手");
            }
        } else {
            sender(entityTypeMessages, "后手胜利");
        }
    }

    private void checkManyRollsError(String msg) throws ManyRollsTimesTooMore, ManyRollsFormatError {
        String space = " ";
        int numParams = 2;
        int maxTimes = 1000;
        if (!msg.contains(space) || msg.split(space).length != numParams || !isNumeric(msg.split(space)[0]) || !isNumeric(msg.split(space)[1])) {
            throw new ManyRollsFormatError(entityTypeMessages);
        }

        if (Integer.parseInt(msg.split(space)[1]) > maxTimes) {
            throw new ManyRollsTimesTooMore(entityTypeMessages);
        }
    }

    private void updateHistory(EntityHistory entityHistory, ArrayList<Future<Integer>> results) throws InterruptedException, ExecutionException {
        for (Future future : results) {
            Thread.sleep(100);
            entityHistory.update((int) future.get());
        }
    }

}
