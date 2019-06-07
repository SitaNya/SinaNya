package dice.sinanya.listener;

import dice.sinanya.dice.get.BG;
import dice.sinanya.dice.get.MakeCocCard;
import dice.sinanya.dice.get.NPC;
import dice.sinanya.dice.getbook.Book;
import dice.sinanya.dice.manager.Roles;
import dice.sinanya.dice.manager.Team;
import dice.sinanya.dice.roll.*;
import dice.sinanya.dice.system.Bot;
import dice.sinanya.dice.system.Help;
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

    private boolean isRB = false;
    private boolean isRP = false;

    private boolean isSC = false;

    private boolean isEN = false;

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

    private boolean isBotInfo = false;
    private boolean isBotOn = false;
    private boolean isBotOff = false;
    private boolean isBotExit = false;

    private boolean isHelpNormal = false;
    private boolean isHelpMake = false;
    private boolean isHelpGroup = false;
    private boolean isHelpBook = false;


    private boolean isBookCard = false;
    private boolean isBookRP = false;
    private boolean isBookKP = false;
    private boolean isBookMAKE = false;

    private boolean isNPC = false;

    private boolean isBG = false;

    private boolean isCoc7 = false;
    private boolean isCoc6 = false;
    private boolean isCoc7d = false;
    private boolean isCoc6d = false;

    private boolean isTi = false;
    private boolean isLi = false;

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
        isTeamShow = messages.matches(tagTeamShow) && !isTeamSet && !isTeamClr && !isTeamMove && !isTeamCall && !isTeamHp && !isTeamSan;

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

        isCoc7d = messages.matches(tagCoc7d);
        isCoc6d = messages.matches(tagCoc6d);
        isCoc6 = messages.matches(tagCoc6);
        isCoc7 = messages.matches(tagCoc7) && !isCoc7d && !isCoc6d && !isCoc6;

        isBookCard = messages.matches(tagBookCard);
        isBookMAKE = messages.matches(tagBookMake);
        isBookRP = messages.matches(tagBookRP);
        isBookKP = messages.matches(tagBookKP);

        isNPC = messages.matches(tagNPC);

        isBG = messages.matches(tagBG);

        isTi = messages.matches(tagTi);
        isLi = messages.matches(tagLi);

        isRB = messages.matches(tagRB);
        isRP = messages.matches(tagRP);

        isSC = messages.matches(tagSC);

        isEN = messages.matches(tagEN);

        isRH = messages.matches(tagRH);
        isRA = messages.matches(tagRA);
        isRC = messages.matches(tagRC);
        isR = messages.matches(tagR) && !isRH && !isRA && !isRC && !isRB && !isRP;
    }

    void toPrivate() {
        Roll roll = new Roll(entityTypeMessages);
        Roles roles = new Roles(entityTypeMessages);
        SanCheck sanCheck = new SanCheck(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);
        RewardAndPunishment rewardAndPunishment = new RewardAndPunishment(entityTypeMessages);
        MakeCocCard makeCocCard = new MakeCocCard(entityTypeMessages);
        Bot bot = new Bot(entityTypeMessages);
        TiAndLi tiAndLi = new TiAndLi(entityTypeMessages);
        Help help = new Help(entityTypeMessages);
        Book book = new Book(entityTypeMessages);
        BG bg = new BG(entityTypeMessages);
        NPC npc = new NPC(entityTypeMessages);

        if (isR) {
            roll.r();
        } else if (isRA) {
            rollAndCheck.ra();
        } else if (isRC) {
            rollAndCheck.rc();
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
    }

    private void toPrivateAndGroup() {
        Roll roll = new Roll(entityTypeMessages);
        Team team = new Team(entityTypeMessages);
        SkillUp skillUp = new SkillUp(entityTypeMessages);
        Bot bot = new Bot(entityTypeMessages);


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

        toPrivate();
    }

    void toGroup() {
        toPrivateAndGroup();
    }

    void toDisGroup() {
        toPrivateAndGroup();
    }

}
