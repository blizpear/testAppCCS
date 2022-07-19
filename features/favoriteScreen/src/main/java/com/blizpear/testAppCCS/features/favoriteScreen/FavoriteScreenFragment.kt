package com.blizpear.testAppCCS.features.favoriteScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FavoriteScreenFragment : Fragment() {

	companion object {

		fun newInstance() = FavoriteScreenFragment()
	}

	private lateinit var viewModel: FavoriteScreenViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.favorite_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProvider(this).get(FavoriteScreenViewModel::class.java)
		// TODO: Use the ViewModel
	}

}