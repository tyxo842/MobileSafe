package tyxo.mobilesafe.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import tyxo.mobilesafe.R;

public abstract class BaseActivityToolbar extends AppCompatActivity {

	public String mPageName = this.getClass().getSimpleName();	// 获取类名
	/**当前Activity的上下文 */
	protected Context mContext;
	public Toolbar mToolbar;
	private ToolbarHelper mToolbarHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		//super.setContentView(layoutResID);
		mToolbarHelper = new ToolbarHelper(this, layoutResID);
		mToolbar = mToolbarHelper.getToolbar();
		setContentView(mToolbarHelper.getContentView());
		// 把toolbar 设置到 Activity 中
		setSupportActionBar(mToolbar);
		// 自定义的一些操作
		onCreateCustomToolbar(mToolbar);

		initView();		// 初始化 view
		initListener();	// 初始化 监听
		initData();		// 初始化 数据
	}

	protected void initView(){}

	protected void initListener(){}

	protected void initData(){}

	public void onCreateCustomToolbar(Toolbar toolbar){
		toolbar.setContentInsetsRelative(0,0);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** 结束Activity */
	@Override
	public void finish() {
		super.finish();
		// 设置切换动画，从右边进入，右边退出
		this.overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		// 设置切换动画，从右边进入，右边退出
		this.overridePendingTransition(R.anim.slide_left_in,R.anim.slide_left_out);
	}

	/*@Override
	protected void onResume() {
		super.onResume();
		//极光推送统计
		JpushManager.getInstance(mContext.getApplicationContext()).onActivityResume(this);
		
		// 添加腾讯的统计服务
		// 如果本Activity是继承基类HHBaseActivity的，可注释掉此行。
//		StatService.onResume(this);
//		StatService.trackBeginPage(this, getClass().getCanonicalName());
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		JpushManager.getInstance(mContext.getApplicationContext()).onActivityPause(this);
		
		// 添加腾讯的统计服务
		// 如果本Activity是继承基类BaseActivity的，可注释掉此行。
//		StatService.onPause(this);
//		StatService.trackEndPage(this, getClass().getCanonicalName());
	}*/
}
