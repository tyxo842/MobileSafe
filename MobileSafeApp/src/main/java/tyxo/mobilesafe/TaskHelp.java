package tyxo.mobilesafe;

import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import tyxo.mobilesafe.activity.SplashActivity;
import tyxo.mobilesafe.base.MyApp;
import tyxo.mobilesafe.base.PlatUser;
import tyxo.mobilesafe.net.volley.VolleyCallBack;
import tyxo.mobilesafe.net.volley.VolleyManager;
import tyxo.mobilesafe.utils.log.HLog;

/**
 * Created by LY on 2016/8/11 14: 07.
 * Mail      1577441454@qq.com
 * Describe : 用法:
 *              0.new TaskHelpNew --> task  (否则userName,orgId放不进去)
 *              1.new VolleyCallBack<JSONObject>()...;
 *              2.task.方法...;
 */
public class TaskHelp {
    private String userName;
    private String orgId;
    private PlatUser platUser;
    //private SharedPreferences mSpUser = MyApp.getAppContext().getSharedPreferences(ConstValues.USER_DATA_FILE, Context.MODE_PRIVATE);
    //private String userPWD = mSpUser.getString("pwd", "");
    //private String userLoginName = mSpUser.getString("userName", "");

    public TaskHelp() {
        //getPlatUserNameOrOrgid(MyApp.getInstance());
    }

    // platUser 为null时,跳转到splash重新登陆
    private void getPlatUserNameOrOrgid(Context context) {
        MyApp.getInstance().CheckNetworkState(context); // 检查网络

        platUser = (PlatUser) MyApp.getInstance().getCurrentUser();
        if (platUser != null) {
            this.userName = platUser.userName;
            this.orgId = platUser.orgId;
        } else {
            Intent intent = new Intent(context, SplashActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); // 有白屏闪过,没有NEW_TASK会蹦
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 没有NEW_TASK会蹦
            context.startActivity(intent);
            return;
        }
    }


    /** 网络请求 */
    public void orderModifyState(Context context,String orderId, String orderState,
                                 VolleyCallBack<JSONObject> callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userName", "tyxo");
            jsonObject.put("orgId", orgId);
            jsonObject.put("orderId", orderId);
            jsonObject.put("orderState", orderState);

                /*JSONArray jsonArray = new JSONArray();
                if (orderDetailIds!=null && orderDetailIds.size()>0){
                    for (int i=0;i<orderDetailIds.size();i++) {
                        JSONObject jsonInfo = new JSONObject();
                        jsonInfo.put("orderDetailId", orderDetailIds.get(i));
                        jsonArray.put(jsonInfo);
                    }
                }
                jsonObject.put("orderDetailIds", jsonArray.toString());*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //String url = StringUtils.combineURl(ConstValues.MYPHOTO_URL, ConstValues.MYPHOTO_URL);
        String url = "";
        VolleyManager.getInstance(context).postJson(url, jsonObject, callback);
        HLog.i("lynet:", "企业  请求url: "+url+"\n请求参数: "+jsonObject.toString());
    }

    public static void requstWeatherDatas(Context context, VolleyCallBack<JSONObject> callback, Map<String,String> header){
        String url = "http://apis.baidu.com/heweather/weather/free?city=beijing";
        VolleyManager.getInstance(context).getJsonWithHeader(url,callback,header);
        /*
        // 原代码 设置, 需要封装
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                HLog.i("tyxo","TaskHelp jsonObject: "+ jsonObject);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                HLog.e("tyxo","TaskHelp volleyError: "+ volleyError);
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://apis.baidu.com/heweather/weather/free?city=beijing";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers==null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<>();
                }
                headers.put("apikey","2600907be4021f9979ecc9554a4065ac");
                //headers.put("Charset", "UTF-8");
                //headers.put("Content-Type", "application/x-javascript");
                //headers.put("Accept-Encoding", "gzip,deflate");
                return headers;
                //return super.getHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //request.setTag(TAG);
        //request.setShouldCache(true);

        queue.add(request);
        //queue.start();*/
    }
}























