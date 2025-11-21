package com.nexthire.nexhire.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexthire.nexhire.databinding.FragmentDepartamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.nexthire.nexhire.R
import com.nexthire.nexhire.ui.adapters.DepartamentoAdapters
import com.nexthire.nexhire.ui.viewmodel.DepartamentoViewModel

class DepartamentoFragment : Fragment(R.layout.fragment_departamento) {

    private var _b: FragmentDepartamentoBinding? = null
    private val b get() = _b!!

    private val vm: DepartamentoViewModel by viewModels()
    private val adapter = DepartamentoAdapters()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _b = FragmentDepartamentoBinding.bind(view)

        b.rvDepartamento.layoutManager = LinearLayoutManager(requireContext())
        b.rvDepartamento.adapter = adapter

        vm.departamento.observe(viewLifecycleOwner) { adapter.submit(it) }
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
