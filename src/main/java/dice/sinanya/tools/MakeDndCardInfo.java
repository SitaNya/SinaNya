package dice.sinanya.tools;

import java.util.ArrayList;
import java.util.Collections;

import static dice.sinanya.tools.ManyRolls.manyRollsProcess;
import static dice.sinanya.tools.RandomInt.random;
import static java.lang.Math.ceil;

public class MakeDndCardInfo {

    public static String makeDndCardInfo() {
        StringBuffer stringBuffer = new StringBuffer();
        int str = get4d6k3multiply();
        int con = get4d6k3multiply();
        int dex = get4d6k3multiply();
        int intValue = get4d6k3multiply();
        int check = get4d6k3multiply();
        int app = get4d6k3multiply();
        int all = str + con + dex + intValue + check + app;
        stringBuffer
                .append("力量:")
                .append(str)
                .append("\t")

                .append("体质:")
                .append(con)
                .append("\t")

                .append("敏捷:")
                .append(dex)
                .append("\t")

                .append("智力:")
                .append(intValue)
                .append("\t")

                .append("感知:")
                .append(check)
                .append("\t")

                .append("魅力:")
                .append(app)
                .append("\t")

                .append("共计:")
                .append(all);
        return stringBuffer.toString();
    }

    private static int get4d6k3multiply() {
        ArrayList<Integer> rollsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            rollsList.add(random(1, 6));
        }
        Collections.sort(rollsList);
        rollsList.remove(0);
        int result = 0;
        for (int tmp : rollsList) {
            result += tmp;
        }
        return result;
    }
}
