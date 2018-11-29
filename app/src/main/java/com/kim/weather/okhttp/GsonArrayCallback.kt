package com.kim.weather.okhttp

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.kim.weather.R
import com.kim.weather.base.MyApplication
import com.kim.weather.base.log
import com.kim.weather.base.toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.net.SocketTimeoutException
import java.util.*

/**
 * Created by Kim on 2018/5/9.
 */
@Suppress("UNCHECKED_CAST")
abstract class GsonArrayCallback<T> : Callback {

    private val handler = OkHttpUtils.handler

    //主线程处理
    abstract fun onResponse(list: List<T>)

    //主线程处理
    abstract fun onFailed(ret: Int, msg: String?)

    override fun onFailure(call: Call, e: IOException) {
        handler!!.post {
            val context = MyApplication().getInstance()
            val msg: String

            // 判断是否时 网络超时
            if (e is SocketTimeoutException) {
                msg = context.getString(R.string.network_time_out)
            } else {
                msg = context.getString(R.string.network_error)
            }
            onFailed(FAILURE, msg)
        }

    }


    @Throws(IOException::class)
    override fun onResponse(call: Call, response: Response) {
        val mList = ArrayList<T>()
        val gson = Gson()

        var json = response.body()!!.string()
        log("Callback", response.request().url().toString() + " -> " + json)

        if (TextUtils.isEmpty(json)) {
            json = "{}"
        }
        val result: ApiResult
        try {
            result = gson.fromJson(json, ApiResult::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            handler!!.post { toast(MyApplication().getInstance(), MyApplication().getInstance().getString(R.string.network_error)) }
            return
        }

        val ret = result.retCode
        if (ret != SUCCESS) {  // 判断请求 错误吗是否是0  0为请求成功 请求失败时没有数据 不需要进行数据解析
            handler!!.post {
                //                if (ret > 1 || TextUtils.isEmpty(result.msg)) {
//                    result.msg = ErrorToolKit.getError(ret)
//                }
                onFailed(ret, result.msg)
            }
            return
        }

        try {
            val array = JsonParser().parse(gson.toJson(result.result)).asJsonArray

            val genType = this.javaClass.genericSuperclass
            val cls = (genType as ParameterizedType).actualTypeArguments[0] as Class<*>


            for (elem in array) {
                //循环遍历把对象添加到集合
                mList.add(gson.fromJson(elem, cls) as T)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        handler!!.post { onResponse(mList) }
    }
}
