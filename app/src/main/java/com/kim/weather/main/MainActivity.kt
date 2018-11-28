package com.kim.weather.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.kim.weather.R
import com.kim.weather.base.BaseActivity

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity)


        var mainFragment: MainFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as MainFragment?

        if (mainFragment == null) {
            mainFragment = MainFragment().newInstance()
            addFragmentToActivity(supportFragmentManager, mainFragment, R.id.contentFrame)
        }

        MainPresenter(mainFragment)
    }

    override fun onClick(v: View?) {
    }

    private var back: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val l = System.currentTimeMillis() - back
            if (l < 2500) {
                finish()
            } else {
                back = System.currentTimeMillis()
                toast(getString(R.string.back_again))
            }
        }
        return false
    }
}
