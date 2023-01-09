package com.karen.avaliacao.presentation.ui.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.karen.avaliacao.databinding.ItemRecyclerPedidoBinding
import com.karen.avaliacao.model.model.pedido.Pedido
import java.text.SimpleDateFormat
import java.util.*

class PedidoAdapter() : RecyclerView.Adapter<PedidoAdapter.ViewHolderPedido>() {

    private var pedidoList: MutableList<Pedido> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPedido {
        val itemBinding =
            ItemRecyclerPedidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderPedido(itemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
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

                if (pedidoResponse.tipo == "ORCAMENTO") {
                    icOrcamento.visibility = View.VISIBLE
                } else {
                    when (pedidoResponse.status) {
                        "Em processamento" -> binding.imgProcessamento.visibility = View.VISIBLE
                        "Pendente" -> binding.icPendente.visibility = View.VISIBLE
                        else -> {
                            if (pedidoResponse.legendas.isNullOrEmpty()) {
                                binding.icLiberado.visibility = View.VISIBLE
                            } else {
                                pedidoResponse.legendas.forEach {
                                    when (it) {
                                        "PEDIDO_SOFREU_CORTE" -> {
                                            binding.icRecusado.visibility =
                                                View.VISIBLE
                                            binding.imgLegenda.visibility = View.VISIBLE
                                        }
                                        "PEDIDO_CANCELADO_ERP" -> binding.icCancelado.visibility =
                                            View.VISIBLE
                                    }
                                }
                            }

                        }
                    }
                }

                when (pedidoResponse.critica) {
                    "FALHA_PARCIAL" -> imgCriticaFalhaP.visibility = View.VISIBLE
                    "FALHA_TOTAL" -> imgCriticaFalhaT.visibility = View.VISIBLE
                }

                tvNumPedRca.text = pedidoResponse.numero_ped_Rca.toString()
                tvNumPedErp.text = pedidoResponse.numero_ped_erp

                val codName = pedidoResponse.codigoCliente + " - " + pedidoResponse.NOMECLIENTE
                tvClientInfo.text = codName

                tvStatus.text = pedidoResponse.status

                val json =
                    pedidoResponse.data.replace("T".toRegex(), " ").replace("Z".toRegex(), "")
                val formatoData = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val dataFormatada = Date(formatoData.parse(json).time)

                val date = Calendar.getInstance().time
                val dateNow = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                if (dateNow.format(date) == dateNow.format((dataFormatada))) {
                    val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
                    val dataFinish = timeFormat.format(dataFormatada)
                    tvHora.text = dataFinish
                } else {
                    val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
                    val dataFinish = dateFormat.format(dataFormatada)
                    tvHora.text = dataFinish
                }
            }
        }
    }
}
