package dice.sinanya.flow;

import com.forte.qqrobot.beans.messages.OriginalAble;
import dice.sinanya.dice.game.Jrrp;
import dice.sinanya.dice.get.*;
import dice.sinanya.dice.getbook.Book;
import dice.sinanya.dice.manager.*;
import dice.sinanya.dice.roll.*;
import dice.sinanya.dice.system.Bot;
import dice.sinanya.dice.system.Help;
import dice.sinanya.dice.system.History;
import dice.sinanya.dice.system.Log;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotFoundSkillException;
import dice.sinanya.exceptions.NotSetKpGroupException;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;
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
class Flow{
    private static final Logger Log = LogManager.getLogger(Flow.class);

    private EntityTypeMessages entityTypeMessages;

    private boolean isR = false;
    private boolean isRH = false;
    private boolean isRA = false;
    private boolean isRC = false;
    private boolean isRAL = false;
    private boolean isRCL = false;
    private boolean isRAV = false;
    private boolean isRCV = false;

    private boolean isRB = false;
    private boolean isRP = false;

    private boolean isSC = false;

    private boolean isEN = false;

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
    private boolean isBookRP = false;
    private boolean isBookKP = false;
    private boolean isBookMAKE = false;

    private boolean isNPC = false;

    private boolean isBG = false;

    private boolean isTZ = false;

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

    private boolean isJRRP = false;

    private boolean isBot = false;

    public Flow(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
        String messages = entityTypeMessages.getMsgGet().getMsg().trim();
        checkMessages(messages);
    }

    private void checkMessages(String messages) {
        messages = messages.toLowerCase();

        isTeamSet = messages.matches(TAG_TEAM_SET);
        isTeamClr = messages.matches(TAG_TEAM_CLR);
        isTeamMove = messages.matches(TAG_TEAM_RM);
        isTeamCall = messages.matches(TAG_TEAM_CALL);
        isTeamHp = messages.matches(TAG_TEAM_HP);
        isTeamSan = messages.matches(TAG_TEAM_SAN);
        isTeamDesc = messages.matches(TAG_TEAM_DESC);
        isTeamEn = messages.matches(TAG_TEAM_EN);
        isTeamShow = messages.matches(TAG_TEAM_SHOW) && !isTeamSet && !isTeamClr && !isTeamMove && !isTeamCall && !isTeamHp && !isTeamSan && !isTeamDesc && !isTeamEn;
//        小队正则匹配逻辑

        isStShow = messages.matches(TAG_ST_SHOW);
        isStList = messages.matches(TAG_ST_LIST);
        isStMove = messages.matches(TAG_ST_RM);
        isStSet = messages.matches(TAG_ST_SET) && !isStShow && !isStList && !isStMove;
//        人物卡角色正则匹配逻辑

        isHelpNormal = messages.matches(TAG_HELP_NORMAL);
        isHelpMake = messages.matches(TAG_HELP_MAKE);
        isHelpGroup = messages.matches(TAG_HELP_GROUP);
        isHelpBook = messages.matches(TAG_HELP_BOOK);
        isHelpDnd = messages.matches(TAG_HELP_DND);
        isHelpInfo = messages.matches(TAG_HELP_INFO) && !isHelpNormal && !isHelpMake && !isHelpGroup && !isHelpBook && !isHelpDnd;

        isCoc7d = messages.matches(TAG_COC7D);
        isCoc6d = messages.matches(TAG_COC6D);
        isCoc6 = messages.matches(TAG_COC6);
        isCoc7 = messages.matches(TAG_COC7) && !isCoc7d && !isCoc6d && !isCoc6;

        isDnd = messages.matches(TAG_DND);
        isRi = messages.matches(TAG_RI);
        isInitClr = messages.matches(TAG_INIT_CLR);
        isInit = messages.matches(TAG_INIT) && !isInitClr;

        isBookCard = messages.matches(TAG_BOOK_CARD);
        isBookMAKE = messages.matches(TAG_BOOK_MAKE);
        isBookRP = messages.matches(TAG_BOOK_RP);
        isBookKP = messages.matches(TAG_BOOK_KP);

        isLogOn = messages.matches(TAG_LOG_ON);
        isLogOff = messages.matches(TAG_LOG_OFF);
        isLogGet = messages.matches(TAG_LOG_GET);
        isLogList = messages.matches(TAG_LOG_LIST);
        isLogDel = messages.matches(TAG_LOG_RM);


        isClueClr = messages.matches(TAG_CLUE_CLR);
        isClueShow = messages.matches(TAG_CLUE_SHOW);
        isClueRm = messages.matches(TAG_CLUE_RM);
        isClueSet = messages.matches(TAG_CLUE_SET) && !isClueClr && !isClueShow && !isClueRm;

        isKp = messages.matches(TAG_KP);
        isHiy = messages.matches(TAG_HIY);

        isBot = messages.equals(".bot");

        isJRRP = messages.matches(TAG_JRRP);

        isNPC = messages.matches(TAG_NPC);

        isBG = messages.matches(TAG_BG);

        isTZ = messages.matches(TAG_TZ);

        isGas = messages.matches(TAG_GAS);

        isTi = messages.matches(TAG_TI);
        isLi = messages.matches(TAG_LI);

        isRB = messages.matches(TAG_RB);
        isRP = messages.matches(TAG_RP);

        isSC = messages.matches(TAG_SC);

        isEN = messages.matches(TAG_EN);

        isSetRollMaxValue = messages.matches(TAG_SET_ROLL_MAX_VALUE);

        isRAL = messages.matches(TAG_RAL);
        isRCL = messages.matches(TAG_RCL);
        isRAV = messages.matches(TAG_RAV);
        isRCV = messages.matches(TAG_RCV);
        isRH = messages.matches(TAG_RH);
        isRA = messages.matches(TAG_RA) && !isRAL && !isRAV;
        isRC = messages.matches(TAG_RC) && !isRCL && !isRCV;
        isR = messages.matches(TAGR) && !isRH && !isRA && !isRC && !isRB && !isRP && !isRi && !isRAL && !isRCL && !isRAV && !isRCV;
    }

