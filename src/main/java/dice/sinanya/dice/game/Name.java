package dice.sinanya.dice.game;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.GetNameTimesTooMoreException;
import dice.sinanya.exceptions.InputNameTimesForNumberException;
import dice.sinanya.exceptions.NotEnableException;
import org.apache.commons.lang.StringUtils;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityGame;
import static dice.sinanya.tools.getinfo.GetName.*;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-08-22
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Name {
    private EntityTypeMessages entityTypeMessages;

    int times = 1;
    StringBuilder nameList = new StringBuilder().append("您获取的随机姓名为:");

    public Name(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void random() throws NotEnableException {
        checkEnable();
        String tag = TAG_NAME;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 3));
        try {
            times = checkNameParam(msg);
        } catch (GetNameTimesTooMoreException | InputNameTimesForNumberException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
            return;
        }
        for (int i = 0; i < times; i++) {
            nameList.append("\n").append(getRandomName());
        }
        sender(entityTypeMessages, nameList.toString());
    }

    public void ch() throws NotEnableException {
        checkEnable();
        String tag = TAG_NAME_CH;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 3));
        try {
            times = checkNameParam(msg);
        } catch (GetNameTimesTooMoreException | InputNameTimesForNumberException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
            return;
        }
        for (int i = 0; i < times; i++) {
            nameList.append("\n").append(getChineseName());
        }
        sender(entityTypeMessages, nameList.toString());
    }

    public void en() throws NotEnableException {
        checkEnable();
        String tag = TAG_NAME_EN;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 3));
        try {
            times = checkNameParam(msg);
        } catch (GetNameTimesTooMoreException | InputNameTimesForNumberException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
            return;
        }
        for (int i = 0; i < times; i++) {
            nameList.append("\n").append(getEnglishName());
        }
        sender(entityTypeMessages, nameList.toString());
    }

    public void jp() throws NotEnableException {
        checkEnable();
        String tag = TAG_NAME_JP;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 3));
        try {
            times = checkNameParam(msg);
        } catch (GetNameTimesTooMoreException | InputNameTimesForNumberException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
            return;
        }
        for (int i = 0; i < times; i++) {
            nameList.append("\n").append(getJapaneseName());
        }
        sender(entityTypeMessages, nameList.toString());
    }

    private int checkNameParam(String msg) throws GetNameTimesTooMoreException, InputNameTimesForNumberException {
        if (msg.equals(NONE)) {
            return times;
        }
        if (!isNumeric(msg)) {
            throw new InputNameTimesForNumberException(entityTypeMessages);
        }
        if (Integer.parseInt(msg) > 10) {
            throw new GetNameTimesTooMoreException(entityTypeMessages);
        }
        return Integer.parseInt(msg);
    }

    private void checkEnable() throws NotEnableException {
        if (!entityGame.isNameSwitch()) {
            throw new NotEnableException(entityTypeMessages);
        }
    }
}
