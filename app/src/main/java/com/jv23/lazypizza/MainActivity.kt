package com.jv23.lazypizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jv23.lazypizza.app.MainViewModel
import com.jv23.lazypizza.core.presentation.designsystem.theme.LazyPizzaTheme
import com.jv23.lazypizza.core.presentation.designsystem.theme.bg
import com.jv23.lazypizza.navigation.NavigationRoot
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
          /*  .apply {
            setKeepOnScreenCondition {
                viewModel.state.value.isCheckingAuth
            }
        }*/
        enableEdgeToEdge()
        setContent {
            LazyPizzaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavigationRoot()
                }
            }
        }
    }
}