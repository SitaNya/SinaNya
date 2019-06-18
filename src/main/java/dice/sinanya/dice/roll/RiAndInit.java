package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.makedata.Calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesInit.INIT_LIST;
import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.system.MessagesTag.TAG_RI;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.RandomInt.random;
import static dice.sinanya.tools.makedata.Sender.sender;
import static java.lang.Math.ceil;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: DND先攻骰掷及列表
 */
public class RiAndInit {

    private static Pattern numAndName = Pattern.compile("([+*/-]?\\d+)([^\\d]+)");
    private static Pattern plus = Pattern.compile("([+*/\\-]\\d)");

    private Pattern nickNameRegex = Pattern.compile("([\\u4e00-\\u9fa5]+)");

    private EntityTypeMessages entityTypeMessages;

    public RiAndInit(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 先攻骰掷，这里支持加值和减值计算，并且如果信息中包含汉字，则和对方昵称放在一起（通常kp使用）
     * 返回结果后还会把结果插入先攻列表记录，用于打印先攻列表
     */
    public void ri() {
        String tag = TAG_RI;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String msgBefore = msg;
        int result = 0;
        boolean add = false;
        int random = random(1, 20);
        String nick;
        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            nick = getRoleChooseByFromQQ(entityTypeMessages);
        } else {
            nick = getNickName(entityTypeMessages);
        }

        Matcher mNumAndName = numAndName.matcher(msg);
        while (mNumAndName.find()) {
            msg = mNumAndName.group(1);
            nick = mNumAndName.group(2) + "(" + getNickName(entityTypeMessages) + ")";
        }

        Matcher mPlus = plus.matcher(msg);
        while (mPlus.find()) {
            result = (int) ceil(Calculator.conversion(random + msg));
            if (msg.contains("+")) {
                add = true;
            }
        }

        if (isNumeric(msg)) {
            result = random + Integer.parseInt(msg);
            add = true;
        }

        Matcher mNickNameRegex = nickNameRegex.matcher(msg);
        if (result == 0) {
            result = random;
            if (mNickNameRegex.find()) {
                nick = msg;
                msg = NONE;
            } else {
                nick = getNickName(entityTypeMessages);
            }
        }

        if (msg.equals(NONE)) {
            sender(entityTypeMessages, nick + "的先攻骰掷,掷出了: D20=" + result);
        } else {
            if (add) {
                sender(entityTypeMessages, nick + "的先攻骰掷,掷出了: D20=" + random + "+" + msg.replace("+", "") + "=" + result);
                msgBefore = random + "+" + msg.replace("+", "").replace("-","") + "=";
            } else {
                sender(entityTypeMessages, nick + "的先攻骰掷,掷出了: D20=" + random + msg + "=" + result);
                msgBefore = random + "-" + msg.replace("+", "").replace("-","") + "=";
            }
        }
        Matcher mMsgBefore = nickNameRegex.matcher(msgBefore);
        if (INIT_LIST.containsKey(entityTypeMessages.getFromGroup())) {
            HashMap<String, String> riList = INIT_LIST.get(entityTypeMessages.getFromGroup());
            if (mMsgBefore.find()) {
                riList.put(nick, ": D20=" + result);
            } else {
                riList.put(nick, ": D20=" + msgBefore  + result);
            }
            INIT_LIST.put(entityTypeMessages.getFromGroup(), riList);
        } else {
            HashMap<String, String> riList = new HashMap<>(30);
            if (mMsgBefore.find()) {
                riList.put(nick, ": D20=" + result);
            } else {
                riList.put(nick, ": D20=" + msgBefore + result);
            }
            INIT_LIST.put(entityTypeMessages.getFromGroup(), riList);
        }
    }

    /**
     * 打印先攻列表，根据群号确定列表后，根据骰点最终结果排序后顺序打印
     */
    public void init() {
        StringBuilder stringBuffer = new StringBuilder();
        if (!INIT_LIST.containsKey(entityTypeMessages.getFromGroup())) {
            sender(entityTypeMessages, messagesSystem.get("dndInitIsEmtpy"));
            return;
        }
        stringBuffer.append("先攻列表为:\n");
        int i = 1;
        for (Map.Entry<String, HashMap<String, String>> mapEntry : INIT_LIST.entrySet()) {
            HashMap<String, String> sort = sortHashMap(mapEntry.getValue());
            for (Map.Entry<String, String> mapEntry2 : sort.entrySet()) {
                stringBuffer.append(i)
                        .append(".\t")
                        .append(mapEntry2.getKey())
                        .append(mapEntry2.getValue())
                        .append("\n");
                i++;
            }
        }
        sender(entityTypeMessages, stringBuffer.substring(0, stringBuffer.length() - 1));
    }

    /**
     * 清空先攻列表
     */
    public void clr() {
        INIT_LIST.remove(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, messagesSystem.get("clrDndInit"));
    }

    /**
     * 根据value从大到小排序先攻列表
     *
     * @param map 先攻列表，key为骰点人+骰点过程，value为骰点结果
     * @return 排序后的map
     */
    private static HashMap<String, String> sortHashMap(HashMap<String, String> map) {
        //從HashMap中恢復entry集合，得到全部的鍵值對集合
        Set<Map.Entry<String, String>> entey = map.entrySet();
        //將Set集合轉為List集合，為了實用工具類的排序方法
        List<Map.Entry<String, String>> list = new ArrayList<>(entey);
        //使用Collections工具類對list進行排序
        list.sort((o1, o2) -> {
            //按照age倒敘排列
            String[] o1Value = o1.getValue().split("=");
            String[] o2Value = o2.getValue().split("=");
            return Integer.parseInt(o2Value[o2Value.length - 1]) - Integer.parseInt(o1Value[o1Value.length - 1]);
        });
        //創建一個HashMap的子類LinkedHashMap集合
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        //將list中的數據存入LinkedHashMap中
        for (Map.Entry<String, String> entry : list) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }
}
