package com.nexthire.nexhire.ui.viewmodel

import androidx.lifecycle.*
import com.nexthire.nexhire.API.habilidades.ApiClient_Habilidades
import com.nexthire.nexhire.models.Habilidades
import kotlinx.coroutines.launch

class HabilidadesViewModel : ViewModel() {
    private val api = ApiClient_Habilidades.create()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _habilidades = MutableLiveData<List<Habilidades>>(emptyList())
    val habilidades: LiveData<List<Habilidades>> = _habilidades

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun load() {
        if (_loading.value == true) return
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                // Esta línea funciona si tu ApiServices_Habilidades tiene "suspend fun getHabilidades()"
                _habilidades.value = api.getHabilidades()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error de red"
            } finally {
                _loading.value = false
            }
        }
    }

}
