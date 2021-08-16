package uz.koinot.onlineshop.utils

import android.view.View

abstract class OnOneOffClickListener : View.OnClickListener {
    private var clickable = true
    override fun onClick(v: View?) {
        if (clickable) {
            clickable = false
            onOneClick(v)
        }
    }

    abstract fun onOneClick(v: View?)

    open fun reset() {
        clickable = true
    }
}
