package mio.kon.lyc.ui.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import mio.kon.lyc.R;
import mio.kon.sdk.util.LogUtils;
import mio.kon.sdk.util.Utility;


/**
 * Created by mio on 15-5-15.
 * 用于摆放1-9张的图片
 * 1张图,width = width/2
 * 2张图,width = width/2
 * 3张图,width = width/3
 * 4张图,width = width/2
 * 5张图,width = width/3
 * ..
 * <p>
 * high = 1*w (宽高比1:1)
 */
public class PicArrView extends RelativeLayout {

    private ArrayList<SimpleDraweeView> mDraweeViews;
    private ArrayList<Uri> mUris;
    private final float FACTOR = 1f;
    private final float PICTURE_WIDTH = 70f;
    private final float PICTURE_PADDING = 5f;
    private int mPicCount;
    private int mPicWidth;
    private int mPicHight;
    private int mPicPadding;

    public PicArrView(Context context) {
        this (context, null);
    }

    public PicArrView(Context context, AttributeSet attrs) {
        this (context, attrs, 0);
    }

    public PicArrView(Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        init ();
    }

    private void init() {
        setVisibility (GONE);
        mPicPadding = Utility.dip2px (getContext (), PICTURE_PADDING);
    }

    public PicArrView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super (context, attrs, defStyleAttr, defStyleRes);
    }

    public void setUrisStr(ArrayList<Uri> uris) {
        if (uris == null || uris.size () == 0) {
            setVisibility (GONE);
            return;
        } else {
            setVisibility (VISIBLE);
        }

        mUris = uris;
        mPicCount = mUris.size ();
        //view 复用
        inflateChildView ();
        mDraweeViews = new ArrayList<SimpleDraweeView> (mPicCount);
        for(int i = 0; i < mUris.size (); i++) {
            SimpleDraweeView draweeView = (SimpleDraweeView) getChildAt (i);
            draweeView.getHierarchy ().setActualImageScaleType (ScalingUtils.ScaleType.CENTER_CROP);
            draweeView.getHierarchy ().setPlaceholderImage (getResources ().getDrawable (R.mipmap.place_holder_image));
            draweeView.setImageURI (mUris.get (i));
            mDraweeViews.add (draweeView);
        }
    }

    private void inflateChildView() {
        int childCount = getChildCount ();
        int diff = childCount - mPicCount;
        if (diff > 0) {
            deleteChild (Math.abs (diff));
        } else {
            addChild (Math.abs (diff));
        }
    }


    private void addChild(int absDiff) {
        for(int i = mPicCount - absDiff; i < mPicCount; i++) {
            addView (new SimpleDraweeView (getContext ()));
        }
    }

    private void deleteChild(int absDiff) {
        for(int i = mPicCount; i < mPicCount + absDiff; i++) {
            removeView (getChildAt (mPicCount));
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = MeasureSpec.getSize (widthMeasureSpec);

        //TODO 这里需要防止图片显示不全
        mPicWidth = Utility.dip2px (getContext (), PICTURE_WIDTH);
        mPicHight = (int) (FACTOR * mPicWidth + 0.5f);
        for(SimpleDraweeView draweeView : mDraweeViews) {
            LayoutParams layoutParams = new LayoutParams (mPicWidth, mPicHight);
            draweeView.setLayoutParams (layoutParams);
        }
        int row = (mPicCount - 1) / 3 + 1;
        int colu = mPicCount > 2 ? 3 : mPicCount;
        int allHightPadding = (row - 1) * mPicPadding;
        int allWidthPadding = (colu - 1) * mPicPadding;

        widthMeasureSpec = MeasureSpec.makeMeasureSpec (mPicWidth * colu + allWidthPadding, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec (mPicHight * row + allHightPadding, MeasureSpec.EXACTLY);
        setMeasuredDimension (widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount ();
        for(int i = 0; i < count; i++) {
            View child = getChildAt (i);
            if (child.getVisibility () != GONE) {
                if (i <= 2) {
                    l = mPicWidth * i + mPicPadding * i;
                    t = 0;
                    r = mPicWidth * (i + 1) + mPicPadding * i;
                    b = mPicHight;
                } else if (i <= 5) {
                    l = mPicWidth * (i - 3) + mPicPadding * (i - 3);
                    t = mPicHight + mPicPadding;
                    r = mPicWidth * (i - 2) + mPicPadding * (i - 3);
                    b = mPicHight * 2 + mPicPadding;
                } else if (i <= 8) {
                    l = mPicWidth * (i - 6) + mPicPadding * (i - 6);
                    t = (mPicHight + mPicPadding) * 2;
                    r = mPicWidth * (i - 5) + mPicPadding * (i - 6);
                    b = (mPicHight + mPicPadding) * 3;
                }
                child.layout (l, t, r, b);
            }
        }
    }


}
