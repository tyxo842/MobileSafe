package tyxo.mobilesafe.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

import tyxo.mobilesafe.ConstValues;
import tyxo.mobilesafe.R;
import tyxo.mobilesafe.adpter.GirlsAdapterMy;
import tyxo.mobilesafe.base.mybase.BaseRecyclerActivity;
import tyxo.mobilesafe.bean.BeanGirls;
import tyxo.mobilesafe.utils.ToastUtil;
import tyxo.mobilesafe.utils.log.HLog;
import tyxo.mobilesafe.widget.recyclerdivider.recyclerbase.HeaderViewRecyclerAdapter;
import tyxo.mobilesafe.widget.recyclerdivider.recyclerbase.LoadMoreView;

/**
 * Created by LY on 2016/8/25 10: 38.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class GirlsActivity extends BaseRecyclerActivity<BeanGirls>{

    private ArrayList<BeanGirls.ShowapiResBodyBean.NewslistBean> beanList; // 返回数据集合
    private GirlsAdapterMy mAdapter;
    private LoadMoreView mLoadMore;

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        mToolbarTitle.setText("Girls");

        mLoadMore = (LoadMoreView) LayoutInflater.from(this).inflate(R.layout.base_load_more, mRecyclerView, false);
    }

    @Override
    protected void initData() {
        super.initData();
        beanList = new ArrayList<>();

        requestNet();
        mAdapter = new GirlsAdapterMy(this, beanList, R.layout.base_recycler_item);
        mAdapter.setOnItemClickLitener(itemClickLitener);
        HeaderViewRecyclerAdapter adapter = new HeaderViewRecyclerAdapter(mAdapter);
        adapter.setLoadingView(mLoadMore);
        //mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setAdapter(adapter);
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

            ArrayList<BeanGirls.ShowapiResBodyBean.NewslistBean> resList = (ArrayList<BeanGirls.ShowapiResBodyBean.NewslistBean>) bean.getShowapi_res_body().getNewslist();
            if (isLoadMore) {
                beanList.addAll(resList);
            } else {
                beanList = resList;
            }
            mAdapter.substituteDatas(beanList);
            mAdapter.notifyDataSetChanged();
            stopLoading();

        }else {
            ToastUtil.showToastS(this,"无更多数据");
            HLog.i("tyxo", "BeanGirls size<=0 返回信息: " + ConstValues.SERVER_RESPONSE_EMPTY);
        }
    }
    @Override
    protected void handleData(BeanGirls beanB) {
        HLog.i("tyxo", "BeanGirls beanB 返回解析: " + beanB.toString());
    }

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
        mLoadMore.setStatus(LoadMoreView.STATUS_LOADING);
        requestNet();
    }

    @Override
    protected void stopLoading() {
        super.stopLoading();
    }
}



















