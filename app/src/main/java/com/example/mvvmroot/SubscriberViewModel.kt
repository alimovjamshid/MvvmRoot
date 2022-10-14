package com.example.mvvmroot

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmroot.db.Subscriber
import com.example.mvvmroot.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name:String = inputName.value!!
        val email:String = inputEmail.value!!
        insert(Subscriber(0,name,email))
        inputName.value=null
        inputEmail.value=null
    }

    fun clearAllDelete() {
        clearAll()
    }

    private fun insert(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.insert(subscriber)
    }

    fun update(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.update(subscriber)
    }

    fun delete(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.delete(subscriber)
    }

    private fun clearAll(): Job = viewModelScope.launch {
        repository.deleteAll()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        Log.e("abc", "abc")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        Log.e("abc", "abc")
    }
}