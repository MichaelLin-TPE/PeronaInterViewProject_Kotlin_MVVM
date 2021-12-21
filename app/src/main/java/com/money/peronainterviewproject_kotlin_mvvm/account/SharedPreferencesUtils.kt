package com.money.peronainterviewproject_kotlin_mvvm.account

import android.content.Context
import android.content.SharedPreferences
import com.money.peronainterviewproject_kotlin_mvvm.MyApplication

class SharedPreferencesUtils {

    companion object{

        fun getSharedPreferences(key:String) : SharedPreferences{

            return MyApplication.instance.getSharedPreferences(key, Context.MODE_PRIVATE)
        }

    }

}