package dice.sinanya.listener;

import dice.sinanya.dice.Game.Jrrp;
import dice.sinanya.dice.get.*;
import dice.sinanya.dice.getbook.Book;
import dice.sinanya.dice.manager.*;
import dice.sinanya.dice.roll.*;
import dice.sinanya.dice.system.Bot;
import dice.sinanya.dice.system.Help;
import dice.sinanya.dice.system.History;
import dice.sinanya.dice.system.Log;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;

import static dice.sinanya.system.MessagesError.strPropErr;
import static dice.sinanya.system.MessagesError.strSetPropSuccess;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.Sender.sender;

class Flow {
    private EntityTypeMessages entityTypeMessages;

    private boolean isR = false;
    private boolean isRH = false;
    private boolean isRA = false;
    private boolean isRC = false;
    private boolean isRE = false;
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

    private boolean isBotInfo = false;
    private boolean isBotOn = false;
    private boolean isBotOff = false;
    private boolean isBotExit = false;

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

    Flow(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
        String messages = entityTypeMessages.getMsgGet().getMsg().trim();
        checkMessages(messages);
    }

    private void checkMessages(String messages) {

        isTeamSet = messages.matches(tagTeamSet);
        isTeamClr = messages.matches(tagTeamClr);
        isTeamMove = messages.matches(tagTeamMove);
        isTeamCall = messages.matches(tagTeamCall);
        isTeamHp = messages.matches(tagTeamHp);
        isTeamSan = messages.matches(tagTeamSan);
        isTeamDesc = messages.matches(tagTeamDesc);
        isTeamShow = messages.matches(tagTeamShow) && !isTeamSet && !isTeamClr && !isTeamMove && !isTeamCall && !isTeamHp && !isTeamSan && !isTeamDesc;

        isStShow = messages.matches(tagStShow);
        isStList = messages.matches(tagStList);
        isStMove = messages.matches(tagStMove);
        isStSet = messages.matches(tagStSet) && !isStShow && !isStList && !isStMove;

        isBotOn = messages.matches(tagBotOn);
        isBotOff = messages.matches(tagBotOff);
        isBotExit = messages.matches(tagBotExit);
        isBotInfo = messages.matches(tagBotShow) && !isBotOn && !isBotOff && !isBotExit;

        isHelpNormal = messages.matches(tagHelpNormal);
        isHelpMake = messages.matches(tagHelpMake);
        isHelpGroup = messages.matches(tagHelpGroup);
        isHelpBook = messages.matches(tagHelpBook);
        isHelpDnd = messages.matches(tagHelpDnd);
        isHelpInfo = messages.matches(tagHelpInfo) && !isHelpNormal && !isHelpMake && !isHelpGroup && !isHelpBook && !isHelpDnd;

        isCoc7d = messages.matches(tagCoc7d);
        isCoc6d = messages.matches(tagCoc6d);
        isCoc6 = messages.matches(tagCoc6);
        isCoc7 = messages.matches(tagCoc7) && !isCoc7d && !isCoc6d && !isCoc6;

        isDnd = messages.matches(tagDnd);
        isRi = messages.matches(tagRi);
        isInitClr = messages.matches(tagInitClr);
        isInit = messages.matches(tagInit) && !isInitClr;

        isBookCard = messages.matches(tagBookCard);
        isBookMAKE = messages.matches(tagBookMake);
        isBookRP = messages.matches(tagBookRP);
        isBookKP = messages.matches(tagBookKP);

        isLogOn = messages.matches(tagLogOn);
        isLogOff = messages.matches(tagLogOff);
        isLogGet = messages.matches(tagLogGet);
        isLogList = messages.matches(tagLogList);
        isLogDel = messages.matches(tagLogDel);


        isClueClr = messages.matches(tagClueClr);
        isClueShow = messages.matches(tagClueShow);
        isClueRm = messages.matches(tagClueRm);
        isClueSet = messages.matches(tagClueSet) && !isClueClr && !isClueShow && !isClueRm;

        isKp = messages.matches(tagKp);
        isHiy = messages.matches(tagHiy);

        isJRRP = messages.matches(tagJRRP);

        isNPC = messages.matches(tagNPC);

        isBG = messages.matches(tagBG);

        isTZ = messages.matches(tagTZ);

        isGas = messages.matches(tagGas);

        isTi = messages.matches(tagTi);
        isLi = messages.matches(tagLi);

        isRB = messages.matches(tagRB);
        isRP = messages.matches(tagRP);

        isSC = messages.matches(tagSC);

        isEN = messages.matches(tagEN);

        isSetRollMaxValue = messages.matches(tagSetRollMaxValue);

        isRAL = messages.matches(tagRAL);
        isRCL = messages.matches(tagRCL);
        isRAV = messages.matches(tagRAV);
        isRCV = messages.matches(tagRCV);
        isRH = messages.matches(tagRH);
        isRA = messages.matches(tagRA) && !isRAL && !isRAV;
        isRC = messages.matches(tagRC) && !isRCL && !isRCV;
        isRE = messages.matches(tagRE);
        isR = messages.matches(tagR) && !isRH && !isRA && !isRC && !isRB && !isRP && !isRi && !isRE && !isRAL && !isRCL && !isRAV && !isRCV;
    }

    void toPrivate() {
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
        BG bg = new BG(entityTypeMessages);
        NPC npc = new NPC(entityTypeMessages);
        TZ tz = new TZ(entityTypeMessages);
        Gas gas = new Gas(entityTypeMessages);
        History history = new History(entityTypeMessages);
        RollForDnd rollForDnd = new RollForDnd(entityTypeMessages);
        Jrrp jrrp=new Jrrp(entityTypeMessages);

        if (isR) {
            roll.r();
        } else if (isRA) {
            rollAndCheck.ra();
        } else if (isRC) {
            rollAndCheck.rc();
        } else if (isRE) {
            rollForDnd.re();
        } else if (isRAL) {
            rollAndCheck.ral();
        } else if (isRCL) {
            rollAndCheck.rcl();
        } else if (isRAV) {
            rollAndCheck.rav();
        } else if (isRCV) {
            rollAndCheck.rcv();
        }

        if (isStSet) {
            try {
                if (roles.set()) {
                    sender(entityTypeMessages, strSetPropSuccess);
                } else {
                    sender(entityTypeMessages, strPropErr);
                }
            } catch (PlayerSetException e) {
                System.out.println(e.getMessage());
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
                e.printStackTrace();
            }
        }

        if (isRB) {
            rewardAndPunishment.rb();
        } else if (isRP) {
            rewardAndPunishment.rp();
        }

        if (isBotInfo) {
            bot.info();
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
            bg.bg();
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

        if (isJRRP){
            jrrp.get();
        }

    }

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
        }

        if (isEN) {
            skillUp.en();
        }

        if (isBotOn) {
            bot.on();
        } else if (isBotOff) {
            bot.off();
        } else if (isBotExit) {
            bot.exit();
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

    void toGroup() {
        toPrivateAndGroup();
    }

    void toDisGroup() {
        toPrivateAndGroup();
    }

}
