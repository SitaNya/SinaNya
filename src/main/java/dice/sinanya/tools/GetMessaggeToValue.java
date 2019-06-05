package dice.sinanya.tools;

import java.util.HashMap;

import static dice.sinanya.tools.MakeSkillName.makeSkillName;

public class GetMessaggeToValue {
    public static HashMap<String, Integer> getMessaggeToValue(HashMap<String, Integer> propertiesDefault, String msg) {
        StringBuilder strSkillValue = new StringBuilder();
        StringBuilder strSkillName = new StringBuilder();
        for (int i = 0; i < msg.length(); ) {
            while (i < msg.length() && !Character.isSpaceChar(msg.charAt(i)) &&
                    !Character.isDigit(msg.charAt(i)) &&
                    msg.charAt(i) != ':' &&
                    msg.charAt(i) != '=') {
                strSkillName.append(msg.charAt(i));
                i++;
            }

            while (i < msg.length() && Character.isDigit(msg.charAt(i))) {
                strSkillValue.append(msg.charAt(i));
                i++;
            }

            if (i < msg.length()) {
                try {
                    propertiesDefault.put(makeSkillName(strSkillName.toString()), Integer.parseInt(strSkillValue.toString()));
                    strSkillName = new StringBuilder();
                    strSkillValue = new StringBuilder();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    System.out.println(strSkillName.toString());
                }
            }
        }
        return propertiesDefault;
    }
}
