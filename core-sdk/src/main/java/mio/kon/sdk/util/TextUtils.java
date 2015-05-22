package mio.kon.sdk.util;

/**
 * Created by mio on 15-4-14.
 */
public class TextUtils {

    public static  boolean isEmpty(String str){
        return android.text.TextUtils.isEmpty (str);
    }

    public static  boolean isNotEmpty(String str){
        return !android.text.TextUtils.isEmpty (str);
    }
}
