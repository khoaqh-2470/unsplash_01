package com.sun.unsplash_01.ui.search

import android.content.SearchRecentSuggestionsProvider
import com.sun.unsplash_01.BuildConfig.APPLICATION_ID

class SuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(APPLICATION_ID, DATABASE_MODE_QUERIES)
    }
}
