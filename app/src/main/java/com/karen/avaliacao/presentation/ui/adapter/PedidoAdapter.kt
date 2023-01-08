package com.karen.avaliacao.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karen.avaliacao.databinding.ItemRecyclerPedidoBinding
import com.karen.avaliacao.model.model.pedido.Pedido

class PedidoAdapter() : RecyclerView.Adapter<PedidoAdapter.ViewHolderPedido>() {

    private var pedidoList: MutableList<Pedido> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPedido {
        val itemBinding =
            ItemRecyclerPedidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderPedido(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderPedido, position: Int) {
        holder.onBind(pedidoList[position])
    }

    override fun getItemCount(): Int = pedidoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listAux: List<Pedido>) {
        pedidoList.clear()
        pedidoList.addAll(listAux)
        notifyDataSetChanged()
    }

    class ViewHolderPedido(private val binding: ItemRecyclerPedidoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(pedidoResponse: Pedido) {
            binding.apply {

                imgProcessamento.visibility = View.GONE
                icRecusado.visibility = View.GONE
                icPendente.visibility = View.GONE
                icBloqueado.visibility = View.GONE
                icLiberado.visibility = View.GONE
                icMontado.visibility = View.GONE
                icFaturado.visibility = View.GONE
                icCancelado.visibility = View.GONE
                icOrcamento.visibility = View.GONE

                tvNumPedRca.text = pedidoResponse.numero_ped_Rca.toString()
                tvNumPedErp.text = pedidoResponse.numero_ped_erp.toString()

                val codName = pedidoResponse.codigoCliente + " - " + pedidoResponse.NOMECLIENTE
                tvClientInfo.text = codName

                tvStatus.text = pedidoResponse.status

                if (pedidoResponse.tipo == "ORCAMENTO") {
                    icOrcamento.visibility = View.VISIBLE
                } else {
                    when (pedidoResponse.status) {
                        "Em processamento" -> binding.imgProcessamento.visibility = View.VISIBLE
                        "Pendente" -> binding.icPendente.visibility = View.VISIBLE
                        else -> {
                            binding.icLiberado.visibility = View.VISIBLE
                        }
                    }

                    //imgCritica
                    tvHora.text = pedidoResponse.data
                    //imgLegenda
                    //tvValorInfo.text = pedidoResponse.
                }
            }

        }
    }
}
