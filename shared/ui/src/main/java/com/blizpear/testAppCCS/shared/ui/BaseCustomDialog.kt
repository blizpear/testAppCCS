package com.blizpear.testAppCCS.shared.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.ref.WeakReference

abstract class BaseCustomDialog<VB : ViewBinding, LS : BaseCustomDialog.BaseDialogListener> :
	AppCompatDialogFragment() {

	interface BaseDialogListener

	private var _binding: VB? = null
	val binding: VB
		get() = _binding ?: throw NullPointerException("Binding can't be null")

	private var _listener: WeakReference<LS?>? = null
	val listener: LS?
		get() = _listener?.get()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = bind(inflater, container, savedInstanceState)
		otherSetups()
		return binding.root
	}

	override fun onDestroyView() {
		_binding = null
		super.onDestroyView()
	}

	abstract fun bind(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): VB

	protected open fun otherSetups() {}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
		super.onCreateDialog(savedInstanceState).apply {
			requestWindowFeature(Window.FEATURE_NO_TITLE)
			window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		}

	@Suppress("UNCHECKED_CAST")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_listener = WeakReference(targetFragment as? LS)
	}

	@Suppress("UNCHECKED_CAST")
	override fun onAttach(context: Context) {
		super.onAttach(context)
		_listener = WeakReference(targetFragment as? LS)
	}

	override fun onDetach() {
		super.onDetach()
		_listener?.clear()
	}
}

inline fun <reified DF : AppCompatDialogFragment> Fragment.showCustomDialog(
	customDialog: DF,
	requestCode: Int = 0
) {
	customDialog.apply {
		val parentFM = this@showCustomDialog.parentFragmentManager
		val tag = customDialog::class.simpleName
		if (parentFM.findFragmentByTag(tag) == null) {
			setTargetFragment(this@showCustomDialog, requestCode)
			show(parentFM, tag)
		} else {
			Log.d("showCustomDialog", "$tag already exist")
		}
	}
}