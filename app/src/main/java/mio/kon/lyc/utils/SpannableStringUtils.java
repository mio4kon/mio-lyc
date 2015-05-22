package mio.kon.lyc.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mio on 15-5-15.
 */
public class SpannableStringUtils {

    private static final Pattern PATTERN_EMOTICON = Pattern.compile("\\[(\\S+?)\\]");

    public static SpannableString span(Context ctx,String text){
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);

        //Match Emoji
        Matcher matcher = PATTERN_EMOTICON.matcher(text);

        while (matcher.find()) {
            // Don't be too long
            if (matcher.end() - matcher.start() < 8) {
                String iconName = matcher.group(0);
                Bitmap bitmap = Emojis.EMOJI_BITMAPS_SCALED.get(iconName);

                if (bitmap != null) {
                    ImageSpan span = new ImageSpan(ctx, bitmap, ImageSpan.ALIGN_BASELINE);
                    ssb.setSpan(span, matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        return SpannableString.valueOf (ssb);
    }

}
