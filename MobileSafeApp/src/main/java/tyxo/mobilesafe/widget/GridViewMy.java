package tyxo.mobilesafe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by LY on 2016/7/21 16: 50.
 * Mail      1577441454@qq.com
 * Describe : 重绘,否则添加头布局,会显示不全.
 */
public class GridViewMy extends GridView{

    public GridViewMy(Context context) {
        super(context);
    }

    public GridViewMy(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewMy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
