package tyxo.mobilesafe.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by LY on 2016/7/21 18: 11.
 * Mail      1577441454@qq.com
 * Describe : 为了设置 多个跑马灯 在同一个界面
 */
public class FocusableTextView extends TextView {
    public FocusableTextView(Context context) {
        super(context);
    }

    public FocusableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setSingleLine();
        setFocusable(true);
        setFocusableInTouchMode(true);
        // 切记如果通过代码实现循环。那么把值设置成-1
        setMarqueeRepeatLimit(-1);

    }

    public FocusableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}




























