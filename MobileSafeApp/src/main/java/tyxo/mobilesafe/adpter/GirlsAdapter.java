package tyxo.mobilesafe.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tyxo.mobilesafe.base.mybase.BaseRecyclerStaggeredAdapter;

/**
 * Created by LY on 2016/8/26 13: 34.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class GirlsAdapter extends BaseRecyclerStaggeredAdapter<GirlsAdapter.MyViewHolder,Object> {

    protected List<Integer> mHeights;                 //随机item的高度

    public GirlsAdapter(Context context, ArrayList datas, int layoutItemId) {
        super(context, datas, layoutItemId);
        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    protected MyViewHolder getViewHolder(View itemView) {
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    protected void initItemView(MyViewHolder holder, Object bean) {

        int position = holder.getLayoutPosition();

        // 设置随机高度
        ViewGroup.LayoutParams lp = holder.item.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.item.setLayoutParams(lp);

        /** 设置item的 view内容数据 */

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View item;

        public MyViewHolder(View view) {
            super(view);
        }
    }
}
