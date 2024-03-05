package com.hevaz.quizK.ayudaia.menuPrincipal.tomaFoto

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.hevaz.quizK.R
import com.hevaz.quizK.ayudaia.MainActivityAyudaIA
import com.hevaz.quizK.databinding.FragmentTabFotoBinding

class Tab_Foto : Fragment() {

    private lateinit var binding: FragmentTabFotoBinding // Reemplaza MyFragmentBinding con el nombre de tu clase de binding

    var seguir: Button? = null
    var epicDialog2: Dialog? = null
    var epicDialogTErminos: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentTabFotoBinding.inflate(inflater, container, false) // Reemplaza MyFragmentBinding con el nombre de tu clase de binding


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
        hamburgesa()





        return binding.root

    }



    private fun hamburgesa() {


        // Establecer un oyente de eventos para los elementos del menú
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Manejar la selección del elemento del menú aquí
            when (menuItem.itemId) {
                R.id.nav_acerca -> {

                    binding.drawerLayout.closeDrawers()

                    true
                }

                R.id.navTerminos -> {

                    binding.drawerLayout.closeDrawers()

                    true
                }
                /*
                R.id.salir -> {
                    FirebaseAuth.getInstance().signOut()
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)

                    true
                }

                 */
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
    override fun onResume() {
        super.onResume()
        binding.particleView.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.particleView.pause()
    }
}
