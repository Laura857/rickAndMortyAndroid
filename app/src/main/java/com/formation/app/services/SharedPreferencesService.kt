package com.formation.app.services

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesService @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val DISPLAY_ONBOARDING = "DISPLAY_ONBOARDING"
        private const val ISLOGGED = "ISLOGGED"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    internal var displayOnBoarding: Boolean
        get() = prefs.getBoolean(DISPLAY_ONBOARDING, true)
        set(value) = prefs.edit { putBoolean(DISPLAY_ONBOARDING, value) }

}
