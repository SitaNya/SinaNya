package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.SaveDocx;
import org.docx4j.openpackaging.exceptions.Docx4JException;

import static dice.sinanya.system.MessagesLog.logNameForGroup;
import static dice.sinanya.system.MessagesLog.logSwitchForGroup;
import static dice.sinanya.system.MessagesLogGetLock.logGetLock;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.LogSave.logSave;
import static dice.sinanya.tools.LogTag.*;
import static dice.sinanya.tools.LogText.getLogText;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.SendMail.sendMail;
import static dice.sinanya.tools.Sender.sender;

public class Log {

    private EntityTypeMessages entityTypeMessages;

    public Log(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void logOn() {
        String tag = tagLogOn;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        if (checkOthorLogTrue(entityTypeMessages.getFromGroup())) {

            sender(entityTypeMessages, getOthorLogTrue(entityTypeMessages.getFromGroup()) + "已处于开启状态，请先关闭它");
        } else {
            if (checkLogTagExist(entityTypeMessages, msg)) {
                sender(entityTypeMessages, msg + "已在本群中存在，已为您重新激活日志，之后的消息将在原日志后追加");
            } else {
                sender(entityTypeMessages, msg + "已创建");
            }
            logNameForGroup.put(entityTypeMessages.getFromGroup(), msg);
            logSwitchForGroup.put(entityTypeMessages.getFromGroup(), true);
            setLogTagSwitch(entityTypeMessages, msg, true);
        }
    }

    public void logOff() {
        String tag = tagLogOff;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        if (checkLogTagExist(entityTypeMessages, msg)) {
            if (checkLogTagSwitch(entityTypeMessages, msg)) {
                setLogTagSwitch(entityTypeMessages, msg, false);
                logNameForGroup.remove(entityTypeMessages.getFromGroup());
                logSwitchForGroup.put(entityTypeMessages.getFromGroup(), false);
                sender(entityTypeMessages, msg + "已关闭，现在可以使用\".log get " + msg + "\"进行获取");
            } else {
                sender(entityTypeMessages, msg + "已处于关闭状态");
            }
        } else {
            sender(entityTypeMessages, "未在此群中找到此日志");
        }
    }

    public void get() {
        String tag = tagLogGet;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        if (!checkLogTagSwitch(entityTypeMessages, msg)) {
            if (logGetLock.contains(msg)) {
                sender(entityTypeMessages, "当前群内有人正在获取日志，请稍等");
            } else {
                logGetLock.add(msg);
            }
            String bigResult = getLogText(new EntityLogTag(entityTypeMessages.getFromGroup(), msg));
            sender(entityTypeMessages, "正在抽取数据库为" + msg + "生成文件");
            logSave(entityTypeMessages.getFromGroup(), msg, bigResult);
            sender(entityTypeMessages, "正在抽取数据库为" + msg + "生成染色文件");
            try {
                new SaveDocx(entityTypeMessages.getFromQQ(), entityTypeMessages.getFromGroup(), msg, bigResult);
            } catch (Docx4JException e) {
                e.printStackTrace();
            }
            sender(entityTypeMessages, msg + "正在发送到您的邮箱" + entityTypeMessages.getFromQQ() + "@qq.com");
            sendMail(entityTypeMessages.getFromQQ(), entityTypeMessages.getFromGroup(), msg);
            sender(entityTypeMessages, "[CQ:at,qq=" + entityTypeMessages.getFromQQ() + "] 已发送到您的QQ邮箱，注意查收");
            logGetLock.remove(msg);
        } else {
            sender(entityTypeMessages, msg + "仍处于打开状态，请关闭后再试");
        }
    }

    public void list() {
        sender(entityTypeMessages, getTagList(entityTypeMessages.getFromGroup()));
    }

    public void del() {
        String tag = tagLogDel;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        if (checkLogTagExist(entityTypeMessages, msg)) {
            if (checkLogTagSwitch(entityTypeMessages, msg)) {
                sender(entityTypeMessages, "您不可以删除未关闭的日志");
            } else {
                delLog(new EntityLogTag(entityTypeMessages.getFromGroup(), msg));
                sender(entityTypeMessages, "已删除日志: " + msg);
            }
        } else {
            sender(entityTypeMessages, "不存在日志: " + msg);
        }
    }
}
