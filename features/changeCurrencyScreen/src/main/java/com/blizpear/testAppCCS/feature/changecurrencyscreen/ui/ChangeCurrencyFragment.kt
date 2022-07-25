package com.blizpear.testAppCCS.feature.changecurrencyscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blizpear.testAppCCS.feature.changecurrencyscreen.R
import com.blizpear.testAppCCS.feature.changecurrencyscreen.databinding.ChangeCurrencyFragmentBinding
import com.blizpear.testAppCCS.feature.changecurrencyscreen.presentation.ChangeCurrencyState
import com.blizpear.testAppCCS.feature.changecurrencyscreen.presentation.ChangeCurrencyViewModel
import com.blizpear.testAppCCS.feature.changecurrencyscreen.ui.adapter.CurrencyAdapter
import com.blizpear.testAppCCS.shared.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeCurrencyFragment :
	BaseFragment<ChangeCurrencyFragmentBinding>(R.layout.change_currency_fragment) {

	companion object {

		fun newInstance() = ChangeCurrencyFragment()
	}

	private val viewModel: ChangeCurrencyViewModel by viewModels()

	private lateinit var currencyAdapter: CurrencyAdapter

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setAdapter()
		setListener()
		setObservers()
	}

	private fun setAdapter() {
		currencyAdapter = CurrencyAdapter(viewModel::onItemClick)
		binding.currenciesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		binding.currenciesList.adapter = currencyAdapter
	}

	private fun setListener() {
		binding.retryButton.setOnClickListener {
			viewModel.getData()
		}
		binding.buttonApply.setOnClickListener {
			viewModel.onApplyButtonClick()
		}
	}

	private fun setObservers() {
		viewLifecycleOwner.lifecycleScope.launchWhenStarted {
			viewModel.state.collect {
				handleState(it)
			}
		}
	}

	private fun handleState(state: ChangeCurrencyState) {
		when (state) {
			is ChangeCurrencyState.Initialize,
			ChangeCurrencyState.Loading    -> renderLoading()

			is ChangeCurrencyState.Error   -> renderError()

			is ChangeCurrencyState.Content -> renderContent(state)
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

	private fun renderContent(state: ChangeCurrencyState.Content) {
		with(binding) {
			progressBar.isVisible = false
			error.isVisible = false

			content.isVisible = true

			currencyAdapter.submitList(state.data)
		}
	}

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ChangeCurrencyFragmentBinding =
		ChangeCurrencyFragmentBinding.inflate(inflater, container, false)
}