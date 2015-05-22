package mio.kon.lyc.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;


import com.facebook.drawee.view.SimpleDraweeView;

import mio.kon.lyc.R;
import mio.kon.lyc.api.group.GroupsApi;
import mio.kon.lyc.auth.AuthUtils;
import mio.kon.lyc.cache.user.UserApiCache;
import mio.kon.lyc.framework.AuthedActivity;
import mio.kon.lyc.model.UserInfo;
import mio.kon.lyc.ui.adapter.FrameViewPagerAdapter;
import mio.kon.sdk.api.ApiManager;
import mio.kon.sdk.util.LogUtils;
import rx.Observable;
import rx.android.view.ViewObservable;


public class MainActivity extends AuthedActivity {

    /** 头像 **/
    private SimpleDraweeView draweeAvatar;
    /** 头像背景 **/
    private SimpleDraweeView draweeBg;
    private ViewPager mViewPager;
    private FrameViewPagerAdapter mFrameViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actLayoutID = R.layout.activity_main;
        super.onCreate (savedInstanceState);
        initDrawer ();
    }

    @Override
    protected void initView() {
        mFrameViewPagerAdapter = new FrameViewPagerAdapter (getSupportFragmentManager(),this);
        draweeAvatar = (SimpleDraweeView) findViewById (R.id.drawee_avatar);
        draweeBg = (SimpleDraweeView) findViewById (R.id.drawee_bg);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter (mFrameViewPagerAdapter);
    }

    @Override
    protected void initData() {
        UserApiCache userApiCache = new UserApiCache (this);
        userApiCache.getUserInfoByUid (u -> flushView (u));
    }

    private void flushView(UserInfo userInfo) {
        draweeAvatar.setImageURI (Uri.parse (userInfo.avatar_large));
        draweeBg.setImageURI (Uri.parse (userInfo.cover_image_phone));
    }

    private void initDrawer() {
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById (R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle (this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.syncState ();
        mDrawerLayout.setDrawerListener (actionBarDrawerToggle);
    }


}
