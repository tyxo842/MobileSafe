package tyxo.mobilesafe.base.mybase;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import tyxo.mobilesafe.ConstValues;
import tyxo.mobilesafe.R;
import tyxo.mobilesafe.TaskHelp;
import tyxo.mobilesafe.base.BaseActivityToolbar;
import tyxo.mobilesafe.net.volley.VolleyCallBack;
import tyxo.mobilesafe.net.volley.VolleyErrorResult;
import tyxo.mobilesafe.utils.log.HLog;

/**
 * Created by LY on 2016/8/25 10: 38.
 * Mail      1577441454@qq.com
 * Describe : <T> 限定数据 bean 的类型,
 *            子类使用的时候: public class GirlsActivity extends BaseRecyclerActivity<BeanGirls>{....}
 */
public abstract class BaseRecyclerActivity<E> extends BaseActivityToolbar{

    protected RecyclerView id_recyclerview;
    protected ArrayList<E> datas;
    protected TaskHelp taskHelp;
    protected VolleyCallBack<JSONObject> callback;

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.activity_single_recyclerview);
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        id_recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);

        //mToolbarTitle.setText("Girls");
    }


    /** 处理数据 */
    protected abstract void handleData(E bean);{
        /** 子类建议做如下判断, 打印size,便于调试接口,按自己情况去写 */
        /*if(bean.getData()!=null && bean.getData().size() > 0){
            HLog.i("tyxo", " response size(): " + bean.getData().size());
        }else {
            HLog.i("tyxo", " size<=0 返回信息: " + ConstValues.SERVER_RESPONSE_EMPTY);
        }*/
    }

    /** 网络请求 */
    protected void requestNet(){

        callback =  new VolleyCallBack<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                HLog.i("tyxo", " response : " + response.toString());
                try {
                    if (!TextUtils.isEmpty(response.toString())) {
                        Type type = new TypeToken<E>() {}.getType();
                        E bean = new Gson().fromJson(response.toString(), type);
                        //Object obj = new Gson().fromJson(response.toString(), Object.class);

                        handleData(bean);   //处理数据
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
}



















