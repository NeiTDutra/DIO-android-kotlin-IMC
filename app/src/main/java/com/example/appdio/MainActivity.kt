package com.example.appdio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import kotlinx.android.synthetic.main.activity_result.*

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
            numberAltura.setText(null)
            numberPeso.setText(null)
        }
    }

    private fun exibirFaixaDePeso() {
        val peso = numberPeso?.text.toString()
        val altura = numberAltura?.text.toString()
        val imc = calcularIMC(peso, altura)

        imc?.let {
            val faixa: String = when (it) {
                in 18.5f..24.9f -> {
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
                    if (it < 18.5f) {
                        getString(R.string.abaixo)
                    } else if (it > 40f) {
                        getString(R.string.obesidade_3)
                    }
                    getString(R.string.indefinido)
                }

            }
            val peso_ideal: String = when (altura.toFloat()) {
                in 1.50f..1.55f -> {
                    getString(R.string.faixa_0)
                }
                in 1.56f..1.60f -> {
                    getString(R.string.faixa_1)
                }
                in 1.61f..1.65f -> {
                    getString(R.string.faixa_2)
                }
                in 1.66f..1.69f -> {
                    getString(R.string.faixa_3)
                }
                in 1.70f..1.75f -> {
                    getString(R.string.faixa_4)
                }
                in 1.76f..1.80f -> {
                    getString(R.string.faixa_5)
                }
                in 1.81f..1.85f -> {
                    getString(R.string.faixa_6)
                }
                else -> ({
                    if (altura.toFloat() > 1.85f) {
                        getString(R.string.faixa_7)
                    }
                    getString(R.string.indefinido)
                }).toString()
            }

            abrirNovaTelaComResultados("%.2f".format(it), faixa, peso_ideal)
        }

    }

    private fun calcularIMC(peso: String, altura: String): Float? {
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()

        return if (peso != null && altura != null) {
            val imc = peso / (altura * altura)
            imc
        } else {
            null
        }
    }

    private fun abrirNovaTelaComResultados(imc: String, faixa: String, peso_ideal: String) {
        val result = Intent(this, ResultActivity::class.java)
        result.putExtra(ResultActivity.IMC_ID, imc)
        result.putExtra(ResultActivity.FAIXA_DE_PESO, faixa)
        result.putExtra(ResultActivity.PESO_IDEAL, peso_ideal)
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