package tyxo.mobilesafe.activity.activityGrid;

import android.view.View;

import tyxo.mobilesafe.R;
import tyxo.mobilesafe.base.BaseActivityToolbar;
import tyxo.mobilesafe.utils.ToastUtil;
import tyxo.mobilesafe.widget.snakemenu.TumblrRelativeLayout;

/**
 * Created by LY on 2016/7/29 16: 04.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class Aciticity6 extends BaseActivityToolbar{

    private View.OnClickListener menuClickListener; //menu 点击事件

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.activity_gride6_snake);
        mToolbarTitle.setText("snake");
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);

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
