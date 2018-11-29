package com.kim.weather.base

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kim.weather.okhttp.APP_DBG

fun Activity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun log(tag: String, log: String) {
    if (APP_DBG) Log.i(tag, log)
}

fun toast(context: Context ,msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun Activity.log(tag: String, log: String) {
    if (APP_DBG) Log.i(tag, log)
}

fun Activity.logE(tag: String, log: String) {
    if (APP_DBG) Log.e(tag, log)
}

fun BaseFragment.toast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}


fun BaseFragment.log(tag: String, log: String) {
    if (APP_DBG) Log.i(tag, log)
}

fun BaseFragment.logE(tag: String, log: String) {
    if (APP_DBG) Log.e(tag, log)
}