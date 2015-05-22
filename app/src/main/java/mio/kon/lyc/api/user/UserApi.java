package mio.kon.lyc.api.user;

import mio.kon.lyc.model.UserInfo;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by mio on 15-5-11.
 * 用户信息相关API
 */
public interface UserApi {

    @GET ("/2/users/show.json")
     Observable<UserInfo> getUserInfoByUid(@Query ("uid") String uid,
                                                 @Query ("access_token") String access_token);






}
