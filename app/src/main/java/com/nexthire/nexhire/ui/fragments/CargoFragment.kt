package com.nexthire.nexhire.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexthire.nexhire.databinding.FragmentCargoBinding
import com.google.android.material.snackbar.Snackbar
import com.nexthire.nexhire.R
import com.nexthire.nexhire.ui.adapters.CargoAdapters
import com.nexthire.nexhire.ui.viewmodel.CargoViewModel

class CargoFragment : Fragment(R.layout.fragment_cargo) {

    private var _b: FragmentCargoBinding? = null
    private val b get() = _b!!

    private val vm: CargoViewModel by viewModels()
    private val adapter = CargoAdapters()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _b = FragmentCargoBinding.bind(view)

        b.rvCargos.layoutManager = LinearLayoutManager(requireContext())
        b.rvCargos.adapter = adapter

        vm.cargos.observe(viewLifecycleOwner) { adapter.submit(it) }
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
