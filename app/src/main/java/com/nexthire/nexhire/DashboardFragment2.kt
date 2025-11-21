package com.nexthire.nexhire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nexthire.nexhire.ui.composedashboard.DashboardScreen
import com.nexthire.nexhire.ui.viewmodel.cubo.DashboardViewModel
import com.nexthire.nexhire.ui.theme.NexHireTheme

class DashboardFragment2 : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                NexHireTheme {
                    DashboardScreen(viewModel)
                }
            }
        }
    }
}