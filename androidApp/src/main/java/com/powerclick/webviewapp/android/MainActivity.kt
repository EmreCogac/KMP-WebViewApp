package com.powerclick.webviewapp.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

import com.google.accompanist.web.LoadingState

import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


       setContent{
               MyApplicationTheme() {
                   WebView()
               }
       }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebView(){
    val navigator = rememberWebViewNavigator()
    val systemUiController: SystemUiController = rememberSystemUiController()

    systemUiController.isStatusBarVisible = false // Status bar
    systemUiController.isNavigationBarVisible = false // Navigation bar
    systemUiController.isSystemBarsVisible = false // Status & Navigation bars
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val state = rememberWebViewState("https://mental.fm")

//        TopAppBar(
//            title = { },
//            navigationIcon = {
//                if (navigator.canGoBack) {
//                    IconButton(onClick = { navigator.navigateBack() }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            }
//        )


        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth(

                )
            )
        }
        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            navigator = navigator,
            onCreated = { it.settings.javaScriptEnabled = true
          }

        )
      Column(
          modifier = Modifier
              .fillMaxWidth(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
          BottomAppBar(
              modifier = Modifier
                  .padding(vertical = 2.dp)
                  .fillMaxWidth()
                  .background(Color.White)
              ,


              actions = {
                  Row(
                      horizontalArrangement = Arrangement.SpaceAround,
                      modifier = Modifier.fillMaxWidth()
                  ) {
                      IconButton(onClick = { navigator.loadUrl("https://mental.fm")
                            }) {
                          Icon(
                              imageVector = Icons.Default.PlayArrow,
                              contentDescription = "Ana Sayfa"
                          )
                      }
                      IconButton(onClick = { navigator.loadUrl("https://mental.fm/hakkinda/") }) {

                          Icon(
                              imageVector = Icons.Default.Info,
                              contentDescription = "hakkında"
                          )
                      }
                      IconButton(onClick = { navigator.loadUrl("https://mental.fm/blog/") }) {
                          Icon(
                              imageVector = Icons.Default.List,
                              contentDescription = "blog"
                          )
                      }
                      IconButton(onClick = { navigator.loadUrl("https://mental.fm/bize-ulasin/") }) {
                          Icon(
                              imageVector = Icons.Default.Email,
                              contentDescription = "bize ulaşın"
                          )
                      }


                  }
              }
          )
      }

    }
}




