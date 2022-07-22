package com.blizpear.testappccs.navigation

import com.github.terrakok.cicerone.Cicerone

fun getCicerone() = Cicerone.create().apply {
	router.newRootScreen(provideRootScreen())
}