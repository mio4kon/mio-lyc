package mio.kon.lyc.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;


import java.util.ArrayList;

import mio.kon.lyc.framework.LycBaseFragment;
import mio.kon.sdk.api.SubscribeWrapper;
import mio.kon.lyc.api.weibo.WeiboApi;
import mio.kon.lyc.auth.AuthUtils;
import mio.kon.lyc.model.StatuseInfo;
import mio.kon.lyc.model.WeiboInfo;
import mio.kon.lyc.ui.adapter.WeiboAdapter;
import mio.kon.sdk.api.ApiManager;
import mio.kon.sdk.support.fragment.IEndlessRefreshListFragment;
import mio.kon.sdk.support.fragment.IRefreshListFragment;
import mio.kon.sdk.util.LogUtils;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by mio on 15-5-14.
 */
public class AllWbFragment extends LycBaseFragment implements IEndlessRefreshListFragment {


    private WeiboApi weiboApi;
    private WeiboAdapter weiboAdapter;

    public AllWbFragment() {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        weiboApi = (WeiboApi) ApiManager.inflate (WeiboApi.class);
        weiboApi.getHomeWeibos (AuthUtils.getToken (getActivity ()), 20, 1)
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (SubscribeWrapper.create (statuseInfo -> refresh (statuseInfo)));

        setOnRefreshListener (() -> {
            weiboApi.getHomeWeibos (AuthUtils.getToken (getActivity ()), 20, 1)
                    .observeOn (AndroidSchedulers.mainThread ())
                    .subscribe (SubscribeWrapper.create (statuseInfo -> refresh (statuseInfo)));
        });
    }

    private void refresh(StatuseInfo statusesInfo) {
        ArrayList<WeiboInfo> weibos = statusesInfo.statuses;
        if (weiboAdapter == null) {
            weiboAdapter = new WeiboAdapter (getActivity (), weibos);
            setRecyclerAdapter (weiboAdapter);
        }else{
            weiboAdapter.replaceDatas (weibos);
        }
        setRefreshing (false);
    }


    @Override
    public void loadMoreData() {
        weiboApi.getHomeWeibos (AuthUtils.getToken (getActivity ()), 40, 1)
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (SubscribeWrapper.create (statuseInfo -> refresh (statuseInfo)));
    }
}
