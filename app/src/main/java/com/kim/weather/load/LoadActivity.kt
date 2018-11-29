package com.kim.weather.load

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import com.kim.weather.base.BaseActivity
import com.kim.weather.main.MainActivity

class LoadActivity : BaseActivity() {


    private val REQUEST_PERMISSIONS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ) {
            Thread(Runnable {
                Thread.sleep(1500)
                mHandler.sendEmptyMessage(1)
            }).start()

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSIONS)
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size == 1) {
            val cameraPermission = grantResults[0]
            if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
                jumpMain()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSIONS)
            }
        }
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
