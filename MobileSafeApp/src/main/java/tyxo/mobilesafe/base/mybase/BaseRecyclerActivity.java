package tyxo.mobilesafe.base.mybase;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import org.json.JSONObject;

import tyxo.mobilesafe.ConstValues;
import tyxo.mobilesafe.R;
import tyxo.mobilesafe.TaskHelp;
import tyxo.mobilesafe.base.BaseActivityToolbar;
import tyxo.mobilesafe.net.volley.VolleyCallBack;
import tyxo.mobilesafe.net.volley.VolleyErrorResult;
import tyxo.mobilesafe.utils.log.HLog;
import tyxo.mobilesafe.widget.recyclerdivider.recyclerbase.LoadMoreView;

/**
 * Created by LY on 2016/8/25 10: 38.
 * Mail      1577441454@qq.com
 * Describe : <T> 限定数据 bean 的类型,
 *            子类使用的时候: public class GirlsActivity extends BaseRecyclerActivity<BeanGirls>{....}.
 *            参考 GirlsActivity 代码.
 */
public abstract class BaseRecyclerActivity<T extends Object> extends BaseActivityToolbar implements SwipeRefreshLayout.OnRefreshListener{

    /*
    public static <T> String arrayToString(ArrayList<T> list) {
    Gson g = new Gson();
    return g.toJson(list);
    }
    public static <T> ArrayList<T> stringToArray(String s) {
    Gson g = new Gson();
    Type listType = new TypeToken<ArrayList<T>>(){}.getType();
    ArrayList<T> list = g.fromJson(s, listType);
    return list;
    }

    正确:
    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
    T[] arr = new Gson().fromJson(s, clazz);
    return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }

    public <T> List<T> deserializeList(String json) {
    Gson gson = new Gson();
    Type type = (new TypeToken<List<T>>() {}).getType();
    return  gson.fromJson(json, type);
    }

    正确:
    public <T> List<T> deserializeList(String json, Type type) {
    Gson gson = new Gson();
    return  gson.fromJson(json, type);
    }

     */

    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mRefreshLayout;
    protected LoadMoreView mLoadMore;

    protected TaskHelp taskHelp;
    protected VolleyCallBack<JSONObject> callback;
    private Class<T> clazz ;
    private GridLayoutManager mLayoutManager;
    private int lastVisibleItem;
    private T beanB;

    protected boolean isRefresh = true;            //是否是下拉刷新
    protected boolean isLoadMore = false;         //是否是上拉加载
    protected int pageSize;
    protected int pageIndex;
    protected int rand;


    @Override
    protected void setMyContentView() {
        setContentView(R.layout.base_fragment_refresh_recycler);
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_refresh);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mRefreshLayout.setOnRefreshListener(this);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(mScrollListener);

        mLoadMore = (LoadMoreView) LayoutInflater.from(this).inflate(R.layout.base_load_more, mRecyclerView, false);
    }

    @Override
    protected void initData() {
        super.initData();
        pageSize = 10;
    }

    /** 处理数据 */
    protected abstract void handleData(T beanB);
    protected abstract void handleData(String resp , Class<T> clazzH);
        /** 子类建议做如下判断, 打印size,便于调试接口,按自己情况去写 */
        //Type type = new TypeToken<BeanGirls>() {}.getType();
        //BeanGirls bean = new Gson().fromJson(response.toString(), type);
        //clazzH = BeanGirls.class;
        //BeanGirls bean = new Gson().fromJson(resp, clazzH);
        /*if(bean.getData()!=null && bean.getData().size() > 0){
            HLog.i("tyxo", " response size(): " + bean.getData().size());
        }else {
            HLog.i("tyxo", " size<=0 返回信息: " + ConstValues.SERVER_RESPONSE_EMPTY);
        }*/


    /** 网络请求 */
    protected void requestNet(){

        callback =  new VolleyCallBack<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                HLog.i("tyxo", " response : " + response.toString());
                try {
                    if (!TextUtils.isEmpty(response.toString())) {
                        /*Type type = new TypeToken<T>() {}.getType();
                        T bean = new Gson().fromJson(response.toString(), type);*/

                        handleData(response.toString(),clazz);   //处理数据
                        //handleData(bean);   //处理数据

                    } else {
                        HLog.i("tyxo", " response为空 返回信息: " + ConstValues.SERVER_RESPONSE_EMPTY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    HLog.i("tyxo", " 解析数据错误" + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyErrorResult result) {
                HLog.i("tyxo", " 请求失败: " + result.toString());
            }
        };

        taskHelp = new TaskHelp();
    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            boolean reachBottom = mLayoutManager.findLastCompletelyVisibleItemPosition()
                    >= mLayoutManager.getItemCount() - 1;
            //if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {//下拉刷新
            if(newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadMore && reachBottom) {
                isLoadMore = true;
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            boolean reachBottom = mLayoutManager.findLastCompletelyVisibleItemPosition()
                    >= mLayoutManager.getItemCount() - 1;
            if(!isLoadMore && reachBottom) {
                onLoadMore();
            }
        }
    };

    // 下拉刷新
    @Override
    public void onRefresh() {
        pageIndex = 1;
        rand = 0;
        isRefresh = true;
        isLoadMore = false;
    }

    // 加载更多
    protected void onLoadMore() {
        isLoadMore = true;
        isRefresh = false;
        mLoadMore.setStatus(LoadMoreView.STATUS_LOADING);
    }
}



















