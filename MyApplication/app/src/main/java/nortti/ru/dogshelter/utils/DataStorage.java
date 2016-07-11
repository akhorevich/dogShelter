package nortti.ru.dogshelter.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStorage {

    public static String getConfig(Context context, String name) {
        return getConfig(context, name, "");
    }

    public static String getConfig(Context context, String name, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(
                Const.NAME, 0);
        String ret = settings.getString(name, defValue);
        return ret;
    }

    public static void setConfig(Context context, String name, String value) {
        SharedPreferences settings = context.getSharedPreferences(
                Const.NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(name, value);
        editor.commit();
    }
}
