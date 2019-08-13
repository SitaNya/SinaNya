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

    public EntityBanProperties() {
        cloudBan = true;
        Prometheus = false;
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

        addGroup = "已加入群";
        addFriend = "已添加好友";
        refuseGroupByBan = "拒绝加入黑名单群";
        refuseFriendByBan = "拒绝添加黑名单好友";

        whiteGroup = "";
        whiteUser = "";
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
