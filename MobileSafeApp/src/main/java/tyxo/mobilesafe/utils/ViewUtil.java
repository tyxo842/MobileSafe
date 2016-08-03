package tyxo.mobilesafe.utils;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import tyxo.mobilesafe.ConstValues;
import tyxo.mobilesafe.Constants;
import tyxo.mobilesafe.widget.SystemBarTintManager;

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

    /**
     * 设置属性动画, 参数:
     *  第一个target :      目标
     *  第二个propertyName: 动画的名称
     *  第三 values       : 可变参数,旋转的角度
     * */
    public static void setAimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotationY",0,90,270,360);
        animator.setDuration(3000);                 //设置动画时间
        animator.setRepeatCount(animator.INFINITE); //设置无限旋转
        animator.start();
    }

    /***
     * 设置状态栏颜色
     * @param context
     */
    public static void setStateBar(Activity context, int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(context,true);
            SystemBarTintManager tintManager = new SystemBarTintManager(context);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(colorId);//通知栏所需颜色
        }
    }
    @TargetApi(19)
    public static void setTranslucentStatus(Activity context, boolean on) {
        Window win = context.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置状态栏高度(对应上边方法设置颜色后,全屏显示问题)
     * @param viewGroup
     */
    public static void setStateBarHeight(Activity activity,ViewGroup viewGroup){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {
            viewGroup.setPadding(0,getStateBarHeight(activity),0,0);
        }
    }
    public static int getStateBarHeight(Activity activity){  //状态栏高度算法
        int stateHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        stateHeight = localRect.top;
        if (0==stateHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                stateHeight = activity.getResources().getDimensionPixelSize(i5);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return stateHeight;
    }

    /*
    //一步一步退回到首页之后，就不能再往后退出了。想要到首页之后，在往后退出的时候，进入主程序，关闭这个内置的webview
    public static void goBack(Context context,WebView mWebView,String url){//判断是否是刚才的url
        if (mWebView.canGoBack()) {
            if (mWebView.getUrl().contains(url)) {
                super.onBackPressed();
            } else {
                mWebView.goBack();
            }
        } else {
            super.onBackPressed();
        }
    }*/

    /** 从本地相册获取照片 */
    public static void getImageFromAlbum(Activity context){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        context.startActivityForResult(intent, Constants.CODE_REQUEST_IMAGE);
    }
    /** 从照相机获取照片 图片被压缩*/
    public static void getImageFromCamera(Activity activity){
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            activity.startActivityForResult(intent, Constants.CODE_REQUEST_CAMERA);
        } else {
            ToastUtil.showToastS(activity,"请确认已经插入SD卡");
        }
    }

    /** 从照相机获取照片 */
    public static void getImageFromCameraBig(Activity activity){
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            String out_file_path = ConstValues.SAVE_IMAGE_DIR_PATH;
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            ConstValues.SAVE_IMAGE_DIR_PATH_TEMP = ConstValues.SAVE_IMAGE_DIR_PATH +System.currentTimeMillis()+ ".jpg";
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getImageFile(ConstValues.SAVE_IMAGE_DIR_PATH_TEMP)));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);//加上这两句,onActivityResult里的data会报空
            activity.startActivityForResult(intent, Constants.CODE_REQUEST_CAMERABIG);
        } else {
            ToastUtil.showToastS(activity,"请确认已经插入SD卡");
        }
    }

    /** 保存从相机返回的图片 :通过bitmap转化成相应的图片文件了,不过得到最终的图片是被压缩了的。 */
    public static boolean saveImage(Bitmap photo, String spath){
        try{
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(spath, false));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true ;
    }

    /** 获取图片的uri */
    public static File getImageFile(String capturePath){
        File file = new File(capturePath);
        return file;
    }

}




























