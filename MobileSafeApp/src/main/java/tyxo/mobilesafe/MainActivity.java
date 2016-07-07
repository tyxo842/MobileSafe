package tyxo.mobilesafe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import tyxo.mobilesafe.ui.ImageViewerActivity;
import tyxo.mobilesafe.utils.log.HLog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;      // 左侧布局
    private ImageView iv_left_header1;          // 左侧头部 图像
    private String url;                         // webView 加载的url
    private View header;
    private TextView textView_title_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();         // 初始化View
        initListener();     // 初始化监听
        initData();         // 初始化数据

    }

    private void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.left_open, R.string.left_close);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        header = navigationView.getHeaderView(0);
        textView_title_left = (TextView) header.findViewById(R.id.textView_title_left);
        iv_left_header1 = (ImageView) header.findViewById(R.id.iv_left_header1);
    }

    private void initListener() {
        fab.setOnClickListener(this);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        iv_left_header1.setOnClickListener(this);
        textView_title_left.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.iv_left_header1:
                url = "http://b164.photo.store.qq.com/psb?/V11IXfXu1OApUM/bRbBm8FNRXVXb*BGLmN4IM2UtDkHFiAuLRcuGcv7RRQ!/b/dL54w2GxAQAA&bo=IANYAgAAAAABAF4!&rf=viewer_4";
                Intent intent = new Intent(this, ImageViewerActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);

//                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.textView_title_left:
                url = "http://b164.photo.store.qq.com/psb?/V11IXfXu1OApUM/bRbBm8FNRXVXb*BGLmN4IM2UtDkHFiAuLRcuGcv7RRQ!/b/dL54w2GxAQAA&bo=IANYAgAAAAABAF4!&rf=viewer_4";
                Glide.with( this )
                        .load( url )
                        .asBitmap()
                        .placeholder(R.drawable.loading) //占位符 也就是加载中的图片，可放个gif
                        .error(R.drawable.icon_zanwu) //失败图片
                        .into( target ) ;
                HLog.i("tyxo","小标题 点击 ... url : "+url);
                break;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } /*else if (id == R.id.iv_left_header1) {
            ToastUtil.showToastS(getApplicationContext(),"点击了header图形");

            Intent intent = new Intent(this, ImageViewerActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }*/

        /*switch (id) {
            case R.id.fab:
                break;
            case R.id.imageview:

                break;
        }*/

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** 加载图片 */
    private SimpleTarget target = new SimpleTarget() {
        @Override
        public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
            //图片加载完成
            iv_left_header1.setImageBitmap((Bitmap) resource);//第一/二处会报: java.lang.ClassCastException: com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable cannot be cast to android.graphics.Bitmap
        }
    };

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            ToastUtil.showToastS();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
