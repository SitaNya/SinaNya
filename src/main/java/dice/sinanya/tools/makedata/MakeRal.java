package dice.sinanya.tools.makedata;

import dice.sinanya.entity.EntityNickAndRandomAndSkill;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.checkdata.CheckResultLevel;

import static dice.sinanya.tools.makedata.GetNickAndRandomAndSkill.getNickAndRandomAndSkill;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: ral的多线程判断成功等级类
 */
public class MakeRal {

    private EntityTypeMessages entityTypeMessages;
    private String rolls;

    public MakeRal(EntityTypeMessages entityTypeMessages, String rolls) {
        this.entityTypeMessages = entityTypeMessages;
        this.rolls = rolls;
    }

    public Integer call() {
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, rolls);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityNickAndRandomAndSkill.getRandom(), entityNickAndRandomAndSkill.getSkill(), false);
        return checkResultLevel.getLevelAndRandom().getLevel();
    }


}
