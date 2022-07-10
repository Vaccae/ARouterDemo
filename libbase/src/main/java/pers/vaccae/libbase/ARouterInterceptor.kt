package pers.vaccae.libbase

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间： 13:10
 * 功能模块说明：
 */
@Interceptor(priority = 1, name = "登陆拦截")
class ARouterInterceptor : IInterceptor {

    override fun init(context: Context?) {

    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        callback?.let {
            postcard?.let { post ->
                if(post.group.equals("M2")) {
                    val isLogin = DataStoreHelper.getData("login", false)
                    if (!isLogin) {
                        val msg = "用户未登陆，无法进入当前页面！"
                        post.tag = msg
                        it.onInterrupt(RuntimeException(msg))
                    }
                }
                it.onContinue(post)
            }
        }
    }
}