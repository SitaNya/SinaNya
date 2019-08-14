package dice.sinanya.entity;


public class EntitySystemProperties {
    String NONE = "";
    String botStart;
    String botAlreadyStart;
    String botStop;
    String botAlreadyStop;
    String botExit;
    String botInfo;

    String bookCard;
    String bookRp;
    String bookKp;
    String bookMake;

    String manyRollsFormat;
    String diceTimesTooBig;

    String setPropFormat;
    String setHelp;
    String NotFoundSkill;
    String setPropSuccess;

    String dndInitIsEmtpy;
    String clrDndInit;

    String needKpGroup;

    String cantInPrivate;
    String onlyManager;

    String alreadyOpen;
    String alreadyClose;
    String notFoundLog;
    String readLock;
    String deleteOpenLog;

    String sanCheck;

    String antagonizeOver;
    String antagonizeFirstSuccess;
    String antagonizeSecondSuccess;
    String antagonizeAllFailed;
    String antagonizeDraw;

    String symptom;

    String enSuccess;
    String enFailed;

    String hiddenDice;

    String teamIsEmpty;
    String teamMemberEnIsEmpty;

    String appendLog;
    String createLog;
    String CantEmptyLogName;

    String sanCheckFumble;
    String sanCheckCriticalSuccess;
    String sanCheckSuccess;
    String sanCheckFailure;

    String CRITICAL_SUCCESS;
    String EXTREME_SUCCESS;
    String HARD_SUCCESS;
    String SUCCESS;
    String FAILURE;
    String FUMBLE;

    String systemDir;

    public EntitySystemProperties() {
        botStart = "机器人已开启";
        botAlreadyStart = "机器人当前处于开启状态";
        botStop = "机器人已关闭";
        botAlreadyStop = "机器人当前处于关闭状态";
        botExit = "正在退出群";
        botInfo = "";

        bookCard = "COC七版规则空白卡奈梅斯·西莉亚私人订制版By贝尔巨佬.xlsx\n请使用此链接下载https://pan.baidu.com/s/1M3veskXYFJjwXP1eKHaX4g。有更新更好的版本请随时联系窝。";
        bookRp = "角色扮演三百六十五问.zip\n请使用此链接下载https://share1.heiluo.com/share/link/8b93f1f15a974d4a9fda1890863a0af1。有更新更好的版本请随时联系窝。";
        bookKp = "克苏鲁的呼唤第七版守秘人规则书 Version1901.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/e88daa8d6565440cbff0a8f7c9c8fe29。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/12XSQc9EEBsfEhhQEesc6nw";
        bookMake = "车卡指南.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/0e1083cdb8144b109be07fd4ef09b082。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/1HHo1B1F9kMRIFDB8J9Ulww 提取码：ql5h";

        manyRollsFormat = "请按照\".ral 值 次数\"的格式输入";
        diceTimesTooBig = "骰点次数过多";

        setPropFormat = "请符合格式.st角色名-力量50体质60";
        setHelp = ".st命令使用错误，请使用.help make命令查看具体用法";
        NotFoundSkill = "您未设置角色卡";

        dndInitIsEmtpy = "先攻列表为空";

        needKpGroup = "未设置kp群";

        cantInPrivate = "此命令私聊不可用";
        onlyManager = "此命令仅群主或管理员可以使用";

        alreadyOpen = "日志%s已经处于开启状态，无法再次开启";
        alreadyClose = "日志%s已经处于关闭状态，无法再次关闭";
        notFoundLog = "未找到日志%s";
        readLock = "正在有人取日志，请稍后";
        deleteOpenLog = "无法删除打开的日志%s，请先关闭";

        sanCheck = "请符合1/1d3|1d6/1d3|0/1这样的san check格式";

        setPropSuccess = "已记录人物卡";

        clrDndInit = "清空先攻列表";

        antagonizeOver = "对抗结束";
        antagonizeFirstSuccess = "先手胜利";
        antagonizeSecondSuccess = "后手胜利";
        antagonizeAllFailed = "全部失败";
        antagonizeDraw = "平手";

        symptom = "";

        enSuccess = "";
        enFailed = "";

        hiddenDice = "kp正在进行暗骰";

        teamIsEmpty = "您的小队为空";
        teamMemberEnIsEmpty = "的技能成功记录为空";


        appendLog = "日志%s已重新开启，将在原基础上追加";
        createLog = "日志%s已创建";
        CantEmptyLogName = "不支持空日志名";

        sanCheckFumble = "";
        sanCheckCriticalSuccess = "";
        sanCheckSuccess = "";
        sanCheckFailure = "";

        CRITICAL_SUCCESS = "大成功";
        EXTREME_SUCCESS = "极难成功";
        HARD_SUCCESS = "困难成功";
        SUCCESS = "成功";
        FAILURE = "失败";
        FUMBLE = "大失败";
    }

    public String getSystemDir() {
        return systemDir;
    }

    public void setSystemDir(String systemDir) {
        this.systemDir = systemDir;
    }

    public String getBotStart() {
        return botStart;
    }

