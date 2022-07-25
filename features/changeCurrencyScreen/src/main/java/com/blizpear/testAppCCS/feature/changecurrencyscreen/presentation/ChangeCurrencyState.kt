package com.blizpear.testAppCCS.feature.changecurrencyscreen.presentation

import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.entity.Currencies

sealed class ChangeCurrencyState {

	object Initialize : ChangeCurrencyState()

	object Error : ChangeCurrencyState()

	object Loading : ChangeCurrencyState()

	class Content(
		val data: List<Currencies>
	) : ChangeCurrencyState()
}