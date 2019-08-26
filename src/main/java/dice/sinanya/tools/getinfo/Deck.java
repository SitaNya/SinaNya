package dice.sinanya.tools.getinfo;

import java.io.File;
import java.util.HashMap;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;

/**
 * @author SitaNya
 * 日期: 2019-08-25
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Deck {


    /**
     * 尝试从指定的配置文件中获取类型
     * @param type 指定的类型
     * @return 获取的语句，或报错骰主未添加此类型
     */
    public static String getDeck(String type) {
        File typeDir = new File(entitySystemProperties.getSystemDir() + File.separator + "deck" + File.separator + type);
        if (!typeDir.exists() || !typeDir.isFile()){
            //TODO
        }
        return "";
    }


    /**
     * 根据目录中的文件名获取已有的列表，每次删除或新增后会刷新
     * @return 返回列表的内容，包括列表中文名和分支命令名
     */
    public static HashMap<String,String> getMyDeck(){
        HashMap<String,String> myDeck=new HashMap<>();
        //TODO
        return myDeck;
    }


    /**
     * 获取全部的deck列表，包括名字、分支指令（已拥有的不显示），每次删除或新增后会刷新
     * @return
     */
    public static HashMap<String,String> getInternetDeck(){
        HashMap<String,String> internetDeck=new HashMap<>();
        HashMap<String,String > myDeck=getMyDeck();
        //TODO
        return internetDeck;
    }
}
