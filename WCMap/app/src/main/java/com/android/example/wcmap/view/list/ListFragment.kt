package com.android.example.wcmap.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.wcmap.R
import com.android.example.wcmap.databinding.FragmentListBinding
import com.android.example.wcmap.viewModel.ListViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var listViewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        binding.viewModel = listViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = manager

        val adapter = ListWcAdapter(WcListener { id ->
            findNavController().navigate(ListFragmentDirections.actionListFragmentToWcDetailsFragment2(id))
        })

        binding.recyclerView.adapter = adapter

        listViewModel.listWC.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.data = it
            }
        })
        return binding.root
    }
}