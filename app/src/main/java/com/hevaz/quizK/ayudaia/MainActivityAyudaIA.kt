package com.hevaz.quizK.ayudaia

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
 import com.hevaz.quizK.R
import com.hevaz.quizK.activity.MainActivity
import com.hevaz.quizK.ayudaia.menuPrincipal.HomeFragment
import com.hevaz.quizK.ayudaia.menuPrincipal.tomaFoto.SampleResultScreen
import com.hevaz.quizK.ayudaia.menuPrincipal.tomaFoto.Tab_Foto
import com.hevaz.quizK.databinding.ActivityMainAyudaIaBinding


class MainActivityAyudaIA : AppCompatActivity() {

    private lateinit var binding: ActivityMainAyudaIaBinding
    private lateinit var navController: NavController
    private val CAMERA_PERMISSION_CODE = 100

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

        // Verificar si el permiso ya está otorgado
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {

        } else {
            // Solicitar permiso
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }


        binding.bottomNavigationView.background = null
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

            override fun onDrawerStateChanged(newState: Int) {}

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerOpened(drawerView: View) {

            }
        })
        // Configura el ActionBarDrawerToggle para manejar la apertura y cierre del DrawerLayout
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navController = NavHostFragment.findNavController(navHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)
        val videoLayout = dialog.findViewById<LinearLayout>(R.id.layoutVideo)
        val shortsLayout = dialog.findViewById<LinearLayout>(R.id.layoutShorts)
         val cancelButton = dialog.findViewById<ImageView>(R.id.cancelButton)
        videoLayout.setOnClickListener {
            dialog.dismiss()

            startCameraWithoutUri(includeCamera = true, includeGallery = false)


            Toast.makeText(this@MainActivityAyudaIA, "Upload a Video is clicked", Toast.LENGTH_SHORT)
                .show()
        }
        shortsLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this@MainActivityAyudaIA, "Create a short is Clicked", Toast.LENGTH_SHORT)
                .show()
            startCameraWithoutUri(includeCamera = false, includeGallery = true)

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
    private val customCropImage = registerForActivityResult(CropImageContract()) {
        if (it !is CropImage.CancelledResult) {
            handleCropImageResult(it.uriContent.toString())
        }
    }
    private fun handleCropImageResult(uri: String) {
    //    SampleResultScreen.start(MainActivity., null, Uri.parse(uri.replace("file:", "")), null)
    }
    private fun startCameraWithoutUri(includeCamera: Boolean, includeGallery: Boolean) {
        customCropImage.launch(
            CropImageContractOptions(
                uri = null,
                cropImageOptions = CropImageOptions(
                    imageSourceIncludeCamera = includeCamera,
                    imageSourceIncludeGallery = includeGallery,
                ),
            ),
        )
    }

    // Manejar el resultado de la solicitud de permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado, puedes abrir la cámara aquí

            } else {
                // Permiso denegado, puedes mostrar un mensaje al usuario o realizar otra acción
            }
        }
    }
    private fun observeDestinationChange() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.introFragment -> {
                    hideSmoothBottomBar()
                }
                else -> {
                    showSmooth()
                }
            }
        }
    }

    fun hideSmoothBottomBar() {
        binding.bottomAppBar.hide()
        binding.fab.hide()
        binding.toolbar.hide()
    }

    fun showSmooth() {
        binding.bottomAppBar.show()
        binding.fab.show()
        binding.toolbar.show()
    }
}
