package dice.sinanya.tools.getinfo;

import dice.sinanya.db.welcome.InsertProperties;
import dice.sinanya.db.welcome.SelectWelcome;
import dice.sinanya.entity.EntityWelcome;

/**
 * @author SitaNya
 * 日期: 2019-08-21
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Welcome {
    static InsertProperties insertProperties=new InsertProperties();
    static SelectWelcome selectWelcome=new SelectWelcome();

    public static void flushWelcome(){
        selectWelcome.flushProperties();
    }

    public static void insertWelcome(long groupId, EntityWelcome entityWelcome){
        insertProperties.insertProperties(groupId, entityWelcome);
    }
}
