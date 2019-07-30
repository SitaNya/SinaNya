package dice.sinanya.flow;

import dice.sinanya.dice.game.Jrrp;
import dice.sinanya.dice.get.*;
import dice.sinanya.dice.getbook.Book;
import dice.sinanya.dice.manager.*;
import dice.sinanya.dice.roll.*;
import dice.sinanya.dice.system.Help;
import dice.sinanya.dice.system.History;
import dice.sinanya.dice.system.Log;
import dice.sinanya.dice.system.Test;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 入口分流类，结合MessagesTag中配置的正则表达式，将被机器人捕捉的小心分流给各个逻辑模块
 * <p>
 * 如果你要添加一个新的命令，需要先去MessagesTag处添加相应的正则表达式，然后在这里分别添加以下内容
 * 1.   isXXXX  布尔值
 * 2.   isXXXX=messages.matches(TAG_XXXXX); 为布尔值赋值
 * 3.   在toPrivate或toPrivateAndGroup中根据需要new自己写的逻辑类并通过布尔值去激活
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public
class Flow {
    private static final Logger Log = LogManager.getLogger(Flow.class);

    private EntityTypeMessages entityTypeMessages;

    private boolean isr = false;
    private boolean isRh = false;
    private boolean isRa = false;
    private boolean isRc = false;
    private boolean isRal = false;
    private boolean isRcl = false;
    private boolean isRav = false;
    private boolean isRcv = false;

    private boolean isRb = false;
    private boolean isRp = false;

    private boolean isSc = false;

    private boolean isEn = false;

    private boolean isSetRollMaxValue = false;

    private boolean isStSet = false;
    private boolean isStShow = false;
    private boolean isStList = false;
    private boolean isStMove = false;

    private boolean isTeamShow = false;
    private boolean isTeamSet = false;
    private boolean isTeamClr = false;
    private boolean isTeamMove = false;
    private boolean isTeamCall = false;
    private boolean isTeamHp = false;
    private boolean isTeamSan = false;
    private boolean isTeamDesc = false;
    private boolean isTeamEn = false;

    private boolean isHelpNormal = false;
    private boolean isHelpMake = false;
    private boolean isHelpGroup = false;
    private boolean isHelpBook = false;
    private boolean isHelpDnd = false;
    private boolean isHelpInfo = false;


    private boolean isBookCard = false;
    private boolean isBookRp = false;
    private boolean isBookKp = false;
    private boolean isBookMake = false;

    private boolean isNpc = false;

    private boolean isBg = false;

    private boolean isTz = false;

    private boolean isGas = false;

    private boolean isCoc7 = false;
    private boolean isCoc6 = false;
    private boolean isCoc7d = false;
    private boolean isCoc6d = false;

    private boolean isLogOn = false;
    private boolean isLogOff = false;
    private boolean isLogGet = false;
    private boolean isLogList = false;
    private boolean isLogDel = false;

    private boolean isTi = false;
    private boolean isLi = false;

    private boolean isDnd = false;
    private boolean isRi = false;
    private boolean isInit = false;
    private boolean isInitClr = false;

    private boolean isClueSet = false;
    private boolean isClueShow = false;
    private boolean isClueRm = false;
    private boolean isClueClr = false;

    private boolean isKp = false;

    private boolean isHiy = false;

    private boolean isJrrp = false;

    private boolean isTest = false;

    public Flow(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
        checkMessages();
    }

    private void initTeamTag() {
        isTeamSet = checkTagRegex(TAG_TEAM_SET);
        isTeamClr = checkTagRegex(TAG_TEAM_CLR);
        isTeamMove = checkTagRegex(TAG_TEAM_RM);
        isTeamCall = checkTagRegex(TAG_TEAM_CALL);
        isTeamHp = checkTagRegex(TAG_TEAM_HP);
        isTeamSan = checkTagRegex(TAG_TEAM_SAN);
        isTeamDesc = checkTagRegex(TAG_TEAM_DESC);
        isTeamEn = checkTagRegex(TAG_TEAM_EN);
        isTeamShow = checkTagRegex(TAG_TEAM_SHOW) && !isTeamSet && !isTeamClr && !isTeamMove && !isTeamCall && !isTeamHp && !isTeamSan && !isTeamDesc && !isTeamEn;
    }

