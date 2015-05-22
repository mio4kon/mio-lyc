package mio.kon.lyc.factory;

import mio.kon.lyc.framework.LycBaseFragment;
import mio.kon.lyc.ui.fragment.AllWbFragment;
import mio.kon.lyc.ui.fragment.CollectionFragment;
import mio.kon.lyc.ui.fragment.MentionedComFragment;
import mio.kon.lyc.ui.fragment.MentionedWbFragment;

/**
 * Created by mio on 15-5-14.
 * //        <item>全部</item>
 //        <item>评论</item>
 //        <item>提及的评论</item>
 //        <item>提及的微博</item>
 //        <item>收藏</item>
 */
public class MainFragmentFactory {

    public static MainFragmentFactory mainFragmentFactory;

    public static LycBaseFragment createFragment(int position) {

        LycBaseFragment lycBaseFragment = null;
        switch (position){
            case 0:
                lycBaseFragment = new AllWbFragment ();
                break;
            case 1:
                lycBaseFragment = new CollectionFragment ();
                break;
            case 2:
                lycBaseFragment = new MentionedComFragment ();
                break;
            case 3:
                lycBaseFragment = new MentionedWbFragment ();
                break;
            case 4:
                lycBaseFragment = new CollectionFragment ();
                break;

        }
        return lycBaseFragment;
    }


}
