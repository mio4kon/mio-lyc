package mio.kon.lyc.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;


/**
 * Created by mio on 15-5-8.
 * 存储读取token的工具类
 */
public class TokenKeeper {

    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";

    private static final String KEY_UID           = "uid";
    private static final String KEY_ACCESS_TOKEN  = "access_token";
    private static final String KEY_EXPIRES_IN    = "expires_in";


    /**
     * 保存 Token 对象到 SharedPreferences。
     *
     * @param ctx 应用程序上下文环境
     * @param token   Token 对象
     */
    public static void writeToken(Context ctx,Oauth2AccessToken token){
        if (null == ctx || null == token) {
            return;
        }

        SharedPreferences pref = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_UID, token.getUid());
        editor.putString(KEY_ACCESS_TOKEN, token.getToken());
        editor.putLong(KEY_EXPIRES_IN, token.getExpiresTime());
        editor.commit ();
    }


    /**
     * 从 SharedPreferences 读取 Token 信息。
     *
     * @param ctx 应用程序上下文环境
     *
     * @return 返回 Token 对象
     */
    public static Oauth2AccessToken readToken(Context ctx) {
        if (null == ctx) {
            return null;
        }

        Oauth2AccessToken token = new Oauth2AccessToken();
        SharedPreferences pref = ctx.getSharedPreferences (PREFERENCES_NAME, Context.MODE_APPEND);
        token.setUid (pref.getString (KEY_UID, ""));
        token.setToken (pref.getString (KEY_ACCESS_TOKEN, ""));
        token.setExpiresTime (pref.getLong (KEY_EXPIRES_IN, 0));
        return token;
    }

    /**
     * 清空 SharedPreferences 中 Token信息。
     *
     * @param ctx 应用程序上下文环境
     */
    public static void clear(Context ctx) {
        if (null == ctx) {
            return;
        }
        SharedPreferences pref = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}
