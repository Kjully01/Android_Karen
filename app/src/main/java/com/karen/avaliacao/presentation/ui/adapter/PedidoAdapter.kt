package com.karen.avaliacao.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karen.avaliacao.databinding.ItemRecyclerPedidoBinding
import com.karen.avaliacao.model.model.pedido.PedidoResponse

class PedidoAdapter() : RecyclerView.Adapter<PedidoAdapter.ViewHolderPedido>() {

    private var pedidoList: MutableList<PedidoResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPedido {
        val itemBinding = ItemRecyclerPedidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderPedido(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderPedido, position: Int) {
        holder.onBind(pedidoList[position])
    }

    override fun getItemCount(): Int = pedidoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listAux: List<PedidoResponse>) {
        pedidoList.clear()
        pedidoList.addAll(listAux)
        notifyDataSetChanged()
    }

    class ViewHolderPedido(private val binding: ItemRecyclerPedidoBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun onBind(pedidoResponse: PedidoResponse){
                binding.apply {
                    tvNumPedRca.text = pedidoResponse.numero_ped_Rca.toString()
                    tvNumPedErp.text = pedidoResponse.numero_ped_erp.toString()

                    val codName = pedidoResponse.codigoCliente + " - " + pedidoResponse.NOMECLIENTE
                    tvClientInfo.text = codName

                    tvStatus.text = pedidoResponse.status
                    //imgCritica
                    tvHora.text = pedidoResponse.data
                    //imgLegenda
                    //tvValorInfo.text = pedidoResponse.
                }
            }

    }

}