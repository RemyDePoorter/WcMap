package com.android.example.wcmap.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.wcmap.R
import com.android.example.wcmap.databinding.FragmentConnexionBinding
import com.android.example.wcmap.model.user.User
import com.android.example.wcmap.viewModel.ConnexionViewModel

class ConnexionFragment : Fragment() {

    private lateinit var binding: FragmentConnexionBinding
    private lateinit var connexionViewModel: ConnexionViewModel
    private lateinit var adapter: ArrayAdapter<String?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_connexion, container, false)

        connexionViewModel = ViewModelProvider(this)[ConnexionViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        binding.registerButton.setOnClickListener {
            checkRegister()
        }

        binding.connexionButton.setOnClickListener {
            checkConnexion()
        }

        val spinner = binding.spinner
        connexionViewModel.listOfEmail.observe(viewLifecycleOwner) { emails ->
            adapter = ArrayAdapter<String?>(
                requireActivity(),
                android.R.layout.simple_spinner_item, emails
            )
            spinner.adapter = adapter
        }

        return binding.root
    }

    private fun checkRegister() {
        val email = binding.emailEdit.text.toString()
        val emailIsValid = connexionViewModel.checkEmail(email)
        val emailIsInside = connexionViewModel.contains(email)

        if (emailIsValid && emailIsInside) {
            val user = User(email, System.currentTimeMillis())
            connexionViewModel.addUser(user)
            Toast.makeText(activity, getString(R.string.emailAdded), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, getString(R.string.invalidEmail), Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkConnexion() {
        val email = binding.spinner.toString()
        if (!checkForInternet()) {
            Toast.makeText(activity, getString(R.string.noInternet), Toast.LENGTH_SHORT).show()
        } else if (connexionViewModel.contains(email)) {
            val user = User(email, System.currentTimeMillis())
            connexionViewModel.updateUser(user)
            Toast.makeText(activity, getString(R.string.emailUpdated), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.mainFragment)
        } else {
            Toast.makeText(activity, getString(R.string.invalidEmail), Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun checkForInternet(): Boolean {
        val context = requireContext().applicationContext
        // Register activity with the connectivity manager service.
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // If the android version is equal to M or greater we need to use the
        // NetworkCapabilities to check what type of network has the internet connection.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                // Indicates this network uses a Wi-Fi transport, or WiFi has network connectivity.
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport or
                // Cellular has network connectivity.
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // Else return false.
                else -> false
            }
        } else {
            // If the android version is below M.
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}