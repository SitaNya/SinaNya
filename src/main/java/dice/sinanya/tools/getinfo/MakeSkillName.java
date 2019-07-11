package dice.sinanya.tools.getinfo;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
    static Map<String, String> skillTable = new TreeMap<>(
            Comparator.reverseOrder());

    static {
        skillTable.put("力量", "str");
        skillTable.put("敏捷", "dex");
        skillTable.put("意志", "pow");
        skillTable.put("pow", "pow");
        skillTable.put("体质", "con");
        skillTable.put("外貌", "app");
        skillTable.put("教育", "edu");
        skillTable.put("体型", "siz");
        String tagInt = "intValue";
        skillTable.put("智力", tagInt);
        skillTable.put("智力/灵感", tagInt);
        skillTable.put("int", tagInt);
        skillTable.put("idea", tagInt);
        skillTable.put("灵感", tagInt);
        String tagSan = "san";
        skillTable.put("san", tagSan);
        skillTable.put("san值", tagSan);
        skillTable.put("理智", tagSan);
        skillTable.put("理智值", tagSan);
        String tagLuck = "luck";
        skillTable.put("幸运", tagLuck);
        skillTable.put("运气", tagLuck);
        String tagMp = "mp";
        skillTable.put("mp", tagMp);
        skillTable.put("魔法", tagMp);
        String tagHp = "hp";
        skillTable.put("hp", tagHp);
        skillTable.put("体力", tagHp);
        skillTable.put("会计", "accounting");
        skillTable.put("人类学", "anthropology");
        skillTable.put("估价", "evaluation");
        skillTable.put("考古学", "archaeology");
        skillTable.put("取悦", "enchantment");
        skillTable.put("魅惑", "enchantment");
        skillTable.put("攀爬", "toClimb");
        String tagComputer = "computerUsage";
        skillTable.put("计算机", tagComputer);
        skillTable.put("计算机使用", tagComputer);
        skillTable.put("电脑", tagComputer);
        skillTable.put("电脑使用", tagComputer);
        String tagCredit = "creditRating";
        skillTable.put("信用", tagCredit);
        skillTable.put("信誉", tagCredit);
        skillTable.put("信用评级", tagCredit);
        skillTable.put("信誉度", tagCredit);
        skillTable.put("信用度", tagCredit);
        String tagCthulhuMythos = "cthulhuMythos";
        skillTable.put("克苏鲁", tagCthulhuMythos);
        skillTable.put("克苏鲁神话", tagCthulhuMythos);
        skillTable.put("cm", tagCthulhuMythos);
        skillTable.put("乔装", "disguise");
        skillTable.put("闪避", "dodge");
        String tagDrive = "drive";
        skillTable.put("汽车", tagDrive);
        skillTable.put("驾驶", tagDrive);
        skillTable.put("汽车驾驶", tagDrive);
        skillTable.put("驾驶汽车", tagDrive);
        skillTable.put("驾驶(汽车)", tagDrive);
        skillTable.put("驾驶:汽车", tagDrive);
        String tagRifle = "rifle";
        skillTable.put("步枪/霰弹枪", tagRifle);
        skillTable.put("步枪", tagRifle);
        skillTable.put("霰弹枪", tagRifle);
        skillTable.put("散弹枪", tagRifle);
        skillTable.put("步霰", tagRifle);
        skillTable.put("步/霰", tagRifle);
        skillTable.put("步散", tagRifle);
        skillTable.put("步/散", tagRifle);
        String tagShipping = "shipping";
        skillTable.put("船驾驶", tagShipping);
        skillTable.put("船", tagShipping);
        skillTable.put("驾驶船", tagShipping);
        skillTable.put("驾驶(船)", tagShipping);
        skillTable.put("驾驶:船", tagShipping);
        String tagAircraftOperation = "aircraftOperation";
        skillTable.put("飞行器驾驶", tagAircraftOperation);
        skillTable.put("飞行器", tagAircraftOperation);
        skillTable.put("驾驶飞行器", tagAircraftOperation);
        skillTable.put("驾驶:飞行器", tagAircraftOperation);
        skillTable.put("驾驶(飞行器)", tagAircraftOperation);
        String tagMachineGun = "machineGun";
        skillTable.put("机枪", tagMachineGun);
        skillTable.put("机关枪", tagMachineGun);
        skillTable.put("电气维修", "electricalMaintenance");
        skillTable.put("电子学", "electronics");
        skillTable.put("快速交谈", "talkingSkill");
        skillTable.put("话术", "talkingSkill");
        skillTable.put("斗殴", "aFistFight");
        skillTable.put("绞具", "wrangle");
        skillTable.put("手枪", "pistol");
        skillTable.put("急救", "firstAid");
        skillTable.put("历史", "history");
        skillTable.put("恐吓", "intimidate");
        skillTable.put("跳跃", "jump");
        skillTable.put("母语", "motherTongue");
        skillTable.put("法律", "law");
        skillTable.put("图书馆", "libraryUse");
        skillTable.put("图书馆使用", "libraryUse");
        skillTable.put("聆听", "listen");
        String tagUnLock = "unlock";
        skillTable.put("开锁", tagUnLock);
        skillTable.put("撬锁", tagUnLock);
        skillTable.put("锁匠", tagUnLock);
        skillTable.put("机械维修", "mechanicalMaintenance");
        skillTable.put("医学", "medicalScience");
        skillTable.put("博物学", "naturalHistory");
        skillTable.put("自然学", "naturalScience");
        skillTable.put("领航", "pilotage");
        skillTable.put("导航", "pilotage");
        skillTable.put("神秘学", "occultScience");
        String tagOperatingHeavyMachinery = "operatingHeavyMachinery";
        skillTable.put("重型操作", tagOperatingHeavyMachinery);
        skillTable.put("重型机械", tagOperatingHeavyMachinery);
        skillTable.put("操作重型机械", tagOperatingHeavyMachinery);
        skillTable.put("重型", tagOperatingHeavyMachinery);
        skillTable.put("说服", "persuade");
        skillTable.put("精分", "psychoanalysis");
        skillTable.put("精神分析", "psychoanalysis");
        skillTable.put("心理", "psychology");
        skillTable.put("心理学", "psychology");
        skillTable.put("骑术", "horsemanship");
        skillTable.put("妙手", "aWonderfulHand");
        skillTable.put("侦察", "investigationOfCrimes");
        skillTable.put("侦查", "investigationOfCrimes");
        skillTable.put("潜行", "stealth");
        skillTable.put("生存", "existence");
        skillTable.put("游泳", "swimming");
        skillTable.put("投掷", "throwValue");
        skillTable.put("追踪", "trackValue");
        skillTable.put("跟踪", "trackValue");
        skillTable.put("驯兽", "domesticatedAnimal");
        skillTable.put("潜水", "diving");
        skillTable.put("爆破", "blast");
        skillTable.put("读唇", "lipReading");
        skillTable.put("催眠", "hypnosis");
        skillTable.put("炮术", "artillery");

        skillTable.put("唱歌", "sing");
        skillTable.put("歌唱", "sing");
        skillTable.put("作画", "paint");
        skillTable.put("做画", "paint");
        skillTable.put("耕做", "tillage");
        skillTable.put("耕作", "tillage");
        skillTable.put("摄影", "photography");
        skillTable.put("表演", "perform");
        skillTable.put("伪造", "forge");
        skillTable.put("文学", "literature");
        skillTable.put("书法", "calligraphy");
        skillTable.put("乐理", "music");
        skillTable.put("厨艺", "cooking");
        skillTable.put("裁缝", "tailor");
        skillTable.put("理发", "haircut");
        skillTable.put("建筑", "architecture");
        skillTable.put("舞蹈", "dance");
        skillTable.put("酿酒", "makeWine");
        skillTable.put("捕鱼", "fishing");
        skillTable.put("制陶", "potteryMaking");
        skillTable.put("雕塑", "sculpture");
        skillTable.put("杂技", "acrobatics");
        skillTable.put("风水", "fengshui");
        skillTable.put("技术制图", "technicalDrawing");
        skillTable.put("打字", "typing");
        skillTable.put("速记", "shorthand");
        skillTable.put("鞭子", "whip");
        skillTable.put("电锯", "electricSaw");
        skillTable.put("斧", "axe");
        skillTable.put("剑", "sword");
        skillTable.put("链枷", "flail");
        skillTable.put("矛", "spear");
        skillTable.put("冲锋枪", "submachineGun");
        skillTable.put("弓术", "archery");
        skillTable.put("火焰喷射器", "flameEjector");
        skillTable.put("重武器", "heavyWeapons");
        skillTable.put("骑乘", "ride");
        skillTable.put("地质学", "geology");
        skillTable.put("化学", "chemistry");
        skillTable.put("生物学", "biology");
        skillTable.put("数学", "mathematics");
        skillTable.put("天文学", "astronomy");
        skillTable.put("物理学", "physics");
        skillTable.put("药学", "pharmacy");
        skillTable.put("植物学", "botany");
        skillTable.put("动物学", "zoology");
        skillTable.put("密码学", "cryptography");
        skillTable.put("工程学", "engineering");
        skillTable.put("气象学", "meteorology");
        skillTable.put("司法科学", "judicialScience");
    }

    private MakeSkillName() {
        throw new IllegalStateException("Utility class");
    }


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
