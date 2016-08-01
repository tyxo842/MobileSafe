package tyxo.mobilesafe.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import me.leolin.shortcutbadger.ShortcutBadger;
import tyxo.mobilesafe.R;
import tyxo.mobilesafe.base.BaseActivityToolbar;
import tyxo.mobilesafe.utils.ToastUtil;
import tyxo.mobilesafe.utils.ViewUtil;
import tyxo.mobilesafe.utils.log.HLog;
import tyxo.mobilesafe.widget.AutoClearEditText;
import tyxo.mobilesafe.widget.TouchImageView;

/**
* @author ly
* @des : 配合 清单设置:
*                   android:label=""
*                   android:theme="@style/MyToolbarTheme"
*/
public class ImageViewerActivity extends BaseActivityToolbar implements RequestListener<String,
        GlideDrawable>, View.OnClickListener ,View.OnLongClickListener {

    private String url;
    private TouchImageView image;       // 图片显示
    private TextView tv_iv_layout_1;    // 点击切换
    private ImageView iv_iv_layout_1;   // 图片显示
    private AutoClearEditText numInput; // badge数字输入框
    private Button button;              // 设置按钮
    private Button removeBadgeBtn;      // 清除按钮
    private TextView tv_iv_layout_2;    // 接收 eventBus 发送的消息

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.activity_iv_layout);
        EventBus.getDefault().register(this);   //注册EventBus

        url = getIntent().getStringExtra("url");

        //find the home launcher Package
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String currentHomePackage = resolveInfo.activityInfo.packageName;

        TextView textViewHomePackage = (TextView) findViewById(R.id.imageActivity_tv_package);
        textViewHomePackage.setText("launcher:" + currentHomePackage);
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        image = (TouchImageView) contentView.findViewById(R.id.picture);
        tv_iv_layout_2 = (TextView) contentView.findViewById(R.id.tv_iv_layout_2);
        tv_iv_layout_1 = (TextView) contentView.findViewById(R.id.tv_iv_layout_1);
        iv_iv_layout_1 = (ImageView) contentView.findViewById(R.id.iv_iv_layout_1);
        ViewUtil.setAimation(iv_iv_layout_1);    //设置属性动画
        numInput = (AutoClearEditText) contentView.findViewById(R.id.imageActivity_et_numinput);
        button = (Button) contentView.findViewById(R.id.imageActivity_btn_setBadge);
        removeBadgeBtn = (Button) contentView.findViewById(R.id.imageActivity_btn_setBadge);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_iv_layout_1.setOnClickListener(this);
        button.setOnClickListener(this);
        removeBadgeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_iv_layout_1:
                HLog.i("tyxo","url 1 : "+url);
                Glide.with( this )
