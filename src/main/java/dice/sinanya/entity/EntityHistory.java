package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 历史骰点信息对象
 */
public class EntityHistory {

    /**
     * @param qqId qq号
     * @param fumble 大失败次数
     * @param criticalSuccess 大成功次数
     * @param extremeSuccess 极难成功次数
     * @param hardSuccess 困难成功次数
     * @param success 成功次数
     * @param failure 失败次数
     * @param times 骰点次数
     * @param mean 骰点均值
     */
    private String qqId;
    private int fumble = 0;
    private int criticalSuccess = 0;
    private int extremeSuccess = 0;
    private int hardSuccess = 0;
    private int success = 0;
    private int failure = 0;
    private int times = 0;
    private double mean = 0.0;

    public EntityHistory(String qqId) {
        this.qqId = qqId;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    public int getFumble() {
        return fumble;
    }

    public int getCriticalSuccess() {
        return criticalSuccess;
    }

    public int getExtremeSuccess() {
        return extremeSuccess;
    }

    public int getHardSuccess() {
        return hardSuccess;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getMean() {
        return (int) Math.floor(mean);
    }

    private void setMean(int mean) {
        this.mean = (this.mean * (times - 1) + mean) / times;
    }

    /**
     * 更新对象中均值、等级、次数等数据，给一般骰点使用
     *
     * @param entityLevelResult 骰点等级结果对象，包含了成功等级和骰点结果值
     */
    public void update(EntityLevelResult entityLevelResult) {
        int level = entityLevelResult.getLevel();
        int value = entityLevelResult.getResult();
        updateLevel(level);
        times++;
        setMean(value);
    }

    /**
     * 更新每种成功等级的次数，给ral和rcl使用
     *
     * @param level 成功等级
     */
    public void update(int level) {
        updateLevel(level);
    }

    /**
     * 根据成功等级更新每种成功等级的次数，内部方法
     *
     * @param level 成功等级
     */
    private void updateLevel(int level) {
        switch (level) {
            case 0:
                fumble++;
                break;
            case 5:
                criticalSuccess++;
                break;
            case 4:
                extremeSuccess++;
                break;
            case 3:
                hardSuccess++;
                break;
            case 2:
                success++;
                break;
            case 1:
                failure++;
                break;
            default:
                break;
        }
    }

    public void setFumble(int fumble) {
        this.fumble = fumble;
    }

    public void setCriticalSuccess(int criticalSuccess) {
        this.criticalSuccess = criticalSuccess;
    }

    public void setExtremeSuccess(int extremeSuccess) {
        this.extremeSuccess = extremeSuccess;
    }

    public void setHardSuccess(int hardSuccess) {
        this.hardSuccess = hardSuccess;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }
}
