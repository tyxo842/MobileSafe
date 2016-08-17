package tyxo.functions.prettygirls.splash;

import android.view.View;

import coder.mylibrary.base.AppActivity;
import coder.mylibrary.base.BaseFragment;
import tyxo.mobilesafe.R;

public class SplashActivityPG extends AppActivity {

    public static SplashActivityPG splashActivityPG ;

    @Override
    protected BaseFragment getFirstFragment() {
        splashActivityPG = this;
        return SplashFragment.getInstance();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash_pg;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.splash_fragment;
    }

    @Override
    public void onClick(View v) {

    }
}
