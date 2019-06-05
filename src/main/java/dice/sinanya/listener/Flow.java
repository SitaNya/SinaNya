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
    private boolean isST = false;
    private boolean isSHOW = false;
    private boolean isLIST = false;
    private boolean isMOVE = false;
    private boolean isTEAM = false;

    Flow(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
        String messages = entityTypeMessages.getMsgGet().getMsg().trim();
        checkMessages(messages);
    }

    private void checkMessages(String messages) {
        String tagR = ".r";

        String tagRH = ".rh";
        String tagRA = ".ra";
        String tagRC = ".rc";
        String tagST = ".st";

        String tagSHOW = ".show";
        String tagLIST = ".list";
        String tagMOVE = ".move";
        String tagTEAM = ".team";

        if (messages.length() >= tagSHOW.length()) {
            isSHOW = messages.substring(0, tagSHOW.length()).endsWith(tagSHOW);
            isLIST = messages.substring(0, tagLIST.length()).endsWith(tagLIST);
            isMOVE = messages.substring(0, tagMOVE.length()).endsWith(tagMOVE);
            isTEAM = messages.substring(0, tagTEAM.length()).endsWith(tagTEAM);
        }
        if (messages.length() >= tagRA.length()) {
            isRH = messages.substring(0, tagRH.length()).equals(tagRH);
            isRA = messages.substring(0, tagRA.length()).equals(tagRA);
            isRC = messages.substring(0, tagRC.length()).equals(tagRC);
            isST = messages.substring(0, tagST.length()).equals(tagST);
        }
        isR = messages.substring(0, tagR.length()).equals(tagR) && !isRH && !isRA && !isRC;
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
        } else if (isST) {
            try {
                if (roles.set()) {
                    sender(entityTypeMessages, strSetPropSuccess);
                } else {
                    sender(entityTypeMessages, strPropErr);
                }
            } catch (PlayerSetException e) {
                System.out.println(e.getMessage());
            }
        } else if (isSHOW) {
            roles.show();
        } else if (isLIST) {
            roles.list();
        } else if (isMOVE) {
            roles.move();
        }
    }

    private void toPrivateAndGroup() {
        Roll roll = new Roll(entityTypeMessages);
        Team team = new Team(entityTypeMessages);

        if (isRH) {
            roll.rh();
        } else if (isTEAM) {
            team.set();
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
