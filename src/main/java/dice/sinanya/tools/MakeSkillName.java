package dice.sinanya.tools;

import java.util.HashMap;

public class MakeSkillName {
    static HashMap<String,String> skillTable=new HashMap<String,String>(100){{
        put("力量","str");
        put("敏捷","dex");
        put("意志","pow");
        put("体质","con");
        put("外貌","app");
        put("教育","edu");
        put("体型","siz");
        put("智力","intValue");
        put("灵感","intValue");
        put("san","san");
        put("san值","san");
        put("理智","san");
        put("理智值","san");
        put("幸运","luck");
        put("运气","luck");
        put("mp","mp");
        put("魔法","mp");
        put("hp","hp");
        put("体力","hp");
        put("会计","accounting");
        put("人类学","anthropology");
        put("估价","evaluation");
        put("考古学","archaeology");
        put("魅惑","enchantment");
        put("攀爬","toClimb");
        put("计算机","computerUsage");
        put("计算机使用","computerUsage");
        put("电脑","computerUsage");
        put("信用","creditRating");
        put("信誉","creditRating");
        put("信用评级","creditRating");
        put("克苏鲁","cthulhuMythos");
        put("克苏鲁神话","cthulhuMythos");
        put("cm","cthulhuMythos");
        put("乔装","disguise");
        put("闪避","dodge");
        put("汽车","drive");
        put("驾驶","drive");
        put("汽车驾驶","drive");
        put("电气维修","electricalMaintenance");
        put("电子学","electronics");
        put("话术","talkingSkill");
        put("斗殴","aFistFight");
        put("绞具","wrangle");
        put("手枪","pistol");
        put("急救","firstAid");
        put("历史","history");
        put("恐吓","intimidate");
        put("跳跃","jump");
        put("母语","motherTongue");
        put("法律","law");
        put("图书馆","libraryUse");
        put("图书馆使用","libraryUse");
        put("聆听","listen");
        put("开锁","unlock");
        put("撬锁","unlock");
        put("锁匠","unlock");
        put("机械维修","mechanicalMaintenance");
        put("医学","medicalScience");
        put("博物学","naturalHistory");
        put("自然学","naturalScience");
        put("领航","pilotage");
        put("导航","pilotage");
        put("神秘学","occultScience");
        put("重型操作","operatingHeavyMachinery");
        put("重型机械","operatingHeavyMachinery");
        put("操作重型机械","operatingHeavyMachinery");
        put("重型","operatingHeavyMachinery");
        put("说服","persuade");
        put("精神分析","psychoanalysis");
        put("心理学","psychology");
        put("骑术","horsemanship");
        put("妙手","aWonderfulHand");
        put("侦查","investigationOfCrimes");
        put("潜行","stealth");
        put("生存","existence");
        put("游泳","swimming");
        put("投掷","throwValue");
        put("追踪","trackValue");
        put("驯兽","domesticatedAnimal");
        put("潜水","diving");
        put("爆破","blast");
        put("读唇","lipReading");
        put("催眠","hypnosis");
        put("炮术","artillery");
    }};
    public static String makeSkillName(String skillName){
        if (skillTable.containsKey(skillName)){
            return skillTable.get(skillName);
        }else {
            return skillName;
        }
    }
}
