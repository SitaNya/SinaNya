package dice.sinanya.tools.log;

import org.apache.logging.log4j.LogManager;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import java.util.Arrays;
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
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
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
            boolean isSpeak = line.contains(":") && !line.split(":")[0].equals(NONE);
            boolean isBotOrHidden = line.contains("=========================================================================================") || line.contains("(");
            boolean isBotDice = line.contains("发起骰掷") || line.contains("骰娘:");
            boolean isKp = getRoleChooseByQQ(qqId).equals(fromName);

            if (isSpeak) {
                if (LOG_COLOR_TAG.containsKey(groupId)) {
                    HashMap<String, Integer> tmp = LOG_COLOR_TAG.get(groupId);
                    if (!tmp.containsKey(line.split(":")[0])) {
                        tmp.put(line.split(":")[0], tmp.size());
                    }
                } else {
                    HashMap<String, Integer> prop = new HashMap<>();
                    prop.put(line.split(":")[0], 1);
                    LOG_COLOR_TAG.put(groupId, prop);
                }
                colorTag = LOG_COLOR_TAG.get(groupId).get(line.split(":")[0]);
                fromName = line.split(":")[0];
            } else if (isBotOrHidden) {
                colorTag = 11;
            } else if (isBotDice) {
                colorTag = 10;
            } else if (isKp) {
                colorTag = 12;
            }
            makePdf(line, RGB.get(colorTag));
        }
        File file = new File(entitySystemProperties.getSystemDir()+"/saveLogs/" + groupId + "/" + msg + ".docx");
        if (!file.getParentFile().mkdirs() || !file.getParentFile().exists()) {
            CQ.logError("日志落地异常", "docx染色文件未能成功生成");
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
        fontSize.setVal(BigInteger.valueOf(30));
        rpr.setSzCs(fontSize);
        rpr.setSz(fontSize);

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
