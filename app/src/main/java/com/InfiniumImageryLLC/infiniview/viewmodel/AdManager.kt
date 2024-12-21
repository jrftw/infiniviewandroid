package com.InfiniumImageryLLC.InfiniView.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AdManagerViewModel(private val context: Context) : ViewModel() {
    private val prefs: SharedPreferences = context.getSharedPreferences("ad_prefs", Context.MODE_PRIVATE)
    private val _adsEnabled = MutableStateFlow(true)
    val adsEnabled: StateFlow<Boolean> = _adsEnabled

    private val _removeAdsPurchased = MutableStateFlow(prefs.getBoolean("removeAdsPurchasedKey", false))
    val removeAdsPurchased: StateFlow<Boolean> = _removeAdsPurchased

    fun markRemoveAdsPurchased() {
        _removeAdsPurchased.update { true }
        prefs.edit().putBoolean("removeAdsPurchasedKey", true).apply()
    }

    fun shouldDisableAds(): Boolean {
        // No direct equivalent to iOS test flight or simulator checks, you can mock logic here
        return false
    }
}

class AdManagerViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AdManagerViewModel(context) as T
    }
}
