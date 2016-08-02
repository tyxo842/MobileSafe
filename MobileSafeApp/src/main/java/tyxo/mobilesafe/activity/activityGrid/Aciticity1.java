package tyxo.mobilesafe.activity.activityGrid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import tyxo.mobilesafe.R;
import tyxo.mobilesafe.utils.ViewUtil;

/**
 * Created by LY on 2016/7/29 16: 04.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class Aciticity1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtil.setStateBar(this,R.color.darkSlateBlue);  /** 设置状态栏颜色 此activity通过样式设置的全屏 此处代码设置会遮挡,在布局加fitsys*/
        setContentView(R.layout.activity_recycler);
        ViewUtil.setStateBarHeight(this,(ViewGroup)findViewById(R.id.act_recycler_rl));/** 获取状态栏高度,并重绘上*/
    }
}
