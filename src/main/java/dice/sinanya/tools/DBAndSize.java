package dice.sinanya.tools;

public class DBAndSize {

    public static String dbGetter6(int a) {
        if (a >= 2 && a <= 12) {
            return "-1D6";
        } else if (a <= 16) {
            return "-1D4";
        } else if (a <= 24) {
            return "0";
        } else if (a <= 32) {
            return "1d4";
        } else if (a <= 40) {
            return "1d6";
        } else {
            return "计算错误";
        }
    }

    public static String dbGetter(int a) {
        if (a >= 2 && a <= 64) {
            return "-2";
        } else if (a <= 84) {
            return "-1";
        } else if (a <= 124) {
            return "0";
        } else if (a <= 164) {
            return "1d4";
        } else if (a <= 204) {
            return "1d6";
        } else {
            return "计算错误";
        }
    }

    public static int BuildGetter(int a) {
        if (a <= 64) {
            return -2;
        } else if (a <= 84) {
            return -1;
        } else if (a <= 124) {
            return 0;
        } else if (a <= 164) {
            return 1;
        } else if (a <= 204) {
            return 2;
        } else {
            return -100;
        }
    }

    public static int MovGetter(int str, int siz, int dex) {
        if (dex < siz && str < siz) {
            return 7;
        } else if (dex > siz && str > siz) {
            return 9;
        } else {
            return 8;
        }
    }
}
