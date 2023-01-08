package com.karen.avaliacao.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karen.avaliacao.model.data_remote.model.cliente.ClienteResponse
import com.karen.avaliacao.model.data_remote.model.pedido.PedidoListResponse
import com.karen.avaliacao.model.data_remote.model.pedido.PedidoResponse
import com.karen.avaliacao.model.data_remote.model.repository.PedidoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PedidoViewModel : ViewModel() {

    private val pedidoRepository: PedidoRepository = PedidoRepository()

    private val _pedidoSuccess: MutableLiveData<PedidoListResponse> = MutableLiveData()
    val pedidoSuccess: LiveData<PedidoListResponse> = _pedidoSuccess

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getPedidos() {
        viewModelScope.launch(Dispatchers.IO) {
            pedidoRepository.getPedido()
                .catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _pedidoSuccess.postValue(it)
                }
        }
    }
}