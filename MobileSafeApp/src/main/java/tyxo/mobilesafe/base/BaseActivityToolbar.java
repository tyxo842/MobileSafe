package tyxo.mobilesafe.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tyxo.mobilesafe.R;
import tyxo.mobilesafe.base.myinterface.IBaseBarCallback;
import tyxo.mobilesafe.base.myinterface.IBaseInit;

public abstract class BaseActivityToolbar extends AppCompatActivity implements IBaseInit, IBaseBarCallback {

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
		super.setContentView(layoutResID);
		mToolbarHelper = new ToolbarHelper(this, layoutResID);
		mToolbar = mToolbarHelper.getToolbar();
		setContentView(mToolbarHelper.getContentView());
		// 把toolbar 设置到 Activity 中
		setSupportActionBar(mToolbar);
		// 自定义的一些操作
		onCreateCustomToolbar(mToolbar);
//		initToobar(true);

		initView();
		initData();
		initListener();
	}

	public void onCreateCustomToolbar(Toolbar toolbar){
		toolbar.setContentInsetsRelative(0,0);
	}

	/** 设置Activity使用的视图 */
	protected abstract void setContentView();

	/** 初始化控件 */
	@Override
	public void initView() { }

	/** 初始化数据 */
	@Override
	public void initData() { }

	/** 初始化事件监听器 */
	@Override
	public void initListener() { }

	/** 初始化 Bar，是否使用自定义的ActionBar布局 */
	public void initToobar() { setToobarLayout(); }

	public void initToobar(boolean isCustomToobar) {
		if (isCustomToobar)
			setToobarLayout();
	}

	/** 自定义 Bar 标题 */
	private TextView toolbarTitle;

	/** 自定义 Bar 图标 */
	private ImageView toolbarIcon;

	/** 设置 Bar布局 */
	protected void setToobarLayout() {

		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.toolbar_demo_layout, null);
		mToolbar = (Toolbar) v.findViewById(R.id.tb_toolbar);
		setSupportActionBar(mToolbar);

//		mToolbar = getSupportActionBar();
		if (null != mToolbar) {
			// 定义  Bar 背景
			// mToolbar.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.bottom_bar));
			// 由子类决定是否显示返回上一级指示符
			// actionBar.setDisplayHomeAsUpEnabled(true);
			// 开放自定义view
			// mToolbar.setDisplayShowCustomEnabled(true);
			//toolbarTitle = (TextView) v.findViewById(R.id.actionbar_tv_title);
			//toolbarIcon = (ImageView) v.findViewById(R.id.actionbar_iv_back);
			//LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			//mToolbar.setCustomView(v, layout);

			toolbarTitle = (TextView) v.findViewById(R.id.tv_toolbar_title);
//			getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
//			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public TextView getBarTitle() {
		return toolbarTitle;
	}

	@Override
	public ImageView getBarIcon() {
		return toolbarIcon;
	}

	@Override
	public void setBarTitleVisibility(int visibility) {
		if (toolbarTitle != null)
			toolbarTitle.setVisibility(visibility);
	}

	@Override
	public void setBarIconVisibility(int visibility) {
		if (toolbarIcon != null)
			toolbarIcon.setVisibility(visibility);
	}

	/** 设置 Bar标题 */
	@Override
	public void setBarTitle(String title) {
		if (toolbarTitle != null)
			toolbarTitle.setText(title);
		else
			getSupportActionBar().setTitle(title);
	}

	@Override
	public void setBarTitle(int resid ) {
		if (toolbarTitle != null)
			toolbarTitle.setText(resid);
		else
			getSupportActionBar().setTitle(resid);
	}

	/** 设置 Bar 图标 */
	@Override
	public void setBarIcon(Bitmap bitmap) {
		if (toolbarIcon != null)
			toolbarIcon.setImageBitmap(bitmap);
	}

	@Override
	public void setBarIcon(int icon) {
		if (toolbarIcon != null)
			toolbarIcon.setImageResource(icon);
	}

	/** activity的返回结果码 */
	private int resultCode;

	/** activity的返回结果数据 */
	private Intent intentData;

	/**  获得返回结果数据 */
	public Intent getIntentData() {
		return intentData;
	}

	/** 设置Activity返回结果 */
	public void setIntentData(Intent intentData) {
		this.intentData = intentData;
	}

	/** 获得Activity返回结果码 */
	public int getResultCode() {
		return resultCode;
	}

	/** 设置Activity返回结果码 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
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

	/** 结束Activity时，设置返回结果 */
	public void finishWithResult(){
		this.setResult(getResultCode(), getIntentData());
		finish();
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
