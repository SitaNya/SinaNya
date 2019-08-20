package dice.sinanya.update;

import javax.swing.*;
import java.io.File;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;

/**
 * @author SitaNya
 * 日期: 2019-08-20
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class UpdateForDice {
    String dir;
    public UpdateForDice(String dir){
        this.dir=dir;
    }

    public void update(){
        File jar=new File(entitySystemProperties.getSystemDir()+"/../com.sinanya.dice.jar");
        File json=new File(entitySystemProperties.getSystemDir()+"/../com.sinanya.dice.json");

        File jarDir = new File(entitySystemProperties.getSystemDir() + "/../");
        File jsonDir = new File(entitySystemProperties.getSystemDir() + "/../");
        if (jar.exists() && json.exists() && jar.delete() && json.delete() && jarDir.isDirectory() && jsonDir.isDirectory()) {
            //TODO 下载文件到指定路径
            JOptionPane.showMessageDialog(null, "更新完毕");
        }else{
            JOptionPane.showMessageDialog(null, "更新失败");
        }
    }
}
