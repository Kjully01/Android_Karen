package com.karen.avaliacao.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karen.avaliacao.model.data_remote.model.cliente.ClienteResponse
import com.karen.avaliacao.model.data_remote.model.repository.ClienteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ClientViewModel : ViewModel() {

    private val clientRepository: ClienteRepository = ClienteRepository()

    private val _clientSuccess: MutableLiveData<ClienteResponse> = MutableLiveData()
    val clientSuccess: LiveData<ClienteResponse> = _clientSuccess

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getClient() {
        viewModelScope.launch(Dispatchers.IO) {
            clientRepository.getCliente()
                .catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _clientSuccess.postValue(it)
                }
        }
    }

}