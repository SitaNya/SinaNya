package dice.sinanya.dice.roll;

import dice.sinanya.dice.MakeNickToSender;
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
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesAntagonize.ANTAGONIZE;
import static dice.sinanya.system.MessagesSystem.SPACE;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.tools.getinfo.GetNickName.getGroupName;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.History.changeHistory;
import static dice.sinanya.tools.getinfo.Kp.getKpGroup;
import static dice.sinanya.tools.makedata.GetNickAndRandomAndSkill.getNickAndRandomAndSkill;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: ra、rc判定，ral、rcl多次骰点，rav、rcv对抗
 */
public class RollAndCheck implements En, MakeNickToSender {


    private String defaultGroupId = "0";

    private EntityTypeMessages entityTypeMessages;

    public RollAndCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 房规判定
     */
    public void ra() {
        String tag = TAG_RA;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2)).replaceAll(" +", "");
        String result = check(msg, false);
        sender(entityTypeMessages, result);
    }

    /**
     * 规则书判定
     */
    public void rc() {
        String tag = TAG_RC;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2)).replaceAll(" +", "").replaceAll(" +", "");
        String result = check(msg, true);
        sender(entityTypeMessages, result);
    }


    /**
     * 房规对抗
     *
     * @throws NotSetKpGroupException 如果私聊未设置kp主群，则会报此错误
     */
    public void rav() throws NotSetKpGroupException {
        String tag = TAG_RAV;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2)).replaceAll(" +", "");
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, msg);
        if (entityNickAndRandomAndSkill.getSkill() == 0) {
            sender(entityTypeMessages, "请输入技能名或技能值");
            return;
        }
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityNickAndRandomAndSkill.getRandom(), entityNickAndRandomAndSkill.getSkill(), false);
        //        使用房规进行判定结果
        StringBuilder stringBuilder = new StringBuilder()
                .append(makeNickToSender(entityNickAndRandomAndSkill.getNick()))
                .append("进行鉴定: D100=")
                .append(entityNickAndRandomAndSkill.getRandom())
                .append("/")
                .append(entityNickAndRandomAndSkill.getSkill())
                .append(checkResultLevel.getLevelResultStr());
        String groupId = makeVforGroupId(msg, checkResultLevel);
        if (ANTAGONIZE.containsKey(groupId) && !groupId.equals(defaultGroupId)) {
            EntityAntagonize entityAntagonize = ANTAGONIZE.get(groupId);
            EntityAntagonize thisEntityAntagonize = checkResultLevel.getAntagonize();

            sender(entityTypeMessages, stringBuilder.toString());
            checkAntagonize(entityTypeMessages, thisEntityAntagonize, entityAntagonize, groupId);
            ANTAGONIZE.remove(groupId);
            CQ.sendGroupMsg(Long.parseLong(groupId), entitySystemProperties.getAntagonizeOver());
        } else if (!groupId.equals(defaultGroupId)) {
            sender(entityTypeMessages, stringBuilder.toString());
            ANTAGONIZE.put(groupId, checkResultLevel.getAntagonize());
            CQ.sendGroupMsg(Long.parseLong(groupId), makeNickToSender(getNickName(entityTypeMessages)) + "发起一次对抗");
        } else {
            throw new NotSetKpGroupException(entityTypeMessages);
        }
    }

    /**
     * 规则书对抗
     *
     * @throws NotSetKpGroupException 如果私聊未设置kp主群，则会报此错误
     */
    public void rcv() throws NotSetKpGroupException {
        String tag = TAG_RCV;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2)).replaceAll(" +", "");
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, msg);
        if (entityNickAndRandomAndSkill.getSkill() == 0) {
            sender(entityTypeMessages, "请输入技能名或技能值");
            return;
        }
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityNickAndRandomAndSkill.getRandom(), entityNickAndRandomAndSkill.getSkill(), true);
//                使用规则书进行判定结果
        StringBuilder stringBuilder = new StringBuilder()
                .append(makeNickToSender(entityNickAndRandomAndSkill.getNick()))
                .append("进行鉴定: D100=")
                .append(entityNickAndRandomAndSkill.getRandom())
                .append("/")
                .append(entityNickAndRandomAndSkill.getSkill())
                .append(checkResultLevel.getLevelResultStr());
        String groupId = makeVforGroupId(msg, checkResultLevel);
        if (ANTAGONIZE.containsKey(groupId) && !groupId.equals(defaultGroupId)) {
//            静态对象Antagonize中包含了以群号为key的EntityAntagonize对象，如果包含的话，那么就说明上一次对抗已经发起了，这次直接给结果
            EntityAntagonize entityAntagonize = ANTAGONIZE.get(groupId);
            EntityAntagonize thisEntityAntagonize = checkResultLevel.getAntagonize();
            sender(entityTypeMessages, stringBuilder.toString());
            checkAntagonize(entityTypeMessages, thisEntityAntagonize, entityAntagonize, groupId);
            ANTAGONIZE.remove(groupId);
            CQ.sendGroupMsg(Long.parseLong(groupId), entitySystemProperties.getAntagonizeOver());
        } else if (!groupId.equals(defaultGroupId)) {
//            静态对象Antagonize中包含了以群号为key的EntityAntagonize对象，如果不包含的话，那么就说明这次是发起对抗，直接插入进去
            CQ.sendGroupMsg(Long.parseLong(groupId), makeNickToSender(getNickName(entityTypeMessages)) + "发起一次对抗");
            sender(entityTypeMessages, stringBuilder.toString());
            ANTAGONIZE.put(groupId, checkResultLevel.getAntagonize());
        } else {
            throw new NotSetKpGroupException(entityTypeMessages);
        }
    }


    /**
     * 房规多次骰点
     */
    public void ral() {
        String tag = TAG_RAL;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        try {
            ArrayList<Integer> rollsList = initRalAndRcl(msg);
            EntityHistory entityHistory = new EntityHistory("0");
            rollsList = (ArrayList<Integer>) rollsList.stream().parallel().map(s -> new MakeRal(entityTypeMessages, msg.split(" ")[0]).call()).collect(Collectors.toList());
            updateHistory(entityHistory, rollsList);
            formatRxlAndSend(entityHistory);
        } catch (ManyRollsFormatException | ManyRollsTimesTooMoreException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * 规则书多次骰点
     */
    public void rcl() {
        String tag = TAG_RCL;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        try {
            ArrayList<Integer> rollsList = initRalAndRcl(msg);
            EntityHistory entityHistory = new EntityHistory("0");

            rollsList = (ArrayList<Integer>) rollsList.stream().parallel().map(s -> new MakeRcl(entityTypeMessages, msg.split(" ")[0]).call()).collect(Collectors.toList());
            updateHistory(entityHistory, rollsList);
            formatRxlAndSend(entityHistory);
        } catch (ManyRollsFormatException | ManyRollsTimesTooMoreException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * @param msg      输入信息，可能包含骰点表达式、技能等
     * @param ruleBook 是否使用规则书
     * @return 包装后的骰点结果字符串
     */
    private String check(String msg, Boolean ruleBook) {
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityNickAndRandomAndSkill.getRandom(), entityNickAndRandomAndSkill.getSkill(), ruleBook);
        String result = makeNickToSender(entityNickAndRandomAndSkill.getNick()) +
                "进行" + msg + "鉴定: D100=" + entityNickAndRandomAndSkill.getRandom() + "/" + entityNickAndRandomAndSkill.getSkill() +
                checkResultLevel.getLevelResultStr();
        checkEn(checkResultLevel.getLevel(), msg, entityTypeMessages.getFromQq(), entityTypeMessages.getFromGroup());
        changeHistory(entityTypeMessages.getFromQq()).update(checkResultLevel.getLevelAndRandom());
        return result;
    }

    /**
     * 根据本次和上次对抗结果，分别对比成功等级->骰点大小->技能上限。返回包装后的对抗结果
     *
     * @param entityTypeMessages 包装信息类，包含发送消息用的方法
     * @param thisAntagonize     这次对抗的骰点结果
     * @param lastAntagonize     已存储的对抗骰点结果
     * @param groupId            群号
     */
    private void checkAntagonize(EntityTypeMessages entityTypeMessages, EntityAntagonize thisAntagonize, EntityAntagonize lastAntagonize, String groupId) {
        int successMinLevel = 2;
        if (lastAntagonize.getLevel() > thisAntagonize.getLevel()) {
            CQ.sendGroupMsg(Long.parseLong(groupId), entitySystemProperties.getAntagonizeFirstSuccess());
        } else if (lastAntagonize.getLevel() == thisAntagonize.getLevel()) {
            if (lastAntagonize.getLevel() < successMinLevel && thisAntagonize.getLevel() < successMinLevel) {
                CQ.sendGroupMsg(Long.parseLong(groupId), entitySystemProperties.getAntagonizeAllFailed());
            } else if (lastAntagonize.getRandom() < thisAntagonize.getRandom()) {
                CQ.sendGroupMsg(Long.parseLong(groupId), entitySystemProperties.getAntagonizeFirstSuccess());
            } else if (lastAntagonize.getSkill() > thisAntagonize.getSkill()) {
                CQ.sendGroupMsg(Long.parseLong(groupId), entitySystemProperties.getAntagonizeFirstSuccess());
            } else if (lastAntagonize.getSkill() == thisAntagonize.getSkill()) {
                CQ.sendGroupMsg(Long.parseLong(groupId), entitySystemProperties.getAntagonizeDraw());
            }
        } else {
            CQ.sendGroupMsg(Long.parseLong(groupId), entitySystemProperties.getAntagonizeSecondSuccess());
        }
    }

    /**
     * @param msg 检查骰点次数是否超过限制
     * @throws ManyRollsTimesTooMoreException 骰点次数太多
     * @throws ManyRollsFormatException       骰点表达式不合规范
     */
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

    /**
     * 等待多线程骰点的结果被标记位isDone后，将结果更新到一个临时的骰点信息中
     *
     * @param entityHistory 临时的骰点信息，用于计算本次的各种成功次数
     * @param results       多线程骰点的对象列表
     */
    private void updateHistory(EntityHistory entityHistory, ArrayList<Integer> results) {
        for (int result : results) {
            entityHistory.update(result);
        }
    }

    /**
     * 将临时骰点信息包装成回复语发送
     *
     * @param entityHistory 临时的骰点信息，用于计算本次的各种成功次数
     */
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

    private String makeVforGroupId(String msg, CheckResultLevel checkResultLevel) {
        checkEn(checkResultLevel.getLevel(), msg, entityTypeMessages.getFromQq(), entityTypeMessages.getFromGroup());
        changeHistory(entityTypeMessages.getFromQq()).update(checkResultLevel.getLevelAndRandom());
        String groupId;
        if (entityTypeMessages.getFromGroup().equals(defaultGroupId)) {
            try {
                groupId = getKpGroup(entityTypeMessages);
                sender(entityTypeMessages, "本次对抗将用于群" + makeGroupNickToSender(getGroupName(groupId)) + "(" + groupId + ")");
            } catch (NotSetKpGroupException e) {
                CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
                groupId = "0";
            }
        } else {
            groupId = entityTypeMessages.getFromGroup();
        }
        return groupId;
    }

    private ArrayList<Integer> initRalAndRcl(String msg) throws ManyRollsFormatException, ManyRollsTimesTooMoreException {
            checkManyRollsError(msg);

        ArrayList<Integer> rollsList = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(msg.split(SPACE)[1]); i++) {
            rollsList.add(0);
        }
        return rollsList;
    }

}
