package dice.sinanya.dice.roll;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dice.sinanya.entity.EntityAntagonize;
import dice.sinanya.entity.EntityHistory;
import dice.sinanya.entity.EntityNickAndRandomAndSkill;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.ManyRollsFormatException;
import dice.sinanya.exceptions.ManyRollsTimesTooMoreException;
import dice.sinanya.exceptions.NotSetKpGroupException;
import dice.sinanya.tools.checkdata.CheckResultLevel;
import dice.sinanya.tools.makedata.MakeRal;
import dice.sinanya.tools.makedata.MakeRcl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.*;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.system.MessagesAntagonize.Antagonize;
import static dice.sinanya.system.MessagesSystem.SPACE;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.History.changeHistory;
import static dice.sinanya.tools.getinfo.Kp.getKpGroup;
import static dice.sinanya.tools.log.Sender.sender;
import static dice.sinanya.tools.makedata.GetNickAndRandomAndSkill.getNickAndRandomAndSkill;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;

/**
 * @author SitaNya
 */
public class RollAndCheck {
    private static final Logger Log = LogManager.getLogger(RollAndCheck.class);

    private String defaultGroupId = "0";

    private EntityTypeMessages entityTypeMessages;

    private ArrayList<Future<Integer>> results = new ArrayList<>();

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


    public void rav() throws NotSetKpGroupException {
        String tag = TAG_RAV;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityNickAndRandomAndSkill.getRandom(), entityNickAndRandomAndSkill.getSkill(), false);
        //        使用房规进行判定结果
        String stringBuilder = entityNickAndRandomAndSkill.getNick() +
                "进行鉴定: D100=" + entityNickAndRandomAndSkill.getRandom() + "/" + entityNickAndRandomAndSkill.getSkill() +
                checkResultLevel.getLevelResultStr();
        changeHistory(entityTypeMessages.getFromQq()).update(checkResultLevel.getLevelAndRandom());
        String groupId;
        if (entityTypeMessages.getFromGroup().equals(defaultGroupId)) {
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
        if (Antagonize.containsKey(groupId) && !groupId.equals(defaultGroupId)) {
            EntityAntagonize entityAntagonize = Antagonize.get(groupId);
            EntityAntagonize thisEntityAntagonize = checkResultLevel.getAntagonize();

            sender(entityTypeMessages, stringBuilder);
            checkAntagonize(entityTypeMessages, thisEntityAntagonize, entityAntagonize, groupId);
            Antagonize.remove(groupId);
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, messagesSystem.get("antagonizeOver"));
        } else if (!groupId.equals(defaultGroupId)) {
            sender(entityTypeMessages, stringBuilder);
            Antagonize.put(groupId, checkResultLevel.getAntagonize());
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, getNickName(entityTypeMessages) + "发起一次对抗");
        } else {
            throw new NotSetKpGroupException(entityTypeMessages);
        }
    }

    public void rcv() throws NotSetKpGroupException {
        String tag = TAG_RCV;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityNickAndRandomAndSkill.getRandom(), entityNickAndRandomAndSkill.getSkill(), true);
