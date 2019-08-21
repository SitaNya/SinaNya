package dice.sinanya.update;

/**
 * @author SitaNya
 * 日期: 2019-08-21
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */

import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

public class ProgressBarThread implements Runnable {

    private final ArrayList<Integer> proList = new ArrayList<Integer>();
    private int progress;//当前进度
    private int totalSize;//总大小
    private boolean run = true;
    private JProgressBar jProgressBar;

    ProgressBarThread(int totalSize, JProgressBar jProgressBar) {
        this.totalSize = totalSize;
        this.jProgressBar = jProgressBar;
    }

    /**
     * @param progress 进度
     */
    void updateProgress(int progress) {
        synchronized (this.proList) {
            if (this.run) {
                this.proList.add(progress);
                this.proList.notify();
            }
        }
    }

    void finish() {
        this.run = false;
        //关闭进度条
    }

    @Override
    public void run() {
        synchronized (this.proList) {
            try {
                while (this.run) {
                    if (this.proList.size() == 0) {
                        this.proList.wait();
                    }
                    synchronized (proList) {
                        this.progress += this.proList.remove(0);
                        jProgressBar.setValue((int) (progress * 1.0 / totalSize * 100));
                        Rectangle rect = new Rectangle(0, 0, jProgressBar.getWidth(), jProgressBar.getHeight());
                        jProgressBar.paintImmediately(rect);
                    }
                }
                CQ.logInfo("更新", "下载完成");
            } catch (Exception e) {
                CQ.logError("更新", "失败: " + e.getMessage() + "\n" + StringUtils.join(e.getStackTrace(), "\n"));
            }
        }
    }
}
