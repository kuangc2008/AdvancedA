package myapp.kc.com.activity.jetpack

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kc.kuanglibrary.BaseActivity
import kotlinx.android.synthetic.main.tips_loading_failed.*
import myapp.kc.com.kuang2016_go.R

/**
 * @author  kc create on 5/10/19.
 */
class NameActivity : BaseActivity() {

    private lateinit var model : NameViewModel2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tips_loading_failed)

        model = ViewModelProviders.of(this).get(NameViewModel2::class.java)
        val nameObserver = Observer<String> {
            Log.i("kcc", "it 22--$it")
            description.text = model.currentName.value
        }

        model.currentName.observe(this, nameObserver)


        retry_btn.setOnClickListener {
            Log.i("kcc", "it --$it")
            val value = if (model.currentName.value.isNullOrEmpty()) "haha" else model.currentName.value
            val anotherName = "$value ${value}"
            model.currentName.value = anotherName;
        }
    }
}