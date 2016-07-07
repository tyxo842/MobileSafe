package tyxo.mobilesafe.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import tyxo.mobilesafe.R;
import tyxo.mobilesafe.base.BaseActivityToolbar;
import tyxo.mobilesafe.utils.log.HLog;
import tyxo.mobilesafe.widget.TouchImageView;

public class ImageViewerActivity extends BaseActivityToolbar implements RequestListener<String,
        GlideDrawable>, View.OnClickListener ,View.OnLongClickListener {

    private String url;
    private TouchImageView image;       // 图片显示
    private TextView tv_iv_layout_1;    // 点击切换
    private ImageView iv_iv_layout_1;   // 图片显示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iv_layout);

        url = getIntent().getStringExtra("url");
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        image = (TouchImageView) contentView.findViewById(R.id.picture);
        tv_iv_layout_1 = (TextView) contentView.findViewById(R.id.tv_iv_layout_1);
        iv_iv_layout_1 = (ImageView) contentView.findViewById(R.id.iv_iv_layout_1);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_iv_layout_1.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        MenuItem itemCompat = menu.findItem(R.id.action_search) ;
//        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(itemCompat);
//        mSearchView.setIconified(false);
//        mSearchView.setIconifiedByDefault(false);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*@Override
    public void onCreateCustomToolbar(Toolbar toolbar) {
        super.onCreateCustomToolbar(toolbar);
        toolbar.showOverflowMenu();
//        getLayoutInflater().inflate(R.layout.toolbar_demo_layout, toolbar);
        getLayoutInflater().inflate(R.layout.layout_toolbar, toolbar);
        TextView tv_toolbar_title = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tv_toolbar_title.setText("点击切换 测试");
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_iv_layout_1:
                HLog.i("tyxo","url 1 : "+url);
                Glide.with( this )
                        .load( url )
                        .asBitmap()
                        .placeholder(R.drawable.loading) //占位符 也就是加载中的图片，可放个gif
                        .error(R.drawable.icon_zanwu) //失败图片
                        .into( target ) ;
                HLog.i("tyxo","url 2 : "+url);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        builder.show();*/

        return true;
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

    /*private class SaveImageTask extends AsyncTask<Bitmap, Void, String> {
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
                Bitmap image = params[0];
                image.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
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
    }*/

    /*@Override
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
    }*/
}
