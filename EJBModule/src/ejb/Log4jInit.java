package ejb;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by tomasfrancisco on 19/11/2016.
 */
public class Log4jInit {

    static Logger log = Logger.getLogger(Log4jInit.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
    }
}
