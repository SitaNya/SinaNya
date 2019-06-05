package dice.sinanya.listener;

import dice.sinanya.dice.roll.Roll;
import dice.sinanya.dice.roll.RollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;

class Flow {
    private EntityTypeMessages entityTypeMessages;

    private boolean isR = false;
    private boolean isRH = false;
    private boolean isRA = false;
    private boolean isRC = false;

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

        isRH = messages.substring(0, tagRH.length()).equals(tagRH);
        isRA = messages.substring(0, tagRA.length()).equals(tagRA);
        isRC = messages.substring(0, tagRC.length()).equals(tagRC);
        isR = messages.substring(0, tagR.length()).equals(tagR) && !isRH && !isRA && !isR;
    }

    void toPrivate() {
        Roll roll = new Roll(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);

        if (isR) {
            roll.r();
        } else if (isRA) {
            rollAndCheck.ra();
        } else if (isRC) {
            rollAndCheck.rc();
        }
    }

    private void groupFunction() {
        Roll roll = new Roll(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);

        if (isR) {
            roll.r();
        } else if (isRH) {
            roll.rh();
        } else if (isRA) {
            rollAndCheck.ra();
        } else if (isRC) {
            rollAndCheck.rc();
        }
    }

    void toGroup() {
        groupFunction();
    }

    void toDisGroup() {
        groupFunction();
    }

}
