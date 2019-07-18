package dice.sinanya.tools.makedata;

import static dice.sinanya.tools.makedata.ManyRolls.manyRollsProcessForCard;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: DND多线程车卡中的信息生成类
 */
public class MakeDndCardInfo {

    private MakeDndCardInfo() {
        throw new IllegalStateException("Utility class");
    }

    public static String makeDndCardInfo() {
        StringBuilder stringBuffer = new StringBuilder();
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

    /**
     * @return 生成4D6K3的值
     */
    private static int get4d6k3multiply() {
        return Integer.parseInt(manyRollsProcessForCard(4, 6, 3));
    }
}
