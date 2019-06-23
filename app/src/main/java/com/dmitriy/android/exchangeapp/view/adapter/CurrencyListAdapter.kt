package com.dmitriy.android.exchangeapp.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dmitriy.android.exchangeapp.R
import com.dmitriy.android.exchangeapp.data.Currency
import com.dmitriy.android.exchangeapp.listen
import com.dmitriy.android.exchangeapp.view.ItemClickListener

class CurrencyListAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    var currendList: ArrayList<Currency>? = ArrayList()
    private lateinit var listener: ItemClickListener

    fun setCurrencyList(listOfCurrency: List<Currency>){
        currendList?.clear()
        currendList?.addAll(listOfCurrency)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.currency_item,parent,false)
        return CurrencyViewHolder(view).listen { position, type ->
            val item = currendList?.get(position)
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return currendList?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: CurrencyViewHolder, position: Int) {
        currendList?.get(position)?.let { viewHolder.setData(it) }
    }

    fun setItemClickListener(listener:ItemClickListener){
        this.listener = listener
    }
}