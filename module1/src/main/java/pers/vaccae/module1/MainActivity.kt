package pers.vaccae.module1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import pers.vaccae.libbase.DataStoreHelper
import pers.vaccae.module1.databinding.ActivityMainBinding
import javax.security.auth.login.LoginException

@Route(path = "/M1/activity")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isLogin = false

    fun showMsg(msg: String = "") {
        binding.textView.text = if (isLogin) {
            "MainActivity\r\n已登陆\r\n"
        } else {
            "MainActivity\r\n未登陆\r\n"
        }
        binding.textView.append(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        DataStoreHelper.putData("login", false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLogin = DataStoreHelper.getData("login", false)
        showMsg()

        binding.btnlogin.setOnClickListener {
            isLogin = !isLogin
            DataStoreHelper.putData("login", isLogin)
            showMsg()
        }

        binding.btntest.setOnClickListener {
            ARouter.getInstance().build("/M1/test").navigation();
        }

        binding.btnm2.setOnClickListener {
            ARouter.getInstance().build("/M2/activity").navigation(this,
                object : NavigationCallback {
                    override fun onFound(postcard: Postcard?) {

                    }

                    override fun onLost(postcard: Postcard?) {

                    }

                    override fun onArrival(postcard: Postcard?) {

                    }

                    override fun onInterrupt(postcard: Postcard?) {
                        postcard?.let {
                            showMsg(it.tag.toString())
                        }
                    }

                });
        }

        binding.btnm3.setOnClickListener {
            ARouter.getInstance().build("/M3/activity")
                .withString("parent", "MainActivity")
                .navigation();
        }
    }
}