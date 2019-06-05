package dice.sinanya.tools;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 当前时间的相关方法
 *
 * @author zhangxiaozhou
 */
public class GetTime {
    /**
     * @return 获取当前时间（格式化后的）
     */
    static public String getNowString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 将格式化好的时间转化为时间戳
     *
     * @param time 传入格式化后的时间
     * @return 时间戳
     */
    public static Timestamp getTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = null;

        try {
            d = format.parse(time);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new Timestamp((d != null)
                ? d.getTime()
                : 0);
    }

}
