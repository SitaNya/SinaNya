package dice.sinanya.dice.manager.imal;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AtQq {

    default ArrayList<String> getAtQqList(String msg) {
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }
        return qqList;
    }
}
