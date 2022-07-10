package pers.vaccae.module3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import pers.vaccae.module3.databinding.ActivityM3Binding

@Route(path = "/M3/activity")
class M3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityM3Binding

    @JvmField
    @Autowired(name = "parent")
    var parent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityM3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ARouter.getInstance().inject(this)

        binding.btnm2.setOnClickListener {
            ARouter.getInstance().build("/M2/activity").navigation();
        }

        binding.textView.append("\r\nparent:${parent}")
    }
}