package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.TiAndLi.*;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.Sender.sender;

/**
 * 临时疯狂与永久疯狂
 * .ti与.li
 *
 * @author SitaNya
 */
public class TiAndLi {

    private EntityTypeMessages entityTypeMessages;

    public TiAndLi(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void ti() {
        StringBuilder stringBuilder = new StringBuilder();
        int indexTi = random(0, ti.size() - 1);
        stringBuilder
                .append(getRoleChooseByFromQQ(entityTypeMessages))
                .append("的疯狂发作-临时症状:\n")
                .append("1D10=")
                .append(indexTi + 1)
                .append("\n");
        symptomFormatAndSend(stringBuilder, indexTi);
        sender(entityTypeMessages, stringBuilder.toString());
    }

    public void li() {
        StringBuilder stringBuilder = new StringBuilder();
        int indexLi = random(0, li.size() - 1);
        stringBuilder
                .append(getRoleChooseByFromQQ(entityTypeMessages))
                .append("的疯狂发作-总结症状:\n")
                .append("1D10=")
                .append(indexLi + 1)
                .append("\n");

        symptomFormatAndSend(stringBuilder, indexLi);
        sender(entityTypeMessages, stringBuilder.toString());
    }

    private void symptomFormatAndSend(StringBuilder stringBuilder, int index) {
        String strLi = li.get(index);
        switch (index) {
            case 9:
                int indexPanic = random(0, strPanic.size() - 1);
                strLi = String.format(strLi, "1D10=" + random(1, 10), "1D" + strPanic.size() + "=" + (indexPanic + 1), strPanic.get(indexPanic));
                break;
            case 8:
                int indexFear = random(0, strFear.size() - 1);
                strLi = String.format(strLi, "1D10=" + random(1, 10), "1D" + strFear.size() + "=" + (indexFear + 1), strFear.get(indexFear));
                break;
            default:
                strLi = String.format(strLi, "1D10=" + random(1, 10));
                break;
        }
        stringBuilder.append(strLi);

    }
}
