package mio.kon.sdk.support.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import mio.kon.sdk.support.adapter.BaseRecyclerAdapter;
import mio.kon.sdk.util.LogUtils;

/**
 * Created by mio on 15-5-22.
 * 封装一些用于复用的Fragment
 */
public class CoreBaseFragment extends Fragment {

    private Context ctx;
    private FragmentType mFragmentType;


    enum FragmentType {
        COMMON_FRAMENT, //普通的,啥也不做
        REFRESH_LIST_FRAGMENT,//带有下拉刷新的Fragment
        ENDLESS_REFRESH_LIST_FRAMENT //带有下拉刷新 上拉加载更多的Fragment
    }

    /**
     * ~~~~~~~~~~~~~IRefreshListFragment START~~~~~~~~~~~~~~~~ *
     */
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    /** ~~~~~~~~~~~~~IRefreshListFragment END~~~~~~~~~~~~~~~~ **/


    /**
     * ~~~~~~~~~~~~~IEndlessRefreshListFragment START~~~~~~~~~~~~~~~~ *
     */
    private EndlessScrollListner mEndlessScrollListner;
    private boolean isLoadingMore = false;

    /**
     * ~~~~~~~~~~~~~IEndlessRefreshListFragment END~~~~~~~~~~~~~~~~ *
     */


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        mFragmentType = FragmentType.COMMON_FRAMENT;
        if (this instanceof IRefreshListFragment) {
            mFragmentType = FragmentType.REFRESH_LIST_FRAGMENT;
        }
        if (this instanceof IEndlessRefreshListFragment) {
            mFragmentType = FragmentType.ENDLESS_REFRESH_LIST_FRAMENT;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ctx = getActivity ();

        if (mFragmentType == FragmentType.REFRESH_LIST_FRAGMENT ||
                mFragmentType == FragmentType.ENDLESS_REFRESH_LIST_FRAMENT) {

            mSwipeRefreshLayout = new SwipeRefreshLayout (ctx);
            mRecyclerView = new RecyclerView (ctx);
            mRecyclerView.setId (android.R.id.list);
            mSwipeRefreshLayout.addView (mRecyclerView, new FrameLayout.
                    LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mLayoutManager = new LinearLayoutManager (ctx);
            mRecyclerView.setLayoutManager (mLayoutManager); //设置LayoutManager
            mSwipeRefreshLayout.setLayoutParams (new ViewGroup.
                    LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return mSwipeRefreshLayout;

        }
        return super.onCreateView (inflater, container, savedInstanceState);
    }

    /**
     * 提供设置Adapter
     * <P>确保该Fragment实现了{@link IRefreshListFragment}</P>
     *
     * @param adapter
     */
    public void setRecyclerAdapter(RecyclerView.Adapter adapter) {
        if (mFragmentType == FragmentType.COMMON_FRAMENT) {
            return;
        }
        mAdapter = adapter;
        if (mRecyclerView == null) {
            return;
        }
        mRecyclerView.setAdapter (mAdapter);
        if (mFragmentType == FragmentType.ENDLESS_REFRESH_LIST_FRAMENT) {
            mEndlessScrollListner = new EndlessScrollListner ();
            mRecyclerView.setOnScrollListener (mEndlessScrollListner);
        }
    }


    /**
     * 设置是否显示刷新
     * <P>确保该Fragment实现了{@link IRefreshListFragment}</P>
     *
     * @param refreshing
     */
    public void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing (refreshing);

        //如何是上拉家在更多时,要还原isLoadingMore
        isLoadingMore = false;
    }

    /**
     * 设置刷新监听器
     * <P>确保该Fragment实现了{@link IRefreshListFragment}</P>
     *
     * @param listener
     */
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener (listener);
    }

    /**
     * 设置SwipeRefreshLayout刷新样式
     * <P>确保该Fragment实现了{@link IRefreshListFragment}</P>
     *
     * @param colorRes1
     * @param colorRes2
     * @param colorRes3
     * @param colorRes4
     */
    public void setColorSchemeResources(int colorRes1, int colorRes2, int colorRes3, int colorRes4) {
        mSwipeRefreshLayout.setColorSchemeResources (colorRes1, colorRes2, colorRes3, colorRes4);
    }

    /**
     * 实现item点击事件
     * <P>确保该Fragment实现了{@link IRefreshListFragment}</P>
     * <P>使用之前确保Adapter是继承{@link BaseRecyclerAdapter}</P>
     */
    public void onItemClick(View itemView, int position) {
        if (mAdapter instanceof BaseRecyclerAdapter) {
            ((BaseRecyclerAdapter) mAdapter).onItemClick (itemView, position);
        }
    }


    /** 下拉加载更多的滑动监听 **/
    class EndlessScrollListner extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled (recyclerView, dx, dy);

            if (mLayoutManager instanceof LinearLayoutManager) {
                int lastItemCount = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition ();
                int totaltemCount = mAdapter.getItemCount ();
                LogUtils._d ("lastItemCount:" + lastItemCount + "=====totaltemCount:" + totaltemCount);

                if (totaltemCount - lastItemCount == 1 && dy > 0) {
                    if (isLoadingMore) {
                        //正在加载
                        LogUtils._d ("正在加载,请不要急啊!");
                    } else {
                        //加载更多
                        mSwipeRefreshLayout.setRefreshing (true);
                        ((IEndlessRefreshListFragment)CoreBaseFragment.this).loadMoreData ();
                    }
                }
            }
        }
    }

}