    private void initStTag() {
        isStShow = checkTagRegex(TAG_ST_SHOW);
        isStList = checkTagRegex(TAG_ST_LIST);
        isStMove = checkTagRegex(TAG_ST_RM);
        isStSet = checkTagRegex(TAG_ST_SET) && !isStShow && !isStList && !isStMove;
    }

    private void initHelpTag() {
        isHelpNormal = checkTagRegex(TAG_HELP_NORMAL);
        isHelpMake = checkTagRegex(TAG_HELP_MAKE);
        isHelpGroup = checkTagRegex(TAG_HELP_GROUP);
        isHelpBook = checkTagRegex(TAG_HELP_BOOK);
        isHelpDnd = checkTagRegex(TAG_HELP_DND);
        isHelpInfo = checkTagRegex(TAG_HELP_INFO) && !isHelpNormal && !isHelpMake && !isHelpGroup && !isHelpBook && !isHelpDnd;
    }

    private void initCocCardTag() {
        isCoc7d = checkTagRegex(TAG_COC7D);
        isCoc6d = checkTagRegex(TAG_COC6D);
        isCoc6 = checkTagRegex(TAG_COC6);
        isCoc7 = checkTagRegex(TAG_COC7) && !isCoc7d && !isCoc6d && !isCoc6;
    }

    private void initDndTag() {
        isDnd = checkTagRegex(TAG_DND);
        isInitClr = checkTagRegex(TAG_INIT_CLR);
        isInit = checkTagRegex(TAG_INIT) && !isInitClr;
    }

    private void initBookTag() {
        isBookCard = checkTagRegex(TAG_BOOK_CARD);
        isBookMake = checkTagRegex(TAG_BOOK_MAKE);
        isBookRp = checkTagRegex(TAG_BOOK_RP);
        isBookKp = checkTagRegex(TAG_BOOK_KP);
    }

    private void initLogTag() {
        isLogOn = checkTagRegex(TAG_LOG_ON);
        isLogOff = checkTagRegex(TAG_LOG_OFF);
        isLogGet = checkTagRegex(TAG_LOG_GET);
        isLogList = checkTagRegex(TAG_LOG_LIST);
        isLogDel = checkTagRegex(TAG_LOG_RM);
    }

    private void initClueTag() {
        isClueClr = checkTagRegex(TAG_CLUE_CLR);
        isClueShow = checkTagRegex(TAG_CLUE_SHOW);
        isClueRm = checkTagRegex(TAG_CLUE_RM);
        isClueSet = checkTagRegex(TAG_CLUE_SET) && !isClueClr && !isClueShow && !isClueRm;
    }

    private void initDiceTag() {
        isRi = checkTagRegex(TAG_RI);

        isRb = checkTagRegex(TAG_RB);
        isRp = checkTagRegex(TAG_RP);

        isRal = checkTagRegex(TAG_RAL);
        isRcl = checkTagRegex(TAG_RCL);
        isRav = checkTagRegex(TAG_RAV);
        isRcv = checkTagRegex(TAG_RCV);
        isRh = checkTagRegex(TAG_RH);
        isRa = checkTagRegex(TAG_RA) && !isRal && !isRav;
        isRc = checkTagRegex(TAG_RC) && !isRcl && !isRcv;
        isr = checkTagRegex(TAGR) && !isRh && !isRa && !isRc && !isRb && !isRp && !isRi && !isRal && !isRcl && !isRav && !isRcv;
    }

