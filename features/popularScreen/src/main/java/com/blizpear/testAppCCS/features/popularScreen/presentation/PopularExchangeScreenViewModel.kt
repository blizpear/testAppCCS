package com.blizpear.testAppCCS.features.popularScreen.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blizpear.testAppCCS.features.popularScreen.domain.usecase.GetPopularExchangeUseCase
import com.blizpear.testAppCCS.features.popularScreen.domain.usecase.SaveFavoritesUseCase
import com.blizpear.testAppCCS.features.popularScreen.domain.usecase.UpdateLocalStorageUseCase
import com.blizpear.testAppCCS.shared.filterdialog.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularExchangeScreenViewModel @Inject constructor(
	private val getPopularExchangeUseCase: GetPopularExchangeUseCase,
	private val updateLocalStorageUseCase: UpdateLocalStorageUseCase,
	private val saveFavoritesUseCase: SaveFavoritesUseCase,
	private val sharedPreferences: SharedPreferences
) : ViewModel() {

	private val _state = MutableStateFlow<PopularExchangeState>(PopularExchangeState.Initialize)
	val state = _state.asStateFlow()

	private var _baseCurrency: String = "USD"

	var currentSortType = FilterType.ALPHABET.id

	init {
		getFromSharedPref()
		getData()
	}

	private fun getFromSharedPref() =
		setBaseCurrency(
			sharedPreferences.getString("BASE_CURRENCY", null)
		)

	fun getData() {
		updateDatabaseWithRemoteData(_baseCurrency)
		getPopularExchange()
	}

	private fun updateDatabaseWithRemoteData(baseCurrency: String = _baseCurrency) {
		viewModelScope.launch {
			try {
				_state.value = PopularExchangeState.Loading
				updateLocalStorageUseCase(baseCurrency)
			} catch (e: Exception) {
				getPopularExchange()
				Log.d("PopularExchangeScreenViewModel", "${this.javaClass.name} ${e.message}")
			}
		}
	}

	private fun getPopularExchange() {
		getPopularExchangeUseCase()
			.onEach { newExchanges ->
				if (newExchanges.isEmpty()) {
					_state.value = PopularExchangeState.Error
				} else {
					val baseCurrency = newExchanges.find {
						it.isBase
					}
					setBaseCurrency(baseCurrency?.currencyName)

					_state.value = PopularExchangeState.Content(newExchanges.sortedBy { it.currencyName }, _baseCurrency)
				}
			}.launchIn(viewModelScope)
	}

	fun setBaseCurrency(value: String?) {
		if (!value.isNullOrBlank() && _baseCurrency != value) {
			setToSharedPref(value)
			_baseCurrency = value
			getData()
		}
	}

	private fun setToSharedPref(baseCurrency: String) {
		with(sharedPreferences.edit())
		{
			putString("BASE_CURRENCY", baseCurrency)
			apply()
		}
	}

	fun setFavorite(currencyName: String): Boolean {
		return if (_state.value is PopularExchangeState.Content) {
			viewModelScope.launch {
				val exchangesList = (_state.value as PopularExchangeState.Content).data

				val newExchangeList = exchangesList.map {
					if (it.currencyName == currencyName) {
						it.copy(isFavorite = !it.isFavorite)
					} else {
						it.copy()
					}
				}
				saveFavoritesUseCase(newExchangeList)
			}
			true
		} else false
	}

	fun setFilter(type: Int) {
		if (_state.value is PopularExchangeState.Content) {

			val oldContent = _state.value as PopularExchangeState.Content
			when (type) {
				FilterType.ALPHABET.id      -> {
					currentSortType = FilterType.ALPHABET.id
					_state.value = PopularExchangeState.Content(data = oldContent.data.sortedBy { it.currencyName }, baseCurrency = oldContent.baseCurrency)
				}

				FilterType.ALPHABET_DESC.id -> {
					currentSortType = FilterType.ALPHABET_DESC.id
					_state.value =
						PopularExchangeState.Content(data = oldContent.data.sortedByDescending { it.currencyName }, baseCurrency = oldContent.baseCurrency)
				}

				FilterType.VALUE.id         -> {
					currentSortType = FilterType.VALUE.id
					_state.value = PopularExchangeState.Content(data = oldContent.data.sortedBy { it.currencyValue }, baseCurrency = oldContent.baseCurrency)
				}

				FilterType.VALUE_DESC.id    -> {
					currentSortType = FilterType.VALUE_DESC.id
					_state.value =
						PopularExchangeState.Content(data = oldContent.data.sortedByDescending { it.currencyValue }, baseCurrency = oldContent.baseCurrency)
				}
			}
		}
	}
}