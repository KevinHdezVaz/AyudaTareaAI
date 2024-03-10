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






        return binding.root

    }










    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
        binding.particleView.pause()
    }
}