package com.nexthire.nexhire.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexthire.nexhire.databinding.FragmentProfesionBinding
import com.google.android.material.snackbar.Snackbar
import com.nexthire.nexhire.R
import com.nexthire.nexhire.ui.adapters.ProfesionAdapters
import com.nexthire.nexhire.ui.viewmodel.ProfesionViewModel

class ProfesionFragment : Fragment(R.layout.fragment_profesion) {

    private var _b: FragmentProfesionBinding? = null
    private val b get() = _b!!

    private val vm: ProfesionViewModel by viewModels()
    private val adapter = ProfesionAdapters()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _b = FragmentProfesionBinding.bind(view)

        b.rvProfesion.layoutManager = LinearLayoutManager(requireContext())
        b.rvProfesion.adapter = adapter

        vm.profesion.observe(viewLifecycleOwner) { adapter.submit(it) }
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
