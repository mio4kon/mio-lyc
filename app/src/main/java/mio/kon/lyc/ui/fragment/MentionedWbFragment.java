package mio.kon.lyc.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mio.kon.lyc.framework.LycBaseFragment;

/**
 * Created by mio on 15-5-14.
 * 提及的微博
 */
public class MentionedWbFragment extends LycBaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView (getActivity ());
        textView.setText ("啦啦啦");
        return textView;
    }
}
