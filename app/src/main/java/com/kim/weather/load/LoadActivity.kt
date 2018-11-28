package com.kim.weather.load

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.kim.weather.base.BaseActivity
import com.kim.weather.main.MainActivity

class LoadActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread(Runnable {
            Thread.sleep(1500)
            mHandler.sendEmptyMessage(1)
        }).start()
    }

    private val mHandler = Handler(Handler.Callback {
        jumpMain()
        false
    })

    fun jumpMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    override fun onClick(v: View?) {
    }
}
