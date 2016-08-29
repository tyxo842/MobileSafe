package tyxo.mobilesafe.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import tyxo.mobilesafe.ConstValues;
import tyxo.mobilesafe.R;
import tyxo.mobilesafe.base.mybase.BaseRecyclerActivity;
import tyxo.mobilesafe.base.mybase.BaseRecyclerStaggeredAdapter;
import tyxo.mobilesafe.bean.BeanGirls;
import tyxo.mobilesafe.utils.ToastUtil;
import tyxo.mobilesafe.utils.log.HLog;
import tyxo.mobilesafe.widget.recyclerdivider.recyclerbase.RatioImageView;

/**
 * Created by LY on 2016/8/25 10: 38.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class GirlsActivity extends BaseRecyclerActivity<BeanGirls>{

    private ArrayList<BeanGirls.ShowapiResBodyBean.NewslistBean> beanList; // 返回数据集合
    private GirlsAdapterMy mAdapter;

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        mToolbarTitle.setText("Girls");
    }

    @Override
    protected void initData() {
        super.initData();
        beanList = new ArrayList<>();

        requestNet();
        mAdapter = new GirlsAdapterMy(this, beanList, R.layout.base_recycler_item);
        mAdapter.setOnItemClickLitener(itemClickLitener);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();

    }

    /** 处理返回的数据 */
    @Override
    protected void handleData(String resp, Class<BeanGirls> clazzH) {
        //Type type = new TypeToken<BeanGirls>() {}.getType();
        //BeanGirls bean = new Gson().fromJson(response.toString(), type);
        clazzH = BeanGirls.class;
        BeanGirls bean = new Gson().fromJson(resp, clazzH);
        BeanGirls.ShowapiResBodyBean beanBody = bean.getShowapi_res_body();

        if(beanBody.getNewslist()!=null && beanBody.getNewslist().size() > 0){
            HLog.i("tyxo", "BeanGirls response size(): " + beanBody.getNewslist().size());

            beanList = (ArrayList<BeanGirls.ShowapiResBodyBean.NewslistBean>) bean.getShowapi_res_body().getNewslist();

        }else {
            HLog.i("tyxo", "BeanGirls size<=0 返回信息: " + ConstValues.SERVER_RESPONSE_EMPTY);
        }
    }

    BaseRecyclerStaggeredAdapter.OnItemClickLitener itemClickLitener = new BaseRecyclerStaggeredAdapter.OnItemClickLitener() {
        @Override
        public void onItemClick(View view, Object bean, int position) {
            ToastUtil.showToastS(GirlsActivity.this,"条目$点击了");
        }

        @Override
        public void onItemLongClick(View view, Object bean, int position) {

        }
    };

    @Override
    protected void requestNet() {
        super.requestNet();
        taskHelp.getGirls(this,pageSize,pageIndex,rand,callback);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        requestNet();
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        pageIndex++;
        requestNet();
    }

    class GirlsAdapterMy extends BaseRecyclerStaggeredAdapter <GirlsAdapterMy.MyHolder,BeanGirls.ShowapiResBodyBean.NewslistBean>{

        public GirlsAdapterMy(Context context, ArrayList datas, int layoutItemId) {
            super(context, datas, layoutItemId);
        }

        @Override
        protected MyHolder getViewHolder(View itemView) {
            return new MyHolder(itemView);
        }

        @Override
        protected void initItemView(MyHolder holder, BeanGirls.ShowapiResBodyBean.NewslistBean bean,int position) {
            holder.itemView.setTag(position);
            Glide.with(GirlsActivity.this)
                    .load(bean.getPicUrl())
                    .centerCrop()
                    .placeholder(R.color.global_background)
                    .into(holder.mGirlImage);
        }

        class MyHolder extends RecyclerView.ViewHolder{
            RatioImageView mGirlImage;
            public MyHolder(View itemView) {
                super(itemView);
                mGirlImage = (RatioImageView) itemView.findViewById(R.id.girl_image_item);
                mGirlImage.setRatio(0.618f);// set the ratio to golden ratio.
            }
        }
    }
}



















