package com.karen.avaliacao.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karen.avaliacao.databinding.FragmentDataClientBinding
import com.karen.avaliacao.model.model.cliente.Cliente
import com.karen.avaliacao.model.model.cliente.ClienteResponse
import com.karen.avaliacao.model.model.cliente.Contato
import com.karen.avaliacao.presentation.ui.adapter.ContatoAdapter
import com.karen.avaliacao.presentation.viewModel.ClientViewModel


class DataClientFragment : Fragment() {

    private lateinit var binding: FragmentDataClientBinding
    private lateinit var adapterContact: ContatoAdapter
    private lateinit var viewModel: ClientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataClientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ClientViewModel::class.java)
        viewModel.getClient()

        observer()
        startAdapter()
    }

    private fun startAdapter() {
        adapterContact = ContatoAdapter()
        binding.rvContact.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterContact
        }
    }

    private fun setDataAdapter(contactList: List<Contato>) {
        adapterContact.setData(contactList)
    }

    private fun setView(client: Cliente) {
        binding.apply {
            val codName = "${client.codigo} - ${client.razao_social}"
            tvCodRazao.text = codName
            tvFantasy.text = client.nomeFantasia
            tvCnpj.text = client.cnpj
            tvRamo.text = client.ramo_atividade
            tvEndereco.text = client.endereco
        }
        setDataAdapter(client.contatos)
    }

    private fun observer() {
        viewModel.apply {
            readCliente.observe(viewLifecycleOwner, Observer{ client ->
                setView(client[0])
            })
            clientSuccess.observe(viewLifecycleOwner, Observer { client ->
                addClient(client.cliente)
//                setView(client)
            })
            error.observe(
                viewLifecycleOwner, Observer {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}