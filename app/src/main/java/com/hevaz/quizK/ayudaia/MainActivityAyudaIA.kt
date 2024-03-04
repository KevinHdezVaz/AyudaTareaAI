package com.hevaz.quizK.ayudaia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hevaz.quizK.R
import com.hevaz.quizK.databinding.ActivityMainAyudaIaBinding


class MainActivityAyudaIA : AppCompatActivity() {

    private lateinit var binding: ActivityMainAyudaIaBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityMainAyudaIaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupSmoothBottomMenu()
        observeDestinationChange()



    }

    private fun setupSmoothBottomMenu() {
        val popupmenu = PopupMenu(this, null)
        popupmenu.inflate(R.menu.menu_main)
        val menu: Menu = popupmenu.getMenu()
        binding.bottomBar.setupWithNavController(menu, navController)
    }

    private fun observeDestinationChange() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
               /*
                R.id.loginFragment -> {
                    binding.bottomBar.hide()
                }

                R.id.registerFragment -> {
                    binding.bottomBar.hide()
                }
                R.id.categoria_Detalles -> {
                    binding.bottomBar.hide()
                }
*/
                R.id.introFragment -> {
                    binding.bottomBar.hide()
                }

                else -> {
                    binding.bottomBar.show()
                }
            }
        }
    }
    fun hideSmoothBottomBar() {
        // oculta el SmoothBottomBar
        binding.bottomBar.hide()

    }

    fun showSmooth() {
        // oculta el SmoothBottomBar
        binding.bottomBar.show()

    }
}