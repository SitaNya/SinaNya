package dice.sinanya.listener;

import dice.sinanya.dice.manager.Roles;
import dice.sinanya.dice.manager.Team;
import dice.sinanya.dice.roll.Roll;
import dice.sinanya.dice.roll.RollAndCheck;
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

    Flow(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
        String messages = entityTypeMessages.getMsgGet().getMsg().trim();
        checkMessages(messages);
    }

    private void checkMessages(String messages) {
        String tagR = "^.r.*";

        String tagRH = "^.rh.*";
        String tagRA = "^.ra.*";
        String tagRC = "^.rc.*";

        String tagStSet = "^.st.*";
        String tagStShow = "^.st[ ]*show.*";
        String tagStList = "^.st[ ]*list.*";
        String tagStMove = "^.st[ ]*move.*";

        String tagTeamSet = "^.team[ ]*set.*";
        String tagTeamShow = "^.team.*";
        String tagTeamClr = "^.team[ ]*clr.*";
        String tagTeamMove = "^.team[ ]*move.*";
        String tagTeamCall = "^.team[ ]*call.*";
        String tagTeamHp = "^.team[ ]*hp.*";
        String tagTeamSan = "^.team[ ]*san.*";

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


        isRH = messages.matches(tagRH);
        isRA = messages.matches(tagRA);
        isRC = messages.matches(tagRC);
        isR = messages.matches(tagR) && !isRH && !isRA && !isRC;
    }

    void toPrivate() {
        Roll roll = new Roll(entityTypeMessages);
        Roles roles = new Roles(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);

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
    }

    private void toPrivateAndGroup() {
        Roll roll = new Roll(entityTypeMessages);
        Team team = new Team(entityTypeMessages);

        if (isRH) {
            roll.rh();
        } else if (isTeamSet) {
            team.set();
        } else if (isTeamShow) {
            team.show();
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