    /**
     * 私聊逻辑，同时在私聊和群中生效
     */
    public void toPrivate() {
        Roll roll = new Roll(entityTypeMessages);
        Roles roles = new Roles(entityTypeMessages);
        SanCheck sanCheck = new SanCheck(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);
        RewardAndPunishment rewardAndPunishment = new RewardAndPunishment(entityTypeMessages);
        MakeCocCard makeCocCard = new MakeCocCard(entityTypeMessages);
        MakeDndCard makeDndCard = new MakeDndCard(entityTypeMessages);
        Bot bot = new Bot(entityTypeMessages);
        TiAndLi tiAndLi = new TiAndLi(entityTypeMessages);
        Help help = new Help(entityTypeMessages);
        Book book = new Book(entityTypeMessages);
        Bj bj = new Bj(entityTypeMessages);
        Npc npc = new Npc(entityTypeMessages);
        Tz tz = new Tz(entityTypeMessages);
        Gas gas = new Gas(entityTypeMessages);
        History history = new History(entityTypeMessages);
        Jrrp jrrp = new Jrrp(entityTypeMessages);

        if (isR) {
            roll.r();
        } else if (isRA) {
            rollAndCheck.ra();
        } else if (isRC) {
            rollAndCheck.rc();
        } else if (isRAL) {
            rollAndCheck.ral();
        } else if (isRCL) {
            rollAndCheck.rcl();
        } else if (isRAV) {
            try {
                rollAndCheck.rav();
            } catch (NotSetKpGroupException e) {
                Log.error(e.getMessage(), e);
            }
        } else if (isRCV) {
            try {
                rollAndCheck.rcv();
            } catch (NotSetKpGroupException e) {
                Log.error(e.getMessage(), e);
            }
        }

        if (isStSet) {
            try {
                if (roles.set()) {
                    sender(entityTypeMessages, messagesSystem.get("setPropSuccess"));
                } else {
                    sender(entityTypeMessages, messagesSystem.get("setHelp"));
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

        if (isSC) {
            try {
                sanCheck.sc();
            } catch (PlayerSetException | SanCheckSetException e) {
                Log.error(e.getMessage(), e);
            }
        }

        if (isRB) {
            rewardAndPunishment.rb();
        } else if (isRP) {
            rewardAndPunishment.rp();
        }

        if (isCoc7d) {
            makeCocCard.coc7d();
        } else if (isCoc7) {
            makeCocCard.coc7();
        } else if (isCoc6d) {
            makeCocCard.coc6d();
        } else if (isCoc6) {
            makeCocCard.coc6();
        }

        if (isTi) {
            tiAndLi.ti();
        } else if (isLi) {
            tiAndLi.li();
        }

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

        if (isBookKP) {
            book.kp();
        } else if (isBookCard) {
            book.card();
        } else if (isBookRP) {
            book.rp();
        } else if (isBookMAKE) {
            book.make();
        }

        if (isNPC) {
            npc.npc();
        }

        if (isBG) {
            bj.bg();
        }

        if (isTZ) {
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

        if (isJRRP) {
            jrrp.get();
        }

        if (isBot) {
            new Bot(entityTypeMessages).info();
        }

    }

    /**
     * 群逻辑区块，所有在这里声明的逻辑都会在群、讨论组中生效。
     * 此外，虽然私聊的逻辑在群中生效，但这里设置的群逻辑并不会在私聊中生效
     */
    private void toPrivateAndGroup() {
        Roll roll = new Roll(entityTypeMessages);
        Team team = new Team(entityTypeMessages);
        SkillUp skillUp = new SkillUp(entityTypeMessages);
        Bot bot = new Bot(entityTypeMessages);
        Log log = new Log(entityTypeMessages);
        RiAndInit riAndInit = new RiAndInit(entityTypeMessages);
        SetRollMaxValue setRollMaxValue = new SetRollMaxValue(entityTypeMessages);

        Clue clue = new Clue(entityTypeMessages);
        Kp kp = new Kp(entityTypeMessages);


        if (isRH) {
            roll.rh();
        }

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
            team.hp();
        } else if (isTeamSan) {
            team.san();
        } else if (isTeamDesc) {
            team.desc();
        } else if (isTeamEn) {
            team.en();
        }

        if (isEN) {
            try {
                skillUp.en();
            } catch (NotFoundSkillException e) {
                Log.error(e.getMessage(), e);
            }
        }

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

        if (isRi) {
            riAndInit.ri();
        } else if (isInit) {
            riAndInit.init();
        } else if (isInitClr) {
            riAndInit.clr();
        }

        if (isClueRm) {
            clue.rm();
        } else if (isClueSet) {
            clue.set();
        } else if (isClueClr) {
            clue.clr();
        } else if (isClueShow) {
            clue.show();
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
}