    public void setBotStart(String botStart) {
        this.botStart = botStart;
    }

    public String getBotAlreadyStart() {
        return botAlreadyStart;
    }

    public void setBotAlreadyStart(String botAlreadyStart) {
        this.botAlreadyStart = botAlreadyStart;
    }

    public String getBotStop() {
        return botStop;
    }

    public void setBotStop(String botStop) {
        this.botStop = botStop;
    }

    public String getBotAlreadyStop() {
        return botAlreadyStop;
    }

    public void setBotAlreadyStop(String botAlreadyStop) {
        this.botAlreadyStop = botAlreadyStop;
    }

    public String getBotExit() {
        return botExit;
    }

    public void setBotExit(String botExit) {
        this.botExit = botExit;
    }

    public String getBotInfo() {
        return botInfo;
    }

    public void setBotInfo(String botInfo) {
        this.botInfo = botInfo;
    }

    public String getBookCard() {
        return bookCard;
    }

    public void setBookCard(String bookCard) {
        this.bookCard = bookCard;
    }

    public String getBookRp() {
        return bookRp;
    }

    public void setBookRp(String bookRp) {
        this.bookRp = bookRp;
    }

    public String getBookKp() {
        return bookKp;
    }

    public void setBookKp(String bookKp) {
        this.bookKp = bookKp;
    }

    public String getBookMake() {
        return bookMake;
    }

    public void setBookMake(String bookMake) {
        this.bookMake = bookMake;
    }

    public String getManyRollsFormat() {
        return manyRollsFormat;
    }

    public void setManyRollsFormat(String manyRollsFormat) {
        this.manyRollsFormat = manyRollsFormat;
    }

    public String getDiceTimesTooBig() {
        return diceTimesTooBig;
    }

    public void setDiceTimesTooBig(String diceTimesTooBig) {
        this.diceTimesTooBig = diceTimesTooBig;
    }

    public String getSetPropFormat() {
        return setPropFormat;
    }

    public void setSetPropFormat(String setPropFormat) {
        this.setPropFormat = setPropFormat;
    }

    public String getSetHelp() {
        return setHelp;
    }

    public void setSetHelp(String setHelp) {
        this.setHelp = setHelp;
    }

    public String getNotFoundSkill() {
        return NotFoundSkill;
    }

    public void setNotFoundSkill(String notFoundSkill) {
        NotFoundSkill = notFoundSkill;
    }

    public String getSetPropSuccess() {
        return setPropSuccess;
    }

    public void setSetPropSuccess(String setPropSuccess) {
        this.setPropSuccess = setPropSuccess;
    }

    public String getDndInitIsEmtpy() {
        return dndInitIsEmtpy;
    }

    public void setDndInitIsEmtpy(String dndInitIsEmtpy) {
        this.dndInitIsEmtpy = dndInitIsEmtpy;
    }

    public String getClrDndInit() {
        return clrDndInit;
    }

    public void setClrDndInit(String clrDndInit) {
        this.clrDndInit = clrDndInit;
    }

    public String getNeedKpGroup() {
        return needKpGroup;
    }

    public void setNeedKpGroup(String needKpGroup) {
        this.needKpGroup = needKpGroup;
    }

    public String getCantInPrivate() {
        return cantInPrivate;
    }

    public void setCantInPrivate(String cantInPrivate) {
        this.cantInPrivate = cantInPrivate;
    }

    public String getOnlyManager() {
        return onlyManager;
    }

    public void setOnlyManager(String onlyManager) {
        this.onlyManager = onlyManager;
    }

    public String getAlreadyOpen() {
        return alreadyOpen;
    }

    public void setAlreadyOpen(String alreadyOpen) {
        this.alreadyOpen = alreadyOpen;
    }

    public String getAlreadyClose() {
        return alreadyClose;
    }

    public void setAlreadyClose(String alreadyClose) {
        this.alreadyClose = alreadyClose;
    }

    public String getNotFoundLog() {
        return notFoundLog;
    }

    public void setNotFoundLog(String notFoundLog) {
        this.notFoundLog = notFoundLog;
    }

    public String getReadLock() {
        return readLock;
    }

    public void setReadLock(String readLock) {
        this.readLock = readLock;
    }

    public String getDeleteOpenLog() {
        return deleteOpenLog;
    }

    public void setDeleteOpenLog(String deleteOpenLog) {
        this.deleteOpenLog = deleteOpenLog;
    }

    public String getSanCheck() {
        return sanCheck;
    }

    public void setSanCheck(String sanCheck) {
        this.sanCheck = sanCheck;
    }

    public String getAntagonizeOver() {
        return antagonizeOver;
    }

    public void setAntagonizeOver(String antagonizeOver) {
        this.antagonizeOver = antagonizeOver;
    }

    public String getAntagonizeFirstSuccess() {
        return antagonizeFirstSuccess;
    }

    public void setAntagonizeFirstSuccess(String antagonizeFirstSuccess) {
        this.antagonizeFirstSuccess = antagonizeFirstSuccess;
    }

    public String getAntagonizeSecondSuccess() {
        return antagonizeSecondSuccess;
    }

