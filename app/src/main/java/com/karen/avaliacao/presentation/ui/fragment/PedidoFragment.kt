package com.karen.avaliacao.presentation.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karen.avaliacao.R
import com.karen.avaliacao.databinding.FragmentDataClientBinding
import com.karen.avaliacao.databinding.FragmentPedidoBinding
import com.karen.avaliacao.model.data_remote.model.cliente.ContatoResponse
import com.karen.avaliacao.model.data_remote.model.pedido.PedidoListResponse
import com.karen.avaliacao.model.data_remote.model.pedido.PedidoResponse
import com.karen.avaliacao.presentation.ui.adapter.ContatoAdapter
import com.karen.avaliacao.presentation.ui.adapter.PedidoAdapter
import com.karen.avaliacao.presentation.viewModel.ClientViewModel
import com.karen.avaliacao.presentation.viewModel.PedidoViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.view.*

class PedidoFragment : Fragment() {

    private lateinit var binding: FragmentPedidoBinding
    private lateinit var adapterPedido: PedidoAdapter
    private lateinit var viewModel: PedidoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPedidoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PedidoViewModel::class.java)
        viewModel.getPedidos()

        observer()
        startAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                requireActivity().onBackPressed()
            }
            R.id.search -> {
                Toast.makeText(requireContext(), "teste", Toast.LENGTH_SHORT).show()
//                true
            }
//            else -> false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startAdapter() {
        adapterPedido = PedidoAdapter()
        binding.rvPedido.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterPedido
        }
    }

    private fun setDataAdapter(pedidoList: List<PedidoResponse>) {
        adapterPedido.setData(pedidoList)
    }

    private fun observer() {
        viewModel.apply {
            pedidoSuccess.observe(viewLifecycleOwner, Observer { pedido ->
                val pedidoList = pedido.pedidosResponse
                setDataAdapter(pedidoList)
            })
            error.observe(
                viewLifecycleOwner, Observer {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}