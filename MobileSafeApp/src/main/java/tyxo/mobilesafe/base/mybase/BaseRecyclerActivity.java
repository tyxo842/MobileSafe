package tyxo.mobilesafe.base.mybase;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import org.json.JSONObject;

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
public abstract class BaseRecyclerActivity<T> extends BaseActivityToolbar{

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

    protected RecyclerView id_recyclerview;
    protected TaskHelp taskHelp;
    protected VolleyCallBack<JSONObject> callback;
    protected Class<T> clazz ;

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
    protected abstract void handleData(String resp , Class<T> clazzH);
        /** 子类建议做如下判断, 打印size,便于调试接口,按自己情况去写 */
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

                        handleData(response.toString(),clazz);   //处理数据

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



















