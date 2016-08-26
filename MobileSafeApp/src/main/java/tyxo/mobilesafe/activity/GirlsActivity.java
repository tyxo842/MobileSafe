package tyxo.mobilesafe.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import tyxo.mobilesafe.R;
import tyxo.mobilesafe.adpter.BaseStaggeredAdapter;
import tyxo.mobilesafe.base.BaseActivityToolbar;

/**
 * Created by LY on 2016/8/25 10: 38.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class GirlsActivity extends BaseActivityToolbar{

    private RecyclerView id_recyclerview;

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.activity_single_recyclerview);
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        id_recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);

        mToolbarTitle.setText("");
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void initData() {
        super.initData();
    }














    private class GirlsAdapter extends BaseStaggeredAdapter{

        public GirlsAdapter(Context context, ArrayList datas, int layoutItemId) {
            super(context, datas, layoutItemId);
        }

        @Override
        protected MyViewHolder getViewHolder(View itemView) {
            MyViewHolder holder = new MyViewHolder(itemView);
            return holder;
        }

        @Override
        protected void initItemView(RecyclerView.ViewHolder holder, Object bean) {

        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }
    }
}



