    private void checkMessages() {
        String forAll = ".*";
        if (checkTagRegex(HEADER_TEAM + forAll)) {
            initTeamTag();
        } else if (checkTagRegex(HEADER_ST + forAll)) {
            initStTag();
        } else if (checkTagRegex(HEADER_HELP + forAll)) {
            initHelpTag();
        } else if (checkTagRegex(HEADER_COC + forAll)) {
            initCocCardTag();
        } else if (checkTagRegex(HEADER_DND + forAll) || checkTagRegex(TAG_INIT)) {
            initDndTag();
        } else if (checkTagRegex(HEADER_BOOK + forAll)) {
            initBookTag();
        } else if (checkTagRegex(HEADER_LOG + forAll)) {
            initLogTag();
        } else if (checkTagRegex(HEADER_CLUE + forAll)) {
            initClueTag();
        } else if (checkTagRegex(TAGR)) {
            initDiceTag();
        } else {
            isKp = checkTagRegex(TAG_KP);
            isHiy = checkTagRegex(TAG_HIY);

            isJrrp = checkTagRegex(TAG_JRRP);

            isSc = checkTagRegex(TAG_SC);
            isEn = checkTagRegex(TAG_EN);
            isSetRollMaxValue = checkTagRegex(TAG_SET_ROLL_MAX_VALUE);

            isNpc = checkTagRegex(TAG_NPC);
            isBg = checkTagRegex(TAG_BG);
            isTz = checkTagRegex(TAG_TZ);
            isGas = checkTagRegex(TAG_GAS);
            isTi = checkTagRegex(TAG_TI);
            isLi = checkTagRegex(TAG_LI);

            isTest=checkTagRegex(TAG_TEST);
        }
    }

    /**
     * 私聊逻辑，同时在私聊和群中生效
     */
    public void toPrivate() {
        MakeDndCard makeDndCard = new MakeDndCard(entityTypeMessages);
        Bj bj = new Bj(entityTypeMessages);
        Npc npc = new Npc(entityTypeMessages);
        Tz tz = new Tz(entityTypeMessages);
        Gas gas = new Gas(entityTypeMessages);
        History history = new History(entityTypeMessages);
        Jrrp jrrp = new Jrrp(entityTypeMessages);
        Test test=new Test(entityTypeMessages);

        isFunctionR();
        isStFunction();
        isScFunction();
        isRbAndRpFunction();
        isCocCardFunction();
        isTiAndLiFunction();
        isHelpFunction();
        isBookFunction();

        if (isNpc) {
            npc.npc();
        }

        if (isBg) {
            bj.bg();
        }

        if (isTz) {
            tz.get();
        }

        if (isGas) {
            gas.get();
        }

        if (isDnd) {
            makeDndCard.dnd();
        }

        if (isHiy) {
            history.hiy();
        }

//        if (isJrrp) {
//            jrrp.get();
//        }

        if (isTest){
            test.get();
        }

    }

    /**
     * 群逻辑区块，所有在这里声明的逻辑都会在群、讨论组中生效。
     * 此外，虽然私聊的逻辑在群中生效，但这里设置的群逻辑并不会在私聊中生效
     */
    private void toPrivateAndGroup() {
        Roll roll = new Roll(entityTypeMessages);
        SetRollMaxValue setRollMaxValue = new SetRollMaxValue(entityTypeMessages);
        Kp kp = new Kp(entityTypeMessages);

        isTeamFunction();
        isEnFunction();
        isLogFunction();
        isRiFunction();
        isClueFunction();

        if (isRh) {
            try {
                roll.rh();
            } catch (ManyRollsTimesTooMoreException e) {
                Log.error(e.getMessage(), e);
            }
        }

        if (isKp) {
            kp.set();
        }

        if (isSetRollMaxValue) {
            setRollMaxValue.set();
        }

        toPrivate();
    }

    /**
     * 将群分流到群逻辑区块
     */
    public void toGroup() {
        toPrivateAndGroup();
    }

    /**
     * 将讨论组分流到群逻辑区块
     */
    public void toDisGroup() {
        toPrivateAndGroup();
    }

    private boolean checkTagRegex(String tag) {
        return entityTypeMessages.getMsgGet().getMsg().trim().toLowerCase().matches(tag);
    }


