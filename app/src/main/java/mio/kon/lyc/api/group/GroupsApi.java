package mio.kon.lyc.api.group;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by mio on 15-5-14.
 * 好友分组API
 */
public interface GroupsApi {

    @GET ("/friendships/groups")
     Observable<Response> getGropps(@Query ("access_token") String access_token);
}
