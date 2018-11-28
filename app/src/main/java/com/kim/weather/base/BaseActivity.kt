package com.kim.weather.base

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast

/**
 *
 */
abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        logError(" onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun getResources(): Resources {
        val resources = super.getResources()
        val configuration = Configuration()
        configuration.setToDefaults()
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return resources
    }

    fun AppCompatActivity.toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun AppCompatActivity.log(mag: String) {
        Log.i(TAG, mag)
    }

    fun AppCompatActivity.logError(mag: String) {
        Log.e(TAG, mag)
    }

    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {

        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }
}
