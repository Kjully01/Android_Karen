package com.karen.avaliacao.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction().replace(R.id.navHost, DataClientFragment())
            .commit()
        val bottomNav: BottomNavigationView = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener(navListener)
    }

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> {
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