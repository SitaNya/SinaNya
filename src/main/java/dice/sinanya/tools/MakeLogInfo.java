package dice.sinanya.tools;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.GetNickName.getNickName;

public class MakeLogInfo {

    public static String makeLogInfo(EntityTypeMessages entityTypeMessages, String info) {
        info = info.trim();
        StringBuffer result = new StringBuffer();

        if (info.contains("log")){
            return "";
        }

        if (info.charAt(0) == '.' || info.trim().charAt(0) == '。') {
            result
                    .append("=========================================================================================\n")
                    .append(getNickName(entityTypeMessages))
                    .append("发起骰掷");
            return result.toString();
        } else {
            result.append(getNickName(entityTypeMessages));
            info = info.replaceAll("([\"“”])", "\"");
            if (info.contains("\"")) {
                result.append(info);
            } else {
                result.append("\"")
                        .append(getNickName(entityTypeMessages))
                        .append(":\t")
                        .append(info)
                        .append("\"");
            }

            if (info.charAt(0) == '(' || info.charAt(0) == '（') {
                info = info.replaceAll("([(（])", "\\(").replaceAll("([)）])", "\\)");
                result.append("(")
                        .append(getNickName(entityTypeMessages))
                        .append(":")
                        .append(info)
                        .append(")");
            }

            if (info.charAt(0) == '#') {
                result.append(getNickName(entityTypeMessages))
                        .append(info);
            }
            return result.toString();
        }
    }

    public static String makeLogInfo(String info) {
        info = info.trim();
        StringBuilder result = new StringBuilder();
        if (info.contains("暗骰结果")) {
            return "";
        }


        result.append(info)
                .append("\n")
                .append("=========================================================================================");

        return result.toString();
    }
}
