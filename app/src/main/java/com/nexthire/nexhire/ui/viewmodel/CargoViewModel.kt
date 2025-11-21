package com.nexthire.nexhire.ui.viewmodel

import androidx.lifecycle.*
import com.nexthire.nexhire.API.cargo.ApiClient_Cargo
import com.nexthire.nexhire.models.Cargos
import kotlinx.coroutines.launch

class CargoViewModel : ViewModel() {
    private val api = ApiClient_Cargo.create()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _cargo = MutableLiveData<List<Cargos>>(emptyList())
    val cargos: LiveData<List<Cargos>> = _cargo

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun load() {
        if (_loading.value == true) return
        _loading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                _cargo.value = api.getCargo()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error de red"
            } finally {
                _loading.value = false
            }
        }
    }
}

