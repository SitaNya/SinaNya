package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-08-13
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class EntityBanProperties {
    boolean cloudBan;
    boolean Prometheus;
    int PrometheusPort;
    boolean heap;

    String master;
    String banListInputNotId;
    String notBanListInput;
    String notMaster;

    boolean doSomthingForBanUserInGroup;
    boolean ignoreBanUser;
    boolean leaveByBanUser;

    boolean leaveGroupByBan;
    boolean banGroupBecauseBan;
    boolean banGroupBecauseReduce;
    boolean banUserBecauseReduce;

    String addGroup;
    String addFriend;
    String refuseGroupByBan;
    String refuseFriendByBan;

    String whiteGroup;
    String whiteUser;
    int alterFrequentness;
    int banFrequentness;

    int clearGroupByOff;
    int clearGroup;

    String frequentnessAlterInfo;
    String frequentnessBanInfo;

    String clearGroupByOffInfo;
    String clearGroupInfo;

    public EntityBanProperties() {
        cloudBan = true;
        Prometheus = false;
        PrometheusPort = 62258;
        heap = false;

        master = "";
        banListInputNotId = "输入的不是QQ号或群号";
        notMaster = "除设定的Master外其他人不可使用此命令";
        notBanListInput = "只有黑名单录入者才可以删除相应调目";

        doSomthingForBanUserInGroup = true;
        ignoreBanUser = true;
        leaveByBanUser = false;

        leaveGroupByBan = true;
        banGroupBecauseBan = true;
        banGroupBecauseReduce = true;
        banUserBecauseReduce = true;

        clearGroupByOff = 10;
        clearGroup = 30;

        addGroup = "已加入群";
        addFriend = "已添加好友";
        refuseGroupByBan = "拒绝加入黑名单群";
        refuseFriendByBan = "拒绝添加黑名单好友";

        alterFrequentness = 40;
        banFrequentness = 80;

        whiteGroup = "";
        whiteUser = "";
        frequentnessAlterInfo = "请勿刷屏，过多可能导致退群拉黑，谢谢合作";
        frequentnessBanInfo = "检测到极大量刷屏，正在退群拉黑";

        clearGroupByOffInfo = "已在群: %s 中超过 %s 日未响应且处于关闭状态，即将退群。\n此次退群不会记录黑名单，如遇到问题请至群162279609进行反馈或使用退群命令缓解问题";
        clearGroupInfo = "已在群: %s 中超过 %s 日未响应，即将退群。\n此次退群不会记录黑名单，如遇到问题请至群162279609进行反馈或使用退群命令缓解问题";
    }

    public String getClearGroupByOffInfo() {
        return clearGroupByOffInfo;
    }

    public void setClearGroupByOffInfo(String clearGroupByOffInfo) {
        this.clearGroupByOffInfo = clearGroupByOffInfo;
    }

    public String getClearGroupInfo() {
        return clearGroupInfo;
    }

    public void setClearGroupInfo(String clearGroupInfo) {
        this.clearGroupInfo = clearGroupInfo;
    }

    public int getPrometheusPort() {
        return PrometheusPort;
    }

    public void setPrometheusPort(int prometheusPort) {
        PrometheusPort = prometheusPort;
    }

    public String getFrequentnessAlterInfo() {
        return frequentnessAlterInfo;
    }

    public void setFrequentnessAlterInfo(String frequentnessAlterInfo) {
        this.frequentnessAlterInfo = frequentnessAlterInfo;
    }

    public String getFrequentnessBanInfo() {
        return frequentnessBanInfo;
    }

    public void setFrequentnessBanInfo(String frequentnessBanInfo) {
        this.frequentnessBanInfo = frequentnessBanInfo;
    }

    public int getClearGroupByOff() {
        return clearGroupByOff;
    }

    public void setClearGroupByOff(int clearGroupByOff) {
        this.clearGroupByOff = clearGroupByOff;
    }

    public int getClearGroup() {
        return clearGroup;
    }

    public void setClearGroup(int clearGroup) {
        this.clearGroup = clearGroup;
    }

    public int getBanFrequentness() {
        return banFrequentness;
    }

    public void setBanFrequentness(int banFrequentness) {
        this.banFrequentness = banFrequentness;
    }

    public int getAlterFrequentness() {
        return alterFrequentness;
    }

    public void setAlterFrequentness(int alterFrequentness) {
        this.alterFrequentness = alterFrequentness;
    }

    public String getNotBanListInput() {
        return notBanListInput;
    }

    public void setNotBanListInput(String notBanListInput) {
        this.notBanListInput = notBanListInput;
    }

    public boolean isCloudBan() {
        return cloudBan;
    }

    public void setCloudBan(boolean cloudBan) {
        this.cloudBan = cloudBan;
    }

    public boolean isPrometheus() {
        return Prometheus;
    }

    public void setPrometheus(boolean prometheus) {
        Prometheus = prometheus;
    }

    public boolean isHeap() {
        return heap;
    }

    public void setHeap(boolean heap) {
        this.heap = heap;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getBanListInputNotId() {
        return banListInputNotId;
    }

    public void setBanListInputNotId(String banListInputNotId) {
        this.banListInputNotId = banListInputNotId;
    }

    public String getNotMaster() {
        return notMaster;
    }

    public void setNotMaster(String notMaster) {
        this.notMaster = notMaster;
    }

    public boolean isDoSomthingForBanUserInGroup() {
        return doSomthingForBanUserInGroup;
    }

    public void setDoSomthingForBanUserInGroup(boolean doSomthingForBanUserInGroup) {
        this.doSomthingForBanUserInGroup = doSomthingForBanUserInGroup;
    }

    public boolean isIgnoreBanUser() {
        return ignoreBanUser;
    }

    public void setIgnoreBanUser(boolean ignoreBanUser) {
        this.ignoreBanUser = ignoreBanUser;
    }

    public boolean isLeaveByBanUser() {
        return leaveByBanUser;
    }

    public void setLeaveByBanUser(boolean leaveByBanUser) {
        this.leaveByBanUser = leaveByBanUser;
    }

    public boolean isLeaveGroupByBan() {
        return leaveGroupByBan;
    }

    public void setLeaveGroupByBan(boolean leaveGroupByBan) {
        this.leaveGroupByBan = leaveGroupByBan;
    }

    public boolean isBanGroupBecauseBan() {
        return banGroupBecauseBan;
    }

    public void setBanGroupBecauseBan(boolean banGroupBecauseBan) {
        this.banGroupBecauseBan = banGroupBecauseBan;
    }

    public boolean isBanGroupBecauseReduce() {
        return banGroupBecauseReduce;
    }

    public void setBanGroupBecauseReduce(boolean banGroupBecauseReduce) {
        this.banGroupBecauseReduce = banGroupBecauseReduce;
    }

    public boolean isBanUserBecauseReduce() {
        return banUserBecauseReduce;
    }

    public void setBanUserBecauseReduce(boolean banUserBecauseReduce) {
        this.banUserBecauseReduce = banUserBecauseReduce;
    }

    public String getAddGroup() {
        return addGroup;
    }

    public void setAddGroup(String addGroup) {
        this.addGroup = addGroup;
    }

    public String getAddFriend() {
        return addFriend;
    }

    public void setAddFriend(String addFriend) {
        this.addFriend = addFriend;
    }

    public String getRefuseGroupByBan() {
        return refuseGroupByBan;
    }

    public void setRefuseGroupByBan(String refuseGroupByBan) {
        this.refuseGroupByBan = refuseGroupByBan;
    }

    public String getRefuseFriendByBan() {
        return refuseFriendByBan;
    }

    public void setRefuseFriendByBan(String refuseFriendByBan) {
        this.refuseFriendByBan = refuseFriendByBan;
    }

    public String getWhiteGroup() {
        return whiteGroup;
    }

    public void setWhiteGroup(String whiteGroup) {
        this.whiteGroup = whiteGroup;
    }

    public String getWhiteUser() {
        return whiteUser;
    }

    public void setWhiteUser(String whiteUser) {
        this.whiteUser = whiteUser;
    }
}
