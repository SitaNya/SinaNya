package dice.sinanya.tools.makedata;

import dice.sinanya.entity.EntityNickAndRandomAndSkill;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.checkdata.CheckResultLevel;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.makedata.GetNickAndRandomAndSkill.getNickAndRandomAndSkill;

public class MakeRcl implements Callable<Integer> {
    private EntityTypeMessages entityTypeMessages;
    private String rolls;

    public MakeRcl(EntityTypeMessages entityTypeMessages, String rolls) {
        this.entityTypeMessages = entityTypeMessages;
        this.rolls = rolls;
    }

    @Override
    public Integer call() {
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, rolls);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityNickAndRandomAndSkill.getRandom(), entityNickAndRandomAndSkill.getSkill(), true);
        return checkResultLevel.getLevelAndRandom().getLevel();
    }

}
