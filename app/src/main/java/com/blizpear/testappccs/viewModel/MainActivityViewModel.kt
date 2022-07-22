package com.blizpear.testappccs.viewModel

import androidx.lifecycle.ViewModel
import com.blizpear.testAppCCS.features.favoriteScreen.getFavoriteScreen
import com.blizpear.testAppCCS.features.popularScreen.getPopularExchangeScreen
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
	private val router: Router
) : ViewModel() {

	fun navigateToFavoriteScreen(): Boolean {
		router.navigateTo(getFavoriteScreen())
		return true
	}

	fun navigateToPopularScreen(): Boolean {
		router.navigateTo(getPopularExchangeScreen())
		return true
	}
}