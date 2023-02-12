package com.example.simya.util.data

import com.example.simya.util.Constants.DEFAULT

object TodayMenuData {
    private var todayMenuTitle: String = DEFAULT
    private var todayMenuDetail: String = DEFAULT

    fun setTodayMenuTitle(todayMenuTitle: String) {
        TodayMenuData.todayMenuTitle = todayMenuTitle
    }

    fun getTodayMenuTitle(): String {
        return todayMenuTitle
    }

    fun setTodayMenuDetail(todayMenuDetail: String) {
        TodayMenuData.todayMenuDetail = todayMenuDetail
    }

    fun getTodayMenuDetail(): String {
        return todayMenuDetail
    }

}