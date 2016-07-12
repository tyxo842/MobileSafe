package tyxo.mobilesafe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;

import tyxo.mobilesafe.adpter.AdapterMainRecycler;
import tyxo.mobilesafe.ui.ImageViewerActivity;
import tyxo.mobilesafe.ui.StaggeredGridLayoutActivity;
import tyxo.mobilesafe.utils.ToastUtil;
import tyxo.mobilesafe.utils.log.HLog;
import tyxo.mobilesafe.widget.DividerItemDecoration;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;      // 左侧布局
    private ImageView iv_left_header1;          // 左侧头部 图像
    private String url;                         // webView 加载的url
    private View header;
    private TextView textView_title_left;

    //不用notifyDataSetChanged,而是notifyItemInserted(position)与notifyItemRemoved(position),否则没动画效果
    private RecyclerView mRecyclerView;     // 主界面(右) recyclerView
    private TextView tv_main_up_recycler_1; // 主界面(右) 跳转recyclerView按钮
    private AdapterMainRecycler mAdapter;
    private ArrayList<String> mDatas;
    private SwipeRefreshLayout swipeRL_recyclerActivity;
    private LinearLayoutManager mLayoutManager;
    private int lastVisibleItem;
    private MyHandler handler;

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

    protected void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.left_open, R.string.left_close);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        header = navigationView.getHeaderView(0);
        textView_title_left = (TextView) header.findViewById(R.id.textView_title_left);
        iv_left_header1 = (ImageView) header.findViewById(R.id.iv_left_header1);

        tv_main_up_recycler_1 = (TextView) findViewById(R.id.tv_main_up_recycler_1);
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_main_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));  // 设置布局管理器
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));//4列,竖着滑动,分割线要配合DividerGridItemDecoration使用
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));//4行,横着滑动

        swipeRL_recyclerActivity = (SwipeRefreshLayout)findViewById(R.id.swipeRL_recyclerActivity);
    }

    protected void initListener() {
        fab.setOnClickListener(this);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        iv_left_header1.setOnClickListener(this);
        textView_title_left.setOnClickListener(this);

        tv_main_up_recycler_1.setOnClickListener(this);
        /*mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 得到第一个item
                int firstVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem == 0) {    // 当前可见item的第一个是否是列表的第一个 如果是第一个应用显示
                    if (!isshow) {              // 如果此时不在现实中 得显示
                        isshow = true;
                    }
                } else {                        // 不是第一个
                    if (disy > 25 && isshow) {  // 滑动距离大于25且显示状态 向下 就隐藏
                        isshow = false;
                        hideToolbar();
                        disy = 0;               // 归零
                    }
                    if (disy<-25 && ! isshow) { // 向上滑动且距离大于25且隐藏状态 就显示
                        isshow = true;
                        showToolbar();
                        disy = 0;               // 归零
                    }
                }
                if ((isshow && dy >0)||(!isshow && dy <0)) { // 增加滑动的距离 只有再触发两种状态的时候才进行叠加
                    disy += dy;
                }
            }
        });*/

//        swipeRL_recyclerActivity.setColorScheme(); // 设置 进度条的颜色变化，最多可以设置4种颜色
        swipeRL_recyclerActivity.setOnRefreshListener(this);
        // 第一次进入页面的时候显示加载进度条 ,调整进度条距离屏幕顶部的距离
        swipeRL_recyclerActivity.setProgressViewOffset(false,0,(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem +1 ==mAdapter.getItemCount()) {
                    swipeRL_recyclerActivity.setRefreshing(true);

                    /* 此处换成网络请求  上拉加载
                    ......
                    * */
                    Message msg = new Message();
                    msg.what = 0;
                    handler.sendMessageDelayed(msg, 1000);
                    ToastUtil.showToastS(getApplicationContext(),"上拉加载");

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

     protected void initData() {
        url = "http://b164.photo.store.qq.com/psb?/V11IXfXu1OApUM/bRbBm8FNRXVXb*BGLmN4IM2UtDkHFiAuLRcuGcv7RRQ!/b/dL54w2GxAQAA&bo=IANYAgAAAAABAF4!&rf=viewer_4";
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
//            mDatas.add(""+i);
            mDatas.add(""+(char)i);
        }
        mAdapter = new AdapterMainRecycler(getApplicationContext(),mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));//list的分割线
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));//grid的分割线
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置item动画

         handler = new MyHandler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_up_recycler_1:
            {
                Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.iv_left_header1:
                Intent intent = new Intent(this, ImageViewerActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);

//                drawer.closeDrawer(GravityCompat.START); // 收起 侧拉
                break;
            case R.id.textView_title_left:
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

    /** recyclerView 上拉下拉 监听*/
    @Override
    public void onRefresh() {
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessageDelayed(msg, 1000);
        ToastUtil.showToastS(getBaseContext(),"下拉刷新");
    }

    /** 加载图片 */
    private SimpleTarget target = new SimpleTarget() {
        @Override
        public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
            //图片加载完成
            iv_left_header1.setImageBitmap((Bitmap) resource);//第一/二处会报: java.lang.ClassCastException: com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable cannot be cast to android.graphics.Bitmap
        }
    };

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    swipeRL_recyclerActivity.setRefreshing(false);
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }






    /*// 隐藏toolbar
    private void hideToolbar(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mToolbar, View.TRANSLATION_Y, 0,-mToolbar.getHeight());
        animator.setDuration(500);
        animator.start();
    }
    // 显示 toolbar
    private void showToolbar(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mToolbar, View.TRANSLATION_Y, -mToolbar.getHeight(),0);
        animator.setDuration(500);
        animator.start();
    }*/

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
        switch (id) {
            case R.id.action_delete:
                mAdapter.removeData(0);
                break;
            case R.id.action_new:
                mAdapter.addData(0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
