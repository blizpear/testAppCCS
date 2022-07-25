package com.blizpear.testAppCCS.features.favoriteScreen.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blizpear.testAppCCS.features.favoriteScreen.domain.usecase.GetFavoriteExchangeUseCase
import com.blizpear.testAppCCS.features.favoriteScreen.domain.usecase.SaveFavoritesUseCase
import com.blizpear.testAppCCS.shared.filterdialog.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
	private val getFavoriteExchangeUseCase: GetFavoriteExchangeUseCase,
	private val saveFavoritesUseCase: SaveFavoritesUseCase,
	private val sharedPreferences: SharedPreferences
) : ViewModel() {

	private val _state = MutableStateFlow<FavoriteState>(FavoriteState.Initialize)
	val state
		get() = _state.asStateFlow()

	var currentSortType = FilterType.ALPHABET.id

	init {
		getData()
	}

	fun getData() {
		_state.value = FavoriteState.Loading
		getFavoriteExchangeUseCase()
			.onEach { newFavorites ->
				if (newFavorites.isEmpty()) {
					_state.value = FavoriteState.Error
				} else {
					_state.value = FavoriteState.Content(newFavorites, getBaseCurrency())
					setFilter()
				}
			}.launchIn(viewModelScope)
	}

	private fun getBaseCurrency(): String = sharedPreferences.getString("BASE_CURRENCY", "") ?: ""

	fun setFilter(type: Int = currentSortType) {
		if (_state.value is FavoriteState.Content) {

			val oldContent = _state.value as FavoriteState.Content
			when (type) {
				FilterType.ALPHABET.id      -> {
					currentSortType = FilterType.ALPHABET.id
					_state.value = FavoriteState.Content(data = oldContent.data.sortedBy { it.currencyName }, baseCurrency = oldContent.baseCurrency)
				}

				FilterType.ALPHABET_DESC.id -> {
					currentSortType = FilterType.ALPHABET_DESC.id
					_state.value = FavoriteState.Content(data = oldContent.data.sortedByDescending { it.currencyName }, baseCurrency = oldContent.baseCurrency)
				}

				FilterType.VALUE.id         -> {
					currentSortType = FilterType.VALUE.id
					_state.value = FavoriteState.Content(data = oldContent.data.sortedBy { it.currencyValue }, baseCurrency = oldContent.baseCurrency)
				}

				FilterType.VALUE_DESC.id    -> {
					currentSortType = FilterType.VALUE_DESC.id
					_state.value = FavoriteState.Content(data = oldContent.data.sortedByDescending { it.currencyValue }, baseCurrency = oldContent.baseCurrency)
				}
			}
		}
	}

	fun setFavorite(currencyName: String) {
		if (_state.value is FavoriteState.Content) {
			viewModelScope.launch {
				val exchangesList = (_state.value as FavoriteState.Content).data

				val newExchangeList = exchangesList.map {
					if (it.currencyName == currencyName) {
						it.copy(isFavorite = !it.isFavorite)
					} else {
						it.copy()
					}
				}
				saveFavoritesUseCase(newExchangeList)
			}

		}
	}
}