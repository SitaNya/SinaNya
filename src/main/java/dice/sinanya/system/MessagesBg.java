package dice.sinanya.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 人物背景的静态信息
 */
public class MessagesBg {
    public static final List<String> INFO;
    public static final List<String> PERSUASION;
    public static final List<String> IMPORTANT_PERSONS;
    public static final List<String> IMPORTANT_PERSONS_INFO;
    public static final List<String> IMPORTANT_MAP;
    public static final List<String> PRECIOUS;
    public static final List<String> SPECIALITY;

    static {
        ArrayList<String> persuasionTmp = new ArrayList<>();
        persuasionTmp.add("１：你信仰并祈并一位大能。（例如毗沙门天、耶稣基督、海尔·塞拉西一世）");
        persuasionTmp.add("２：人类无需上帝。（例如坚定的无神论者，人文主义者，世俗主义者）");
        persuasionTmp.add("３：科学万能！科学万岁！你将选择其中之一。（例如进化论，低温学，太空探索）");
        persuasionTmp.add("４：命中注定。（例如因果报应，种姓系统，超自然存在）");
        persuasionTmp.add("５：社团或秘密结社的一员。（例如共济会，女协，匿名者）");
        persuasionTmp.add("６：社会坏掉了，而你将成为正义的伙伴。应斩除之物是？（例如毒品，暴力，种族歧视）");
        persuasionTmp.add("７：神秘依然在。（例如占星术，招魂术，塔罗）");
        persuasionTmp.add("８：诸君，我喜欢政治。（例如保守党，共产党，自由党）");
        persuasionTmp.add("９：“金钱就是力量，我的朋友，我将竭尽全力获取我能看到的一切。”（例如贪婪心，进取心，冷酷心）");
        persuasionTmp.add("１０：竞选者/激进主义者。（例如女权运动人，平等主义家，工会权柄）");
        PERSUASION = new ArrayList<>(Collections.unmodifiableCollection(persuasionTmp));
    }

    static {
        ArrayList<String> infoTmp = new ArrayList<>();
        infoTmp.add("结实的");
        infoTmp.add("英俊的");
        infoTmp.add("笨拙的");
        infoTmp.add("机灵的");
        infoTmp.add("迷人的");
        infoTmp.add("娃娃脸");
        infoTmp.add("聪明的");
        infoTmp.add("邋遢的");
        infoTmp.add("死人脸");
        infoTmp.add("肮脏的");
        infoTmp.add("耀眼的");
        infoTmp.add("书呆子");
        infoTmp.add("年轻的");
        infoTmp.add("疲倦脸");
        infoTmp.add("肥胖的");
        infoTmp.add("啤酒肚");
        infoTmp.add("长头发");
        infoTmp.add("苗条的");
        infoTmp.add("优雅的");
        infoTmp.add("稀烂的");
        infoTmp.add("矮壮的");
        infoTmp.add("苍白的");
        infoTmp.add("阴沉的");
        infoTmp.add("平庸的");
        infoTmp.add("乐观的");
        infoTmp.add("棕褐色");
        infoTmp.add("皱纹人");
        infoTmp.add("古板的");
        infoTmp.add("狐臭的");
        infoTmp.add("狡猾的");
        infoTmp.add("健壮的");
        infoTmp.add("娇俏的");
        infoTmp.add("筋肉人");
        infoTmp.add("魁梧的");
        infoTmp.add("迟钝的");
        infoTmp.add("虚弱的");
        INFO = new ArrayList<>(Collections.unmodifiableCollection(infoTmp));
    }

    static {
        ArrayList<String> importantPersonsTmp = new ArrayList<>();
        importantPersonsTmp.add("１：父辈。（例如母亲，父亲，继母）");
        importantPersonsTmp.add("２：祖父辈。（例如外祖母，祖父）");
        importantPersonsTmp.add("３：兄弟。（例如妹妹，半血亲妹妹，无血缘妹妹）");
        importantPersonsTmp.add("４：孩子。（儿子或女儿）");
        importantPersonsTmp.add("５：另一半。（例如配偶，未婚夫，爱人）");
        importantPersonsTmp.add("６那位指引你人生技能的人。指明该技能和该人。（例如学校教师，师傅，父亲）");
        importantPersonsTmp.add("７：青梅竹马。（例如同学，邻居，幼驯染）");
        importantPersonsTmp.add("８：名人。偶像或者英雄。当然也许你从未见过他。（例如电影明星，政治家，音乐家。）");
        importantPersonsTmp.add("９：游戏中的另一位调查员伙伴。随机或自选。");
        importantPersonsTmp.add("１０：游戏中另一外ＮＰＣ。详情咨询你的守秘人");
        IMPORTANT_PERSONS = new ArrayList<>(Collections.unmodifiableCollection(importantPersonsTmp));
    }

