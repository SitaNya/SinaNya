package dice.sinanya.tools;

public class CheckValue {
    public static Object checkValue(Object object) {
        if (object == null) {
            return "null";
        } else {
            return object.toString();
        }
    }
}
