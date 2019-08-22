package dice.sinanya.update;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dice.sinanya.system.MessagesSystem;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.windows.imal.HttpGet.checkHttpFileExist;
import static dice.sinanya.windows.imal.HttpGet.sendGet;

/**
 * @author SitaNya
 * 日期: 2019-08-20
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class UpdateForDice {
    ExecutorService cachedThreadPool;
    File jar;
    File json;
    File jarDir;

    File newJar;
    File newJson;

    boolean jarExists;
    boolean jsonExists;
    boolean jarDirExists;

    boolean newJarExists;
    boolean newJsonExists;

    ProgressBarThread pbt;
    JProgressBar downJarProgress;
    JProgressBar downJsonProgress;

    public UpdateForDice(JProgressBar downJarProgress, JProgressBar downJsonProgress) {
        String dir = entitySystemProperties.getSystemDir();
        this.jar = new File(dir + "/../com.sinanya.dice.jar");
        this.json = new File(dir + "/../com.sinanya.dice.json");

        newJar = new File(dir + "/../com.sinanya.dice.jar.new");
        newJson = new File(dir + "/../com.sinanya.dice.json.new");

        this.jarDir = new File(dir + "/../");

        this.jarExists = jar.exists();
        this.jsonExists = json.exists();
        this.newJarExists = newJar.exists();
        this.newJarExists = newJson.exists();
        this.jarDirExists = jar.exists() && jarDir.isDirectory();
        this.downJarProgress = downJarProgress;
        this.downJsonProgress = downJsonProgress;
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("download-jar-and-json-%d").build();
        //Common Thread Pool
        cachedThreadPool = new ThreadPoolExecutor(1, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    public void update() throws IOException {
        if (newJarExists && newJsonExists) {
            newJar.delete();
            newJson.delete();
        }
            try {
                downLoadFromUrl("http://123.207.150.160/com.sinanya.dice.jar", "com.sinanya.dice.jar.new", downJarProgress, jarDir.getPath());
                downLoadFromUrl("http://123.207.150.160/com.sinanya.dice.json", "com.sinanya.dice.json.new", downJsonProgress, jarDir.getPath());
                Rectangle rect = new Rectangle(0, 0, downJsonProgress.getWidth(), downJsonProgress.getHeight());
                downJsonProgress.setValue(downJsonProgress.getMaximum());
                downJsonProgress.paintImmediately(rect);
                cachedThreadPool.shutdown();
                newJsonExists = newJson.exists();
                newJarExists = newJar.exists();
            } catch (IOException e) {
                CQ.logError("下载文件异常", StringUtils.join(e.getStackTrace(), "\n"));
                JOptionPane.showMessageDialog(null, "下载失败，请重新下载");
                return;
            }

        long jarSize = new URL("http://123.207.150.160/com.sinanya.dice.jar").openConnection().getContentLength();
        long jsonSize = new URL("http://123.207.150.160/com.sinanya.dice.json").openConnection().getContentLength();


        if (newJarExists && newJsonExists && newJar.length() == jarSize && newJson.length() == jsonSize) {
            if (jarExists) {
                jar.delete();
            }
            if (jsonExists) {
                json.delete();
            }
            newJar.renameTo(jar);
            newJson.renameTo(json);
            JOptionPane.showMessageDialog(null, "更新完毕,请检查进度条是否已满\n若未满请不要关闭酷Q重新更新。\n若进度条已满，请立刻关闭所有相关窗口并重启酷Q");
        }else{
            JOptionPane.showMessageDialog(null, "更新失败,请不要关闭酷Q重新更新。");
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
        return !MessagesSystem.VERSIONS.equals(sendGet("http://123.207.150.160/version"));
    }

    public boolean serverFileExist() {
        return checkHttpFileExist("http://123.207.150.160/com.sinanya.dice.json") && checkHttpFileExist("http://123.207.150.160/com.sinanya.dice.jar");
    }

    private void downLoadFromUrl(String urlStr, String fileName, JProgressBar jProgressBar, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//        conn.setRequestProperty("lfwywxqyh_token", toekn);

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        this.pbt = new ProgressBarThread(conn.getContentLength(), jProgressBar);
        cachedThreadPool.execute(pbt);
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        fos.close();
        inputStream.close();
        pbt.finish();
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
            pbt.updateProgress(len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
