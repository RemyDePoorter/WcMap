package com.android.example.wcmap.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.example.wcmap.R

import com.android.example.wcmap.databinding.FragmentWcDetailsBinding

class WcDetailsFragment : Fragment() {
    private val args: WcDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentWcDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wc_details, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.specloc.text = args.recordDetail.fields.specloc
        binding.typeInfo.text = args.recordDetail.fields.typefr
        binding.districtInfo.text = args.recordDetail.fields.zPcddFr
        binding.streetInfo.text = args.recordDetail.fields.rue
        binding.numInfo.text = args.recordDetail.fields.nrpol
        binding.levelInfo.text = args.recordDetail.fields.niveau.toString()
        binding.managementInfo.text = args.recordDetail.fields.gestion
        binding.pmrInfo.text = args.recordDetail.fields.pmr
        binding.freeInfo.text = args.recordDetail.fields.gratuite
        binding.hoursInfo.text = args.recordDetail.fields.heureouv

        return binding.root

    }
}