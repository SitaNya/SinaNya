package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.system.TiAndLi.*;
import static dice.sinanya.tools.getinfo.RandomInt.random;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 临时疯狂与总结疯狂
 */
public class TiAndLi {

    private EntityTypeMessages entityTypeMessages;

    public TiAndLi(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 临时疯狂
     */
    public void ti() {
        StringBuilder stringBuilder = new StringBuilder();
        int indexTi = random(0, ti.size() - 1);
        String strTi = ti.get(indexTi);
        stringBuilder
                .append(getRoleChooseByFromQQ(entityTypeMessages))
                .append("的疯狂发作-临时症状:\n")
                .append("1D10=")
                .append(indexTi + 1)
                .append("\n");
        symptomFormatAndSend(stringBuilder, strTi, indexTi);
        sender(entityTypeMessages, stringBuilder.toString());
    }

    /**
     * 总结疯狂
     */
    public void li() {
        StringBuilder stringBuilder = new StringBuilder();
        int indexLi = random(0, li.size() - 1);
        String strLi = li.get(indexLi);
        stringBuilder
                .append(getRoleChooseByFromQQ(entityTypeMessages))
                .append("的疯狂发作-总结症状:\n")
                .append("1D10=")
                .append(indexLi + 1)
                .append("\n");

        symptomFormatAndSend(stringBuilder, strLi, indexLi);
        sender(entityTypeMessages, stringBuilder.toString());
    }


    /**
     * 将疯狂症状格式化后返回
     *
     * @param stringBuilder 传入的StringBuilder对象，由于java特性，这个对象在append后无需return
     * @param strSymptom 疯狂症状字符串，因为临时和总结的字符串不同，这里当做参数传入
     * @param index 具体疯狂的下标，9为狂躁症，8为恐惧症需要做额外信息补充
     */
    private void symptomFormatAndSend(StringBuilder stringBuilder, String strSymptom, int index) {
        switch (index) {
            case 9:
                int indexPanic = random(0, strPanic.size() - 1);
                strSymptom = String.format(strSymptom, "1D10=" + random(1, 10), "1D" + strPanic.size() + "=" + (indexPanic + 1), strPanic.get(indexPanic));
                break;
            case 8:
                int indexFear = random(0, strFear.size() - 1);
                strSymptom = String.format(strSymptom, "1D10=" + random(1, 10), "1D" + strFear.size() + "=" + (indexFear + 1), strFear.get(indexFear));
                break;
            default:
                strSymptom = String.format(strSymptom, "1D10=" + random(1, 10));
                break;
        }
        stringBuilder.append(strSymptom);
        stringBuilder.append(messagesSystem.get("symptom"));
    }
}
