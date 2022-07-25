package com.blizpear.testappccs.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blizpear.testAppCCS.features.favoriteScreen.ui.FavoriteScreenFragment
import com.blizpear.testAppCCS.features.popularScreen.ui.PopularExchangeScreenFragment
import com.blizpear.testappccs.R
import com.blizpear.testappccs.databinding.ActivityMainBinding
import com.blizpear.testappccs.viewModel.MainActivityViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	@Inject
	lateinit var navigatorHolder: NavigatorHolder
	private val navigator = AppNavigator(this, R.id.fragment_container)

	private var _binding: ActivityMainBinding? = null
	private val binding get() = _binding!!

	private val viewModel: MainActivityViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		_binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.bottomNav.setOnItemSelectedListener {
			if (binding.bottomNav.menu.findItem(it.itemId).isChecked) {
				false
			} else {
				when (it.itemId) {
					R.id.favorite_item -> viewModel.navigateToFavoriteScreen()

					R.id.popular_item  -> viewModel.navigateToPopularScreen()

					else               -> return@setOnItemSelectedListener false
				}
			}
		}
	}

	override fun onBackPressed() {
		super.onBackPressed()

		when (supportFragmentManager.findFragmentById(R.id.fragment_container)) {
			is FavoriteScreenFragment -> {
				binding.bottomNav.selectedItemId = R.id.favorite_item
			}

			is PopularExchangeScreenFragment -> {
				binding.bottomNav.selectedItemId = R.id.popular_item
			}
		}
	}

	override fun onStart() {
		super.onStart()
		navigatorHolder.setNavigator(navigator)
	}

	override fun onStop() {
		super.onStop()
		navigatorHolder.removeNavigator()
	}

	override fun onDestroy() {
		super.onDestroy()

		_binding = null
	}
}