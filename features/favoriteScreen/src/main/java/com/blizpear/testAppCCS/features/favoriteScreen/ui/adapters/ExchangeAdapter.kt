package com.blizpear.testAppCCS.features.favoriteScreen.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.blizpear.testAppCCS.features.favoriteScreen.domain.entity.FavoriteExchange
import com.blizpear.testAppCCS.features.favoriteScreen.ui.viewholders.ExchangeHolder

class ExchangeAdapter(
	private val onFavoriteClick: (String) -> Unit
) : ListAdapter<FavoriteExchange, ExchangeHolder>(ExchangeDiffUtil()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeHolder =
		ExchangeHolder.from(parent)

	override fun onBindViewHolder(holder: ExchangeHolder, position: Int) {
		holder.bind(getItem(position), onFavoriteClick)
	}

	private class ExchangeDiffUtil : DiffUtil.ItemCallback<FavoriteExchange>() {

		override fun areItemsTheSame(oldItem: FavoriteExchange, newItem: FavoriteExchange): Boolean =
			oldItem.currencyName == newItem.currencyName

		override fun areContentsTheSame(oldItem: FavoriteExchange, newItem: FavoriteExchange): Boolean =
			oldItem == newItem
	}
}