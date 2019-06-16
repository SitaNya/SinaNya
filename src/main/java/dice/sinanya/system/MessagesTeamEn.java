package dice.sinanya.system;

import dice.sinanya.entity.EntityQqAndGroup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 小队技能成功情况的记录，用于记录EN
 */
public interface MessagesTeamEn {
    HashMap<EntityQqAndGroup, ArrayList<String>> teamEn = new HashMap<>();
}
