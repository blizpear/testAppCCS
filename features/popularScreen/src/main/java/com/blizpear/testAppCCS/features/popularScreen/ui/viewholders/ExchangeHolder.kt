package com.blizpear.testAppCCS.features.popularScreen.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blizpear.testAppCCS.features.popularScreen.R
import com.blizpear.testAppCCS.features.popularScreen.databinding.ExchangeItemBinding
import com.blizpear.testAppCCS.features.popularScreen.domain.entity.PopularExchange
import java.util.*

class ExchangeHolder(
	private val binding: ExchangeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): ExchangeHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = ExchangeItemBinding.inflate(inflater, parent, false)
			return ExchangeHolder(binding)
		}
	}

	fun bind(
		popularExchange: PopularExchange,
		onFavoriteClick: (String) -> Unit
	) {
		binding.exchangeText.text = binding.root.context.getString(
			R.string.exchange,
			popularExchange.currencyName.uppercase(Locale.getDefault()),
			popularExchange.currencyValue
		)
		binding.favIcon.setImageResource(setIcon(popularExchange.isFavorite))

		binding.favIcon.setOnClickListener {
			onFavoriteClick(popularExchange.currencyName)
		}
	}

	private fun setIcon(isFav: Boolean): Int =
		if (isFav)
			com.blizpear.testAppCCS.shared.ui.R.drawable.yellow_star
		else
			com.blizpear.testAppCCS.shared.ui.R.drawable.empty_star
}