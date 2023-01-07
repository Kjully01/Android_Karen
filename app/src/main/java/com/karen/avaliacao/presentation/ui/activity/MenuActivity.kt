package com.karen.avaliacao.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.karen.avaliacao.R
import com.karen.avaliacao.databinding.ActivityMainBinding

class MenuActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
    }

    private fun setView() {
        binding.apply {
            btnClient.apply {
                imgButton.setImageResource(R.drawable.ic_maxima_pessoas)
                tvButton.text = "Clientes"
                card.setOnClickListener {
                    Intent(this@MenuActivity,DetailsActivity::class.java).also {
                        startActivity(it)
                    }
                }
            }
        }
        binding.apply {
            btnSummary.apply {
                imgButton.setImageResource(R.drawable.ic_maxima_resumo_vendas)
                tvButton.text = "Resumo de vendas"
                card.setOnClickListener {
                    Toast.makeText(this@MenuActivity, "Teste", Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.apply {
            btnPedido.apply {
                imgButton.setImageResource(R.drawable.ic_maxima_pedido)
                tvButton.text = "Pedidos"
                card.setOnClickListener {
                    Toast.makeText(this@MenuActivity, "Teste", Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.apply {
            btnSettings.apply {
                imgButton.setImageResource(R.drawable.ic_maxima_ferramentas)
                tvButton.text = "Feramentas"
                card.setOnClickListener {
                    Toast.makeText(this@MenuActivity, "Teste", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}