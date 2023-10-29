package com.space.jetpack_compose_showcase

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class ComposeActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            Greeting(name = "Space International")
        }
    }

    @Composable
    fun Greeting(name: String){
        Text("Welcome, $name!")
    }
}