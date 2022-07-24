package com.blizpear.testAppCCS.features.popularScreen.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.blizpear.testAppCCS.features.popularScreen.domain.entity.PopularExchange
import com.blizpear.testAppCCS.features.popularScreen.ui.viewholders.ExchangeHolder

class ExchangeAdapter(
	private val onFavoriteClick: (String) -> Unit
) : ListAdapter<PopularExchange, ExchangeHolder>(ExchangeDiffUtil()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeHolder =
		ExchangeHolder.from(parent)

	override fun onBindViewHolder(holder: ExchangeHolder, position: Int) {
		holder.bind(getItem(position), onFavoriteClick)
	}

	private class ExchangeDiffUtil : DiffUtil.ItemCallback<PopularExchange>() {

		override fun areItemsTheSame(oldItem: PopularExchange, newItem: PopularExchange): Boolean =
			oldItem.currencyName == newItem.currencyName

		override fun areContentsTheSame(oldItem: PopularExchange, newItem: PopularExchange): Boolean =
			oldItem == newItem
	}
}