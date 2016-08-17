package tyxo.functions.prettygirls.splash;


import android.util.Log;

import tyxo.functions.prettygirls.app.MyApplication;
import tyxo.functions.prettygirls.data.bean.GirlsBean;
import tyxo.functions.prettygirls.data.source.GirlsDataSource;
import tyxo.functions.prettygirls.data.source.GirlsResponsitory;

/**
 * Created by oracleen on 2016/6/28.
 */
public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;
    private GirlsResponsitory mResponsitory;

    public SplashPresenter(SplashContract.View view) {
        mView = view;
        mResponsitory = new GirlsResponsitory();
    }

    @Override
    public void start() {
        mResponsitory.getGirl(new GirlsDataSource.LoadGirlsCallback() {

            @Override
            public void onGirlsLoaded(GirlsBean girlsBean) {
                mView.showGirl(girlsBean.getResults().get(0).getUrl());
                MyApplication.currentGirl = girlsBean.getResults().get(0).getUrl();
                Log.i("tyxo","currentGril url : "+MyApplication.currentGirl);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showGirl();
            }

        });
    }
}
