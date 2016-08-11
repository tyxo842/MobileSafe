package tyxo.mobilesafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import tyxo.mobilesafe.activity.SplashActivity;
import tyxo.mobilesafe.base.MyApp;
import tyxo.mobilesafe.base.PlatUser;
import tyxo.mobilesafe.net.volley.VolleyCallBack;
import tyxo.mobilesafe.net.volley.VolleyManager;
import tyxo.mobilesafe.utils.StringUtils;
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
    private SharedPreferences mSpUser = MyApp.getAppContext().getSharedPreferences(ConstValues.USER_DATA_FILE, Context.MODE_PRIVATE);
    private String userPWD = mSpUser.getString("pwd", "");
    private String userLoginName = mSpUser.getString("userName", "");

    public TaskHelp() {
        getPlatUserNameOrOrgid(MyApp.getInstance());
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
            jsonObject.put("userName", userName);
            jsonObject.put("userPwd", userPWD);
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
        String url = StringUtils.combineURl(ConstValues.MYPHOTO_URL, ConstValues.MYPHOTO_URL);
        VolleyManager.getInstance(context).postJson(url, jsonObject, callback);
        HLog.i("lynet:", "企业  请求url: "+url+"\n请求参数: "+jsonObject.toString());
    }
}
