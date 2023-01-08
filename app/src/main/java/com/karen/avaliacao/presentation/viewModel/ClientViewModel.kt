package com.karen.avaliacao.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.karen.avaliacao.model.data_local.database.DatabaseLocal
import com.karen.avaliacao.model.data_local.repository.ClienteRepositoryLocal
import com.karen.avaliacao.model.model.cliente.ClienteResponse
import com.karen.avaliacao.model.data_remote.repository.repository_remote.ClienteRepository
import com.karen.avaliacao.model.model.cliente.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ClientViewModel(application: Application) : AndroidViewModel(application) {

    private val clientRepository: ClienteRepository = ClienteRepository()
    private var clientRepositoryLocal: ClienteRepositoryLocal

    var readCliente: LiveData<List<Cliente>>

    private val _clientSuccess: MutableLiveData<ClienteResponse> = MutableLiveData()
    val clientSuccess: LiveData<ClienteResponse> = _clientSuccess

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    init {
        val clienteDao = DatabaseLocal.getDatabase(
            application
        ).clientDao()
        clientRepositoryLocal = ClienteRepositoryLocal(clienteDao)
        readCliente = clientRepositoryLocal.readCliente
    }

    fun addClient(cliente: Cliente) {
        viewModelScope.launch(Dispatchers.IO) {
            clientRepositoryLocal.addCliente(cliente)
        }
    }

//    fun getClientLocal(): List<Cliente> {
//        viewModelScope.launch(Dispatchers.IO) {
//            return@launch clientRepositoryLocal.getCliente()
//        }
//    }

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