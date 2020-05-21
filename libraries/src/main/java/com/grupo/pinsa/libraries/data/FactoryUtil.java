package com.grupo.pinsa.libraries.data;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Juan J. Palomera on 25/09/2018
 */

public abstract class FactoryUtil {
    /**
     * Api Rest
     */
    public static boolean getBodyResult(Object object) {
        try {
            return object != null && (object.getClass().getMethod("getResultado").invoke(object).equals("YES") || object.getClass().getMethod("getResultado").invoke(object).equals("SI"));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getBodyMessage(Object object) {
        try {
            return object.getClass().getMethod("getMensaje").invoke(object).toString();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getBodyError(Object object) {
        try {
            return object.getClass().getMethod("getError").invoke(object).toString();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return "";
    }
}
