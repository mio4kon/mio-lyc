package mio.kon.lyc.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import mio.kon.lyc.R;
import mio.kon.lyc.model.WeiboInfo;
import mio.kon.lyc.ui.widget.PicArrView;
import mio.kon.lyc.utils.SpannableStringUtils;
import mio.kon.sdk.support.adapter.BaseRecyclerAdapter;

/**
 * Created by mio on 15-5-18.
 */
public class WeiboAdapter extends BaseRecyclerAdapter<WeiboInfo> {

    private SimpleDraweeView draweeView;
    private TextView tvName;
    private TextView tvMessage;
    private TextView tvFromName;
    private TextView tvFromMessage;
    private RelativeLayout rlFromContent;
    private PicArrView mFromPicArrView,mPicArrView;

    public WeiboAdapter(Context ctx, List<WeiboInfo> datas) {
        super (ctx, datas);
    }



    @Override
    public int getItemResource() {
        return R.layout.item_weibo;
    }

    @Override
    public void findView(ViewHolder holder) {
        draweeView = holder.findView (R.id.drawee_avatar);
        tvName = holder.findView (R.id.tv_name);
        tvMessage = holder.findView (R.id.tv_message);
        tvFromName = holder.findView (R.id.tv_from_name);
        tvFromMessage = holder.findView (R.id.tv_from_message);
        rlFromContent = holder.findView (R.id.rl_from_content);
        mPicArrView = holder.findView (R.id.pic_arr);
        mFromPicArrView = holder.findView (R.id.pic_arr_from);
    }

    @Override
    public void onBindedViewHolder(ViewHolder holder, int position) {
        draweeView.setImageURI (Uri.parse (datas.get (position).user.avatar_large));
        tvName.setText (datas.get (position).user.screen_name);
        tvMessage.setText (SpannableStringUtils.span (ctx, datas.get (position).text));

        ArrayList<WeiboInfo.PictureUrl> pic_urls =null;
        PicArrView picArrView;
        if (datas.get (position).retweeted_status != null) {    /** 转发 **/
            rlFromContent.setVisibility (View.VISIBLE);
            tvFromName.setText (datas.get (position).retweeted_status.user.screen_name);
            tvFromMessage.setText (SpannableStringUtils
                    .span (ctx, datas.get (position).retweeted_status.text));
            //获取中等大小图片urls
            pic_urls = datas.get (position).retweeted_status.pic_urls;
            ArrayList<Uri> uris = new ArrayList<Uri> ();
            for(int i = 0; i < pic_urls.size (); i++) {
                WeiboInfo.PictureUrl pictureUrl = pic_urls.get (i);
                String middleUrl = pictureUrl.thumbnail_pic;
                uris.add (Uri.parse (middleUrl));
            }
            mPicArrView.setVisibility (View.GONE);
            mFromPicArrView.setUrisStr (uris);
        } else {                                              /** 非转发 **/
            rlFromContent.setVisibility (View.GONE);
            pic_urls = datas.get (position).pic_urls;
            ArrayList<Uri> uris = new ArrayList<Uri> ();
            for(int i = 0; i < pic_urls.size (); i++) {
                WeiboInfo.PictureUrl pictureUrl = pic_urls.get (i);
                String middleUrl = pictureUrl.thumbnail_pic;
                uris.add (Uri.parse (middleUrl));
            }
            mFromPicArrView.setVisibility (View.GONE);
            mPicArrView.setUrisStr (uris);
        }
    }

}
