package com.kalaham.ebitwaretest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kalaham.ebitwaretest.adapter.AdapterHistorial
import com.kalaham.ebitwaretest.database.DatabaseConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Screen_Data_Base : Fragment() {

    private lateinit var adapter: AdapterHistorial
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_screen__data__base, container, false)

        initViews(view)

        val lyManager = LinearLayoutManager(activity)
        lyManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = lyManager
        recycler.setHasFixedSize(true)

        displayPersons()

        return view
    }

    private fun initViews(view: View) {
        recycler = view.findViewById(R.id.recycler_view)
    }

    private fun displayPersons() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val listaPersonas = DatabaseConfig.getInstance(requireContext()).personasDAO().getAll()
                CoroutineScope(Dispatchers.Main).launch {
                    if (listaPersonas.isEmpty()) {
                        Toast.makeText(requireContext(), "Sin información.", Toast.LENGTH_SHORT).show()
                    } else {
                        adapter = AdapterHistorial(requireContext(), listaPersonas)
                        recycler.adapter = adapter
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Error al mostrar las personas: ${e.message}")
        }
    }

}