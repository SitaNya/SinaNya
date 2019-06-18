package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotSetKpGroupException;

import static dice.sinanya.tools.getinfo.Kp.getKpGroup;
import static dice.sinanya.tools.getinfo.Kp.setKpGroup;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: kp主群
 */
public class Kp {
    private EntityTypeMessages entityTypeMessages;

    public Kp(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 设定kp主群，由于基本不造成影响，所以不做删除了
     */
    public void set() {
        setKpGroup(entityTypeMessages, entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已设置此群为您的带团群");
    }

    /**
     * 获取此kp当前设定的主群群号
     *
     * @return 获取kp主群群号
     * @throws NotSetKpGroupException 没找到则报错并原渠道回复报错语，具体语句配置文件中可以修改
     */
    public String get() throws NotSetKpGroupException {
        return getKpGroup(entityTypeMessages);
    }
}
