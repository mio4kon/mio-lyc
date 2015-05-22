package mio.kon.lyc.framework;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import mio.kon.lyc.R;

/**
 * Created by mio on 15-5-11.
 */
public  class LycBaseActivity extends AppCompatActivity {
    protected int actLayoutID = -1;
    protected View actLayoutView = null;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //make activity pretty
        if (Build.VERSION.SDK_INT >= 21) {
            requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
        }
        super.onCreate (savedInstanceState);
    }



    /**
     * 设置activity布局_needed
     * @param layoutResID
     */
    protected void _setContentView(@LayoutRes int layoutResID) {
        setContentView (layoutResID);
        initToolBar ();
    }

    /**
     * 设置activity布局_needed
     * @param view
     */
    protected void _setContentView(View view) {
        setContentView (view);
        initToolBar ();
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById (R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayUseLogoEnabled (true);

        if (Build.VERSION.SDK_INT >= 21) {
            toolbar.setElevation(getToolbarElevation());

            View shadow =findViewById (R.id.toobar_shadow);

            if (shadow != null) {
                shadow.setVisibility(View.GONE);
            }
        }
        toolbar.setTitle ("六叶草");
    }




    public float getToolbarElevation() {
        if (Build.VERSION.SDK_INT >= 21) {
            return 12.8f;
        } else {
            return -1;
        }
    }

}
