package com.InfiniumImageryLLC.infiniview.model

import android.content.Context

class ReferralsManager private constructor(context: Context) {
    companion object {
        private const val PREFS = "referrals_prefs"
        private const val REFERRALS_KEY = "referrals"
        @Volatile private var INSTANCE: ReferralsManager? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: ReferralsManager(context.applicationContext).also { INSTANCE = it }
        }
    }

    private val sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun addReferrals(usernames: List<String>): Boolean {
        val current = sp.getStringSet(REFERRALS_KEY, emptySet())?.toMutableSet() ?: mutableSetOf()
        if (current.size + usernames.size > 5) return false
        if (usernames.any { it.contains("@") || it.lowercase() != it }) return false
        current.addAll(usernames)
        sp.edit().putStringSet(REFERRALS_KEY, current).apply()
        return true
    }

    fun totalReferrals(): Int {
        return sp.getStringSet(REFERRALS_KEY, emptySet())?.size ?: 0
    }
}
