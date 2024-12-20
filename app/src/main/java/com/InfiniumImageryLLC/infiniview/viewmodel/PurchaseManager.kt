package com.InfiniumImageryLLC.infiniview.viewmodel

import androidx.lifecycle.ViewModel

class PurchaseManager : ViewModel() {
    fun purchaseRemoveAds(onResult: (Boolean) -> Unit) {
        // Integrate Play Billing. Placeholder:
        onResult(true)
    }

    fun restorePurchases(onResult: (Boolean) -> Unit) {
        // Placeholder
        onResult(true)
    }
}
