package com.blizpear.testAppCCS.shared.filterdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.blizpear.testAppCCS.shared.filterdialog.databinding.FilterDialogFragmentBinding
import com.blizpear.testAppCCS.shared.ui.BaseCustomDialog

private const val CURRENT_FILTER = "CURRENT_FILTER"

var FilterDialogFragment.currentFilter
	get() = arguments?.getInt(CURRENT_FILTER) ?: throw IllegalArgumentException("Arguments can't be null")
	set(value) {
		arguments = arguments?.also { it.putInt(CURRENT_FILTER, value) } ?: bundleOf(CURRENT_FILTER to value)
	}

class FilterDialogFragment :
	BaseCustomDialog<FilterDialogFragmentBinding, FilterDialogFragment.FilterDialogFragmentListener>() {

	interface FilterDialogFragmentListener : BaseDialogListener {

		fun onDialogCloseResult(requestCode: Int)
	}

	companion object {

		fun newInstance(
			currentFilter: Int
		) = FilterDialogFragment().apply {
			this.currentFilter = currentFilter
		}
	}

	override fun bind(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): FilterDialogFragmentBinding =
		FilterDialogFragmentBinding.inflate(inflater, container, false)

	override fun otherSetups() {
		isCancelable = true

		binding.radioGroup.check(setRadioButton(currentFilter))

		binding.acceptButton.setOnClickListener {
			dismiss()
			listener?.onDialogCloseResult(checkRadioButton())
		}

		binding.declineButton.setOnClickListener {
			dismiss()
		}
	}

	private fun setRadioButton(currentFilter: Int): Int =
		when (currentFilter) {
			FilterType.ALPHABET.id      -> binding.alphabetAscRb.id
			FilterType.ALPHABET_DESC.id -> binding.alphabetDescRb.id
			FilterType.VALUE.id         -> binding.valueAscRb.id
			FilterType.VALUE_DESC.id    -> binding.valueDescRb.id

			else                        -> -1
		}

	private fun checkRadioButton(): Int =
		when (binding.radioGroup.checkedRadioButtonId) {
			binding.alphabetAscRb.id  -> FilterType.ALPHABET.id

			binding.alphabetDescRb.id -> FilterType.ALPHABET_DESC.id

			binding.valueAscRb.id     -> FilterType.VALUE.id

			binding.valueDescRb.id    -> FilterType.VALUE_DESC.id

			else                      -> -1
		}
}