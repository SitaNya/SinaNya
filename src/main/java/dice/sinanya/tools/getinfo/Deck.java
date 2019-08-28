package dice.sinanya.tools.getinfo;

import dice.sinanya.db.deck.SelectDeckList;
import dice.sinanya.entity.EntityDeckBack;
import dice.sinanya.entity.EntityDeckList;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.commons.lang.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.tools.makedata.RandomInt.random;

/**
 * @author SitaNya
 * 日期: 2019-08-25
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Deck {

    static Pattern functionString = Pattern.compile("\\{\\$(.*)\\}");
    static Pattern functionStringBack = Pattern.compile("\\{%(.*)\\}");
    static HashMap<EntityDeckBack, ArrayList<Integer>> deckBack = new HashMap<>();

    /**
     * 尝试从指定的配置文件中获取类型
     *
     * @param deckType 指定的类型
     * @return 获取的语句，或报错骰主未添加此类型
     */
    private static HashMap<String, ArrayList<String>> getDeckMap(String deckType) {
        File deckTypeFile = new File(entitySystemProperties.getSystemDir() +File.separator+ "deck" + File.separator+ deckType);
//        File deckTypeFile = new File(deckType);
        HashMap<String, ArrayList<String>> deckList = new HashMap<>();
        if (!deckTypeFile.exists() || !deckTypeFile.isFile()) {
            return deckList;
        }
        Yaml yaml = new Yaml();
        ArrayList<String> valueList;
        try {
            // 加载配置文件
            Map map = yaml.load(new FileInputStream(deckTypeFile));
            for (Object key : map.keySet()) {
                if (!key.equals("name") && !key.equals("command") && !key.equals("author") && !key.equals("version") && !key.equals("desc")) {
                    String value = map.get(key).toString();
                    valueList = new ArrayList<>(Arrays.asList(value.substring(1, value.length() - 1).split(",")));
                    deckList.put(String.valueOf(key), valueList);
                }
            }
        } catch (IOException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
        return deckList;
    }

    /**
     * 尝试从指定的配置文件中获取类型
     *
     * @param deckType 指定的类型
     * @return 获取的语句，或报错骰主未添加此类型
     */
    private static HashMap<String, ArrayList<String>> getDeckHelp() {
        File deckTypeDir = new File(entitySystemProperties.getSystemDir() + File.separator + "deck");
        HashMap<String, ArrayList<String>> deckList = new HashMap<>();
        if (deckTypeDir.exists() && deckTypeDir.isDirectory()) {
            Yaml yaml = new Yaml();
            for (File file : deckTypeDir.listFiles()) {
                ArrayList<String> keyList=new ArrayList<>();
                try {
                    // 加载配置文件
                    Map map = yaml.load(new FileInputStream(file));
                    for (Object key : map.keySet()) {
                        if (!key.equals("name") && !key.equals("command") && !key.equals("author") && !key.equals("version") && !key.equals("desc") && !key.equals("default")) {
                            keyList.add(String.valueOf(key));
                        }
                    }
                    if (map.containsKey("default")){
                        keyList.add(String.valueOf(map.get("command")));
                    }
                } catch (IOException e) {
                    CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
                }
                deckList.put(file.getName(),keyList);
            }
        }
        return deckList;
    }

    public static String getDeck(EntityTypeMessages entityTypeMessages, String deckType, String type) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        HashMap<String, ArrayList<String>> deck = getDeckMap(deckType);
        ArrayList<String> randomList = new ArrayList<>();
        randomList.add("未找到");
        if (deck.containsKey(type)) {
            randomList = deck.get(type);
        }
        int random = random(0, randomList.size() - 1);
        String result = randomList.get(random);
        Matcher functionStringFind = functionString.matcher(result);
        while (functionStringFind.find()) {
            String tmp = functionStringFind.group(1);
            result = result.replaceFirst(functionString.toString(), getDeck(entityTypeMessages, deckType, tmp));
        }
        Matcher functionStringBackFind = functionStringBack.matcher(result);
        while (functionStringBackFind.find()) {
            String tmp = functionStringBackFind.group(1);
            randomList = deck.get(tmp);
            EntityDeckBack entityDeckBack = new EntityDeckBack(qqId, deckType, tmp);
            if (deckBack.containsKey(entityDeckBack) && deckBack.get(entityDeckBack).size() != randomList.size()) {
                ArrayList<Integer> backList = deckBack.get(entityDeckBack);
                do {
                    random = random(0, randomList.size() - 1);
                } while (backList.contains(random));
                result = result.replaceFirst(functionStringBack.toString(), randomList.get(random));
                backList.add(random);
                deckBack.put(entityDeckBack, backList);
            } else if (!deckBack.containsKey(entityDeckBack)) {
                ArrayList<Integer> backList = new ArrayList<>();
                random = random(0, randomList.size() - 1);
                result = result.replaceFirst(functionStringBack.toString(), randomList.get(random));
                backList.add(random);
                deckBack.put(entityDeckBack, backList);
            } else {
//                sender(entityTypeMessages, "牌堆已抽干，重新生成");
                System.out.println("牌堆已抽干，重新生成");
                deckBack.remove(entityDeckBack);
                ArrayList<Integer> backList = new ArrayList<>();
                random = random(0, randomList.size() - 1);
                result = result.replaceFirst(functionStringBack.toString(), randomList.get(random));
                backList.add(random);
                deckBack.put(entityDeckBack, backList);
            }
        }
        return result;
    }

    public static ArrayList<EntityDeckList> getHasDeckList() {
        ArrayList<EntityDeckList> hasDeckList = new ArrayList();
        File deckTypeDir = new File(entitySystemProperties.getSystemDir() +File.separator+ "deck");
//        File deckTypeDir = new File("src/main/resources/");
        if (deckTypeDir.isDirectory()) {
            for (File deckType : deckTypeDir.listFiles()) {
                if (deckType.isFile()) {
                    EntityDeckList entityDeckList = new EntityDeckList();
                    String deckName = "未找到";
                    Yaml yaml = new Yaml();
                    try {
                        // 加载配置文件
                        Map map = yaml.load(new FileInputStream(deckType));
                        deckName = String.valueOf(map.get("name"));
                        entityDeckList.setName(deckName);
                        entityDeckList.setCommand(deckType.getName());
                        entityDeckList.setAuthor(String.valueOf(map.get("author")));
                        entityDeckList.setVersion((int) map.get("version"));
                        entityDeckList.setDesc(String.valueOf(map.get("desc")));
                        hasDeckList.add(entityDeckList);
                    } catch (IOException e) {
                        CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
                    }
                }
            }
        }
        return hasDeckList;
    }


    /**
     * 获取全部的deck列表，包括名字、分支指令（已拥有的不显示），每次删除或新增后会刷新
     *
     * @return
     */
    public static ArrayList<EntityDeckList> getInternetDeck() {
        ArrayList<EntityDeckList> result = new ArrayList<>();
        ArrayList<EntityDeckList> hasDeckList = getHasDeckList();
        ArrayList<EntityDeckList> internetDeckList = new SelectDeckList().selectDeckList();
        for (EntityDeckList entityDeckList : internetDeckList) {
            if (!hasDeckList.contains(entityDeckList)) {
                result.add(entityDeckList);
            }
        }
        return result;
    }
}
