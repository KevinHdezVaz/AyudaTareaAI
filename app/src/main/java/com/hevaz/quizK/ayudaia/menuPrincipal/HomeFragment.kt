package com.hevaz.quizK.ayudaia.menuPrincipal

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.hevaz.quizK.R
import com.hevaz.quizK.activity.MainActivity
import com.hevaz.quizK.ayudaia.MainActivityAyudaIA
import com.hevaz.quizK.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home)   {
    private lateinit var binding: FragmentHomeBinding
     var epicDialog2: Dialog? = null
    var epicDialogTErminos: Dialog? = null
     var seguir: Button? = null
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        updateIcon()
        binding.darktheme.setOnClickListener {
            if (isDarkMode()) {
                disableDarkMode()
            } else {
                enableDarkMode()
            }
            updateIcon()
        }


//para salir
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }


        hamburgesa()




    }



    private fun updateIcon() {
        if (isDarkMode()) {
            binding.darktheme.setImageResource(R.drawable.baseline_wb_sunny_24)
        } else {
            binding.darktheme.setImageResource(R.drawable.baseline_mode_night_24)
        }
    }
    private fun enableDarkMode(){

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
    public fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }




    private fun hamburgesa() {

// Obtener referencia al NavigationView
        val navigationView = binding.navigationView

// Obtener referencia al TextView en el header
        val headerView = navigationView.getHeaderView(0)
        val txtUsuario = headerView.findViewById<TextView>(R.id.textUsuario)

// Obtener la información del usuario actual de Firebase
        val user = FirebaseAuth.getInstance().currentUser

// Verificar si el usuario ha iniciado sesión y establecer el correo electrónico o el usuario en el TextView correspondiente
        if (user != null) {
            txtUsuario.text = user.email ?:  "Usuario"
        } else {
            txtUsuario.text = "Usuario"
        }

        // Establecer un oyente de eventos para los elementos del menú
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Manejar la selección del elemento del menú aquí
            when (menuItem.itemId) {
                R.id.nav_acerca -> {
               //     mostrarSalir()
                    binding.drawerLayout.closeDrawers()

                    true
                }

                R.id.navTerminos -> {
                 //   terminos()
                    binding.drawerLayout.closeDrawers()

                    true
                }
                R.id.nav_night -> {
                    if (isDarkMode()) {
                        disableDarkMode()
                    } else {
                        enableDarkMode()
                    }
                    binding.drawerLayout.closeDrawers()
                    true
                }
                R.id.salir -> {
                 //   FirebaseAuth.getInstance().signOut()
                //    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)

                    true
                }


                R.id.nav_share -> {
                    compartir()
                    true
                }
                R.id.nav_profile -> {
                    formutodo("https://play.google.com/store/apps/details?id=com.app.formutodo&hl=es_MX&gl=US")
                    true
                }

                // Agrega más elementos del menú si es necesario
                else -> false
            }
        }

        // Agregar el botón de hamburguesa en la barra de herramientas

        val toggle = ActionBarDrawerToggle(requireActivity(), binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()




        // Agregar un listener al DrawerLayout
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerStateChanged(newState: Int) {}

            override fun onDrawerClosed(drawerView: View) {
                (requireActivity() as MainActivityAyudaIA).showSmooth()
            }

            override fun onDrawerOpened(drawerView: View) {
                // Lógica a realizar cuando se abre el Navigation Drawer
                (requireActivity() as MainActivityAyudaIA).hideSmoothBottomBar()
            }
        })



    }
    private fun isDarkMode(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    private fun modoDark() {
        enableDarkMode()
    }



    private fun formutodo(enlace : String) {
        val url = enlace
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun compartir() {
        val compartir = Intent(Intent.ACTION_SEND)
        compartir.type = "text/plain"
        val mensaje =
            "SaberApp - Aprende todo en uno. \nApp completa con todo lo que un estudiante necesita. \nhttps://play.google.com/store/apps/details?id=com.app.formutodo"
        compartir.putExtra(Intent.EXTRA_SUBJECT, "Miles de cursos GRATIS")
        compartir.putExtra(Intent.EXTRA_TEXT, mensaje)
        startActivity(Intent.createChooser(compartir, "Compartir Via"))



    }





}