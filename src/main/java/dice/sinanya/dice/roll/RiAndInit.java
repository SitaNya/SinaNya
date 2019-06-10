package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesInit.initList;
import static dice.sinanya.system.MessagesTag.tagRi;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.Sender.sender;

public class RiAndInit {

    private static Pattern num = Pattern.compile("(\\d+)");

    private EntityTypeMessages entityTypeMessages;

    public RiAndInit(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void ri() {
        String tag = tagRi;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String msgBefore = msg;
        int result;
        boolean add = false;
        int random = random(1, 20);
        int addValue = 0;
        StringBuilder name = new StringBuilder();
        if (msg.contains(" ") && msg.split(" ").length > 1) {
            for (int i = 1; i < msg.split(" ").length; i++) {
                name.append(msg.split(" ")[i]).append(" ");
            }
            msg = msg.split(" ")[0];
        }

        Matcher mNum = num.matcher(msg);
        while (mNum.find()) {
            addValue = Integer.parseInt(mNum.group(1));
        }
        msg = msg.replaceAll(num.toString(), "");
        if (msg.contains("-")) {
            result = random - addValue;
            msg = msg.replace("-", "").trim();
        } else if (msg.contains("+") || isNumeric(msg)) {
            result = random + addValue;
            msg = msg.replace("+", "").trim();
            add = true;
        } else if (addValue != 0) {
            result = random + addValue;
            msg = msg.replace("+", "").trim();
            add = true;
        } else {
            result = random;
        }

        String nick;

        if (!name.toString().equals("")) {
            nick = name.toString();
        } else {
            if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
                nick = getRoleChooseByFromQQ(entityTypeMessages);
            } else {
                nick = getNickName(entityTypeMessages);
            }
        }

        if (msg.equals("")) {
            sender(entityTypeMessages, nick + "掷出了: D20=" + result);
        } else {
            nick = msg;
            if (add) {
                sender(entityTypeMessages, nick + "掷出了: D20=" + random + "+" + addValue + "=" + result);
                msgBefore = random + "+" + addValue + "=";
            } else {
                sender(entityTypeMessages, nick + "掷出了: D20=" + random + "-" + addValue + "=" + result);
                msgBefore = random + "-" + addValue + "=";
            }
        }
        if (initList.containsKey(entityTypeMessages.getFromGroup())) {
            HashMap<String, String> riList = initList.get(entityTypeMessages.getFromGroup());
            riList.put(nick, ": D20=" + msgBefore + result);
            initList.put(entityTypeMessages.getFromGroup(), riList);
        } else {
            HashMap<String, String> riList = new HashMap<>();
            riList.put(nick, ": D20=" + msgBefore + result);
            initList.put(entityTypeMessages.getFromGroup(), riList);
        }
    }

    public void init() {
        StringBuilder stringBuffer = new StringBuilder();
        if (!initList.containsKey(entityTypeMessages.getFromGroup())) {
            sender(entityTypeMessages, "先攻列表为空");
            return;
        }
        stringBuffer.append("先攻列表为:\n");
        int i = 1;
        for (Map.Entry<String, HashMap<String, String>> mapEntry : initList.entrySet()) {
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

    public void clr() {
        initList.remove(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已清空本群的先攻列表");
    }

    public static HashMap<String, String> sortHashMap(HashMap<String, String> map) {
        //從HashMap中恢復entry集合，得到全部的鍵值對集合
        Set<Map.Entry<String, String>> entey = map.entrySet();
        //將Set集合轉為List集合，為了實用工具類的排序方法
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(entey);
        //使用Collections工具類對list進行排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                //按照age倒敘排列
                String[] o1Value = o1.getValue().split("=");
                String[] o2Value = o2.getValue().split("=");
                return Integer.parseInt(o2Value[o2Value.length - 1]) - Integer.parseInt(o1Value[o1Value.length - 1]);
            }
        });
        //創建一個HashMap的子類LinkedHashMap集合
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        //將list中的數據存入LinkedHashMap中
        for (Map.Entry<String, String> entry : list) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }
}