    static {
        ArrayList<String> importantPersonsInfoTmp = new ArrayList<>();
        importantPersonsInfoTmp.add("１：你欠了他们人情。他们帮助了你什么？（例如，经济上，困难时期的庇护，给你第一份工作）");
        importantPersonsInfoTmp.add("２：他们教会了你一些东西。（例如，技能，如何去爱，如何成为男子汉）");
        importantPersonsInfoTmp.add("３：他们给了你生命的意义。（例如，你渴望成为他们那样的人，你苦苦追寻着他们，你想让他们高兴）");
        importantPersonsInfoTmp.add("４：你曾害了他们，而现在寻求救赎。例如，偷窃了他们的钱财，向警方报告了他们的行踪，在他们绝望时拒绝救助）");
        importantPersonsInfoTmp.add("５：同甘共苦。（例如，你们共同经历过困难时期，你们携手成长，共同度过战争）");
        importantPersonsInfoTmp.add("６：你想向他们证明自己。（例如，自己找到工作，自己搞到老婆，自己考到学历）");
        importantPersonsInfoTmp.add("７：你崇拜他们。（例如，崇拜他们的名头，他们的魅力，他们的工作）");
        importantPersonsInfoTmp.add("８：后悔的感觉。（例如，你本应死在他们面前，你背弃了你的誓言，你在可以助人之时驻足不前）");
        importantPersonsInfoTmp.add("９：你试图证明你比他们更出色。他们的缺点是？（例如，懒惰，酗酒，冷漠）");
        importantPersonsInfoTmp.add("１０：他们扰乱了你的人生，而你寻求复仇。发生了什么？（例如，射杀爱人之日，国破家亡之时，明镜两分之际）");
        IMPORTANT_PERSONS_INFO = new ArrayList<>(Collections.unmodifiableCollection(importantPersonsInfoTmp));
    }

    static {
        ArrayList<String> importantMapTmp = new ArrayList<>();
        importantMapTmp.add("１：你最爱的学府。（例如，中学，大学）");
        importantMapTmp.add("２：你的故乡。（例如，乡下老家，小镇村，大都市）");
        importantMapTmp.add("３：相识初恋之处。（例如，音乐会，度假村，核弹避难所）");
        importantMapTmp.add("４：静思之地。（例如，图书馆，你的乡土别墅，钓鱼中）");
        importantMapTmp.add("５：社交之地。（例如，绅士俱乐部，地方酒吧，叔叔的家）");
        importantMapTmp.add("６：联系你思想/信念的场所。（例如，小教堂，麦加，巨石阵）");
        importantMapTmp.add("７：重要之人的坟墓。（例如，另一半，孩子，爱人）");
        importantMapTmp.add("８：家族所在。（例如，乡下小屋，租屋，幼年的孤儿院）");
        importantMapTmp.add("９：生命中最高兴时的所在。（例如，初吻时坐着的公园长椅，你的大学）");
        importantMapTmp.add("１０：工作地点。（例如，办公室，图书馆，银行）");
        IMPORTANT_MAP = new ArrayList<>(Collections.unmodifiableCollection(importantMapTmp));
    }

    static {
        ArrayList<String> preciousTmp = new ArrayList<>();
        preciousTmp.add("１：与你得意技相关之物。（例如华服，假ＩＤ卡，青铜指虎）");
        preciousTmp.add("２：职业必需品。（例如医疗包，汽车，撬锁器）");
        preciousTmp.add("３：童年的遗留物。（例如漫画书，随身小刀，幸运币）");
        preciousTmp.add("４：逝者遗物。（例如烛堡，钱包里的遗照，信）");
        preciousTmp.add("５：重要之人给予之物。（例如戒指，日志，地图）");
        preciousTmp.add("６：收藏品。（例如撤票，标本，记录）");
        preciousTmp.add("７：你发掘而不知真相的东西。答案追寻中。（例如，橱柜里找到的未知语言信件，一根奇怪的从父亲处继承来的来源不明的风琴，花园里挖出来的奇妙的银球）");
        preciousTmp.add("８：体育用品。（例如，球棒，签名棒球，鱼竿）");
        preciousTmp.add("９：武器。（例如，半自动左轮，老旧的猎用来福，靴刃）");
        preciousTmp.add("１０：宠物。（例如狗，猫，乌龟）");
        PRECIOUS = new ArrayList<>(Collections.unmodifiableCollection(preciousTmp));
    }

    static {
        ArrayList<String> specialityTmp = new ArrayList<>();
        specialityTmp.add("１：慷慨大方。（例如，小费大手，及时雨，慈善家）");
        specialityTmp.add("２：善待动物。（例如，爱猫人士，农场出生，与马同舞）");
        specialityTmp.add("３：梦想家。（例如，惯常异想天开，预言家，创造者）");
        specialityTmp.add("４：享乐主义者。（例如，派对大师，酒吧醉汉，“放纵到死”）");
        specialityTmp.add("５：赌徒，冒险家。（例如，扑克脸，任何事都来一遍，活在生死边缘）");
        specialityTmp.add("６：好厨子，好吃货。（例如，烤得一手好蛋糕，无米之炊都能做好，优雅的食神）");
        specialityTmp.add("７：女人缘/万人迷。（例如，长袖善舞，甜言蜜语，电眼乱放）");
        specialityTmp.add("８：忠心在我。（例如，背负自己的朋友，从未破誓，为信念而死）");
        specialityTmp.add("９：好名头。（例如，村里最好的饭后聊天人士，虔信圣徒，不惧任何危险）");
        specialityTmp.add("１０：雄心壮志。（例如，梦想远大，目标是成为ＢＯＳＳ，渴求一切）");
        SPECIALITY = new ArrayList<>(Collections.unmodifiableCollection(specialityTmp));
    }

    private MessagesBg() {
        throw new IllegalStateException("Utility class");
    }
}
