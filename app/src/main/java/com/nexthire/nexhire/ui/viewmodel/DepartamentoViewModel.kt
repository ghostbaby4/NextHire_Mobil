package com.nexthire.nexhire.ui.viewmodel

import androidx.lifecycle.*
import com.nexthire.nexhire.API.departamento.ApiClient_Departamento
import com.nexthire.nexhire.models.Departamento
import com.nexthire.nexhire.models.Habilidades
import kotlinx.coroutines.launch

class DepartamentoViewModel : ViewModel() {
    private val api = ApiClient_Departamento.create()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _departamento = MutableLiveData<List<Departamento>>(emptyList())
    val departamento: LiveData<List<Departamento>> = _departamento

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun load() {
        if (_loading.value == true) return
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                // Esta línea funciona si tu ApiServices_Habilidades tiene "suspend fun getHabilidades()"
                _departamento.value = api.getDepartamentos()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error de red"
            } finally {
                _loading.value = false
            }
        }
    }

}
