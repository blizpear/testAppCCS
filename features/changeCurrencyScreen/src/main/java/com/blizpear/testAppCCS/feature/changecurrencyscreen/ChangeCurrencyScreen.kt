package com.blizpear.testAppCCS.feature.changecurrencyscreen

import com.blizpear.testAppCCS.feature.changecurrencyscreen.ui.ChangeCurrencyFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getChangeCurrencyScreen() = FragmentScreen { ChangeCurrencyFragment.newInstance() }