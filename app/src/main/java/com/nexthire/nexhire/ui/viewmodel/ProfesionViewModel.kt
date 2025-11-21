package com.nexthire.nexhire.ui.viewmodel

import androidx.lifecycle.*
import com.nexthire.nexhire.API.profesion.ApiClient_Profesion
import com.nexthire.nexhire.models.Profesion
import kotlinx.coroutines.launch

class ProfesionViewModel : ViewModel() {
    private val api = ApiClient_Profesion.create()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _profesion = MutableLiveData<List<Profesion>>(emptyList())
    val profesion: LiveData<List<Profesion>> = _profesion

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun load() {
        if (_loading.value == true) return
        _loading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                _profesion.value = api.getProfesion()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error de red"
            } finally {
                _loading.value = false
            }
        }
    }
}