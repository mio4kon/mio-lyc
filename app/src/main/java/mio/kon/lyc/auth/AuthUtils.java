package mio.kon.lyc.auth;

import android.app.Activity;
import android.content.Context;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import mio.kon.lyc.constants.WeiBoCons;
import mio.kon.sdk.util.LogUtils;

/**
 * Created by mio on 15-5-8.
 */
public class AuthUtils {
    public static Oauth2AccessToken accessToken;

    /**
     * Auth认证
     * @param ctx
     */
    public static void auth(Context ctx,AuthListener.AuthCallBack authCallBack) {
        AuthInfo authInfo = new AuthInfo (ctx, WeiBoCons.APP_KEY, WeiBoCons.REDIRECT_URL, WeiBoCons.SCOPE);
        SsoHandler mSsoHandler = new SsoHandler ((Activity) ctx, authInfo);
        mSsoHandler.authorizeWeb (new AuthListenerWrapper (ctx,authCallBack));
    }

    /**
     * 检查Token是否有效
     * @param ctx
     * @return 是否有效
     */
    public static boolean checkToken(Context ctx){
        accessToken = TokenKeeper.readToken (ctx);
        return accessToken == null ? false : accessToken.isSessionValid ();
    }

    /**
     * 其实并不是反认证.只是把本地存的token清除
     * @param ctx
     */
    public static void unAuth(Context ctx){
         TokenKeeper.clear (ctx);
    }


    /**
     * 未过期时获取Token值
     */
    public static  String getToken(Context ctx){
        if(checkToken (ctx)){
            String token = accessToken.getToken ();
            return token;
        }
        return  null;
    }

    /**
     * 未过期时获取uid值
     */
    public static  String getUid(Context ctx){
        if(checkToken (ctx)){
            String uid = accessToken.getUid ();
            return uid;
        }
        return  null;
    }
}
