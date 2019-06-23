package com.dmitriy.android.exchangeapp.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dmitriy.android.exchangeapp.R
import com.dmitriy.android.exchangeapp.data.Currency

class CurrencyListAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    private  var currendList: ArrayList<Currency>? = ArrayList()

    fun setCurrencyList(listOfCurrency: List<Currency>){
        currendList?.clear()
        currendList?.addAll(listOfCurrency)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.currency_item,parent,false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currendList?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: CurrencyViewHolder, position: Int) {
        currendList?.get(position)?.let { viewHolder.setData(it) }
    }
}