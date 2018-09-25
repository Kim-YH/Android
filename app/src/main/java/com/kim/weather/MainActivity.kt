package com.kim.weather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        tv_title.text = "123123"
        var s = tv_title.text.toString()
        s += "-> 123"

        Log.i(TAG, s)
    }


    fun sun(x: Int, y: Int): Int {

        return x;
    }

}
