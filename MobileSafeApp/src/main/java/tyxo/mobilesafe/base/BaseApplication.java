package tyxo.mobilesafe.base;

import android.app.Application;
import android.content.Context;

import java.io.File;

import dodola.hotfixlib.HotFix;
import tyxo.mobilesafe.utils.hotfix.Utils;

public class BaseApplication extends Application {

	private static BaseApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		File dexPath = new File(getDir("dex", Context.MODE_PRIVATE), "hackdex_dex.jar");
		Utils.prepareDex(this.getApplicationContext(),dexPath,"hackdex_dex.jar");
		HotFix.patch(this,dexPath.getAbsolutePath(),"dodola.hackdex.AntilazyLoad");
		try {
			this.getClassLoader().loadClass("dadala.hackdex.AntilazyLoad");
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}

	public static BaseApplication getInstance() {
		return instance;
	}
}


















