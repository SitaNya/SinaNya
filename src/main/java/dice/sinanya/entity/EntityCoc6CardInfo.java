package dice.sinanya.entity;

import dice.sinanya.entity.imal.CocCardInfo;
import dice.sinanya.entity.imal.GetDb;

import static java.lang.Math.ceil;

/**
 * 6版数据细化对象
 *
 * @author SitaNya
 */
public class EntityCoc6CardInfo extends CocCardInfo implements GetDb {

    private int luck;

    private int hp;
    private int san;
    private int idea;
    private int know;
    private String db;

    public EntityCoc6CardInfo() {
        this.luck = pow * 5;

        this.hp = (int) ceil((siz + con) / 2.0);
        this.san = pow * 5;
        this.idea = intValue * 5;
        this.know = edu * 5;
        this.db = dbGetter(siz + str);
    }

    /**
     * 获取db加值字符串
     *
     * @param a 传入数字a为力量与体型之和
     * @return 返回DB加值字符串
     */
    @Override
    public String dbGetter(int a) {
        int sizeLevel1 = 2;
        int sizeLevel2 = 12;
        int sizeLevel3 = 16;
        int sizeLevel4 = 24;
        int sizeLevel5 = 32;
        int sizeLevel6 = 40;
        if (a >= sizeLevel1 && a <= sizeLevel2) {
            return "-1D6";
        } else if (a <= sizeLevel3) {
            return "-1D4";
        } else if (a <= sizeLevel4) {
            return "0";
        } else if (a <= sizeLevel5) {
            return "1d4";
        } else if (a <= sizeLevel6) {
            return "1d6";
        } else {
            return "计算错误";
        }
    }

    public int getLuck() {
        return luck;
    }

    public int getHp() {
        return hp;
    }

    public int getSan() {
        return san;
    }

    public int getIdea() {
        return idea;
    }

    public int getKnow() {
        return know;
    }

    public String getDb() {
        return db;
    }
}
