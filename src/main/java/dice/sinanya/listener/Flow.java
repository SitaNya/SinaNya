package dice.sinanya.listener;

import dice.sinanya.Dice.Roll.Roll;
import dice.sinanya.entity.EntityTypeMessages;

class Flow {
    private EntityTypeMessages entityTypeMessages;

    Flow(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    void toPrivate() {
        Roll roll = new Roll(entityTypeMessages);
        roll.r();
    }

    void toGroup() {
        Roll roll = new Roll(entityTypeMessages);
        roll.r();
    }

    void toDisGroup() {
        Roll roll = new Roll(entityTypeMessages);
        roll.r();
    }
}
