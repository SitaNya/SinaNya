package dice.sinanya.exceptions;

public class PlayerSetException extends Exception {

    public PlayerSetException() {
        super("很抱歉，参数输入错误");
    }

    public PlayerSetException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PlayerSetException(Throwable cause) {
        super(cause);
    }
}
