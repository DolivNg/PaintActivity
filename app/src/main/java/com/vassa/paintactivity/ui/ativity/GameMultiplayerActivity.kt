package com.vassa.paintactivity.ui.ativity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.vassa.paintactivity.R
import com.vassa.paintactivity.databinding.ActivityGameMultiplayerBinding

class GameMultiplayerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityGameMultiplayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityGameMultiplayerBinding.inflate(layoutInflater)
        setContentView(binding.root)



       /* val navController = findNavController(R.id.nav_host_fragment_content_game_multiplayer)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/


    }


}