//                使用规则书进行判定结果
        String stringBuilder = entityNickAndRandomAndSkill.getNick() +
                "进行鉴定: D100=" + entityNickAndRandomAndSkill.getRandom() + "/" + entityNickAndRandomAndSkill.getSkill() +
                checkResultLevel.getLevelResultStr();
        changeHistory(entityTypeMessages.getFromQq()).update(checkResultLevel.getLevelAndRandom());
        String groupId;
        if (entityTypeMessages.getFromGroup().equals(defaultGroupId)) {
            try {
                groupId = getKpGroup(entityTypeMessages);

            } catch (NotSetKpGroupException e) {
                e.printStackTrace();
                groupId = "0";
            }
        } else {
            groupId = entityTypeMessages.getFromGroup();
        }
        if (Antagonize.containsKey(groupId) && !groupId.equals(defaultGroupId)) {
            EntityAntagonize entityAntagonize = Antagonize.get(groupId);
            EntityAntagonize thisEntityAntagonize = checkResultLevel.getAntagonize();
            sender(entityTypeMessages, stringBuilder);
            checkAntagonize(entityTypeMessages, thisEntityAntagonize, entityAntagonize, groupId);
            Antagonize.remove(groupId);
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, messagesSystem.get("antagonizeOver"));
        } else if (!groupId.equals(defaultGroupId)) {
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, getNickName(entityTypeMessages) + "发起一次对抗");
            sender(entityTypeMessages, stringBuilder);
            Antagonize.put(groupId, checkResultLevel.getAntagonize());
        } else {
            throw new NotSetKpGroupException(entityTypeMessages);
        }
    }

    public void ral() {
        String tag = TAG_RAL;
        EntityHistory entityHistory = new EntityHistory("0");
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        try {
            checkManyRollsError(msg);
        } catch (ManyRollsTimesTooMoreException | ManyRollsFormatException manyRollsTimesTooMoreException) {
            Log.error(manyRollsTimesTooMoreException.getMessage(), manyRollsTimesTooMoreException);
        }
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("coc-ral-%d").build();
        ExecutorService exec = new ThreadPoolExecutor(Integer.parseInt(msg.split(" ")[1]), Integer.parseInt(msg.split(" ")[1]), 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
//        声明一个多线程池coc-ral用于包装多次骰掷
        for (int i = 0; i < Integer.parseInt(msg.split(SPACE)[1]); i++) {
            results.add(exec.submit(new MakeRal(entityTypeMessages, msg.split(" ")[0])));
            //将MakeRal类加入多线程池以保证速度
        }

        try {
            updateHistory(entityHistory, results);
        } catch (InterruptedException | ExecutionException e) {
            Log.error(e.getMessage(), e);
        }

        exec.shutdown();
        formatRxlAndSend(entityHistory);
    }

    public void rcl() {
        String tag = TAG_RCL;
        EntityHistory entityHistory = new EntityHistory("0");
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        try {
            checkManyRollsError(msg);
        } catch (ManyRollsTimesTooMoreException | ManyRollsFormatException manyRollsTimesTooMoreException) {
            Log.error(manyRollsTimesTooMoreException.getMessage(), manyRollsTimesTooMoreException);
        }

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("coc-rcl-%d").build();
        ExecutorService exec = new ThreadPoolExecutor(Integer.parseInt(msg.split(" ")[1]), Integer.parseInt(msg.split(" ")[1]), 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
        //        声明一个多线程池coc-rcl用于包装多次骰掷

        for (int i = 0; i < Integer.parseInt(msg.split(SPACE)[1]); i++) {
            results.add(exec.submit(new MakeRcl(entityTypeMessages, msg.split(" ")[0])));
            //submit返回一个Future，代表了即将要返回的结果
        }

        try {
            updateHistory(entityHistory, results);
        } catch (InterruptedException | ExecutionException e) {
            Log.error(e.getMessage(), e);
        }
        exec.shutdown();
        formatRxlAndSend(entityHistory);
    }

    private String check(String msg, Boolean ruleBook) {
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityNickAndRandomAndSkill.getRandom(), entityNickAndRandomAndSkill.getSkill(), ruleBook);
        String result = entityNickAndRandomAndSkill.getNick() +
                "进行鉴定: D100=" + entityNickAndRandomAndSkill.getRandom() + "/" + entityNickAndRandomAndSkill.getSkill() +
                checkResultLevel.getLevelResultStr();
        changeHistory(entityTypeMessages.getFromQq()).update(checkResultLevel.getLevelAndRandom());
        return result;
    }

    private void checkAntagonize(EntityTypeMessages entityTypeMessages, EntityAntagonize thisAntagonize, EntityAntagonize lastAntagonize, String groupId) {
        int successMinLevel = 2;
        if (lastAntagonize.getLevel() > thisAntagonize.getLevel()) {
            entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, messagesSystem.get("antagonizeFirstSuccess"));
        } else if (lastAntagonize.getLevel() == thisAntagonize.getLevel()) {
            if (lastAntagonize.getLevel() < successMinLevel && thisAntagonize.getLevel() < successMinLevel) {
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, messagesSystem.get("antagonizeAllFailed"));
            } else if (lastAntagonize.getRandom() < thisAntagonize.getRandom()) {
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, messagesSystem.get("antagonizeFirstSuccess"));
            } else if (lastAntagonize.getSkill() > thisAntagonize.getSkill()) {
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, messagesSystem.get("antagonizeFirstSuccess"));
            } else if (lastAntagonize.getSkill() == thisAntagonize.getSkill()) {
                entityTypeMessages.getMsgSender().SENDER.sendGroupMsg(groupId, messagesSystem.get("antagonizeDraw"));
            }
        } else {
            sender(entityTypeMessages, messagesSystem.get("antagonizeSecondSuccess"));
        }
    }

    private void checkManyRollsError(String msg) throws ManyRollsTimesTooMoreException, ManyRollsFormatException {
        int numParams = 2;
        int maxTimes = 1000;
        if (!msg.contains(SPACE) || msg.split(SPACE).length != numParams || !isNumeric(msg.split(SPACE)[0]) || !isNumeric(msg.split(SPACE)[1])) {
            throw new ManyRollsFormatException(entityTypeMessages);
        }

        if (Integer.parseInt(msg.split(SPACE)[1]) > maxTimes) {
            throw new ManyRollsTimesTooMoreException(entityTypeMessages);
        }
    }

    private void updateHistory(EntityHistory entityHistory, ArrayList<Future<Integer>> results) throws InterruptedException, ExecutionException {
        for (Future future : results) {
            Thread.sleep(100);
            entityHistory.update((int) future.get());
        }
    }

    private void formatRxlAndSend(EntityHistory entityHistory) {
        String stringBuilder = "大成功:\t" +
                entityHistory.getCriticalSuccess() +
                "次" +
                "\n" +
                "极难成功:\t" +
                entityHistory.getExtremeSuccess() +
                "次" +
                "\n" +
                "困难成功:\t" +
                entityHistory.getHardSuccess() +
                "次" +
                "\n" +
                "成功:\t" +
                entityHistory.getSuccess() +
                "次" +
                "\n" +
                "失败:\t" +
                entityHistory.getFailure() +
                "次" +
                "\n" +
                "大失败:\t" +
                entityHistory.getFumble() +
                "次";
        sender(entityTypeMessages, stringBuilder);
    }

}
