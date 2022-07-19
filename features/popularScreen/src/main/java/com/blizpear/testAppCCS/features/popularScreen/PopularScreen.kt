package com.blizpear.testAppCCS.features.popularScreen

import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getPopularScreen() = FragmentScreen { PopularScreenFragment.newInstance() }