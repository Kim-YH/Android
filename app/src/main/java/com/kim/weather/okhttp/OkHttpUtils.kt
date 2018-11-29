package com.kim.weather.okhttp

import android.os.Environment
import android.os.Handler
import com.google.gson.Gson
import com.kim.weather.base.log
import okhttp3.*
import java.io.File
import java.io.Serializable
import java.util.concurrent.TimeUnit

/**
 * 封装OkHttp3的工具类 用单例设计模式
 * Created by Kim on 2018/5/9.
 */
class OkHttpUtils private constructor() : Serializable {

    @Synchronized
    private fun getOkHttpClient(): OkHttpClient? {
        //判空 为空创建实例
        if (okHttpClient == null) {

            //  File sdcache = getExternalCacheDir();
            //缓存目录
            val sdcache = File(Environment.getExternalStorageDirectory(), "cache")
            val cacheSize : Long= 10 * 1024 * 1024

            // 拦截器 设置 添加Headers  token 参数
//            val interceptor = object : Interceptor {
//                @Throws(IOException::class)
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val originalRequest = chain.request()
//                            .newBuilder()
//                            .cacheControl(CacheControl.FORCE_NETWORK)
//                            .build()
//                    if (!OneApplication.getInstance().isLogin()) {
//                        return chain.proceed(originalRequest)
//                    }
//
//                    val request = chain.request()
//                            .newBuilder()
//                            .addHeader("Content-Type", "application/json")
//                            .addHeader("Connection", "keep-alive")
//                            .addHeader("Accept", "*/*")
//                            .addHeader("Cookie", "add cookies here")
//                            .addHeader("token", OneApplication.getInstance().getToken())
//                            .cacheControl(CacheControl.FORCE_NETWORK)
//                            .build()
//
//                    //                    Utils.logError(TAG, "token -> " + OneApplication.getInstance().getToken());
//
//                    return chain.proceed(request)
//                }
//            }


            okHttpClient = OkHttpClient.Builder()
                    //添加OkHttp3的拦截器
//                    .addInterceptor(interceptor)
                    //                    .addNetworkInterceptor(new CacheInterceptor())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .cache(Cache(sdcache.absoluteFile, cacheSize))
                    .build()
        }
        return okHttpClient
    }

    //    public static String getUrl() {
    //        return ApiConsts.APP_DBG ? ApiConsts.IP_TEST : ApiConsts.IP_SERVER;
    //    }


    /**
     * get请求
     * 参数1 url
     * 参数2 需要提交给服务器的参数  没有时可传null
     * 参数3 回调Callback
     */
    fun doGet(url: String, params: Map<String, String>?, callback: Callback) {
        var url = url
        if (null != params) {
            url = getUrlParam(url, params)
        }

        log(TAG, "url -> $url")
        //创建OkHttpClient请求对象
        val okHttpClient = getOkHttpClient()
        //创建Request
        val request = Request.Builder().url(url).build()
        //得到Call对象
        val call = okHttpClient!!.newCall(request)
        //执行异步请求
        call.enqueue(callback)


    }

    /**
     * post请求
     * 参数1 url
     * 参数2 需要提交给服务器的参数
     * 参数3 回调Callback
     */

    fun doPost(url: String, params: Map<String, String>, callback: Callback) {
        //创建OkHttpClient请求对象
        val okHttpClient = getOkHttpClient()


        //3.x版本post请求换成FormBody 封装键值对参数
        val builder = setParams(url, params)

        //创建Request
        val request = Request.Builder().url(url).post(builder.build()).build()

        val call = okHttpClient!!.newCall(request)
        call.enqueue(callback)

    }

    /**
     * Post请求发送JSON数据
     * 参数一：请求Url
     * 参数二：需要提交给服务器的参数
     * 参数三：回调Callback
     */
    fun doPostJson(url: String, params: Map<String, String>, callback: Callback) {
        //创建OkHttpClient请求对象
        val okHttpClient = getOkHttpClient()


        val MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8")
        val gson = Gson()
        val jsonStr = gson.toJson(params)
        if (APP_DBG) {
            log(TAG, "url -> $url")
            log(TAG, "json -> $jsonStr")
        }

        val requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonStr)

        //创建Request
        val request = Request.Builder().url(url).post(requestBody).build()