    private void isFunctionR() {
        Roll roll = new Roll(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);
        if (isr) {
            try {
                roll.r();
            } catch (ManyRollsTimesTooMoreException e) {
                Log.error(e.getMessage(), e);
            }
        } else if (isRa) {
            rollAndCheck.ra();
        } else if (isRc) {
            rollAndCheck.rc();
        } else if (isRal) {
            rollAndCheck.ral();
        } else if (isRcl) {
            rollAndCheck.rcl();
        } else if (isRav) {
            try {
                rollAndCheck.rav();
            } catch (NotSetKpGroupException e) {
                Log.error(e.getMessage(), e);
            }
        } else if (isRcv) {
            try {
                rollAndCheck.rcv();
            } catch (NotSetKpGroupException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    private void isStFunction() {
        Roles roles = new Roles(entityTypeMessages);

        if (isStSet) {
            try {
                if (!roles.set()) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("setHelp"));
                }
            } catch (PlayerSetException e) {
                Log.error(e.getMessage(), e);
            }
        } else if (isStShow) {
            roles.show();
        } else if (isStList) {
            roles.list();
        } else if (isStMove) {
            roles.move();
        }
    }

    private void isScFunction() {
        SanCheck sanCheck = new SanCheck(entityTypeMessages);

        if (isSc) {
            try {
                sanCheck.sc();
            } catch (PlayerSetException | SanCheckSetException | ManyRollsTimesTooMoreException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    private void isRbAndRpFunction() {
        RewardAndPunishment rewardAndPunishment = new RewardAndPunishment(entityTypeMessages);

        if (isRb) {
            rewardAndPunishment.rb();
        } else if (isRp) {
            rewardAndPunishment.rp();
        }
    }

    private void isCocCardFunction() {
        MakeCocCard makeCocCard = new MakeCocCard(entityTypeMessages);

        if (isCoc7d) {
            makeCocCard.coc7d();
        } else if (isCoc7) {
            makeCocCard.coc7();
        } else if (isCoc6d) {
            makeCocCard.coc6d();
        } else if (isCoc6) {
            makeCocCard.coc6();
        }
    }

    private void isTiAndLiFunction() {
        TiAndLi tiAndLi = new TiAndLi(entityTypeMessages);

        if (isTi) {
            tiAndLi.ti();
        } else if (isLi) {
            tiAndLi.li();
        }
    }

    private void isHelpFunction() {
        Help help = new Help(entityTypeMessages);

        if (isHelpNormal) {
            help.normal();
        } else if (isHelpMake) {
            help.make();
        } else if (isHelpGroup) {
            help.group();
        } else if (isHelpBook) {
            help.book();
        } else if (isHelpDnd) {
            help.dnd();
        } else if (isHelpInfo) {
            help.info();
        }
    }

    private void isBookFunction() {
        Book book = new Book(entityTypeMessages);

        if (isBookKp) {
            book.kp();
        } else if (isBookCard) {
            book.card();
        } else if (isBookRp) {
            book.rp();
        } else if (isBookMake) {
            book.make();
        }
    }

    private void isTeamFunction() {
        Team team = new Team(entityTypeMessages);

        if (isTeamSet) {
            team.set();
        } else if (isTeamShow) {
            team.show();
        } else if (isTeamMove) {
            team.remove();
        } else if (isTeamClr) {
            team.clr();
        } else if (isTeamCall) {
            team.call();
        } else if (isTeamHp) {
            try {
                team.hp();
            } catch (ManyRollsTimesTooMoreException e) {
                Log.error(e.getMessage(), e);
            }
        } else if (isTeamSan) {
            team.san();
        } else if (isTeamDesc) {
            team.desc();
        } else if (isTeamEn) {
            team.en();
        }
    }

    private void isEnFunction() {
        SkillUp skillUp = new SkillUp(entityTypeMessages);

        if (isEn) {
            try {
                skillUp.en();
            } catch (NotFoundSkillException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    private void isLogFunction() {
        Log log = new Log(entityTypeMessages);

        if (isLogOn) {
            log.logOn();
        } else if (isLogOff) {
            log.logOff();
        } else if (isLogGet) {
            log.get();
        } else if (isLogList) {
            log.list();
        } else if (isLogDel) {
            log.del();
        }
    }

    private void isRiFunction() {
        RiAndInit riAndInit = new RiAndInit(entityTypeMessages);

        if (isRi) {
            riAndInit.ri();
        } else if (isInit) {
            riAndInit.init();
        } else if (isInitClr) {
            riAndInit.clr();
        }
    }

    private void isClueFunction() {
        Clue clue = new Clue(entityTypeMessages);

        if (isClueRm) {
            clue.rm();
        } else if (isClueSet) {
            clue.set();
        } else if (isClueClr) {
            clue.clr();
        } else if (isClueShow) {
            clue.show();
        }
    }
}
