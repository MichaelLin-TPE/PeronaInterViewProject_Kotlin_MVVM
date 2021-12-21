package com.money.peronainterviewproject_kotlin_mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.money.peronainterviewproject_kotlin_mvvm.json.WeatherTime

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var timeArray = ArrayList<WeatherTime>()

    private var index = 0

    private lateinit var onTimeItemClickListener: OnTimeItemClickListener

    fun setOnTimeItemClickListener(onTimeItemClickListener: OnTimeItemClickListener){
        this.onTimeItemClickListener = onTimeItemClickListener
    }

    companion object{
        const val TIME = 0
        const val PHOTO = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TIME){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.time_item_layout,parent,false)
            return TimeViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item_layout,parent,false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TimeViewHolder){

            if (index >= timeArray.size){
                index = 0
            }
            holder.showView(timeArray[position - index])
            index++
            holder.setOnTimeItemClickListener(onTimeItemClickListener)
            return
        }
        if (holder is PhotoViewHolder){
            holder.showView()
        }

    }

    override fun getItemViewType(position: Int): Int {

        return if (position % 2 == 1) PHOTO else TIME
    }

    override fun getItemCount(): Int = timeArray.size * 2

    class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val tvTime : TextView = itemView.findViewById(R.id.item_time)

        private val tvTemp : TextView = itemView.findViewById(R.id.item_temp)

        private val root : ConstraintLayout = itemView.findViewById(R.id.item_root)

        private lateinit var onTimeItemClickListener: OnTimeItemClickListener

        fun setOnTimeItemClickListener(onTimeItemClickListener: OnTimeItemClickListener){
            this.onTimeItemClickListener = onTimeItemClickListener
        }


        fun showView(weatherTime: WeatherTime) {

            tvTime.text = String.format("%s\n%s",weatherTime.startTime,weatherTime.endTime)
            if (weatherTime.parameter == null){
                return
            }
            tvTemp.text = String.format("%s",weatherTime.parameter.parameterName+weatherTime.parameter.parameterUnit)

            root.setOnClickListener {
                onTimeItemClickListener.onTimeClick(weatherTime)
            }

        }

    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun showView() {

        }

    }


    fun interface OnTimeItemClickListener{
        fun onTimeClick(data : WeatherTime)
    }
}