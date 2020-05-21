package com.grupo.pinsa.libraries.common;

import android.graphics.drawable.Drawable;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.grupo.pinsa.libraries.GrupoPinsaAplicacion;
import com.grupo.pinsa.libraries.R;
import com.grupo.pinsa.libraries.dialogos.Dialogo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class Conversion {
    public static String resourceToString(@StringRes int message) {
        return GrupoPinsaAplicacion.GRUPO_PINSA.getApplicationContext().getString(message);
    }

    public static int resourceToColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(GrupoPinsaAplicacion.GRUPO_PINSA.getApplicationContext(), colorRes);
    }

    public static Drawable resourceToDrawable(@DrawableRes int drawable) {
        return GrupoPinsaAplicacion.GRUPO_PINSA.getApplicationContext().getDrawable(drawable);
    }

    public static String toMD5(String mensaje) {
        try {
            if (mensaje != null) {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(mensaje.getBytes());
                byte messageDigest[] = digest.digest();

                StringBuilder hexString = new StringBuilder();
                for (byte aMessageDigest : messageDigest) {
                    String hex = Integer.toHexString(0xFF & aMessageDigest);
                    while (hex.length() < 2)
                        hex = "0".concat(hex);

                    hexString.append(hex);
                }

                return hexString.toString();
            } else {
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA, R.string.grupopinsa_conversion_error_md5, Dialogo.ACEPTAR, true);
        }

        return "";
    }

    public static String toString(String input) {
        return input != null ? input : "";
    }

    public static String toShortStringDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(date);
    }

    public static String toShortStringDate(String date) {
        return toShortStringDate(toDate(date));
    }

    public static String toShortStringDateOracle(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String toStringDateOracle(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date toDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = format.parse(input);
            return date;
        } catch (ParseException e) {
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA.getBaseContext(), R.string.grupopinsa_conversion_error_date, Dialogo.ACEPTAR, true);
            return Calendar.getInstance().getTime();
        }
    }

    public static Date toDateTime(String input) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = format.parse(input);
            return date;
        } catch (ParseException e) {
            Dialogo.showDialogoError(GrupoPinsaAplicacion.GRUPO_PINSA.getBaseContext(), R.string.grupopinsa_conversion_error_date, Dialogo.ACEPTAR, true);
            return Calendar.getInstance().getTime();
        }
    }

    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEmptyString(String input) {
        return (input == null || input.equals(""));
    }
}
