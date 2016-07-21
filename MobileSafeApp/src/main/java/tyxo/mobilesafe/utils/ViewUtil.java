package tyxo.mobilesafe.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;

/**
 * Created by LY on 2016/7/21 11: 14.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class ViewUtil {
    /**
     * @author tyxo
     * @created at 2016/7/21 10:21
     * @des : 设置 SpannableString 的样式
     *  setSpan(Object what, int start, int end, int flags)方法 参数:
     *      what : 表示设置的格式是什么
     *      start: 表示需要设置格式的子字符串起始下标
     *      end  : 表示终了下标
     *      flags: 属性,共有四种
     *          Spanned.SPAN_INCLUSIVE_EXCLUSIVE 从起始下标到终了下标，包括起始下标
     *          Spanned.SPAN_INCLUSIVE_INCLUSIVE 从起始下标到终了下标，同时包括起始下标和终了下标
     *          Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 从起始下标到终了下标，但都不包括起始下标和终了下标
     *          Spanned.SPAN_EXCLUSIVE_INCLUSIVE 从起始下标到终了下标，包括终了下标
     */
    public static void setSpannableStringStyle(Context context,SpannableString ss) {
        /*
        //字体颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099ee"));
        //ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.white));
        ss.setSpan(colorSpan,9,ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        /*
        //背景颜色
        BackgroundColorSpan colorSpan = new BackgroundColorSpan(Color.parseColor("#ac00ff30"));
        ss.setSpan(colorSpan,9,ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        /*
        //字体大小不一
        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.2f);
        RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan sizeSpan03 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan04 = new RelativeSizeSpan(1.8f);
        RelativeSizeSpan sizeSpan05 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan06 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan sizeSpan07 = new RelativeSizeSpan(1.2f);
        ss.setSpan(sizeSpan01,0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(sizeSpan02,1,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(sizeSpan03,2,3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(sizeSpan04,3,4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(sizeSpan05,4,5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(sizeSpan06,5,6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(sizeSpan07,6,7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        /*
        //设置中划线
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        ss.setSpan(strikethroughSpan,5,ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        /*
        //设置下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        ss.setSpan(underlineSpan,5,ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        /*
        //设置上标
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        ss.setSpan(superscriptSpan,5,8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        /*
        //设置下标
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        ss.setSpan(subscriptSpan,5,8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        /*
        //设置粗体,斜体
        StyleSpan styleSpan01 = new StyleSpan(Typeface.BOLD);//粗体
        StyleSpan styleSpan02 = new StyleSpan(Typeface.ITALIC);//斜体
        ss.setSpan(styleSpan01,5,8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(styleSpan02,9,11, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        main_up_tv_1.setHighlightColor(Color.parseColor("#36969696"));//?*/
        /*
        //设置表情
        Drawable drawable = context.getResources().getDrawable(R.drawable.trade_person);
        drawable.setBounds(0,0,42,42);
        ImageSpan imageSpan = new ImageSpan(drawable);
        ss.setSpan(imageSpan,6,8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        /*
        //可点击的富文本
        ClickableSpanMy clickableSpanMy = new ClickableSpanMy(context, "www.baidu.com");
        ss.setSpan(clickableSpanMy,5,ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);*/
        //设置超链接文本,其实此类继承clickableSpan
        URLSpan urlSpan = new URLSpan("http://www.baidu.com");
        ss.setSpan(urlSpan,5,ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        /*除此之外，还有MaskFilterSpan可以实现模糊和浮雕效果，
        RasterizerSpan可以实现光栅效果，因为以上两个使用频率不高，而且效果也不是很明显*/
    }
}




























