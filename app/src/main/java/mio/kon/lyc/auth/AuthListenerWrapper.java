package mio.kon.lyc.auth;

import android.content.Context;


/**
 * Created by mio on 15-5-8.
 */
public  class AuthListenerWrapper extends AuthListener {
    public AuthListenerWrapper(Context ctx,AuthCallBack authCallBack) {
        super (ctx,authCallBack);
    }
}
