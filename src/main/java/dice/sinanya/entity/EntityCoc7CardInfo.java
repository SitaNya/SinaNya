package dice.sinanya.entity;

import dice.sinanya.entity.imal.CocCardInfo;
import dice.sinanya.entity.imal.GetCoc7Info;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: coc7版数据细化对象
 */
public class EntityCoc7CardInfo extends CocCardInfo implements GetCoc7Info {
    /**
     * @param luck 幸运
     * @param hasLuck 含幸运总和
     * @param hp 血量
     * @param san 理智
     * @param mp 蓝量
     * @param db 伤害加值
     * @param build 体型加值
     * @param mov 移动
     */
    private int luck;
    private int hasLuck;

    private int hp;
    private int san;
    private int mp;
    private String db;
    private int build;
    private int mov;

    /**
     * 这里给一些该*5的数据*5了，然后计算了db、血量、理智、体型、移动
     */
    public EntityCoc7CardInfo() {
        this.luck = get3d6multiply() * 5;
        this.hasLuck = notLuck * 5 + luck;

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

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getStr() {
        return str * 5;
    }

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getCon() {
        return con * 5;
    }

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getSiz() {
        return siz * 5;
    }

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getDex() {
        return dex * 5;
    }

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getApp() {
        return app * 5;
    }

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getInt() {
        return intValue * 5;
    }

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getPow() {
        return pow * 5;
    }

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getEdu() {
        return edu * 5;
    }

    /**
     * @return 返回7版*5后的数据
     */
    @Override
    public int getNotLuck() {
        return notLuck * 5;
    }
}
