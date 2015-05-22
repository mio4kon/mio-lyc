package mio.kon.lyc.api.weibo;

import mio.kon.lyc.model.StatuseInfo;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by mio on 15-5-14.
 */
public interface WeiboApi {


    /** 获取我的首页微博 **/
    @GET ("/2/statuses/home_timeline.json")
    Observable<StatuseInfo> getHomeWeibos(@Query("access_token") String access_token,
                                     @Query("count") int count,
                                     @Query("page") int page
    );
}
