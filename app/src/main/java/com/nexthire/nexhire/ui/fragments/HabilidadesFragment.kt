package com.nexthire.nexhire.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexthire.nexhire.databinding.FragmentHabilidadesBinding
import com.google.android.material.snackbar.Snackbar
import com.nexthire.nexhire.R
import com.nexthire.nexhire.ui.adapters.HabilidadesAdapters
import com.nexthire.nexhire.ui.viewmodel.HabilidadesViewModel

class HabilidadesFragment : Fragment(R.layout.fragment_habilidades) {

    private var _b: FragmentHabilidadesBinding? = null
    private val b get() = _b!!

    private val vm: HabilidadesViewModel by viewModels()
    private val adapter = HabilidadesAdapters()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _b = FragmentHabilidadesBinding.bind(view)

        b.rvHabilidades.layoutManager = LinearLayoutManager(requireContext())
        b.rvHabilidades.adapter = adapter

        vm.habilidades.observe(viewLifecycleOwner) { adapter.submit(it) }
        vm.loading.observe(viewLifecycleOwner) {
            b.progress.visibility = if (it) View.VISIBLE else View.GONE
        }
        vm.error.observe(viewLifecycleOwner) { it?.let { msg ->
            Snackbar.make(b.root, msg, Snackbar.LENGTH_LONG).show()
        }}

        vm.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
