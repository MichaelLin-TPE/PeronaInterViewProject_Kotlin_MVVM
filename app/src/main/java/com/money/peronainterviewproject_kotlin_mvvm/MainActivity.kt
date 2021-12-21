package com.money.peronainterviewproject_kotlin_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.money.peronainterviewproject_kotlin_mvvm.account.AccountManager
import com.money.peronainterviewproject_kotlin_mvvm.databinding.ActivityMainBinding
import com.money.peronainterviewproject_kotlin_mvvm.json.WeatherTime

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: MainAdapter

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this,MainViewModel.MainViewModelFactory()).get(MainViewModel::class.java)
    }

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding =  DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        dataBinding.vm = viewModel
        dataBinding.lifecycleOwner = this
        checkComeBackTime()
        initView()

    }

    private fun checkComeBackTime() {
        AccountManager.setOnShowWelcomeInformation{
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        }
        AccountManager.countingEnterAppTimes()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onActivityStart()
        handleLiveData()
    }

    private fun handleLiveData() {
        viewModel.showToastLiveData.observe(this,{
            if (it.isEmpty()){
                return@observe
            }
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })

        viewModel.showTimeListLiveData.observe(this,{
            adapter = MainAdapter()
            adapter.timeArray = it
            dataBinding.recyclerView.adapter = adapter
            adapter.setOnTimeItemClickListener{data->
                intentToPage2(data)
            }
        })
    }

    private fun intentToPage2(data: WeatherTime) {

        val it = Intent(this,MainActivity2::class.java)
        it.putExtra("data",data)
        startActivity(it)

    }

    override fun onPause() {
        super.onPause()
        viewModel.showToastLiveData.value = ""
        viewModel.showToastLiveData.removeObservers(this)

        viewModel.showTimeListLiveData.value = ArrayList()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onActivityDestroy()
    }

    private fun initView() {
        dataBinding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}