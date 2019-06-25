package dice.sinanya.tools.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;

import static dice.sinanya.system.MessagesLogColorTag.LOG_COLOR_TAG;
import static dice.sinanya.system.MessagesRgb.RGB;
import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByQQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 保存染色文件
 */
public class SaveDocx {

    private static final Logger Log = LogManager.getLogger(SaveDocx.class);
    private ObjectFactory factory;
    private MainDocumentPart documentPart;

    /**
     * 将输入的内容保存到docx文件
     *
     * @param groupId   群号
     * @param qqId      qq号
     * @param msg       日志名
     * @param bigResult 所有日志内容
     * @throws Docx4JException 可能报出docx错误
     */
    public SaveDocx(String groupId, String qqId, String msg, final String bigResult) throws Docx4JException {
        WordprocessingMLPackage wordMlPackage = WordprocessingMLPackage.createPackage();
        factory = Context.getWmlObjectFactory();
        documentPart = wordMlPackage.getMainDocumentPart();
        String fromName = "";
        int colorTag = 100;
        for (final String line : bigResult.split("\n")) {
            if (line.contains(":") && !line.split(":")[0].equals(NONE)) {
                if (!LOG_COLOR_TAG.containsKey(groupId)) {
                    LOG_COLOR_TAG.put(groupId, new HashMap<String, Integer>() {{
                        put(line.split(":")[0], 1);
                    }});
                } else {
                    HashMap<String, Integer> tmp = LOG_COLOR_TAG.get(groupId);
                    if (!tmp.containsKey(line.split(":")[0])) {
                        tmp.put(line.split(":")[0], tmp.size());
                    }
                }
                colorTag = LOG_COLOR_TAG.get(groupId).get(line.split(":")[0]);
                fromName = line.split(":")[0];
            } else if (line.contains("=========================================================================================") || line.contains("(")) {
                colorTag = 11;
            } else if (line.contains("发起骰掷") || line.contains("骰娘:")) {
                colorTag = 10;
            } else if (getRoleChooseByQQ(qqId).equals(fromName)) {
                colorTag = 12;
            }
            makePdf(line, RGB.get(colorTag));
        }
        File file = new File("../saveLogs/" + groupId + "/" + msg + ".docx");
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                Log.error("docx染色文件未能成功生成");
            }
        }
        wordMlPackage.save(file);
    }

    /**
     * 将单条对象染色后添加到文档对象
     *
     * @param inputText  输入单条
     * @param inputColor 输入颜色
     */
    private void makePdf(String inputText, String inputColor) {
        // word对象
        documentPart.addObject(createTitle(inputText, inputColor));
    }

    /**
     * 根据输入值和单条颜色生成单条对象
     *
     * @param inputText  输入信息
     * @param inputColor 染色信息
     * @return 输入返回值
     */
    private P createTitle(String inputText, String inputColor) {
        RPr rpr = factory.createRPr();
        RFonts font = new RFonts();

        //设置字体
        font.setAscii("宋体");
        font.setEastAsia("宋体");
        //经测试发现这个设置生效
        rpr.setRFonts(font);

        //设置颜色
        Color color = new Color();
        color.setVal(inputColor);
        rpr.setColor(color);

        //设置字体大小
        HpsMeasure fontSize = new HpsMeasure();
        fontSize.setVal(new BigInteger("30"));
        rpr.setSzCs(fontSize);
        rpr.setSz(fontSize);

//        //设置粗体
//        BooleanDefaultTrue bold = factory.createBooleanDefaultTrue();
//        bold.setVal(Boolean.TRUE);
//        rpr.setB(bold);
//
//        //设置斜体
//        BooleanDefaultTrue ltalic = new BooleanDefaultTrue();
//        rpr.setI(ltalic);
//
//        //设置删除线
//        BooleanDefaultTrue deleteLine = new BooleanDefaultTrue();
//        deleteLine.setVal(Boolean.TRUE);
//        rpr.setStrike(deleteLine);
//
//        //设置下划线
//        U u = factory.createU();
//        u.setVal(UnderlineEnumeration.SINGLE);
//        u.setVal(UnderlineEnumeration.DOUBLE);//双下划线
//        u.setVal(UnderlineEnumeration.DASH);//虚线
//        u.setVal(UnderlineEnumeration.WAVE);//波浪线
//        rpr.setU(u);


        //设置显示文本

        Text text = factory.createText();
        text.setValue(inputText);
        R r = factory.createR();
        r.getContent().add(text);
        r.setRPr(rpr);

        P p = factory.createP();
        //设置段落居中
        PPr ppr = new PPr();
        Jc jc = new Jc();
        jc.setVal(JcEnumeration.LEFT);
        ppr.setJc(jc);
        p.setPPr(ppr);
        p.getContent().add(r);

        return p;
    }

}
