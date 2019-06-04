package dice.sinanya.tools;

import com.forte.qqrobot.sender.MsgSender;
import dice.sinanya.entity.EntityResult;
import dice.sinanya.exceptions.PlayerSetException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * @author zhangxiaozhou
 */
public class CheckEntityResult {
    private static Logger log = LogManager.getLogger(CheckEntityResult.class.getName());

    public static boolean checkEntityResult(EntityResult entityResult, MsgSender msgSender) {
        int status = entityResult.getStatus();
        switch (status) {
            case 0:
                return true;
            case 1:
                try {
                    throw new PlayerSetException();
                } catch (PlayerSetException e) {
                    log.error(e.getMessage(), e);
                }
                return false;
            default:
                return false;
        }
    }
}
