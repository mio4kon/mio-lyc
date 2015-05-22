package mio.kon.lyc.framework;

import android.app.Application;
import android.content.Context;


import com.facebook.drawee.backends.pipeline.Fresco;

import mio.kon.lyc.constants.WeiBoCons;
import mio.kon.lyc.utils.Emojis;
import mio.kon.sdk.api.ApiManager;
import mio.kon.sdk.database.SqliteHelperOrm;
import mio.kon.sdk.util.LogUtils;


/**
 * Created by mio on 15-5-8.
 */
public class LycApplication extends Application {

    public static Context mContext;
    public LycApplication(){

    }

    @Override
    public void onCreate() {
        mContext=getApplicationContext();
        //~~~初始化:Log等级
        LogUtils.setLogLevel (LogUtils.LEVEL_VERBOSE);
        //LogUtil.setLogLevel (LogUtil.LEVEL_NO_LOG); 正式

        /** 初始化Api **/
        ApiManager.initApi (mContext,WeiBoCons.HOST_URL);
        /** 初始化Orm **/
        SqliteHelperOrm.initOrm (mContext);
        /** 初始化Fresco **/
        Fresco.initialize (mContext);
        /** 初始化emoji **/
        Emojis.init (mContext);
    }

}
