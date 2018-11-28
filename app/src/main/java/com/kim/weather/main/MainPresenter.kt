package com.kim.weather.main

internal class MainPresenter internal constructor(view: MainContract.View) : MainContract.Presenter {
    private var mView: MainContract.View

    init {
        mView = view
        mView.setPresenter(this)
    }

    override fun start() {
    }
}
