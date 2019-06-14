package dice.sinanya.entity;

import dice.sinanya.entity.imal.CocCardInfo;
import dice.sinanya.entity.imal.GetCoc7Info;

/**
 * 7版数据细化对象
 *
 * @author SitaNya
 */
public class EntityCoc7CardInfo extends CocCardInfo implements GetCoc7Info {
    private int luck;
    private int hasLuck;

    private int hp;
    private int san;
    private int mp;
    private String db;
    private int build;
    private int mov;

    public EntityCoc7CardInfo() {
        this.luck = get3d6multiply() * 5;
        this.hasLuck = notLuck + luck;

        this.hp = (siz * 5 + con * 5) / 10;
        this.san = pow * 5;
        this.mp = pow;
        this.db = dbGetter(siz * 5 + str * 5);
        this.build = buildGetter(siz * 5 + str * 5);
        this.mov = movGetter(str * 5, siz * 5, dex * 5);
    }

    public int getLuck() {
        return luck;
    }

    public int getHasLuck() {
        return hasLuck;
    }

    public int getHp() {
        return hp;
    }

    public int getSan() {
        return san;
    }

    public int getMp() {
        return mp;
    }

    public String getDb() {
        return db;
    }

    public int getBuild() {
        return build;
    }

    public int getMov() {
        return mov;
    }

    @Override
    public int getStr() {
        return str * 5;
    }

    @Override
    public int getCon() {
        return con * 5;
    }

    @Override
    public int getSiz() {
        return siz * 5;
    }

    @Override
    public int getDex() {
        return dex * 5;
    }

    @Override
    public int getApp() {
        return app * 5;
    }

    @Override
    public int getInt() {
        return intValue * 5;
    }

    @Override
    public int getPow() {
        return pow * 5;
    }

    @Override
    public int getEdu() {
        return edu * 5;
    }

    @Override
    public int getNotLuck() {
        return notLuck;
    }
}
