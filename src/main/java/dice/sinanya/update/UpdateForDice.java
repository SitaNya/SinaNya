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
    File jar;
    File json;
    File jarDir;
    boolean jarExists;
    boolean jsonExists;
    boolean jarDirExists;

    public UpdateForDice() {
        String dir = entitySystemProperties.getSystemDir();
        this.jar = new File(dir + "/../com.sinanya.dice.jar");
        this.json = new File(dir + "/../com.sinanya.dice.json");

        this.jarDir = new File(dir + "/../");

        this.jarExists = jar.exists();
        this.jsonExists = json.exists();
        this.jarDirExists = jar.exists() && jarDir.isDirectory();
    }

    public void update(){
        if (jar.delete() && json.delete()) {
            //TODO 下载文件到指定路径
            JOptionPane.showMessageDialog(null, "更新完毕");
        }else{
            JOptionPane.showMessageDialog(null, "更新失败");
        }
    }

    public boolean isJarExists() {
        return jarExists;
    }

    public boolean isJsonExists() {
        return jsonExists;
    }

    public boolean isJarDirExists() {
        return jarDirExists;
    }

    public boolean checkNeedUpdate(){
        //TODO
        return true;
    }
}
