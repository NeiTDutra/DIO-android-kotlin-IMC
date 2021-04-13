package com.example.appdio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.w("Lifecycle", "Tela sendo criada" )
    }

    override fun onStart() {
        super.onStart()
        Log.w( "Lifecycle", "Tela visível" )
    }

    override fun onResume() {
        super.onResume()
        Log.w("Lifecycle", "Tela interativa")
    }

    override fun onPause() {
        super.onPause()
        Log.w("Lifecycle", "Tela pausada sem foco")
    }

    override fun onStop() {
        super.onStop()
        Log.w("Lifecycle", "Tela parada")
    }

    override fun onRestart() {
        super.onRestart()
        Log.w("Lifecycle","Tela reiniciada")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("Lifecycle", "Tela destruída")
    }
}