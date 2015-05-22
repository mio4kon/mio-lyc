package mio.kon.lyc.api.user;


import mio.kon.lyc.model.TokenInfo;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by mio on 15-5-11.
 * 当前授权用户Api
 */
public interface AccountApi  {

    /**
     * 获取当前认证用户的Token信息 包括uid
     * @param access_token
     * @return
     */
    @FormUrlEncoded
    @POST ("/oauth2/get_token_info.json")
    Observable<TokenInfo> getTokenInfo(@Field ("access_token") String access_token);





}
