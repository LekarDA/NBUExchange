package com.dmitriy.android.exchangeapp.view

import android.content.Intent
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
import java.lang.Exception

class MainActivity : CoroutineAppCompatActivity(),ItemClickListener{


    private val CURRENCY_DATA = "CURRENCY_DATA"
    private lateinit var  layoutManager : LinearLayoutManager
    private lateinit var adapter : CurrencyListAdapter
    private val BASE_URL = "statdirectory/exchange"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
        val apiService = NBUApiService.create()
        if (savedInstanceState != null) adapter.setCurrencyList(savedInstanceState.getParcelableArrayList(CURRENCY_DATA))
        else {
            launch {
                try {
                    val response = apiService.getCurrentCurrency(baseUrl = BASE_URL, value = "")
                    if (response.isSuccessful) this@MainActivity.onResponse(response.body()!!)
                    else this@MainActivity.onFailure(response.errorBody())
                } catch (e: Exception) {
                    Log.i("ExchangeApp", "exception" + e.toString())
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelableArrayList(CURRENCY_DATA, adapter.currendList)
        super.onSaveInstanceState(outState)
    }

    private fun initList() {
        layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        adapter= CurrencyListAdapter()
        list.adapter = adapter
        adapter.setItemClickListener(this)
    }


    fun onFailure(error: ResponseBody?) {
        Log.i("ExchangeApp","error" + error?.string())
    }

    fun onResponse(data:List<Currency>) {
        adapter.setCurrencyList(data)
    }

    override fun onItemClick(currencyItem: Currency?) {
        val intent = DetailActivity.newIntent(this, currencyItem)
        startActivity(intent)
    }
}
