package com.kim.weather.okhttp

/**
 * 接口返回结果集
 * Created by Kim on 2018/11/28.
 */
data class ApiResult(var retCode: Int = -1, var msg: String?, var result: Any?)
