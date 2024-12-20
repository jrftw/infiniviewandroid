package com.InfiniumImageryLLC.infiniview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.InfiniumImageryLLC.infiniview.model.Achievement
import com.InfiniumImageryLLC.infiniview.model.AchievementsManager

class AchievementsViewModel(app: Application) : AndroidViewModel(app) {
    private val manager = AchievementsManager.getInstance(app)
    private val _achievements = MutableLiveData<List<Achievement>>(manager.allAchievements())
    val achievements: LiveData<List<Achievement>> get() = _achievements

    fun refresh() {
        _achievements.value = manager.allAchievements()
    }
}
