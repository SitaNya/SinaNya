package dice.sinanya.tools.log;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.getinfo.GetNickName.getNickName;

public class MakeLogInfo {

    public static String makeLogInfo(EntityTypeMessages entityTypeMessages, String info) {
        info = info.trim();
        StringBuilder result = new StringBuilder();

        if (info.contains("log")) {
            return "";
        }

        if (info.charAt(0) == '.' || info.trim().charAt(0) == '。') {
            result
                    .append("\n=========================================================================================\n")
                    .append(getNickName(entityTypeMessages))
                    .append("发起骰掷");
            return result.toString();
        } else {
            info = info.replaceAll("([\"“”])", "\"");
            boolean fristIsTalk = info.charAt(0) == '"' || info.charAt(0) == '“' || info.charAt(0) == '”';
            boolean lastIsTalk = info.charAt(info.length() - 1) == '"' || info.charAt(info.length() - 1) == '“' || info.charAt(info.length() - 1) == '”';
            if (fristIsTalk && lastIsTalk) {
                result.append(getNickName(entityTypeMessages))
                        .append(":\t")
                        .append("\"")
                        .append(info.replace("\"", ""))
                        .append("\"");
                result.append(info);
            } else if ((info.charAt(0) == '(' && !info.contains(")") && !info.contains("）")) || ((info.charAt(0) == '（' || info.charAt(0) == '(') && (info.charAt(info.length() - 1) == ')' || info.charAt(info.length() - 1) == '）'))) {
                info = info.replaceAll("([(（])", "").replaceAll("([)）])", "");
                result.append("(")
                        .append(getNickName(entityTypeMessages))
                        .append(":")
                        .append(info)
                        .append(")");
            } else {
                result.append(getNickName(entityTypeMessages))
                        .append(":\t")
                        .append("\"")
                        .append(info)
                        .append("\"");
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
        if (info.contains("暗骰结果") || info.contains("日志")) {
            return "";
        }


        result.append(info)
                .append("\n")
                .append("=========================================================================================\n");

        return result.toString();
    }
}
