package dice.sinanya.listener;

import dice.sinanya.Dice.Roll.Roll;
import dice.sinanya.Dice.Roll.RollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;

class Flow {
    private EntityTypeMessages entityTypeMessages;

    Flow(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    void toPrivate() {
        Roll roll = new Roll(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);

        String messages = entityTypeMessages.getMsgGet().getMsg().trim();

        if (messages.matches("^.r[^abcphs]*")) {
            roll.r();
        } else if (messages.matches("^.ra[0-9 ]*")) {
            rollAndCheck.ra();
        }
    }

    void toGroup() {
        Roll roll = new Roll(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);

        String messages = entityTypeMessages.getMsgGet().getMsg();

        if (messages.matches("^.r[^abcphs]*")) {
            roll.r();
        } else if (messages.matches("^.rh[0-9d ]*")) {
            roll.rh();
        } else if (messages.matches("^.ra[0-9 ]*")) {
            rollAndCheck.ra();
        }
    }

    void toDisGroup() {
        Roll roll = new Roll(entityTypeMessages);
        RollAndCheck rollAndCheck = new RollAndCheck(entityTypeMessages);

        String messages = entityTypeMessages.getMsgGet().getMsg();

        if (messages.matches("^.r[^abcphs]*.*")) {
            roll.r();
        } else if (messages.matches("^.rh[0-9 ]*")) {
            roll.rh();
        } else if (messages.matches("^.ra[0-9 ]*")) {
            rollAndCheck.ra();
        }
    }
}
