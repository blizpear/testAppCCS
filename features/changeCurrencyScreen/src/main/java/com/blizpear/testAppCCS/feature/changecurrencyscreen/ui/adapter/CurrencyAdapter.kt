package com.blizpear.testAppCCS.feature.changecurrencyscreen.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.entity.Currencies
import com.blizpear.testAppCCS.feature.changecurrencyscreen.ui.holder.CurrencyHolder

class CurrencyAdapter(
	private val onItemClick: (String) -> Unit
) : ListAdapter<Currencies, CurrencyHolder>(CurrencyDiffUtil()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder =
		CurrencyHolder.from(parent)

	override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
		holder.bind(getItem(position), onItemClick)
	}

	private class CurrencyDiffUtil : DiffUtil.ItemCallback<Currencies>() {

		override fun areItemsTheSame(oldItem: Currencies, newItem: Currencies): Boolean =
			oldItem.currencies == newItem.currencies

		override fun areContentsTheSame(oldItem: Currencies, newItem: Currencies): Boolean =
			oldItem == newItem
	}
}