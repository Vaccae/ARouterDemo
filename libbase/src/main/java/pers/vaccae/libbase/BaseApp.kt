package pers.vaccae.libbase

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间： 15:26
 * 功能模块说明：
 */
class BaseApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: BaseApp
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        mContext = this

        InitARouter(instance)
    }

    private fun InitARouter(baseApp: Application) {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        // 此处注意本身ARouter也有BuildConfig，这里用的是Android的BuildConfig，如果引用错了是无效的
        if (pers.vaccae.libbase.BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(baseApp);  // 尽可能早，推荐在Application中初始化
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }

}