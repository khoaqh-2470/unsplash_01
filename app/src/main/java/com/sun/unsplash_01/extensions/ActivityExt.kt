package com.sun.unsplash_01.extensions

import android.app.Activity
import android.view.View

fun Activity.hideStatusBar() {
    this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
}
