package com.kim.weather.base

interface BaseView<T> {
    val isActive: Boolean

    fun setPresenter(presenter: T)

}
