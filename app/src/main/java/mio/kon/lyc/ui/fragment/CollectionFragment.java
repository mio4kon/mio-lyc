package mio.kon.lyc.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import mio.kon.lyc.framework.LycBaseFragment;

/**
 * Created by mio on 15-5-14.
 */
public class CollectionFragment extends LycBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SimpleDraweeView draweeView = new SimpleDraweeView (getActivity ());
//        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder (getResources ());
//        builder.
        draweeView.getHierarchy ().setActualImageScaleType (ScalingUtils.ScaleType.CENTER_INSIDE);
        draweeView.setImageURI (Uri.parse ("http://ww1.sinaimg.cn/bmiddle/005NaFxIgw1es4r8gwey7j30go09qac9.jpg"));
        return draweeView;
    }
}
