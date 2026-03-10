package utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerConfig {

    public static Logger getLogger(Class<?> clazz) {

        Logger logger = Logger.getLogger(clazz.getName());

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);

        logger.addHandler(handler);
        logger.setLevel(Level.ALL);

        return logger;
    }
}