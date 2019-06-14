package dice.sinanya.tools;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;

import static dice.sinanya.system.MessagesLogColorTag.logColorTag;
import static dice.sinanya.system.MessagesRGB.RBG;
import static dice.sinanya.system.MessagesSystem.*;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByQQ;

public class SaveDocx {

    WordprocessingMLPackage wordMLPackage;
    ObjectFactory factory = Context.getWmlObjectFactory();

    public void makePDF(String inputText, String inputColor) throws Docx4JException {
        // word对象


        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        documentPart.addObject(createTitle(inputText, inputColor));
    }

    private P createTitle(String inputText, String inputColor) {
        RPr rpr = factory.createRPr();
        RFonts font = new RFonts();

        //设置字体
        font.setAscii("宋体");
        font.setEastAsia("宋体");//经测试发现这个设置生效
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

    public SaveDocx(String groupId, String qqId, String msg, final String bigResult) throws Docx4JException {
        wordMLPackage = WordprocessingMLPackage.createPackage();
        String fromName = "";
        int colorTag = 100;
        for (final String line : bigResult.split("\n")) {
            if (line.contains(":") && !line.split(":")[0].equals("")) {
                if (!logColorTag.containsKey(groupId)) {
                    logColorTag.put(groupId, new HashMap<String, Integer>() {{
                        put(line.split(":")[0], 1);
                    }});
                } else {
                    HashMap<String, Integer> tmp = logColorTag.get(groupId);
                    if (!tmp.containsKey(line.split(":")[0])) {
                        tmp.put(line.split(":")[0], tmp.size());
                    }
                }
                colorTag = logColorTag.get(groupId).get(line.split(":")[0]);
                fromName = line.split(":")[0];
            } else if (line.contains("=========================================================================================")) {
                colorTag = 11;
            } else if (line.contains("发起骰掷")) {
                colorTag = 10;
            } else if (getRoleChooseByQQ(qqId).equals(fromName)) {
                colorTag = 12;
            }
//            else if (line.length() > 1&& !line.split(":")[1].equals("")) {
//                if (testOut(line.split(":")[1])) {
//                    colorTag = 10;
//                }
//            }
            makePDF(line, RBG.get(colorTag));
        }
        File file = null;
        if (OSX_MODEL) {
            file = new File("/Users/SitaNya/Desktop/" + groupId + "/" + msg + ".docx");
        } else if (WIN_MODEL) {
            file = new File("C:/Files/" + groupId + "/" + msg + ".docx");
        } else if (LINUX_MODEL) {
            file = new File("/tmp/" + groupId + "/" + msg + ".docx");
        }
        assert file != null;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        wordMLPackage.save(file);
    }

    private String makeAssci(String inputKey) {
        int tmp = 0;
        char[] b = (inputKey).toCharArray();
        //转换成响应的ASCLL
        for (char c : b) {
            tmp += (int) c;
        }
        return RBG.get(tmp % 10);
    }

    private boolean testOut(String line) {
        return (line.charAt(0) == '(' && !line.contains(")") && !line.contains("）")) || ((line.charAt(0) == '（' || line.charAt(0) == '(') && (line.charAt(line.length() - 1) == ')' || line.charAt(line.length() - 1) == '）'));
    }
}
