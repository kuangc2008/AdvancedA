package myapp.kc.com.activity.jetpack

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * @author  kc create on 5/10/19.
 */
class NameViewModel2 : ViewModel() {

    val currentName : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}