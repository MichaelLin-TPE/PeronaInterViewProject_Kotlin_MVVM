package com.money.peronainterviewproject_kotlin_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.money.peronainterviewproject_kotlin_mvvm.json.WeatherTime

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        val tvInfo = findViewById<TextView>(R.id.tv_information)

        intent.extras.let {

            val data = it?.getSerializable("data") as WeatherTime

            tvInfo.text = String.format("%s\n%s\n%s",data.startTime,data.endTime,data.parameter.parameterName+data.parameter.parameterUnit)
        }


    }
}