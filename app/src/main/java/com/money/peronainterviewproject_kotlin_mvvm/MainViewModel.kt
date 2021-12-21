package com.money.peronainterviewproject_kotlin_mvvm

import android.util.Log
import androidx.lifecycle.*
import com.money.peronainterviewproject_kotlin_mvvm.api.RetrofitManager
import com.money.peronainterviewproject_kotlin_mvvm.json.WeatherObject
import com.money.peronainterviewproject_kotlin_mvvm.json.WeatherTime
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {


    val showProgressBarLiveData = MutableLiveData(false)

    val showToastLiveData = MutableLiveData("")

    val showTimeListLiveData = MutableLiveData<ArrayList<WeatherTime>>()

    private val disposable = CompositeDisposable()

    fun onActivityStart() {

        showProgressBarLiveData.value = true

        RetrofitManager.getRequestApi().getWeatherApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<WeatherObject> {
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onNext(data: WeatherObject) {
                    showProgressBarLiveData.value = false
                    Log.i("Michael", "catch weatherApi : " + data.records.datasetDescription)
                    if (!isDataCorrect(data)){
                        showToastLiveData.value = "Data error , Please try again later."
                        return
                    }
                    val timeArray = data.records.locationArrayList[0].elementArrayList[0].timeArrayList
                    showTimeListLiveData.value = timeArray

                }

                override fun onError(e: Throwable) {
                    showProgressBarLiveData.value = false
                    Log.i("Michael", "getWeatherApi error : $e")
                    showToastLiveData.value = "Connect Error TimeOut , Please try again later."
                }

                override fun onComplete() {

                    Log.i("Michael", "getWeatherApi complete")
                }

            })


    }

    private fun isDataCorrect(data: WeatherObject): Boolean {

        if (data.records == null ||
                data.records.locationArrayList.isNullOrEmpty() ||
                data.records.locationArrayList[0].elementArrayList.isNullOrEmpty() ||
                data.records.locationArrayList[0].elementArrayList[0].timeArrayList.isNullOrEmpty()){

            return false
        }
        return true
    }

    fun onActivityDestroy() {
        disposable.clear()
    }

    open class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }

}