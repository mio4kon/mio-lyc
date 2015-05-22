package mio.kon.lyc.model;


import java.util.ArrayList;

/**
 * Created by mio on 15-5-14.
 * 单条微博状态信息
 */
public class WeiboInfo {
    /** Thu May 14 16:28:16 +0800 2015 **/
    public String created_at;
    public String idstr;
    public String text;
    /** 微博来源 **/
    public String source;
    /** 是否已收藏 **/
    public String favorited;
    /** 是否被斩断 **/
    public String truncated;
    /** 微博作者的!用户信息!字段 **/
    public UserInfo user;
    /** 被转发的原!微博信息!字段 **/
    public WeiboInfo retweeted_status;
    /** 转发数 **/
    public int reposts_count;
    /** 评论数 **/
    public int comments_count;
    /** 表态数 **/
    public int attitudes_count;
    /** 缩略图的URLS,但也可从中获取大图地址 **/
    public ArrayList<PictureUrl> pic_urls ;

    public class PictureUrl {

        public String thumbnail_pic;

        /** 获取原图 **/
        public String getLarge(){
            return thumbnail_pic.replace("thumbnail", "large");
        }
        /** 获取中等图 **/
        public String getMiddle(){
            return thumbnail_pic.replace("thumbnail", "bmiddle");
        }
    }



}
