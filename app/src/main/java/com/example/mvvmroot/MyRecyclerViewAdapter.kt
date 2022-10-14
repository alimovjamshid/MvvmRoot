package com.example.mvvmroot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmroot.databinding.ListItemBinding
import com.example.mvvmroot.db.Subscriber

class MyRecyclerViewAdapter(private val subscriberList:List<Subscriber>):RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater:LayoutInflater=LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position])
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }


}

class MyViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(subscriber: Subscriber){
        binding.nameTxtView.text = subscriber.name
        binding.emailTxtView.text = subscriber.email
    }
}
