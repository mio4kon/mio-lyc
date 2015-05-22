package mio.kon.lyc.auth;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * Created by mio on 15-5-8.
 */
public  class AuthListener implements WeiboAuthListener {

    protected Oauth2AccessToken mAccessToken;
    private AuthCallBack authCallBack;
    private Context ctx;
    public AuthListener(Context ctx,AuthCallBack authCallBack) {
        this.ctx = ctx;
        this.authCallBack = authCallBack;
    }

    public interface AuthCallBack {
        void authSuccess();

        void authCancel();

        void authFailed(String code);
    }

    @Override
    public void onComplete(Bundle values) {
        // 从 Bundle 中解析 Token
        mAccessToken = Oauth2AccessToken.parseAccessToken (values);
        //保存AccessToken到本地
        TokenKeeper.writeToken (ctx,mAccessToken);
        if (mAccessToken.isSessionValid ()) {
            authCallBack.authSuccess ();

        } else {
            // 以下几种情况，您会收到 Code：
            // 1. 当您未在平台上注册的应用程序的包名与签名时；
            // 2. 当您注册的应用程序包名与签名不正确时；
            // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
            String code = values.getString ("code");
            authCallBack.authFailed (code);
        }
    }

    @Override
    public void onCancel() {
        authCallBack.authCancel ();
    }

    @Override
    public void onWeiboException(WeiboException e) {
        authCallBack.authFailed ("WeiboException");
    }
}
