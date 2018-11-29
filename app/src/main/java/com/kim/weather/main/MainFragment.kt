package com.kim.weather.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kim.weather.R
import com.kim.weather.base.BaseFragment
import com.kim.weather.bean.QueryBean
import kotlinx.android.synthetic.main.fragment_main.*

internal class MainFragment : BaseFragment(), MainContract.View {

    private var mPresenter: MainContract.Presenter? = null

    fun newInstance(): MainFragment {
        return MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter!!.query("通州", "北京")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override val isActive: Boolean
        get() = isAdded

    override fun onClick(v: View) {

    }

    override fun setData(queryBean: QueryBean) {
        tv.text = queryBean.toString()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }
}
