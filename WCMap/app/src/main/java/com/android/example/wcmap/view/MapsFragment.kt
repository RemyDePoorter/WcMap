package com.android.example.wcmap.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.wcmap.R
import com.android.example.wcmap.databinding.FragmentMapsBinding
import com.android.example.wcmap.viewModel.MapsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private lateinit var mapsViewModel: MapsViewModel
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var position: LatLng
    private lateinit var binding: FragmentMapsBinding

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        mapsViewModel.listWC.observe(viewLifecycleOwner, Observer {
            fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(requireContext())
            fetchLocation()
            addMarker()
        })
    }

    /**
     * Get user position and add marker on map.
     */
    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101
            )
            return
        }
        map.isMyLocationEnabled = true
        task.addOnSuccessListener {
            if (it != null) {
                position = LatLng(it.latitude, it.longitude)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 12f))
            }
        }
    }

    /**
     * Add marker to toilet positions.
     */
    private fun addMarker() {
        val wcList = mapsViewModel.listWC
        for (wc in wcList.value!!) {
            val latitude = wc.geometry.coordinates[1]
            val longitude = wc.geometry.coordinates[0]
            val position = LatLng(latitude, longitude)
            map.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(wc.fields.specloc)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Pour corriger le menu, je dois tout réfractorer,
        par soucis de temps je le laisse comme il est
        sinon j'aurais voulu faire ceci :

        https://androidtutos.com/bottom-navigation-view/

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.bottomNavigation.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_run -> {
                    //ouvir gmaps avec itineraire vers l'adresse du wc le + proche
                    true
                }
                R.id.action_list -> {
                    findNavController().navigate(R.id.listFragment)
                    true
                }
                R.id.action_map -> {
                    Log.i("TESTREMY", "click bouton")
                    findNavController().navigate(R.id.action_mainFragment_to_mapsFragment)
                    true
                }
                R.id.action_favorites -> {
                    //aller sur la page favoris
                    true
                }
                R.id.action_logout -> {
                    findNavController().navigate(R.id.fragment_connexion)
                    true
                }
                else -> false
            }
        }
        return binding.root
        */
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapsViewModel = ViewModelProvider(this)[MapsViewModel::class.java]
        mapsViewModel.fetchData()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

}