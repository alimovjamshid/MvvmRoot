package com.example.mvvmroot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmroot.databinding.ActivityMainBinding
import com.example.mvvmroot.db.SubscriberDAO
import com.example.mvvmroot.db.SubscriberDataBase
import com.example.mvvmroot.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val database = SubscriberDataBase.getInstance(this)
        val dao:SubscriberDAO = database.subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory)[SubscriberViewModel::class.java]
        binding.myViewModel=subscriberViewModel
        binding.lifecycleOwner=this
        initRecyclerView()
    }
    private fun initRecyclerView(){
        binding.subscriberRecycleview.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()
    }

    private fun displaySubscriberList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            binding.subscriberRecycleview.adapter = MyRecyclerViewAdapter(it)
        })
    }
}