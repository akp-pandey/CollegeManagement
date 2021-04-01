package com.example.expandablerecyclerview.module.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData

class ConnectivityLiveData(val context: Context):LiveData<Boolean>() {
    private var connectivityManager:ConnectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    private val networkReciver=object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }

    }

    private fun updateConnection() {
        val activeNetwork:NetworkInfo?=connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected==true)
    }

    override fun onActive() {
        super.onActive()
        context.registerReceiver(networkReciver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReciver)
    }
}