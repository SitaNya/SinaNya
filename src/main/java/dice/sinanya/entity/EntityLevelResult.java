package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 骰点成功等级对象，包含成功等级与骰点结果
 */
public class EntityLevelResult {
    /**
     * @param level 成功等级，以数字表示，越小失败程度越高，越大成功程度越高
     * @param result 骰点具体结果值
     */
    private int level;
    private int result;

    public EntityLevelResult(int level, int result) {
        this.level = level;
        this.result = result;
    }

    public int getLevel() {
        return level;
    }

    public int getResult() {
        return result;
    }
}
