package com.kim.weather.bean


data class AirQuality(

        var aqi: Int = 0,
        var city: String? = null,
        var district: String? = null,
        var fetureData: List<FetureData>? = null,
        var hourData: List<HourData>? = null,
        var no2: Int = 0,
        var pm10: Int = 0,
        var pm25: Int = 0,
        var province: String? = null,
        var quality: String? = null,
        var so2: Int = 0,
        var updateTime: String? = null
)
