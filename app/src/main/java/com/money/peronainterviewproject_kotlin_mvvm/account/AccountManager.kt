package com.money.peronainterviewproject_kotlin_mvvm.account

import android.util.Log

class AccountManager {

    companion object{

        private lateinit var onShowWelcomeInformation: OnShowWelcomeInformation

        private const val COMEBACK_TIMES = "come_back_times"

        fun setOnShowWelcomeInformation(onShowWelcomeInformation: OnShowWelcomeInformation){
            this.onShowWelcomeInformation = onShowWelcomeInformation
        }

        fun countingEnterAppTimes(){

            var count = SharedPreferencesUtils.getSharedPreferences(COMEBACK_TIMES).getInt(COMEBACK_TIMES,0)

            count++
            saveCount(count)
            Log.i("Michael", "count : $count")
            if (count >= 2){
                onShowWelcomeInformation.onShowToast("Welcome Back!")
            }
        }

        private fun saveCount(count: Int) {
            SharedPreferencesUtils.getSharedPreferences(COMEBACK_TIMES).edit().putInt(COMEBACK_TIMES,count).apply()
        }


    }

    fun interface OnShowWelcomeInformation{
        fun onShowToast(msg : String)
    }

}