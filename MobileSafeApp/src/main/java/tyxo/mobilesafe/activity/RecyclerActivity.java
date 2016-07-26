package tyxo.mobilesafe.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tyxo.mobilesafe.R;
import tyxo.mobilesafe.adpter.AdapterRecyclerHeader;
import tyxo.mobilesafe.base.BaseActivityToolbar;

/**
 * Created by LY on 2016/7/26 10: 49.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class RecyclerActivity extends BaseActivityToolbar {

    private RecyclerView mRecyclerview;
    private LinearLayoutManager mLayoutManager;
    private AdapterRecyclerHeader mAdapter;

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.activity_recycler);
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        mRecyclerview = (RecyclerView) findViewById(R.id.activity_recycler_recyclerview);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new AdapterRecyclerHeader();
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.addDatas(getAdapterData());
        setHeader();
    }

    public void setHeader() {
        View header = LayoutInflater.from(this).inflate(R.layout.layout_recycler_header_gridview,mRecyclerview,false);
        mAdapter.setHeaderView(header);
    }

    @Override
    public void onCreateCustomToolbar(Toolbar toolbar) {
        super.onCreateCustomToolbar(toolbar);
        TextView tv_toolbar_title = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tv_toolbar_title.setText("RecyclerActivity");
    }

    //设置传入 adapter 内的数据
    private List<String> getAdapterData() {
        List<String> list = new ArrayList<>();
        for (int i=0;i<10;i++) {
            list.add(i,"条目 "+i);
        }
        return list;
    }


}


