    public void setAntagonizeSecondSuccess(String antagonizeSecondSuccess) {
        this.antagonizeSecondSuccess = antagonizeSecondSuccess;
    }

    public String getAntagonizeAllFailed() {
        return antagonizeAllFailed;
    }

    public void setAntagonizeAllFailed(String antagonizeAllFailed) {
        this.antagonizeAllFailed = antagonizeAllFailed;
    }

    public String getAntagonizeDraw() {
        return antagonizeDraw;
    }

    public void setAntagonizeDraw(String antagonizeDraw) {
        this.antagonizeDraw = antagonizeDraw;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        if (symptom.equals(NONE)) {
            this.symptom = symptom;
        } else {
            this.symptom = "\n" + symptom;
        }
    }

    public String getEnSuccess() {
        return enSuccess;
    }

    public void setEnSuccess(String enSuccess) {
        if (enSuccess.equals(NONE)) {
            this.enSuccess = enSuccess;
        } else {
            this.enSuccess = "\n" + enSuccess;
        }
    }

    public String getEnFailed() {
        return enFailed;
    }

    public void setEnFailed(String enFailed) {
        if (enFailed.equals(NONE)) {
            this.enFailed = enFailed;
        } else {
            this.enFailed = "\n" + enFailed;
        }
    }

    public String getHiddenDice() {
        return hiddenDice;
    }

    public void setHiddenDice(String hiddenDice) {
        this.hiddenDice = hiddenDice;
    }

    public String getTeamIsEmpty() {
        return teamIsEmpty;
    }

    public void setTeamIsEmpty(String teamIsEmpty) {
        this.teamIsEmpty = teamIsEmpty;
    }

    public String getTeamMemberEnIsEmpty() {
        return teamMemberEnIsEmpty;
    }

    public void setTeamMemberEnIsEmpty(String teamMemberEnIsEmpty) {
        this.teamMemberEnIsEmpty = teamMemberEnIsEmpty;
    }

    public String getAppendLog() {
        return appendLog;
    }

    public void setAppendLog(String appendLog) {
        this.appendLog = appendLog;
    }

    public String getCreateLog() {
        return createLog;
    }

    public void setCreateLog(String createLog) {
        this.createLog = createLog;
    }

    public String getCantEmptyLogName() {
        return CantEmptyLogName;
    }

    public void setCantEmptyLogName(String cantEmptyLogName) {
        CantEmptyLogName = cantEmptyLogName;
    }

    public String getSanCheckFumble() {
        return sanCheckFumble;
    }

    public void setSanCheckFumble(String sanCheckFumble) {
        if (sanCheckFumble.equals(NONE)) {
            this.sanCheckFumble = sanCheckFumble;
        } else {
            this.sanCheckFumble = "\n" + sanCheckFumble;
        }
    }

    public String getSanCheckCriticalSuccess() {
        return sanCheckCriticalSuccess;
    }

    public void setSanCheckCriticalSuccess(String sanCheckCriticalSuccess) {
        if (sanCheckCriticalSuccess.equals(NONE)) {
            this.sanCheckCriticalSuccess = sanCheckCriticalSuccess;
        } else {
            this.sanCheckCriticalSuccess = "\n" + sanCheckCriticalSuccess;
        }
    }

    public String getSanCheckSuccess() {
        return sanCheckSuccess;
    }

    public void setSanCheckSuccess(String sanCheckSuccess) {
        if (sanCheckSuccess.equals(NONE)) {
            this.sanCheckSuccess = sanCheckSuccess;
        } else {
            this.sanCheckSuccess = "\n" + sanCheckSuccess;
        }
    }

    public String getSanCheckFailure() {
        return sanCheckFailure;
    }

    public void setSanCheckFailure(String sanCheckFailure) {
        if (sanCheckFailure.equals(NONE)) {
            this.sanCheckFailure = sanCheckFailure;
        } else {
            this.sanCheckFailure = "\n" + sanCheckFailure;
        }
    }

    public String getCRITICAL_SUCCESS() {
        return CRITICAL_SUCCESS;
    }

    public void setCRITICAL_SUCCESS(String CRITICAL_SUCCESS) {
        this.CRITICAL_SUCCESS = CRITICAL_SUCCESS;
    }

    public String getEXTREME_SUCCESS() {
        return EXTREME_SUCCESS;
    }

    public void setEXTREME_SUCCESS(String EXTREME_SUCCESS) {
        this.EXTREME_SUCCESS = EXTREME_SUCCESS;
    }

    public String getHARD_SUCCESS() {
        return HARD_SUCCESS;
    }

    public void setHARD_SUCCESS(String HARD_SUCCESS) {
        this.HARD_SUCCESS = HARD_SUCCESS;
    }

    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public String getFAILURE() {
        return FAILURE;
    }

    public void setFAILURE(String FAILURE) {
        this.FAILURE = FAILURE;
    }

    public String getFUMBLE() {
        return FUMBLE;
    }

    public void setFUMBLE(String FUMBLE) {
        this.FUMBLE = FUMBLE;
    }
}
