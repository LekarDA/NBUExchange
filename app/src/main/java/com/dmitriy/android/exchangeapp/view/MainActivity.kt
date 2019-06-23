package com.dmitriy.android.exchangeapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.dmitriy.android.exchangeapp.R
import com.dmitriy.android.exchangeapp.data.Currency
import com.dmitriy.android.exchangeapp.service.NBUApiService
import com.dmitriy.android.exchangeapp.view.adapter.CurrencyListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : CoroutineAppCompatActivity(){

    private lateinit var  layoutManager : LinearLayoutManager
    private lateinit var adapter : CurrencyListAdapter
    private val BASE_URL = "statdirectory/exchange"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
        val apiService = NBUApiService.create()

        launch {
            try{
                val response = apiService.getCurrentCurrency(baseUrl = BASE_URL,value = "")
                if(response.isSuccessful) this@MainActivity.onResponse(response.body()!!)
                else this@MainActivity.onFailure(response.errorBody())
            }catch (e:Exception){
                Log.i("ExchangeApp","exception" + e.toString())
            }

        }
    }

    private fun initList() {
        layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        adapter= CurrencyListAdapter()
        list.adapter = adapter
    }


    fun onFailure(error: ResponseBody?) {
        Log.i("ExchangeApp","error" + error?.string())
    }

    fun onResponse(data:List<Currency>) {
        adapter.setCurrencyList(data)
    }
}
