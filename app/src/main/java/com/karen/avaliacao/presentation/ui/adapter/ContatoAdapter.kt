package com.karen.avaliacao.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karen.avaliacao.databinding.ItemRecyclerContactBinding
import com.karen.avaliacao.model.data_remote.model.cliente.ContatoResponse

class ContatoAdapter() : RecyclerView.Adapter<ContatoAdapter.ViewHolderAnimals>() {

    private var contactList: MutableList<ContatoResponse> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderAnimals {
        val itemBinding = ItemRecyclerContactBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolderAnimals(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderAnimals, position: Int) {
        holder.onBind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listAux: List<ContatoResponse>) {
        contactList.clear()
        contactList.addAll(listAux)
        notifyDataSetChanged()
    }

    class ViewHolderAnimals(private val binding: ItemRecyclerContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(contatoResponse: ContatoResponse) {
            binding.apply {
                tvTelInfo.text = contatoResponse.telefone
                tvCelInfo.text = contatoResponse.celular
                tvConjugeInfo.text = contatoResponse.conjuge
                tvTipoInfo.text = contatoResponse.tipo
                //tvHobbieInfo.text = contatoResponse.
                tvEmailInfo.text = contatoResponse.email
                tvDtNascInfo.text = contatoResponse.data_nascimento
                tvDtNascConjugeInfo.text = contatoResponse.dataNascimentoConjuge
                tvTimeInfo.text = contatoResponse.time
            }
        }

    }
}