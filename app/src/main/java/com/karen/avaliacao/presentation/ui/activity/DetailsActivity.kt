package com.karen.avaliacao.presentation.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.karen.avaliacao.R
import com.karen.avaliacao.databinding.ActivityDetailsBinding
import com.karen.avaliacao.presentation.ui.fragment.DataClientFragment
import com.karen.avaliacao.presentation.ui.fragment.AlvaraFragment
import com.karen.avaliacao.presentation.ui.fragment.PedidoFragment

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private var currentFragment: Fragment = DataClientFragment()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.overflowIcon = getDrawable(R.drawable.ic_baseline_more_vert_24)

        supportFragmentManager.beginTransaction().replace(R.id.navHost, DataClientFragment())
            .commit()
        val bottomNav: BottomNavigationView = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener(navListener)
    }

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.data -> {
                currentFragment = DataClientFragment()
                binding.toolbar.title = "Dados do cliente"
            }
            R.id.registration -> {
                currentFragment = PedidoFragment()
                binding.toolbar.title = "Hist. de pedidos"
            }
            R.id.favorite -> {
                currentFragment = AlvaraFragment()
                binding.toolbar.title = "Alvarás"
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.navHost, currentFragment).commit()
        true
    }
}