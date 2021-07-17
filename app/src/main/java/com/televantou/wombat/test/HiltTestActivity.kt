package com.televantou.wombat.test

/**
 * Created by Eirini Televantou on 17/07/2021 for Wombat.
 */
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

//Required for using FragmentScenario in UI tests
@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity()