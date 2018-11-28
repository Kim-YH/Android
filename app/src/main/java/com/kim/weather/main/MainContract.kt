package com.kim.weather.main

import com.kim.weather.base.BasePresenter
import com.kim.weather.base.BaseView

internal interface MainContract {

    interface View : BaseView<MainContract.Presenter>

    interface Presenter : BasePresenter
}
