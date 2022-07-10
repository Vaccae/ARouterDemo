package pers.vaccae.module2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import pers.vaccae.module2.R
import pers.vaccae.module2.databinding.ActivityM2Binding

@Route(path = "/M2/activity")
class M2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityM2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityM2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnm3.setOnClickListener {
            ARouter.getInstance().build("/M3/activity")
                .withString("parent","M2Activity")
                .navigation();
        }
    }
}