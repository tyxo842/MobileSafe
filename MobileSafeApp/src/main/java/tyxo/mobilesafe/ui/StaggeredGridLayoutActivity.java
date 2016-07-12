package tyxo.mobilesafe.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tyxo.mobilesafe.R;
import tyxo.mobilesafe.adpter.StaggeredHomeAdapter;
import tyxo.mobilesafe.base.BaseActivityToolbar;
import tyxo.mobilesafe.utils.ToastUtil;

public class StaggeredGridLayoutActivity extends BaseActivityToolbar {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredHomeAdapter mStaggeredHomeAdapter;
    private SwipeRefreshLayout swipeRL_recyclerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);

        initEvent();
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        mToolbarTitle.setText("recyclerView");
        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        swipeRL_recyclerActivity = (SwipeRefreshLayout)findViewById(R.id.swipeRL_recyclerActivity);
        mStaggeredHomeAdapter = new StaggeredHomeAdapter(this, mDatas);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mStaggeredHomeAdapter);
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initEvent() {
        mStaggeredHomeAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.showToastS(getApplicationContext(), position + " click");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtil.showToastS(getApplicationContext(), position + " long click");
                /*mDatas.remove(position);
                mStaggeredHomeAdapter.notifyItemRemoved(position);*/
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_staggered, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mStaggeredHomeAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                mStaggeredHomeAdapter.removeData(1);
                break;
        }
        return true;
    }
}
