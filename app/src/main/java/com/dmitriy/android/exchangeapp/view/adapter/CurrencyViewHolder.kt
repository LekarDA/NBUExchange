package com.dmitriy.android.exchangeapp.view.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.dmitriy.android.exchangeapp.data.Currency
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrencyViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView), View.OnClickListener {

    private var view: View = containerView

    init {
        view.setOnClickListener(this)
    }
    fun setData(currency: Currency) {
        view.title.text = currency.name
        view.code.text = currency.code
    }

    override fun onClick(v: View?) {
        Log.d("RecyclerView", "CLICK!")
    }
}