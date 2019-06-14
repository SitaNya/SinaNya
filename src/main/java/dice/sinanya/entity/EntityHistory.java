package dice.sinanya.entity;

/**
 * 历史骰点信息对象
 *
 * @author SitaNya
 */
public class EntityHistory {

    private String qqId;
    private int fumble = 0;
    private int criticalSuccess = 0;
    private int extremeSuccess = 0;
    private int hardSuccess = 0;
    private int success = 0;
    private int failure = 0;
    private int times = 0;
    private int mean = 0;

    public EntityHistory(String qqId) {
        this.qqId = qqId;
    }

    public EntityHistory(String qqId, int fumble, int criticalSuccess, int extremeSuccess, int hardSuccess, int success, int failure, int times, int mean) {
        this.qqId = qqId;
        this.fumble = fumble;
        this.criticalSuccess = criticalSuccess;
        this.extremeSuccess = extremeSuccess;
        this.hardSuccess = hardSuccess;
        this.success = success;
        this.failure = failure;
        this.times = times;
        this.mean = mean;
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
        return mean;
    }

    private void setMean(int mean) {
        this.mean = (this.mean * (times - 1) + mean) / times;
    }

    public void update(EntityLevelResult entityLevelResult) {
        int level = entityLevelResult.getLevel();
        int value = entityLevelResult.getResult();
        updateLevel(level);
        times++;
        setMean(value);
    }

    public void update(int level) {
        updateLevel(level);
    }

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
}
