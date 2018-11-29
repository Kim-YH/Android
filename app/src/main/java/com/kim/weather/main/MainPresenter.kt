package com.kim.weather.main

import com.kim.weather.bean.QueryBean
import com.kim.weather.okhttp.*
import java.net.URLEncoder
import java.util.*

internal class MainPresenter internal constructor(view: MainContract.View, okHttpUtils: OkHttpUtils) : MainContract.Presenter {
    private var mOkHttpUtils: OkHttpUtils? = null
    private var mView: MainContract.View? = null


    init {
        mView = view
        mOkHttpUtils = okHttpUtils
        mView!!.setPresenter(this)
    }

    override fun query(city: String, province: String) {
        val params = HashMap<String, String>()
        params[URL_KEY_KEY] = APP_KEY
        params[URL_KEY_CITY] = URLEncoder.encode(city, "utf-8")
        params[URL_KEY_PROVINCE] = URLEncoder.encode(province, "utf-8")
        mOkHttpUtils!!.doGet(URL_QUERY, params, object : GsonArrayCallback<QueryBean>() {
            override fun onResponse(list: List<QueryBean>) {
                if (mView!!.isActive) {
                    mView!!.setData(list[0])
                }
            }

            override fun onFailed(ret: Int, msg: String?) {
            }
        })
    }

    override fun start() {
    }
}
