package com.hevaz.quizK.ayudaia

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hevaz.quizK.R
import com.hevaz.quizK.ayudaia.menuPrincipal.HomeFragment
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

        observeDestinationChange()

binding.fab.setOnClickListener {
    showBottomDialog()
}
        binding.bottomNavigationView.background = null
        val navController = NavHostFragment.findNavController(navHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController);


    }



    private fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)
        val videoLayout = dialog.findViewById<LinearLayout>(R.id.layoutVideo)
        val shortsLayout = dialog.findViewById<LinearLayout>(R.id.layoutShorts)
        val liveLayout = dialog.findViewById<LinearLayout>(R.id.layoutLive)
        val cancelButton = dialog.findViewById<ImageView>(R.id.cancelButton)
        videoLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this@MainActivityAyudaIA, "Upload a Video is clicked", Toast.LENGTH_SHORT)
                .show()
        }
        shortsLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this@MainActivityAyudaIA, "Create a short is Clicked", Toast.LENGTH_SHORT)
                .show()
        }
        liveLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this@MainActivityAyudaIA, "Go live is Clicked", Toast.LENGTH_SHORT).show()
        }
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
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
                    binding.bottomAppBar.hide()
                }

                else -> {
                     binding.bottomAppBar.show()
                }
            }
        }
    }
   fun hideSmoothBottomBar(){
       binding.bottomAppBar.hide()
    }
fun showSmooth(){
    binding.bottomAppBar.show()

}
}