package dice.sinanya.tools;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.DBAndSize.dbGetter;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class MakeCoc7Card implements Callable<String> {

    public MakeCoc7Card() {

    }

    @Override
    public String call() {
        StringBuilder stringBuilder = new StringBuilder();
        int str = get3d6multiply5();
        int con = get3d6multiply5();
        int siz = get2d6plus6multiply5();
        int dex = get3d6multiply5();
        int app = get3d6multiply5();
        int intValue = get2d6plus6multiply5();
        int pow = get3d6multiply5();
        int edu = get2d6plus6multiply5();
        int luck = get3d6multiply5();

        int notLuck = str + con + siz + dex + app + intValue + pow + edu;
        int hasLuck = str + con + siz + dex + app + intValue + pow + edu + luck;

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

                .append("幸运:")
                .append(luck)
                .append(" ")

                .append("血量:")
                .append((int) floor((siz + con) / 10))
                .append(" ")

                .append("DB:")
                .append(dbGetter(siz + str))
                .append(" ")

                .append("总值:")
                .append(notLuck)
                .append("/")
                .append(hasLuck)
                .append(" ")

                .append("占总点数的:").append(String.valueOf((hasLuck * 1.0 / 630) * 100).substring(0, 5)).append("%");
        return stringBuilder.toString();
    }

    private int get2d6plus6multiply5() {
        return (int) (ceil(Calculator.conversion(manyRollsProcess(2, 6))) + 6) * 5;
    }

    private int get3d6multiply5() {
        return (int) ceil(Calculator.conversion(manyRollsProcess(3, 6))) * 5;
    }
}
