package com.blizpear.testAppCCS.features.favoriteScreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blizpear.testAppCCS.features.favoriteScreen.R
import com.blizpear.testAppCCS.features.favoriteScreen.databinding.FavoriteFragmentBinding
import com.blizpear.testAppCCS.features.favoriteScreen.presentation.FavoriteScreenViewModel
import com.blizpear.testAppCCS.features.favoriteScreen.presentation.FavoriteState
import com.blizpear.testAppCCS.features.favoriteScreen.ui.adapters.ExchangeAdapter
import com.blizpear.testAppCCS.shared.filterdialog.FilterDialogFragment
import com.blizpear.testAppCCS.shared.ui.BaseFragment
import com.blizpear.testAppCCS.shared.ui.showCustomDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteScreenFragment :
	BaseFragment<FavoriteFragmentBinding>(R.layout.favorite_fragment),
	FilterDialogFragment.FilterDialogFragmentListener {

	companion object {

		fun newInstance() = FavoriteScreenFragment()
	}

	private val viewModel: FavoriteScreenViewModel by viewModels()

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
	}

	private fun setListener() {
		binding.retryButton.setOnClickListener {
			viewModel.getData()
		}

		binding.filter.setOnClickListener {
			val filterDialogFragment =
				FilterDialogFragment.newInstance(viewModel.currentSortType)
			showCustomDialog(filterDialogFragment)
		}
	}

	private fun setObservers() {
		viewLifecycleOwner.lifecycleScope.launchWhenStarted {
			viewModel.state.collect {
				handleState(it)
			}
		}
	}

	private fun handleState(state: FavoriteState) {
		when (state) {
			is FavoriteState.Initialize,
			FavoriteState.Loading    -> renderLoading()

			is FavoriteState.Error   -> renderError()

			is FavoriteState.Content -> renderContent(state)
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

	private fun renderContent(state: FavoriteState.Content) {
		with(binding) {
			progressBar.isVisible = false
			error.isVisible = false

			content.isVisible = true

			binding.currency.text = getString(R.string.favorite_screen_currency, state.baseCurrency)
			exchangeAdapter.submitList(state.data) {
				binding.exchangeList.scrollToPosition(0)
			}
		}
	}

	override fun onDialogCloseResult(requestCode: Int) {
		viewModel.setFilter(requestCode)
	}

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FavoriteFragmentBinding =
		FavoriteFragmentBinding.inflate(inflater, container, false)
}