//                        .load( url )
                        .load( R.drawable.glide_photo )
                        .asBitmap()
                        .placeholder(R.drawable.loading) //占位符 也就是加载中的图片，可放个gif
                        .error(R.drawable.icon_zanwu) //失败图片
                        .into( target ) ;
                //Glide.with(this).load(R.drawable.ic_launceher).centerCrop().transform(new GlideRoundTransform(this)).into(iv_demo);
                HLog.i("tyxo","url 2 : "+url);
                //ToastUtil.showToastS(this,"修改自己项目里面的东东,拦截操作改为toast,先打补丁再测");
                break;
            case R.id.imageActivity_btn_setBadge:
            {
                int badgeCount = 0;
                try {
                    badgeCount = Integer.parseInt(numInput.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Error input", Toast.LENGTH_SHORT).show();
                }

                boolean success = ShortcutBadger.applyCount(this, badgeCount);
                ToastUtil.showToastS(mContext,"Set count=" + badgeCount + ", success=" + success);
                break;
            }

            case R.id.imageActivity_btn_removeBadge:
            {
                boolean success = ShortcutBadger.removeCount(this);
                ToastUtil.showToastS(mContext,"success=" + success);
                break;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(new String[]{"保存图片"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                image.setDrawingCacheEnabled(true);
                Bitmap imageBitmap = image.getDrawingCache();
                if (imageBitmap != null) {
                    new SaveImageTask().execute(imageBitmap);
                }
            }
        });
        builder.show();

        return true;
    }

    /**
     * 显示为灰色,但是已经调用了,eventBus .
     * 执行在主线程,
     * 非常实用，可以在这里将子线程加载到的数据直接设置到界面中。
     * */
    @Subscribe
    public void onEventMainThread(Object event){
        String msg = (String) event;
        tv_iv_layout_2.setText(msg);
    }

    /** 与发布者在同一个线程 */
    @Subscribe
    public void onEvent(Object event){ }

    /**
     * 执行在子线程，如果发布者是子线程则直接执行，如果发布者不是子线程，则创建一个再执行,
     * 此处可能会有线程阻塞问题。
     */
    @Subscribe
    public void onEventBackgroundThread(Object event){ }

    /**
     * 执行在在一个新的子线程,
     * 适用于多个线程任务处理， 内部有线程池管理。
     */
    @Subscribe
    public void onEventAsync(Object event){ }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.menu_imageactivity, menu);
        MenuItem itemCompat = menu.findItem(R.id.action_search) ;
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(itemCompat);
        mSearchView.setIconified(false);
        mSearchView.setIconifiedByDefault(false);*/
        getMenuInflater().inflate(R.menu.menu_imageactivity, menu);
        return true;
    }

    @Override
    public void onCreateCustomToolbar(Toolbar toolbar) {
        super.onCreateCustomToolbar(toolbar);
        TextView tv_toolbar_title = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tv_toolbar_title.setText("ImageActivity");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        switch (id) {
            case R.id.image_action_1:   //跳转到recyclerActivity
                Intent intent = new Intent(this, RecyclerActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private SimpleTarget target = new SimpleTarget() {
        @Override
        public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
            //图片加载完成
            iv_iv_layout_1.setImageBitmap((Bitmap) resource);//第一/二处会报: java.lang.ClassCastException: com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable cannot be cast to android.graphics.Bitmap
        }
    };

    @Override
    public boolean onException(Exception e, String model,
                               com.bumptech.glide.request.target.Target<GlideDrawable> target,
                               boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(GlideDrawable resource, String model,
                                   com.bumptech.glide.request.target.Target<GlideDrawable> target,
                                   boolean isFromMemoryCache, boolean isFirstResource) {
        image.setImageDrawable(resource);
        image.setOnLongClickListener(this);
        return false;
    }

    private class SaveImageTask extends AsyncTask<Bitmap, Void, String> {
        @Override
        protected String doInBackground(Bitmap... params) {
            String result = "保存失败";
            try {
                String sdcard = Environment.getExternalStorageDirectory().toString();

                File file = new File(sdcard + "/Download");
                if (!file.exists()) {
                    file.mkdirs();
                }

                File imageFile = new File(file.getAbsolutePath(), new Date().getTime() + ".jpg");
                FileOutputStream outStream = null;
                outStream = new FileOutputStream(imageFile);
                Bitmap imageBit = params[0];
                imageBit.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                outStream.flush();
                outStream.close();
                result = getResources().getString(R.string.save_picture_success, file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            ToastUtil.showToastS(getApplicationContext(), result);
            image.setDrawingCacheEnabled(false);
        }
    }

    /*@Override
    public void onCreateCustomToolbar(Toolbar toolbar) {
        super.onCreateCustomToolbar(toolbar);
        toolbar.showOverflowMenu();
        //getLayoutInflater().inflate(R.layout.toolbar_demo_layout, toolbar);
        getLayoutInflater().inflate(R.layout.layout_toolbar, toolbar);
        TextView tv_toolbar_title = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tv_toolbar_title.setText("点击切换 测试");
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade(0)
                .listener(this)
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        MobclickAgent.onResume(this);

    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); //反注册EventBus
    }
}
