package dice.sinanya.listener;

import dice.sinanya.dice.manager.Roles;
import dice.sinanya.dice.manager.Team;
import dice.sinanya.dice.roll.*;
import dice.sinanya.dice.system.Bot;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;

import static dice.sinanya.system.MessagesError.strPropErr;
import static dice.sinanya.system.MessagesError.strSetPropSuccess;
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

    Flow(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
        String messages = entityTypeMessages.getMsgGet().getMsg().trim();
        checkMessages(messages);
    }

    private void checkMessages(String messages) {
        String header = "^*[.。][ ]*";
        String tagR = "^\\.[ ]*r.*";

        String tagRH = header + "rh.*";
        String tagRA = header + "ra.*";
        String tagRC = header + "rc.*";

        String tagRB = header + "rb.*";
        String tagRP = header + "rp.*";

        String tagSC = header + "sc.*";

        String tagEN = header + "en.*";

        String headerSt = header + "st[ ]*";
        String tagStSet = headerSt + ".*";
        String tagStShow = headerSt + "show.*";
        String tagStList = headerSt + "list.*";
        String tagStMove = headerSt + "move.*";

        String headerTeam = header + "team[ ]*";
        String tagTeamShow = headerTeam + ".*";
        String tagTeamSet = headerTeam + "set.*";
        String tagTeamClr = headerTeam + "clr.*";
        String tagTeamMove = headerTeam + "move.*";
        String tagTeamCall = headerTeam + "call.*";
        String tagTeamHp = headerTeam + "hp.*";
        String tagTeamSan = headerTeam + "san.*";

        String headerBot = header + "bot[ ]*";
        String tagBotShow = headerBot + ".*";
        String tagBotOn = headerBot + "on.*";
        String tagBotOff = headerBot + "off.*";
        String tagBotExit = headerBot + "exit.*";

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
        Bot bot = new Bot(entityTypeMessages);

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
            sanCheck.sc();
        }

        if (isRB) {
            rewardAndPunishment.rb();
        } else if (isRP) {
            rewardAndPunishment.rp();
        }

        if (isBotInfo) {
            bot.info();
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
