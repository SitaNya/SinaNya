package dice.sinanya.tools;

import java.util.concurrent.Callable;

import static dice.sinanya.tools.MakeCocCardInfo.makeCardInfo;

public class MakeCoc7Card implements Callable<String> {

    public MakeCoc7Card() {

    }

    @Override
    public String call() {
        return makeCardInfo();
    }


}
