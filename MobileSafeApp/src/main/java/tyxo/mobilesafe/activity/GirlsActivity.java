package tyxo.mobilesafe.activity;

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
    protected void initData() {
        super.initData();

        requestNet();
    }

    @Override
    protected void handleData(BeanGirls bean) {
         /*if(bean.getData()!=null && bean.getData().size() > 0){
            HLog.i("tyxo", " response size(): " + bean.getData().size());
        }else {
            HLog.i("tyxo", " size<=0 返回信息: " + ConstValues.SERVER_RESPONSE_EMPTY);
        }*/
        HLog.i("tyxo","返回 BeanGirls : "+bean.toString());
    }

    @Override
    protected void requestNet() {
        super.requestNet();
        taskHelp.getGirls(this,10,1,0,callback);
    }
}



















