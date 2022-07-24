package com.blizpear.testAppCCS.features.popularScreen

import com.blizpear.testAppCCS.features.popularScreen.ui.PopularExchangeScreenFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getPopularExchangeScreen() = FragmentScreen { PopularExchangeScreenFragment.newInstance() }