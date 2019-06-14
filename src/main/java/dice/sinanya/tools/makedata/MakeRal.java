package dice.sinanya.tools.makedata;

import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.checkdata.CheckResultLevel;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.makedata.MakeRollCheckResult.makeResult;

public class MakeRal implements Callable<Integer> {

    EntityTypeMessages entityTypeMessages;
    private String rolls;

    public MakeRal(EntityTypeMessages entityTypeMessages, String rolls) {
        this.entityTypeMessages = entityTypeMessages;
        this.rolls = rolls;
    }

    @Override
    public Integer call() {
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, rolls);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), false);
        return checkResultLevel.getLevelAndRandom().getLevel();
    }


}
