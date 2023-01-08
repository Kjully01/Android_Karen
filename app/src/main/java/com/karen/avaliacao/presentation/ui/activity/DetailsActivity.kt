package com.karen.avaliacao.presentation.ui.activity

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.navHost, DataClientFragment())
            .commit()
        val bottomNav: BottomNavigationView = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener(navListener)

        val actionBar = supportActionBar
        actionBar?.title = "Home"
    }

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> {
                currentFragment = DataClientFragment()
                //binding.toolbar.title = "Home"
            }
            R.id.registration -> {
                currentFragment = PedidoFragment()
                //binding.toolbar.title = "Cadastrar"
            }
            R.id.favorite -> {
                currentFragment = AlvaraFragment()
                //binding.toolbar.title = "Favoritos"
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.navHost, currentFragment).commit()
        true
    }

}