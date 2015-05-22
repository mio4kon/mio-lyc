package mio.kon.sdk.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mio on 15-5-8.
 * sharedPreference工具类
 */
public class SpUtils {

    private static int SAVE_MODE = Context.MODE_PRIVATE;

    public static void setMode(int saveMode) {
        SAVE_MODE =saveMode;
    }

    public static void save(Context ctx,String spName,String key,String value){
        SharedPreferences pref = ctx.getSharedPreferences (spName,SAVE_MODE);
        SharedPreferences.Editor edit = pref.edit ();
        edit.putString (key,value);
        edit.commit ();
    }
}
