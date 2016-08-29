package tyxo.mobilesafe.activity;

import android.view.View;

import com.google.gson.Gson;

import tyxo.mobilesafe.base.mybase.BaseRecyclerActivity;
import tyxo.mobilesafe.bean.BeanGirls;
import tyxo.mobilesafe.utils.log.HLog;

/**
 * Created by LY on 2016/8/25 10: 38.
 * Mail      1577441454@qq.com
 * Describe :
 */
public class GirlsActivity extends BaseRecyclerActivity<BeanGirls>{

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        mToolbarTitle.setText("Girls");
    }

    @Override
    protected void initData() {
        super.initData();

        requestNet();
    }

    @Override
    protected void handleData(String resp, Class<BeanGirls> clazzH) {
        //Type type = new TypeToken<BeanGirls>() {}.getType();
        //BeanGirls bean = new Gson().fromJson(response.toString(), type);
        clazzH = BeanGirls.class;
        BeanGirls bean = new Gson().fromJson(resp, clazzH);
        bean.getShowapi_res_body();
        /*if(bean.getData()!=null && bean.getData().size() > 0){
            HLog.i("tyxo", " response size(): " + bean.getData().size());
        }else {
            HLog.i("tyxo", " size<=0 返回信息: " + ConstValues.SERVER_RESPONSE_EMPTY);
        }*/

        HLog.i("tyxo","BeanGirls 返回 BeanGirls : "+bean.toString());
    }

    @Override
    protected void requestNet() {
        super.requestNet();
        taskHelp.getGirls(this,10,1,0,callback);
    }
}



















