package dice.sinanya.entity;

public class EntityHistory {

    String qqId;
    int Fumble = 0;
    int CriticalSuccess = 0;
    int ExtremeSuccess = 0;
    int HardSuccess = 0;
    int Success = 0;
    int Failure = 0;
    int times = 0;
    int mean = 0;

    public EntityHistory(String qqId) {
        this.qqId = qqId;
    }

    public EntityHistory(String qqId, int fumble, int criticalSuccess, int extremeSuccess, int hardSuccess, int success, int failure, int times, int mean) {
        this.qqId = qqId;
        Fumble = fumble;
        CriticalSuccess = criticalSuccess;
        ExtremeSuccess = extremeSuccess;
        HardSuccess = hardSuccess;
        Success = success;
        Failure = failure;
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
        return Fumble;
    }

    public void setFumble(int fumble) {
        Fumble = fumble;
    }

    public int getCriticalSuccess() {
        return CriticalSuccess;
    }

    public void setCriticalSuccess(int criticalSuccess) {
        CriticalSuccess = criticalSuccess;
    }

    public int getExtremeSuccess() {
        return ExtremeSuccess;
    }

    public void setExtremeSuccess(int extremeSuccess) {
        ExtremeSuccess = extremeSuccess;
    }

    public int getHardSuccess() {
        return HardSuccess;
    }

    public void setHardSuccess(int hardSuccess) {
        HardSuccess = hardSuccess;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public int getFailure() {
        return Failure;
    }

    public void setFailure(int failure) {
        Failure = failure;
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

    public void setMean(int mean) {
        this.mean = (this.mean * (times - 1) + mean) / times;
    }

    public void update(EntityLevelResult entityLevelResult) {
        int level = entityLevelResult.getLevel();
        int value = entityLevelResult.getResult();
        switch (level) {
            case 0:
                Fumble++;
                break;
            case 5:
                CriticalSuccess++;
                break;
            case 4:
                ExtremeSuccess++;
                break;
            case 3:
                HardSuccess++;
                break;
            case 2:
                Success++;
                break;
            case 1:
                Failure++;
                break;
            default:
                break;
        }
        times++;
        setMean(value);
    }
}
