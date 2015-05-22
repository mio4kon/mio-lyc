package mio.kon.sdk.support.fragment;

/**
 * Created by mio on 15-5-22.
 * 标记接口:标记此Fragment为带有下拉刷新,上拉加载更多的ListFragment
 */
public interface IEndlessRefreshListFragment   extends IRefreshListFragment{

    void loadMoreData();
}
