package tyxo.mobilesafe.base;

import android.app.Application;
import android.content.Context;

import java.io.File;

public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		File dexPath = new File(getDir("dex", Context.MODE_PRIVATE), "hackdex_dex.jar");
		//Utils.prepareDex(this.getApplicationContext(),dexPath,"hackdex_dex.jar");
		//HotFix.path(this,dexPath.getAbsolutePath(),"dodola.hackdex.AntilazyLoad");
		try {
			this.getClassLoader().loadClass("dadala.hackdex.AntilazyLoad");
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}

	private static BaseApplication instance;

	public static BaseApplication getInstance() {
		return instance;
	}
}


















