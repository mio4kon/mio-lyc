package mio.kon.lyc.cache.user;

import android.content.Context;

import mio.kon.sdk.api.SubscribeWrapper;
import mio.kon.lyc.api.user.UserApi;
import mio.kon.lyc.auth.AuthUtils;
import mio.kon.lyc.model.UserInfo;
import mio.kon.sdk.api.ApiManager;
import mio.kon.sdk.database.DbHelper;
import mio.kon.sdk.util.LogUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by mio on 15-5-12.
 */
public class UserApiCache {

    private UserInfo userInfo;
    private Context ctx;
    private final UserApi userApi;
    private final DbHelper<UserInfo> dbHelper;

    public UserApiCache(Context context) {
        ctx = context;
        userApi = (UserApi) ApiManager.inflate (UserApi.class);
        dbHelper = DbHelper.getInstance ();
    }


    /**
     * 先从本地获取userinfo之后从网络获取
     *
     * @param updateListener
     * @return
     */
    public void getUserInfoByUid(UpdateListener updateListener) {
        Observable<UserInfo> observable;
        if (dbHelper.exists (UserInfo.class)) {
            //从cache获取UserInfo
            LogUtils._d ("从本地获取userinfo");
            userInfo = getUserInfoCache ();
            updateListener.onUpdate (userInfo);
        }
        //从网络获取UserInfo
        LogUtils._d ("从网络获取UserInfo");
        userApi.getUserInfoByUid (AuthUtils.getUid (ctx), AuthUtils.getToken (ctx))
                .doOnNext (u -> saveToCache (u))
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (SubscribeWrapper.create (u -> {
                    if (updateListener != null)
                        updateListener.onUpdate (u);
                }));


    }


    /**
     * 从本地获取userinfo
     *
     * @return
     */
    public UserInfo getUserInfoCache() {
        return dbHelper.queryFirstForAll (UserInfo.class);
    }

    /**
     * 存储到本地
     *
     * @param userInfo
     */
    private void saveToCache(UserInfo userInfo) {
        LogUtils._d ("存储userinfo");
        dbHelper.createOrUpdate (userInfo);
    }

    private UpdateListener updateListener;

    public interface UpdateListener {
        void onUpdate(UserInfo userInfo);
    }
}


