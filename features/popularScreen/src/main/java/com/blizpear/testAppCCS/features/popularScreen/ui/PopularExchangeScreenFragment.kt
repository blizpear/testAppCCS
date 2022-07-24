package com.blizpear.testAppCCS.features.popularScreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blizpear.testAppCCS.features.popularScreen.R
import com.blizpear.testAppCCS.features.popularScreen.databinding.PopularExchangeFragmentBinding
import com.blizpear.testAppCCS.features.popularScreen.presentation.PopularExchangeScreenViewModel
import com.blizpear.testAppCCS.features.popularScreen.presentation.PopularExchangeState
import com.blizpear.testAppCCS.features.popularScreen.ui.adapters.ExchangeAdapter
import com.blizpear.testAppCCS.shared.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularExchangeScreenFragment :
	BaseFragment<PopularExchangeFragmentBinding>(R.layout.popular_exchange_fragment) {

	companion object {

		fun newInstance() = PopularExchangeScreenFragment()
	}

	private val viewModel: PopularExchangeScreenViewModel by viewModels()

	private lateinit var exchangeAdapter: ExchangeAdapter

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setAdapter()
		setListener()
		setObservers()
	}

	private fun setAdapter() {
		exchangeAdapter = ExchangeAdapter(viewModel::setFavorite)
		binding.exchangeList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		binding.exchangeList.adapter = exchangeAdapter
		binding.exchangeList.scrollToPosition(0)
	}

	private fun setListener() {
		binding.retryButton.setOnClickListener {
			viewModel.getData()
		}

		binding.changeCurrency.setOnClickListener {
			Toast.makeText(requireContext(), "change clicked", Toast.LENGTH_SHORT).show()
		}

		binding.filter.setOnClickListener {
			Toast.makeText(requireContext(), "filter clicked", Toast.LENGTH_SHORT).show()
		}
	}

	private fun setObservers() {
		viewLifecycleOwner.lifecycleScope.launchWhenStarted {
			viewModel.state.collect {
				handleState(it)
			}
		}
	}

	private fun handleState(state: PopularExchangeState) {
		when (state) {
			is PopularExchangeState.Initialize,
			PopularExchangeState.Loading    -> renderLoading()

			is PopularExchangeState.Error   -> renderError()

			is PopularExchangeState.Content -> renderContent(state)
		}
	}

	private fun renderLoading() {
		with(binding) {
			content.isVisible = false
			error.isVisible = false

			progressBar.isVisible = true
		}
	}

	private fun renderError() {
		with(binding) {
			progressBar.isVisible = false
			content.isVisible = false

			error.isVisible = true
		}
	}

	private fun renderContent(state: PopularExchangeState.Content) {
		with(binding) {
			progressBar.isVisible = false
			error.isVisible = false

			content.isVisible = true

			binding.currency.text = getString(R.string.currency, state.baseCurrency)
			exchangeAdapter.submitList(state.data)
		}
	}

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): PopularExchangeFragmentBinding =
		PopularExchangeFragmentBinding.inflate(inflater, container, false)
}