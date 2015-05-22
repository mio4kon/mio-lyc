package mio.kon.sdk.api;

import android.content.Context;
import mio.kon.sdk.Exception.ApiException;
import retrofit.RestAdapter;


/**
 * Created by mio on 15-5-11.
 */
public class ApiManager {
    private static RestAdapter mRestAdapter;

    /**
     * 在程序入口处初始化API
     *
     * @param HOST_URL rest请求主路径
     * @return
     */
    public static RestAdapter initApi(Context ctx,String HOST_URL) {
        if (mRestAdapter == null) {
            mRestAdapter = new RestAdapter.Builder ()
                    .setEndpoint (HOST_URL)
                    .setLogLevel (RestAdapter.LogLevel.FULL)
                    .build ();
        }
        return mRestAdapter;
    }

    public static Object inflate(Class clazz){
        if(mRestAdapter == null ){
            throw new ApiException ("未初始化,必须在Application中调用initApi");
        }
        return mRestAdapter.create (clazz) ;
    }


}
