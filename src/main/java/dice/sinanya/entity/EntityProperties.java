package dice.sinanya.entity;

public class EntityProperties {
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

    String mailUserName;
    String mailPassword;
    String masterMail;
    String serverPort;
    String javaPort;
    String hostIp;

    String CRITICAL_SUCCESS;
    String EXTREME_SUCCESS;
    String HARD_SUCCESS;
    String SUCCESS;
    String FAILURE;
    String FUMBLE;

    String notMaster;
    String master;

    String PrometheusPort;
    String heap;

    String dbPassword;

    String cloudBan;
    String banListInputNotId;

    public void EntityPropertiesInit() {
        botStart="机器人已开启";
        botAlreadyStart="机器人当前处于开启状态";
        botStop="机器人已关闭";
        botAlreadyStop="机器人当前处于关闭状态";
        botExit="正在退出群";
        botInfo="";
        banListInputNotId","输入的不是QQ号或群号";

        bookCard="COC七版规则空白卡奈梅斯·西莉亚私人订制版By贝尔巨佬.xlsx\n请使用此链接下载https://pan.baidu.com/s/1M3veskXYFJjwXP1eKHaX4g。有更新更好的版本请随时联系窝。";
        bookRP="角色扮演三百六十五问.zip\n请使用此链接下载https://share1.heiluo.com/share/link/8b93f1f15a974d4a9fda1890863a0af1。有更新更好的版本请随时联系窝。";
        bookKp="克苏鲁的呼唤第七版守秘人规则书 Version1901.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/e88daa8d6565440cbff0a8f7c9c8fe29。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/12XSQc9EEBsfEhhQEesc6nw";
        bookMake="车卡指南.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/0e1083cdb8144b109be07fd4ef09b082。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/1HHo1B1F9kMRIFDB8J9Ulww 提取码：ql5h";

        manyRollsFormat="请按照\".ral 值 次数\"的格式输入";
        diceTimesTooBig="骰点次数过多";

        setPropFormat="请符合格式.st角色名-力量50体质60";
        setHelp=".st命令使用错误，请使用.help make命令查看具体用法";
        NotFoundSkill="您未设置角色卡";

        dndInitIsEmtpy="先攻列表为空";

        needKpGroup="未设置kp群";

        can'tInPrivate="此命令私聊不可用";
        onlyManager="此命令仅群主或管理员可以使用";

        alreadyOpen="日志已经处于开启状态，无法再次开启";
        alreadyClose="日志已经处于关闭状态，无法再次关闭";
        notFoundLog="未找到日志";
        readLock="正在有人取日志，请稍后";
        deleteOpenLog="无法删除打开的日志，请先关闭";

        sanCheck="请符合1/1d3|1d6/1d3|0/1这样的san check格式";

        setPropSuccess="已记录人物卡";

        clrDndInit="清空先攻列表";

        antagonizeOver="对抗结束";
        antagonizeFirstSuccess="先手胜利";
        antagonizeSecondSuccess="后手胜利";
        antagonizeAllFailed="全部失败";
        antagonizeDraw="平手";

        symptom="";

        enSuccess="";
        enFailed="";

        hiddenDice="kp正在进行暗骰";

        teamIsEmpty="您的小队为空";
        teamMemberEnIsEmpty="的技能成功记录为空";


        appendLog="日志已重新开启，将在原基础上追加";
        createLog="日志已创建";
        CantEmptyLogName="不支持空日志名";

        sanCheckFumble="";
        sanCheckCriticalSuccess="";
        sanCheckSuccess="";
        sanCheckFailure="";

        mailUserName="2730902267@qq.com";
        mailPassword="kktjwuakdafbdcej";
        masterMail="450609203@qq.com";
        serverPort="80";
        javaPort="9999";
        hostIp="127.0.0.1";

        CRITICAL_SUCCESS="";
        EXTREME_SUCCESS="";
        HARD_SUCCESS="";
        SUCCESS="";
        FAILURE="";
        FUMBLE="";

        notMaster="除设定的Master外其他人不可使用此命令";
        master="0";

        PrometheusPort="1260";

        heap="false";
        cloudBan="true";

        dbPassword="rong";
    }
}
