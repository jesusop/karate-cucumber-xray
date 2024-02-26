package util;

import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerUtil {
    private static final Map<Class, org.slf4j.Logger> LOGGER_MAP = new ConcurrentHashMap();

    public LoggerUtil() {
    }

    public static Object getLogger(Class clazz) {
        return LOGGER_MAP.computeIfAbsent(clazz, LoggerFactory::getLogger);
    }
}
