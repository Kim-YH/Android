package com.kim.weather.base

import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Toast

abstract class BaseFragment : Fragment(), View.OnClickListener {
    private val TAG = javaClass.simpleName

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        logError(" onAttach")
    }

    override fun onResume() {
        super.onResume()
        logError(" onResume")
    }


    override fun onPause() {
        super.onPause()
        logError(" onPause")
    }

    fun BaseFragment.toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


    fun BaseFragment.log(mag: String) {
        Log.i(TAG, mag)
    }

    fun BaseFragment.logError(mag: String) {
        Log.e(TAG, mag)
    }
}
