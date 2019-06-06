package dice.sinanya.tools;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.DBAndSize.dbGetter;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;
import static java.lang.Math.ceil;

public class MakeCoc6Card implements Callable<String> {

    public MakeCoc6Card() {

    }

    @Override
    public String call() {
        StringBuilder stringBuilder = new StringBuilder();
        int str = get3d6multiply();
        int con = get3d6multiply();
        int siz = get2d6plus6multiply();
        int dex = get3d6multiply();
        int app = get3d6multiply();
        int intValue = get2d6plus6multiply();
        int pow = get3d6multiply();
        int edu = get3d6multiply() + 3;

        int notLuck = str + con + siz + dex + app + intValue + pow + edu;

        stringBuilder
                .append("力量:")
                .append(str)
                .append(" ")

                .append("体质:")
                .append(con)
                .append(" ")

                .append("体型:")
                .append(siz)
                .append(" ")

                .append("敏捷:")
                .append(dex)
                .append(" ")

                .append("魅力:")
                .append(app)
                .append(" ")

                .append("智力:")
                .append(intValue)
                .append(" ")

                .append("意志:")
                .append(pow)
                .append(" ")

                .append("教育:")
                .append(edu)
                .append(" ")

                .append("血量:")
                .append((int) ceil((siz + con) / 2.0))
                .append(" ")

                .append("理智:")
                .append(pow * 5)
                .append(" ")

                .append("灵感:")
                .append(intValue * 5)
                .append(" ")

                .append("幸运:")
                .append(pow * 5)
                .append(" ")

                .append("知识:")
                .append(edu * 5)
                .append(" ")

                .append("DB:")
                .append(dbGetter(siz + str))

                .append("\n总值:")
                .append(notLuck)
                .append(" ")

                .append("占总点数的:")
                .append(String.valueOf((notLuck * 1.0 / 147) * 100).substring(0, 5)).append("%");
        return stringBuilder.toString();
    }

    private int get2d6plus6multiply() {
        return (int) (ceil(Calculator.conversion(manyRollsProcess(2, 6))) + 6);
    }

    private int get3d6multiply() {
        return (int) ceil(Calculator.conversion(manyRollsProcess(3, 6)));
    }
}
