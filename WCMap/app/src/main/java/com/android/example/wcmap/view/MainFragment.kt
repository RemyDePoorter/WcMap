package com.android.example.wcmap.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.example.wcmap.R
import com.android.example.wcmap.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.listButton.setOnClickListener {
            findNavController().navigate(R.id.listFragment)
        }
        binding.mapButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mapsFragment)
        }

        binding.logoutButton.setOnClickListener {
            findNavController().navigate(R.id.fragment_connexion)
        }

        return binding.root
    }
}