package com.example.hahaton.utils

import android.content.Context

object FavoritesManager {
    private const val PREFS_NAME = "favorites_prefs"
    private const val KEY_FAVORITES = "favorites_list"

    fun addFavorite(context: Context, eventId: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val set = prefs.getStringSet(KEY_FAVORITES, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        set.add(eventId)
        prefs.edit().putStringSet(KEY_FAVORITES, set).apply()
    }

    fun removeFavorite(context: Context, eventId: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val set = prefs.getStringSet(KEY_FAVORITES, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        set.remove(eventId)
        prefs.edit().putStringSet(KEY_FAVORITES, set).apply()
    }

    fun isFavorite(context: Context, eventId: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val set = prefs.getStringSet(KEY_FAVORITES, mutableSetOf()) ?: emptySet()
        return set.contains(eventId)
    }
}
