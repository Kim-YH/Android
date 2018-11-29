package com.kim.weather.bean

data class QueryBean(
        var airCondition: String? = null,
        var airQuality: AirQuality? = null,
        var city: String? = null,
        var coldIndex: String? = null,
        var date: String? = null,
        var distrct: String? = null,
        var dressingIndex: String? = null,
        var exerciseIndex: String? = null,
        var future: List<Future>? = null,
        var humidity: String? = null,
        var pollutionIndex: String? = null,
        var province: String? = null,
        var sunrise: String? = null,
        var sunset: String? = null,
        var temperature: String? = null,
        var time: String? = null,
        var updateTime: String? = null,
        var washIndex: String? = null,
        var weather: String? = null,
        var week: String? = null,
        var wind: String? = null
        )