        val call = okHttpClient!!.newCall(request)
        call.enqueue(callback)
    }

    /**
     * put 请求
     * 参数1 url
     * 参数2 需要提交给服务器的参数
     * 参数3 回调Callback
     */

    fun doPut(url: String, params: Map<String, String>, callback: Callback) {
        //创建OkHttpClient请求对象
        val okHttpClient = getOkHttpClient()


        //3.x版本post请求换成FormBody 封装键值对参数
        val builder = setParams(url, params)

        //创建Request
        val request = Request.Builder().url(url).put(builder.build()).build()

        val call = okHttpClient!!.newCall(request)
        call.enqueue(callback)

    }


    /**
     * Put 请求发送JSON数据
     * 参数一：请求Url
     * 参数二：需要提交给服务器的参数
     * 参数三：回调Callback
     */
    fun doPutJson(url: String, params: Map<String, String>, callback: Callback) {
        //创建OkHttpClient请求对象
        val okHttpClient = getOkHttpClient()


        val MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8")
        val gson = Gson()
        val jsonStr = gson.toJson(params)
        if (APP_DBG) {
            log(TAG, "url -> $url")
            log(TAG, "json -> $jsonStr")
        }

        val requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonStr)

        //创建Request
        val request = Request.Builder().url(url).put(requestBody).build()

        val call = okHttpClient!!.newCall(request)
        call.enqueue(callback)
    }

    /**
     * Put 请求发送JSON数据
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    fun doPutJson(url: String, jsonParams: String, callback: Callback) {
        log(TAG, "json -> $jsonParams")
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams)
        val request = Request.Builder().url(url).put(requestBody).build()
        val call = getOkHttpClient()!!.newCall(request)
        call.enqueue(callback)
    }

    /**
     * put 请求
     * 参数1 url
     * 参数2 需要提交给服务器的参数
     * 参数3 回调Callback
     */

    fun doDelete(url: String, params: Map<String, String>, callback: Callback) {

        //创建OkHttpClient请求对象
        val okHttpClient = getOkHttpClient()

        //3.x版本post请求换成FormBody 封装键值对参数
        val builder = setParams(url, params)

        //创建Request
        val request = Request.Builder().url(url).delete(builder.build()).build()

        val call = okHttpClient!!.newCall(request)
        call.enqueue(callback)

    }

    private fun setParams(url: String, params: Map<String, String>?): FormBody.Builder {
        if (APP_DBG) {
            log(TAG, "url -> $url")
            traversal(params)
        }

        val builder = FormBody.Builder()
        //遍历集合
        if (null != params) {
            for (key in params.keys) {
                builder.add(key, params[key])
            }
        }
        return builder
    }


    fun startSocket(url: String, webSocketListener: WebSocketListener): WebSocket {
        val mOkHttpClient = getOkHttpClient()

        val request = Request.Builder().url(url).build()
        val webSocket = mOkHttpClient!!.newWebSocket(request, webSocketListener)
        mOkHttpClient!!.dispatcher().executorService().shutdown()
        return webSocket
    }

    /**
     * post请求上传文件
     * params 参数
     * fileMap 图片列表
     * callback 回调
     */
    fun uploadPic(isPut: Boolean, url: String, params: Map<String, String>?, fileMap: Map<String, File>?, callback: Callback) {
        //创建OkHttpClient请求对象
        val okHttpClient = getOkHttpClient()
        //创建RequestBody 封装file参数

        val mbody = MultipartBody.Builder().setType(MultipartBody.FORM)

        if (null != params) {
            for (key in params.keys) {
                log(TAG, "key : " + key + " --> value : " + params[key])
                mbody.addFormDataPart(key, params[key])
            }
        }

        if (null != fileMap) {
            for (key in fileMap.keys) {
                log(TAG, "getPath --> " + fileMap[key]!!.path)
                mbody.addFormDataPart(key, fileMap[key]!!.name, RequestBody.create(MediaType.parse("image/jpeg"), fileMap[key]))
            }
        }


        //创建RequestBody 设置类型等
        val requestBody = mbody.build()

        //创建Request
        val request: Request

        request = Request.Builder().url(url).post(requestBody).build()
        //        if (isPut) {
        //            request = new Request.Builder().url(url).put(requestBody).build();
        //        } else {
        //        }

        //得到Call
        val call = okHttpClient!!.newCall(request)
        //执行请求
        call.enqueue(callback)

    }


    /**
     * 下载文件 以流的形式把apk写入的指定文件 得到file后进行安装
     * 参数一：请求Url
     * 参数二：保存文件的路径名
     * 参数三：保存文件的文件名
     */
    fun download(url: String, callback: Callback) {
        val request = Request.Builder().url(url).build()
        val call = getOkHttpClient()!!.newCall(request)
        call.enqueue(callback)
        //        call.enqueue(new Callback() {
        //
        //        });

    }


    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    private fun getNameFromUrl(url: String): String {
        return url.substring(url.lastIndexOf("/") + 1)
    }


    companion object {
        private val TAG = "OkHttpUtils"

        /**
         * 懒汉 安全 加同步
         * 私有的静态成员变量 只声明不创建
         * 私有的构造方法
         * 提供返回实例的静态方法
         */

        private var okHttpUtils: OkHttpUtils? = null

        //加同步安全
        val instance: OkHttpUtils?
            get() {
                if (okHttpUtils == null) synchronized(OkHttpUtils::class.java) {
                    if (okHttpUtils == null) {
                        okHttpUtils = OkHttpUtils()
                    }
                }
                return okHttpUtils
            }

        private var okHttpClient: OkHttpClient? = null

        private var mHandler: Handler? = null

        val handler: Handler?
            @Synchronized get() {
                if (mHandler == null) {
                    mHandler = Handler()
                }

                return mHandler
            }


        /**
         * 网络请求get 方法 参数添加
         *
         * @param url    地址
         * @param params 参数
         */
        private fun getUrlParam(url: String, params: Map<String, String>?): String {
            var url = url
            if (null != params) {
                val iter = params.keys.iterator()
                var i = 0
                while (iter.hasNext()) {
                    val key = iter.next()
                    if (i == 0) {
                        url += "?" + key + "=" + params[key]
                        i++
                    } else {
                        url += "&" + key + "=" + params[key]
                    }
                }
            }
            return url
        }


        /**
         * 循环打印 Map 的 key 和 value
         *
         * @param map 需要打印的 Map
         */
        private fun traversal(map: Map<String, String>?) {
            if (null == map) return
            val iter = map.keys.iterator()
            while (iter.hasNext()) {
                val key = iter.next()
                val value = map[key]
                log(TAG, "key : $key --> value : $value")

            }
        }
    }

}


