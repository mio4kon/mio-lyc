package mio.kon.lyc.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.HashMap;

import mio.kon.sdk.util.Utility;

/**
 * Created by mio on 15-5-15.
 */
public class Emojis {
    /** emoji键值对 **/
    public static final HashMap<String, String> EMOJIS = new HashMap<String, String>();
    /** emoji图片 **/
    public static final HashMap<String, Bitmap> EMOJI_BITMAPS = new HashMap<String, Bitmap>();
    /** emoji缩放图片 **/
    public static final HashMap<String, Bitmap> EMOJI_BITMAPS_SCALED = new HashMap<String, Bitmap>();


    static {

        EMOJIS.put("[挖鼻屎]", "kbsa_org.png");

        EMOJIS.put("[泪]", "sada_org.png");

        EMOJIS.put("[亲亲]", "qq_org.png");

        EMOJIS.put("[晕]", "dizzya_org.png");

        EMOJIS.put("[可爱]", "tza_org.png");

        EMOJIS.put("[花心]", "hsa_org.png");

        EMOJIS.put("[汗]", "han.png");

        EMOJIS.put("[衰]", "cry.png");

        EMOJIS.put("[偷笑]", "heia_org.png");

        EMOJIS.put("[打哈欠]", "k_org.png");

        EMOJIS.put("[睡觉]", "sleepa_org.png");

        EMOJIS.put("[哼]", "hatea_org.png");

        EMOJIS.put("[可怜]", "kl_org.png");

        EMOJIS.put("[右哼哼]", "yhh_org.png");

        EMOJIS.put("[酷]", "cool_org.png");

        EMOJIS.put("[生病]", "sb_org.png");

        EMOJIS.put("[馋嘴]", "cza_org.png");

        EMOJIS.put("[害羞]", "shamea_org.png");

        EMOJIS.put("[怒]", "angrya_org.png");

        EMOJIS.put("[闭嘴]", "bz_org.png");

        EMOJIS.put("[钱]", "money_org.png");

        EMOJIS.put("[嘻嘻]", "tootha_org.png");

        EMOJIS.put("[左哼哼]", "zhh_org.png");

        EMOJIS.put("[委屈]", "wq_org.png");

        EMOJIS.put("[鄙视]", "bs2_org.png");

        EMOJIS.put("[吃惊]", "cj_org.png");

        EMOJIS.put("[吐]", "t_org.png");

        EMOJIS.put("[懒得理你]", "ldln_org.png");

        EMOJIS.put("[思考]", "sk_org.png");

        EMOJIS.put("[怒骂]", "nm_org.png");

        EMOJIS.put("[哈哈]", "laugh.png");

        EMOJIS.put("[抓狂]", "crazya_org.png");

        EMOJIS.put("[抱抱]", "bba_org.png");

        EMOJIS.put("[爱你]", "lovea_org.png");

        EMOJIS.put("[鼓掌]", "gza_org.png");

        EMOJIS.put("[悲伤]", "bs_org.png");

        EMOJIS.put("[嘘]", "x_org.png");

        EMOJIS.put("[呵呵]", "smilea_org.png");

        EMOJIS.put("[感冒]", "gm.png");

        EMOJIS.put("[黑线]", "hx.png");

        EMOJIS.put("[愤怒]", "face335.png");

        EMOJIS.put("[失望]", "face032.png");

        EMOJIS.put("[做鬼脸]", "face290.png");

        EMOJIS.put("[阴险]", "face105.png");

        EMOJIS.put("[困]", "face059.png");

        EMOJIS.put("[拜拜]", "face062.png");

        EMOJIS.put("[疑问]", "face055.png");

        EMOJIS.put("[赞]", "face329.png");

        EMOJIS.put("[心]", "hearta_org.png");

        EMOJIS.put("[伤心]", "unheart.png");

        EMOJIS.put("[囧]", "j_org.png");

        EMOJIS.put("[奥特曼]", "otm_org.png");

        EMOJIS.put("[蜡烛]", "lazu_org.png");

        EMOJIS.put("[蛋糕]", "cake.png");

        EMOJIS.put("[弱]", "sad_org.png");

        EMOJIS.put("[ok]", "ok_org.png");

        EMOJIS.put("[威武]", "vw_org.png");

        EMOJIS.put("[猪头]", "face281.png");

        EMOJIS.put("[月亮]", "face18.png");

        EMOJIS.put("[浮云]", "face229.png");

        EMOJIS.put("[咖啡]", "face74.png");

        EMOJIS.put("[爱心传递]", "face221.png");

        EMOJIS.put("[来]", "face277.png");

        EMOJIS.put("[熊猫]", "face002.png");

        EMOJIS.put("[帅]", "face94.png");

        EMOJIS.put("[不要]", "face274.png");

        EMOJIS.put("[笑哈哈]", "lxh_xiaohaha.png");

        EMOJIS.put("[好爱哦]", "lxh_haoaio.png");

        EMOJIS.put("[噢耶]", "lxh_oye.png");

        EMOJIS.put("[偷乐]", "lxh_toule.png");

        EMOJIS.put("[泪流满面]", "lxh_leiliumanmian.png");

        EMOJIS.put("[巨汗]", "lxh_juhan.png");

        EMOJIS.put("[抠鼻屎]", "lxh_koubishi.png");

        EMOJIS.put("[求关注]", "lxh_qiuguanzhu.png");

        EMOJIS.put("[好喜欢]", "lxh_haoxihuan.png");

        EMOJIS.put("[崩溃]", "lxh_bengkui.png");

        EMOJIS.put("[好囧]", "lxh_haojiong.png");

        EMOJIS.put("[震惊]", "lxh_zhenjing.png");

        EMOJIS.put("[别烦我]", "lxh_biefanwo.png");

        EMOJIS.put("[不好意思]", "lxh_buhaoyisi.png");

        EMOJIS.put("[羞嗒嗒]", "lxh_xiudada.png");

        EMOJIS.put("[得意地笑]", "lxh_deyidexiao.png");

        EMOJIS.put("[纠结]", "lxh_jiujie.png");

        EMOJIS.put("[给劲]", "lxh_feijin.png");

        EMOJIS.put("[悲催]", "lxh_beicui.png");

        EMOJIS.put("[甩甩手]", "lxh_shuaishuaishou.png");

        EMOJIS.put("[好棒]", "lxh_haobang.png");

        EMOJIS.put("[瞧瞧]", "lxh_qiaoqiao.png");

        EMOJIS.put("[不想上班]", "lxh_buxiangshangban.png");

        EMOJIS.put("[困死了]", "lxh_kunsile.png");

        EMOJIS.put("[许愿]", "lxh_xuyuan.png");

        EMOJIS.put("[丘比特]", "lxh_qiubite.png");

        EMOJIS.put("[有鸭梨]", "lxh_youyali.png");

        EMOJIS.put("[想一想]", "lxh_xiangyixiang.png");

        EMOJIS.put("[躁狂症]", "lxh_kuangzaozheng.png");

        EMOJIS.put("[转发]", "lxh_zhuanfa.png");

        EMOJIS.put("[互相膜拜]", "lxh_xianghumobai.png");

        EMOJIS.put("[雷锋]", "lxh_leifeng.png");

        EMOJIS.put("[杰克逊]", "lxh_jiekexun.png");

        EMOJIS.put("[玫瑰]", "lxh_meigui.png");

        EMOJIS.put("[hold住]", "lxh_holdzhu.png");

        EMOJIS.put("[群体围观]", "lxh_quntiweiguan.png");

        EMOJIS.put("[推荐]", "lxh_tuijian.png");

        EMOJIS.put("[赞啊]", "lxh_zana.png");

        EMOJIS.put("[被电]", "lxh_beidian.png");

        EMOJIS.put("[霹雳]", "lxh_pili.png");

        EMOJIS.put("[doge]", "doge_org.gif");

        EMOJIS.put("[喵喵]", "mm_org.gif");

        EMOJIS.put("[笑cry]", "xiaoku_org.gif");

    }

    public static void init(Context context) {

        int fontHeight = Utility.getFontHeight (context, 16f);

        AssetManager am = context.getAssets();

        for (String key : EMOJIS.keySet()) {

            try {

                Bitmap bitmap = BitmapFactory.decodeStream (am.open (EMOJIS.get (key)));

                EMOJI_BITMAPS.put(key, bitmap);

                // 根据字体大小缩放

                Matrix matrix = new Matrix();

                matrix.postScale((float) fontHeight / bitmap.getWidth(), (float) fontHeight / bitmap.getHeight());

                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                EMOJI_BITMAPS_SCALED.put(key, bitmap);

            } catch (Exception e) {
                e.printStackTrace ();
            }
        }


    }


}
