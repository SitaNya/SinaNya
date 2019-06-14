package dice.sinanya.tools.makedata;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.makedata.MakeDndCardInfo.makeDndCardInfo;

public class MakeDndCard implements Callable<String> {

    public MakeDndCard() {

    }

    @Override
    public String call() {
        return makeDndCardInfo();
    }
}