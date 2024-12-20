package com.InfiniumImageryLLC.infiniview.model

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

class AchievementsManager private constructor(context: Context) {
    companion object {
        private const val PREFS = "achievements_prefs"
        private const val KEY = "UserAchievements"
        @Volatile private var INSTANCE: AchievementsManager? = null

        fun getInstance(context: Context): AchievementsManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AchievementsManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    private val sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    private var achievements: List<Achievement> = listOf()

    init {
        loadAchievements()
    }

    fun loadAchievements() {
        val defaults = defaultAchievements()
        val data = sp.getString(KEY, null)
        achievements = if (data != null) {
            val decoded = runCatching { Json.decodeFromString<List<Achievement>>(data) }.getOrNull() ?: defaults
            val decodedIDs = decoded.map { it.id }.toSet()
            val merged = decoded.toMutableList().apply {
                for (ach in defaults) {
                    if (!decodedIDs.contains(ach.id)) add(ach)
                }
            }
            merged
        } else {
            defaults
        }
    }

    fun saveAchievements() {
        val encoded = Json.encodeToString(achievements)
        sp.edit().putString(KEY, encoded).apply()
    }

    private fun date(month: Int, day: Int): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, month - 1)
        cal.set(Calendar.DAY_OF_MONTH, day)
        cal.set(Calendar.HOUR_OF_DAY,0)
        cal.set(Calendar.MINUTE,0)
        cal.set(Calendar.SECOND,0)
        cal.set(Calendar.MILLISECOND,0)
        return cal.timeInMillis
    }

    fun defaultAchievements(): List<Achievement> {
        val achs = mutableListOf<Achievement>()
        achs.add(Achievement("streak_7","7-Day Streak","Open the app 7 days in a row",10,AchievementType.Streak(7),false,0,7))
        achs.add(Achievement("streak_25","25-Day Streak","Open the app 25 days in a row",10,AchievementType.Streak(25),false,0,25))
        achs.add(Achievement("holiday_christmas","Merry Christmas!","Open on Christmas Day",50,AchievementType.Holiday(date(12,25)),false,0,1))
        achs.add(Achievement("holiday_newyear","Happy New Year!","Open on New Year's Day",50,AchievementType.Holiday(date(1,1)),false,0,1))
        achs.add(Achievement("videos_50","Video Watcher","Watch 50 videos",10,AchievementType.Custom("videos_watched",50),false,0,50))
        achs.add(Achievement("invite_5","Spread the Word","Invite 5 friends",10,AchievementType.Custom("invites_sent",5),false,0,5))

        achs.add(Achievement("streak_50","50-Day Streak","Open 50 days in a row",10,AchievementType.Streak(50),false,0,50))
        achs.add(Achievement("streak_100","100-Day Streak","Open 100 days in a row",10,AchievementType.Streak(100),false,0,100))

        achs.add(Achievement("holiday_valentine","Valentine's Surprise","Open on Valentine's Day",50,AchievementType.Holiday(date(2,14)),false,0,1))
        achs.add(Achievement("holiday_halloween","Halloween Haunt","Open on Halloween",50,AchievementType.Holiday(date(10,31)),false,0,1))
        achs.add(Achievement("holiday_summersolstice","Summer Solstice","Open on June 21",50,AchievementType.Holiday(date(6,21)),false,0,1))
        achs.add(Achievement("holiday_independence","Independence Day","Open on July 4th",50,AchievementType.Holiday(date(7,4)),false,0,1))
        achs.add(Achievement("holiday_workersday","Worker's Pride","Open on May 1st",50,AchievementType.Holiday(date(5,1)),false,0,1))
        achs.add(Achievement("holiday_aprilfools","April Fools","Open on April 1st",50,AchievementType.Holiday(date(4,1)),false,0,1))
        achs.add(Achievement("holiday_luckyseven","Lucky Seven","Open on July 7th",50,AchievementType.Holiday(date(7,7)),false,0,1))

        achs.add(Achievement("videos_200","Video Marathon","Watch 200 videos",10,AchievementType.Custom("videos_watched",200),false,0,200))
        achs.add(Achievement("videos_500","Video Legend","Watch 500 videos",10,AchievementType.Custom("videos_watched",500),false,0,500))
        achs.add(Achievement("invites_20","Inviter Supreme","Invite 20 friends",10,AchievementType.Custom("invites_sent",20),false,0,20))
        achs.add(Achievement("videos_1000","Binge Watcher","Watch 1000 videos",10,AchievementType.Custom("videos_watched",1000),false,0,1000))
        achs.add(Achievement("videos_25","Video Collector","Watch 25 videos",10,AchievementType.Custom("videos_watched",25),false,0,25))
        achs.add(Achievement("invites_10","Influencer","Invite 10 friends",10,AchievementType.Custom("invites_sent",10),false,0,10))
        return achs
    }

    fun updateProgress(action: String, increment: Int = 1) {
        achievements = achievements.map {
            when(it.type) {
                is AchievementType.Custom -> {
                    val t = it.type as AchievementType.Custom
                    if (t.action == action && !it.isUnlocked) {
                        val newProgress = it.currentProgress + increment
                        if (newProgress >= t.countNeeded) {
                            it.copy(currentProgress = t.countNeeded, isUnlocked = true)
                        } else {
                            it.copy(currentProgress = newProgress)
                        }
                    } else it
                }
                else -> it
            }
        }
        saveAchievements()
    }

    fun checkForStreakAchievement(currentStreak: Int) {
        achievements = achievements.map {
            if (it.type is AchievementType.Streak && !it.isUnlocked) {
                val days = (it.type as AchievementType.Streak).days
                val prog = if (currentStreak > it.target) it.target else currentStreak
                if (currentStreak >= days) {
                    it.copy(currentProgress=prog,isUnlocked=true)
                } else {
                    it.copy(currentProgress=prog)
                }
            } else it
        }
        saveAchievements()
    }

    fun checkForHolidayAchievement() {
        val today = Calendar.getInstance()
        today.set(Calendar.HOUR_OF_DAY,0)
        today.set(Calendar.MINUTE,0)
        today.set(Calendar.SECOND,0)
        today.set(Calendar.MILLISECOND,0)
        val todayMillis = today.timeInMillis

        achievements = achievements.map {
            if (!it.isUnlocked && it.type is AchievementType.Holiday) {
                val date = (it.type as AchievementType.Holiday).date
                val achDay = Calendar.getInstance()
                achDay.timeInMillis = date
                achDay.set(Calendar.HOUR_OF_DAY,0)
                achDay.set(Calendar.MINUTE,0)
                achDay.set(Calendar.SECOND,0)
                achDay.set(Calendar.MILLISECOND,0)
                val achMillis = achDay.timeInMillis
                if (achMillis == todayMillis) {
                    it.copy(isUnlocked=true, currentProgress=1)
                } else it
            } else it
        }
        saveAchievements()
    }

    fun allAchievements(): List<Achievement> = achievements
    fun totalAchievementPoints(): Int = achievements.filter { it.isUnlocked }.sumOf { it.points }
}
