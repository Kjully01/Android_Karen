package com.karen.avaliacao.presentation.ui.fragment

import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karen.avaliacao.R
import com.karen.avaliacao.databinding.FragmentPedidoBinding
import com.karen.avaliacao.model.model.pedido.Pedido
import com.karen.avaliacao.presentation.ui.adapter.PedidoAdapter
import com.karen.avaliacao.presentation.viewModel.PedidoViewModel
import com.karen.avaliacao.utils.NetworkChecker

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
            R.id.search -> {
                Toast.makeText(requireContext(), "teste", Toast.LENGTH_SHORT).show()
            }
            R.id.subtitle -> {
                dialogSubtitle()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val networkChecker by lazy {
        NetworkChecker(
            ContextCompat.getSystemService(
                requireContext(),
                ConnectivityManager::class.java
            )
        )
    }

    private fun dialogSubtitle() {
        val view: View = layoutInflater.inflate(R.layout.subtitle_dialog, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        builder.setNegativeButton("Fechar", DialogInterface.OnClickListener { dialog, id ->

        })
        val alert = builder.create()
        alert.show()
    }

    private fun startAdapter() {
        adapterPedido = PedidoAdapter()
        binding.rvPedido.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterPedido
        }
    }

    private fun setDataAdapter(pedidoList: List<Pedido>) {
        adapterPedido.setData(pedidoList)
    }

    private fun observer() {
//        networkChecker.performActionIfConnect {
        viewModel.apply {
            pedidoSuccess.observe(viewLifecycleOwner, Observer { pedido ->
//                    addPedido(pedido)
                setDataAdapter(pedido.pedidosResponse)
            })
            error.observe(
                viewLifecycleOwner, Observer {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            )
        }
//        }

//        networkChecker.performActionIfNotConnect {
//            viewModel.apply {
//                getPedido.observe(viewLifecycleOwner, Observer { pedido ->
//                    setDataAdapter(pedido)
//                })
//                error.observe(
//                    viewLifecycleOwner, Observer {
//                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//                    }
//                )
//            }
//        }
    }
}