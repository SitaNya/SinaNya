package dice.sinanya.system;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 骰点回复的静态信息
 * <p>
 * 用列表保存后可以实现随机回复词
 */
public interface MessagesLevel {


    ArrayList<String> STR_CRITICAL_SUCCESS = new ArrayList<String>() {{
        add("啊~是大成功呢！\n如果您是玩家的话还请不要放松哦~\n不过如果您是KP的话……这些都是我可爱的玩家们，可否通融一下呢？");
        add("大成功呢~\n这一定是各位努力的成果，奈梅斯也为你们感到高兴哦");
        add("哇，是大成功！\n不过~时来运转是好事，但千万不要骄傲过头哦？奈梅斯还期待你们有个好结局呢~");
        add("啊，大成功吗？\n谢谢我？这其实都是各位努力的结果呀，奈梅斯没做什么的");
        add("大成功！太棒……\n……抱歉我失态了，但各位小小的庆祝一下应该是可以的吧？");
        add("是大成功呢\n如果想要有更好的结果，请安静下来给KP多一点思考的时间哦");
    }};

    ArrayList<String> STR_EXTREME_SUCCESS = new ArrayList<String>() {{
        add("极难成功！\n如果是战斗轮的话可别忘了穿刺伤害哦？\n是KP投出来的？那还是请您姑且忘记那回事吧~");
        add("极难成功吗？\n是个好兆头呢");
        add("极难成功！愿好运永伴您左右");
        add("是极难成功!\n您下次一定会大成功的！");
        add("极难成功！\n请好好利用它，幸运要配上努力才会更加闪耀哦");
    }};
    ArrayList<String> STR_HARD_SUCCESS = new ArrayList<String>() {{
        add("困难成功！\n这已经是很好的结果了，请开心起来吧");
        add("困难成功!\n请不要大意的继续前进吧，奈梅斯会尽力保护大家的");
        add("困难成功！\n您有好的点子吗？请开始吧，无论什么结果奈梅斯都会为您鼓掌的");
        add("困难成功了！\n这是好运的起始哦~");
        add("困难成功呢\n哎？就算您夸我我也……好吧我接下来会努力的！");
    }};
    ArrayList<String> STR_SUCCESS = new ArrayList<String>() {{
        add("成功了呢\n不要大意的继续前进吧");
        add("成功\n我会再接再厉的");
        add("成功了吗？\n不要太过兴奋啊，我们得静下心来才好继续一起往前走呢");
        add("成功了\n这是您努力的回报呢");
        add("成功了！\n您之前的辛苦没有白费，我会一直尽力帮您的");
        add("成功了！\n不要看奈梅斯啦，这是您的功劳哦");
        add("成功！\n看来我进入状态了");
        add("成功！\n请继续相信我吧！");
        add("成功！\n虽然奈梅斯并不强，但稍微依靠我一下也是可以的哦");
    }};
    ArrayList<String> STR_FAILURE = new ArrayList<String>() {{
        add("啊！失败……\n真是非常抱歉!");
        add("失败了吗……\n不要放弃，我会努力让下一次成功的！");
        add("失败了呀……\n对不起这是我的错，我会想想办法的");
        add("失败了？！\n怎么可能……");
        add("失败\n但至少不是大失败，松一口气想办法补救吧");
        add("失败了……\n也许值得孤注一掷？");
        add("失败\n但请不要灰心呢~（摸摸头）");
        add("失败了\n但这是我们所有人的游戏，大家一起想办法挽回局面吧！");
    }};
    ArrayList<String> STR_FUMBLE = new ArrayList<String>() {{
        add("大失败……\n哦天……我今天是怎么了");
        add("大失败？！\nKP你一定是看错了，请让我重骰一次！");
        add("大失败了吗……\n但这可打不倒我们！");
        add("大失败？！\nKP大大，这些都是我可爱的玩家们，能否想办法救他们一下？（鞠躬）");
        add("大失败……\n我不喜欢这样……");
        add("大失败……\n我保证，这是最后一次了！请相信我！");
        add("大失败……\n……这都是奈梅斯的错，非常对不起！");
    }};

    HashMap<String, ArrayList<String>> LEVEL_MAP = new HashMap<String, ArrayList<String>>(6) {{
        put("STR_CRITICAL_SUCCESS", STR_CRITICAL_SUCCESS);
        put("STR_EXTREME_SUCCESS", STR_EXTREME_SUCCESS);
        put("STR_HARD_SUCCESS", STR_HARD_SUCCESS);
        put("STR_SUCCESS", STR_SUCCESS);
        put("STR_FAILURE", STR_FAILURE);
        put("STR_FUMBLE", STR_FUMBLE);
    }};
}
