package com.example.appdio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        Log.w("Lifecycle", "Tela sendo criada" )
    }

    private fun setListeners() {
        buttonCalcular?.setOnClickListener {
            exibirFaixaDePeso()
        }
    }

    private fun exibirFaixaDePeso() {
        val peso = numberPeso?.text.toString()
        val altura = numberAltura?.text.toString()
        val imc = calcularIMC(peso, altura)

        imc?.let {
            val faixa: String = when (it) {
                in 18.5..24.9 -> {
                    getString(R.string.peso_normal)
                }
                in 25f..29f -> {
                    getString(R.string.sobrepeso)
                }
                in 30f..34.9f -> {
                    getString(R.string.obesidade_1)
                }
                in 35f..39f -> {
                    getString(R.string.obesidade_2)
                }
                else -> {
                    if (it < 18.5) {
                        getString(R.string.abaixo)
                    } else if (it > 40) {
                        getString(R.string.obesidade_3)
                    }
                    getString(R.string.indefinido)
                }

            }

            abrirNovaTelaComResultados("%.2f".format(it), faixa)
        }

    }

    private fun calcularIMC(peso: String, altura: String): Float? {
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()

        return if (peso != null && altura != null) {
            val imc = peso / (altura * altura)
            //titleTXT.text = getString(R.string.imc_resultado).format(imc)
            imc
        } else {
            null
        }
    }

    private fun abrirNovaTelaComResultados(imc: String, faixa: String) {
        val result = Intent(this, ResultActivity::class.java)
        result.putExtra(ResultActivity.IMC_ID, imc)
        result.putExtra(ResultActivity.FAIXA_DE_PESO, faixa)
        startActivity(result)
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