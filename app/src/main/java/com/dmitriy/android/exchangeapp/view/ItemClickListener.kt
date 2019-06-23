package com.dmitriy.android.exchangeapp.view

import com.dmitriy.android.exchangeapp.data.Currency

interface ItemClickListener {
    fun onItemClick(currencyItem: Currency?)
}