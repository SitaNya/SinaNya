package dice.sinanya.tools;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.MakeDndCardInfo.makeDndCardInfo;

public class MakeDndCard implements Callable<String> {

    public MakeDndCard() {

    }

    @Override
    public String call() {
        return makeDndCardInfo();
    }
}