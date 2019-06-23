package com.dmitriy.android.exchangeapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dmitriy.android.exchangeapp.R
import com.dmitriy.android.exchangeapp.data.Currency
import kotlinx.android.synthetic.main.detail_currency.*

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_currency)
        val currency = intent.getParcelableExtra<Currency>(CURRENCY_ITEM)
        dateValue.setText(currency.exchangeDate)
        currentValue.setText(currency.rate.toString())
        codeValue.setText(currency.id.toString())
    }

    companion object {
        private val CURRENCY_ITEM = "currency_Item"

        fun newIntent(context: Context, currency: Currency?): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CURRENCY_ITEM, currency)
            return intent
        }
    }
}