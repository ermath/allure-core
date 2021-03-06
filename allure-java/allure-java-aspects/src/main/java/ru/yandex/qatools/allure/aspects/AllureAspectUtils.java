package ru.yandex.qatools.allure.aspects;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.Arrays;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 24.10.13
 */
public final class AllureAspectUtils {
    private AllureAspectUtils() {
    }

    public static String getTitle(String namePattern, String methodName, Object[] parameters) {
        String finalPattern = namePattern.replaceAll("\\{method\\}", methodName);
        Object[] results = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++){
            results[i] = arrayToString(parameters[i]);
        }
        return MessageFormat.format(finalPattern, results);
    }

    public static Object arrayToString(Object obj) {
        if (obj.getClass().isArray()) {
            int len = Array.getLength(obj);
            String[] strings = new String[len];
            for (int i = 0; i < len; i++){
                strings[i] = String.valueOf(Array.get(obj, i));
            }
            return Arrays.toString(strings);
        } else {
            return obj;
        }
    }
}
