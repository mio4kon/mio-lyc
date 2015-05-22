package mio.kon.sdk.util;

import android.content.Context;
import android.graphics.Paint;

/**
 * Created by mio on 15-5-11.
 * some helper
 */
public class Utility {


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /** 获取字体高度 **/
    public static int getFontHeight(Context context, float fontSize) {
        // Convert Dp To Px
        float px = dip2px (context,fontSize);
        // Use Paint to get font height
        Paint p = new Paint();
        p.setTextSize(px);
        Paint.FontMetrics fm = p.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }
}
