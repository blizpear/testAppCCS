package com.blizpear.testAppCCS.features.favoriteScreen

import com.blizpear.testAppCCS.features.favoriteScreen.ui.FavoriteScreenFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getFavoriteScreen() = FragmentScreen { FavoriteScreenFragment.newInstance() }