package com.kim.weather.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kim.weather.R
import com.kim.weather.base.BaseFragment

internal class MainFragment : BaseFragment(), MainContract.View {

    private var mPresenter: MainContract.Presenter? = null

    fun newInstance(): MainFragment {
        return MainFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override val isActive: Boolean
        get() = isAdded

    override fun onClick(v: View) {

    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }
}
