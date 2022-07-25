package com.blizpear.testAppCCS.feature.changecurrencyscreen.ui.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.blizpear.testAppCCS.feature.changecurrencyscreen.databinding.CurrencyItemBinding
import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.entity.Currencies

class CurrencyHolder(
	private val binding: CurrencyItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): CurrencyHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = CurrencyItemBinding.inflate(inflater, parent, false)
			return CurrencyHolder(binding)
		}
	}

	fun bind(
		currencies: Currencies,
		onItemClick: (String) -> Unit
	) {
		binding.currencyText.text = currencies.currencies
		binding.selectedIcon.isVisible = currencies.isSelected
		binding.currencyItem.setOnClickListener {
			onItemClick(currencies.currencies)
		}
	}
}