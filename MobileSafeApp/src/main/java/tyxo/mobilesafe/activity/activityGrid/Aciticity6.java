package tyxo.mobilesafe.activity.activityGrid;

import android.os.Build;
import android.view.View;

import tyxo.mobilesafe.R;
import tyxo.mobilesafe.base.BaseActivity;
import tyxo.mobilesafe.utils.StatusBarUtil;
import tyxo.mobilesafe.utils.ToastUtil;
import tyxo.mobilesafe.utils.log.HLog;
import tyxo.mobilesafe.widget.snakemenu.TumblrRelativeLayout;

/**
 * Created by LY on 2016/7/29 16: 04.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class Aciticity6 extends BaseActivity {

    private View.OnClickListener menuClickListener; //menu 点击事件

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_gride6_snake);
        //setContentView(R.layout.activity_gride6);
        //设置状态栏透明
        StatusBarUtil.setTranslucentBackground(this);

        HLog.v("tyxo"," version : "+ Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
    }

    @Override
    public void initView() {

        menuClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToastS(Aciticity6.this,"menu 点击了");
            }
        };
        TumblrRelativeLayout rootLayout = (TumblrRelativeLayout) findViewById(R.id.activity6_snake_menu_trl);
        rootLayout.setMenuListener(menuClickListener);
    }
}
