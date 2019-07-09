package dice.sinanya.tools.getinfo;

import java.util.HashMap;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 技能名对照表
 */
class MakeSkillName {
    /**
     * @param skillTable 标记了所有技能名的不同叫法，如生命和hp都会被这个对象重置为hp
     */
    static HashMap<String, String> skillTable = new HashMap<String, String>(100) {{
        put("力量", "str");
        put("敏捷", "dex");
        put("pow", "pow");
        put("意志", "pow");
        put("体质", "con");
        put("外貌", "app");
        put("教育", "edu");
        put("体型", "siz");
        put("智力", "intValue");
        put("智力/灵感", "intValue");
        put("int", "intValue");
        put("idea", "intValue");
        put("灵感", "intValue");
        put("san", "san");
        put("san值", "san");
        put("理智", "san");
        put("理智值", "san");
        put("幸运", "luck");
        put("运气", "luck");
        put("mp", "mp");
        put("魔法", "mp");
        put("hp", "hp");
        put("体力", "hp");
        put("会计", "accounting");
        put("人类学", "anthropology");
        put("估价", "evaluation");
        put("考古学", "archaeology");
        put("取悦", "enchantment");
        put("魅惑", "enchantment");
        put("攀爬", "toClimb");
        put("计算机", "computerUsage");
        put("计算机使用", "computerUsage");
        put("电脑", "computerUsage");
        put("电脑使用", "computerUsage");
        put("信用", "creditRating");
        put("信誉", "creditRating");
        put("信用评级", "creditRating");
        put("信誉度", "creditRating");
        put("信用度", "creditRating");
        put("克苏鲁", "cthulhuMythos");
        put("克苏鲁神话", "cthulhuMythos");
        put("cm", "cthulhuMythos");
        put("乔装", "disguise");
        put("闪避", "dodge");
        put("汽车", "drive");
        put("驾驶", "drive");
        put("汽车驾驶", "drive");
        put("驾驶汽车", "drive");
        put("驾驶(汽车)", "drive");
        put("驾驶:汽车", "drive");
        put("步枪/霰弹枪", "rifle");
        put("步枪", "rifle");
        put("霰弹枪", "rifle");
        put("散弹枪", "rifle");
        put("步霰", "rifle");
        put("步/霰", "rifle");
        put("步散", "rifle");
        put("步/散", "rifle");
        put("船驾驶", "shipping");
        put("船", "shipping");
        put("驾驶船", "shipping");
        put("驾驶(船)", "shipping");
        put("驾驶:船", "shipping");
        put("飞行器驾驶", "aircraftOperation");
        put("飞行器", "aircraftOperation");
        put("驾驶飞行器", "aircraftOperation");
        put("驾驶:飞行器", "aircraftOperation");
        put("驾驶(飞行器)", "aircraftOperation");
        put("机枪", "machineGun");
        put("机关枪", "machineGun");
        put("电气维修", "electricalMaintenance");
        put("电子学", "electronics");
        put("话术", "talkingSkill");
        put("快速交谈", "talkingSkill");
        put("斗殴", "aFistFight");
        put("绞具", "wrangle");
        put("手枪", "pistol");
        put("急救", "firstAid");
        put("历史", "history");
        put("恐吓", "intimidate");
        put("跳跃", "jump");
        put("母语", "motherTongue");
        put("法律", "law");
        put("图书馆", "libraryUse");
        put("图书馆使用", "libraryUse");
        put("聆听", "listen");
        put("开锁", "unlock");
        put("撬锁", "unlock");
        put("锁匠", "unlock");
        put("机械维修", "mechanicalMaintenance");
        put("医学", "medicalScience");
        put("博物学", "naturalHistory");
        put("自然学", "naturalScience");
        put("领航", "pilotage");
        put("导航", "pilotage");
        put("神秘学", "occultScience");
        put("重型操作", "operatingHeavyMachinery");
        put("重型机械", "operatingHeavyMachinery");
        put("操作重型机械", "operatingHeavyMachinery");
        put("重型", "operatingHeavyMachinery");
        put("说服", "persuade");
        put("精神分析", "psychoanalysis");
        put("精分", "psychoanalysis");
        put("心理学", "psychology");
        put("心理", "psychology");
        put("骑术", "horsemanship");
        put("妙手", "aWonderfulHand");
        put("侦察", "investigationOfCrimes");
        put("侦查", "investigationOfCrimes");
        put("潜行", "stealth");
        put("生存", "existence");
        put("游泳", "swimming");
        put("投掷", "throwValue");
        put("追踪", "trackValue");
        put("跟踪", "trackValue");
        put("驯兽", "domesticatedAnimal");
        put("潜水", "diving");
        put("爆破", "blast");
        put("读唇", "lipReading");
        put("催眠", "hypnosis");
        put("炮术", "artillery");

        put("唱歌", "sing");
        put("歌唱", "sing");
        put("作画", "paint");
        put("做画", "paint");
        put("耕做", "tillage");
        put("耕作", "tillage");
        put("摄影", "photography");
        put("表演", "perform");
        put("伪造", "forge");
        put("文学", "literature");
        put("书法", "calligraphy");
        put("乐理", "music");
        put("厨艺", "cooking");
        put("裁缝", "tailor");
        put("理发", "haircut");
        put("建筑", "architecture");
        put("舞蹈", "dance");
        put("酿酒", "makeWine");
        put("捕鱼", "fishing");
        put("制陶", "potteryMaking");
        put("雕塑", "sculpture");
        put("杂技", "acrobatics");
        put("风水", "fengshui");
        put("技术制图", "technicalDrawing");
        put("打字", "typing");
        put("速记", "shorthand");
        put("鞭子", "whip");
        put("电锯", "electricSaw");
        put("斧", "axe");
        put("剑", "sword");
        put("链枷", "flail");
        put("矛", "spear");
        put("冲锋枪", "submachineGun");
        put("弓术", "archery");
        put("火焰喷射器", "flameEjector");
        put("重武器", "heavyWeapons");
        put("骑乘", "ride");
        put("地质学", "geology");
        put("化学", "chemistry");
        put("生物学", "biology");
        put("数学", "mathematics");
        put("天文学", "astronomy");
        put("物理学", "physics");
        put("药学", "pharmacy");
        put("植物学", "botany");
        put("动物学", "zoology");
        put("密码学", "cryptography");
        put("工程学", "engineering");
        put("气象学", "meteorology");
        put("司法科学", "judicialScience");
    }};

    /**
     * 如果skillTable中存在这个技能名的key，则返回规整后的value，如“司法科学”将返回“judicialScience”
     * 如果skillTable中不存在这个技能名的key，则返回传入值本身，如“司法科xue”将返回“司法科xue”
     *
     * @param skillName 传入技能名
     * @return 格式化后的技能名
     */
    static String makeSkillName(String skillName) {
        return skillTable.getOrDefault(skillName, skillName);
    }
}
