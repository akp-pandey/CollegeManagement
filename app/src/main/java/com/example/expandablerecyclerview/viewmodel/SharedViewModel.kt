package com.example.expandablerecyclerview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import io.reactivex.disposables.CompositeDisposable


class SharedViewModel(application: Application):AndroidViewModel(application) {
    val compositeDisposable=CompositeDisposable()
    val progressValue by lazy { MutableLiveData<Boolean>() }


}