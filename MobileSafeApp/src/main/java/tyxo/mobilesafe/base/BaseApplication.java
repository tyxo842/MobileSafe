package tyxo.mobilesafe.base;

import android.app.Application;
import android.content.Context;

import java.io.File;

import dodola.hotfixlib.HotFix;
import tyxo.mobilesafe.utils.hotfix.Utils;
/**
* @author ly
* @created at 2016/7/28 11:22
 *
* @des : 	将修改过后的.class文件打成jar包(运行之后就生成了class文件).
 *
 *在自己目录 : E:\WorkPlace_Studio_Test\MobileSafe\hackdex\build\intermediates\classes\debug,运行(生成hack.jar):
 * 			jar cvf hack.jar dodola/hackdex/*
 *在自己目录 : E:\StudioSDK\build-tools\23.0.3 , 运行dx命令 , 会在此路径的文件夹下生成 hack_dex.jar.
			dx  --dex --output hack_dex.jar (此处将hack.jar 拖过来,直接打名字会找不到)

下方两个命令,同上(在固定路径下,命令行运行):
			jar cvf path.jar dodola/hotfix/BugClass.class
			dx  --dex --output path_dex.jar path.jar
 *
 * 		动态改变BaseDexClassLoader对象间接引用的dexElements.
 * 		必须在应用启动的时候，降这个hack_dex.jar插入到dexElements，否则肯定会出事故的。
 *
 * 		注意 : 如果先点击测试，再点击打补丁，再测试是不会变化的，因为类一旦加载以后，不会重新再去重新加载了。
*/
public class BaseApplication extends Application {

	private static BaseApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		// 读取path_dex.jar文件
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


















