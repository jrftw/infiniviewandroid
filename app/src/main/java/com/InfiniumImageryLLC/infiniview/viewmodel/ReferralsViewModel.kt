package com.InfiniumImageryLLC.infiniview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.InfiniumImageryLLC.infiniview.model.ReferralsManager

class ReferralsViewModel(app: Application): AndroidViewModel(app) {
    private val manager = ReferralsManager.getInstance(app)
    val referralCount = MutableLiveData(manager.totalReferrals())
    val alertMessage = MutableLiveData<String?>(null)

    fun addReferrals(usernames: List<String>) {
        val result = manager.addReferrals(usernames)
        if (!result) {
            alertMessage.value = "Could not add referrals. Check rules."
        } else {
            referralCount.value = manager.totalReferrals()
        }
    }
}
