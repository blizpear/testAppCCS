package com.blizpear.testAppCCS.feature.changecurrencyscreen.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.usecase.GetCurrenciesUseCase
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeCurrencyViewModel @Inject constructor(
	private val getCurrenciesUseCase: GetCurrenciesUseCase,
	private val sharedPreferences: SharedPreferences,
	private val router: Router
) : ViewModel() {

	private val _state = MutableStateFlow<ChangeCurrencyState>(ChangeCurrencyState.Initialize)
	val state
		get() = _state.asStateFlow()

	private var _currentCurrency: String = ""

	init {
		getFromSharedPref()
		getData()
	}

	private fun getFromSharedPref() =
		setBaseCurrency(
			sharedPreferences.getString("BASE_CURRENCY", null)
		)

	private fun setBaseCurrency(value: String?) {
		if (!value.isNullOrBlank()) {
			_currentCurrency = value
		}
	}

	fun getData() {
		viewModelScope.launch {
			try {
				_state.value = ChangeCurrencyState.Loading

				val currencies = getCurrenciesUseCase(_currentCurrency)
				_state.value = ChangeCurrencyState.Content(data = currencies)
			} catch (e: Exception) {
				Log.d("changeCurrencyViewModel", "${e.message}")
				_state.value = ChangeCurrencyState.Error
			}
		}
	}

	fun onItemClick(currencyName: String) {
		if (_state.value is ChangeCurrencyState.Content) {
			setBaseCurrency(currencyName)

			viewModelScope.launch {
				_state.value = ChangeCurrencyState.Content(
					(_state.value as ChangeCurrencyState.Content).data.map {
						if (it.currencies == currencyName)
							it.copy(isSelected = true)
						else
							it.copy(isSelected = false)
					})
			}
		}
	}

	fun onApplyButtonClick() {
		router.sendResult("CHANGE_CURRENCY", _currentCurrency)
		router.exit()
	}
}