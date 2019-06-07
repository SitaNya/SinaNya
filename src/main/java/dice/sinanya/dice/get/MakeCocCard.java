package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.Calculator;
import dice.sinanya.tools.MakeCoc6Card;
import dice.sinanya.tools.MakeCoc7Card;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static dice.sinanya.system.MessagesTag.tagCoc6;
import static dice.sinanya.system.MessagesTag.tagCoc7;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.DBAndSize.*;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class MakeCocCard {

    private EntityTypeMessages entityTypeMessages;

    public MakeCocCard(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void coc7d() {
        String nick = getNickName(entityTypeMessages);
        StringBuilder stringBuilder = new StringBuilder();

        int str = get3d6multiply() * 5;
        int con = get3d6multiply() * 5;
        int siz = get2d6plus6multiply() * 5;
        int dex = get3d6multiply() * 5;
        int app = get3d6multiply() * 5;
        int intValue = get2d6plus6multiply() * 5;
        int pow = get3d6multiply() * 5;
        int edu = get2d6plus6multiply() * 5;
        int luck = get3d6multiply() * 5;

        int notLuck = str + con + siz + dex + app + intValue + pow + edu;
        int hasLuck = str + con + siz + dex + app + intValue + pow + edu + luck;

        stringBuilder
                .append(nick)
                .append("的人物作成:")
                .append("\n")
                .append("力量STR=3D6*5=")
                .append(str).append("/")
                .append(str / 2).append("/")
                .append(str / 5).append("\n")

                .append("体质CON=3D6*5=")
                .append(con).append("/")
                .append(con / 2).append("/")
                .append(con / 5).append("\n")

                .append("体型SIZ=(2D6+6)*5=")
                .append(siz).append("/")
                .append(siz / 2).append("/")
                .append(siz / 5).append("\n")

                .append("敏捷DEX=3D6*5=")
                .append(dex).append("/")
                .append(dex / 2).append("/")
                .append(dex / 5).append("\n")

                .append("外貌APP=3D6*5=")
                .append(app).append("/")
                .append(app / 2).append("/")
                .append(app / 5).append("\n")

                .append("智力INT=(2D6+6)*5=")
                .append(intValue).append("/")
                .append(intValue / 2).append("/")
                .append(intValue / 5).append("\n")

                .append("意志POW=3D6*5=")
                .append(pow).append("/")
                .append(pow / 2).append("/")
                .append(pow / 5).append("\n")

                .append("教育EDU=(2D6+6)*5=")
                .append(edu).append("/")
                .append(edu / 2).append("/")
                .append(edu / 5).append("\n")

                .append("幸运LUCK=3D6*5=")
                .append(luck).append("/")
                .append(luck / 2).append("/")
                .append(luck / 5).append("\n").append("\n")

                .append("共计:\n")
                .append("不带幸运为:\t")
                .append(notLuck).append("\t大约为 ").append(String.valueOf((notLuck * 1.0 / 540) * 100).substring(0, 5)).append("% 强度(越高越好)\n")
                .append("带幸运为:\t")
                .append(hasLuck).append("\t大约为 ").append(String.valueOf((hasLuck * 1.0 / 630) * 100).substring(0, 5)).append("% 强度(越高越好)")
                .append("\n").append("\n")

                .append("生命值HP=(SIZ+CON)/10(向下取整)=")
                .append((int) floor((siz + con) / 10))
                .append("\n")

                .append("理智SAN=POW=")
                .append(pow)
                .append("\n")

                .append("魔法值MP=POW/5=")
                .append(pow / 5)
                .append("\n")

                .append("伤害奖励DB=")
                .append(dbGetter(siz + str))
                .append("\n")
                .append("体格BUILD=")
                .append(BuildGetter(siz + str))
                .append("\n")

                .append("移动力MOV=")
                .append(MovGetter(str, siz, dex));

        sender(entityTypeMessages, stringBuilder.toString());
    }

    public void coc6d() {
        String nick = getNickName(entityTypeMessages);
        StringBuilder stringBuilder = new StringBuilder();

        int str = get3d6multiply();
        int con = get3d6multiply();
        int siz = get2d6plus6multiply();
        int dex = get3d6multiply();
        int app = get3d6multiply();
        int intValue = get2d6plus6multiply();
        int pow = get3d6multiply();
        int edu = get3d6multiply() + 3;

        int notLuck = str + con + siz + dex + app + intValue + pow + edu;

        stringBuilder
                .append(nick)
                .append("的人物作成:")
                .append("\n")
                .append("力量STR=3D6=")
                .append(str).append("/")
                .append(str / 2).append("/")
                .append(str / 5).append("\n")

                .append("体质CON=3D6=")
                .append(con).append("/")
                .append(con / 2).append("/")
                .append(con / 5).append("\n")

                .append("体型SIZ=2D6+6=")
                .append(siz).append("/")
                .append(siz / 2).append("/")
                .append(siz / 5).append("\n")

                .append("敏捷DEX=3D6=")
                .append(dex).append("/")
                .append(dex / 2).append("/")
                .append(dex / 5).append("\n")

                .append("外貌APP=3D6=")
                .append(app).append("/")
                .append(app / 2).append("/")
                .append(app / 5).append("\n")

                .append("智力INT=2D6+6=")
                .append(intValue).append("/")
                .append(intValue / 2).append("/")
                .append(intValue / 5).append("\n")

                .append("意志POW=3D6=")
                .append(pow).append("/")
                .append(pow / 2).append("/")
                .append(pow / 5).append("\n")

                .append("教育EDU=3D6+3=")
                .append(edu).append("/")
                .append(edu / 2).append("/")
                .append(edu / 5).append("\n")

                .append("共计:\n")
                .append(notLuck).append("为 ").append(String.valueOf((notLuck * 1.0 / 147) * 100).substring(0, 5)).append("% 强度(越高越好)\n")
                .append("\n").append("\n")

                .append("生命值HP=(SIZ+CON)/2(向上取整)=")
                .append((int) ceil((siz + con) / 2.0))
                .append("\n")

                .append("理智SAN=POW*5=")
                .append(pow * 5)
                .append("\n")

                .append("灵感IDEA=INT*5=")
                .append(intValue * 5)
                .append("\n")

                .append("幸运LUCK=POW*5=")
                .append(pow * 5)
                .append("\n")

                .append("知识KNOW=EDU*5=")
                .append(edu * 5)
                .append("\n")

                .append("伤害奖励DB=")
                .append(dbGetter(siz + str));

        sender(entityTypeMessages, stringBuilder.toString());
    }

    public void coc7() {
        String tag = tagCoc7;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 11));
        msg = deleteTag(msg, ".coc");

        int times = 1;

        if (isNumeric(msg)) {
            times = Integer.parseInt(msg);
        }

        String nick = getNickName(entityTypeMessages);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(nick)
                .append("的7版人物做成:")
                .append("\n");


        ExecutorService exec = Executors.newCachedThreadPool();//工头
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();//
        for (int i = 0; i < times; i++) {
            results.add(exec.submit(new MakeCoc7Card()));//submit返回一个Future，代表了即将要返回的结果
        }

        for (Future future : results) {
            while (!future.isDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                stringBuilder.append(future.get());
                stringBuilder.append("\n");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        exec.shutdown();
        sender(entityTypeMessages, stringBuilder.toString().substring(0, stringBuilder.length() - 1));
    }

    public void coc6() {
        String tag = tagCoc6;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 11));

        int times = 1;

        if (isNumeric(msg)) {
            times = Integer.parseInt(msg);
        }

        String nick = getNickName(entityTypeMessages);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nick)
                .append("的6版人物做成:")
                .append("\n");

        ExecutorService exec = Executors.newCachedThreadPool();//工头
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();//
        for (int i = 0; i < times; i++) {
            results.add(exec.submit(new MakeCoc6Card()));//submit返回一个Future，代表了即将要返回的结果
        }

        for (Future future : results) {
            while (!future.isDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                stringBuilder.append(future.get());
                stringBuilder.append("\n");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        exec.shutdown();
        sender(entityTypeMessages, stringBuilder.toString().substring(0, stringBuilder.length() - 1));
    }

    private int get2d6plus6multiply() {
        return (int) (ceil(Calculator.conversion(manyRollsProcess(2, 6))) + 6);
    }

    private int get3d6multiply() {
        return (int) ceil(Calculator.conversion(manyRollsProcess(3, 6)));
    }
}
