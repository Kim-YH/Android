package com.kim.weather.main

import com.kim.weather.base.BasePresenter
import com.kim.weather.base.BaseView
import com.kim.weather.bean.QueryBean

internal interface MainContract {

    interface View : BaseView<MainContract.Presenter>{
        fun setData(queryBean: QueryBean)
    }

    interface Presenter : BasePresenter {
        fun query(city: String, province: String)
    }
}
