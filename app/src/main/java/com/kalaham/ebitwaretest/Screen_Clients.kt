package com.kalaham.ebitwaretest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.kalaham.ebitwaretest.retrofit.BitwareService
import com.kalaham.ebitwaretest.retrofit.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create
import java.lang.Exception

class Screen_Clients : Fragment() {

    private lateinit var btnFrase: Button
    private lateinit var txtFrase: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen__clients, container, false)

        initViews(view)
        setListeners()

        return view
    }

    private fun initViews(view: View) {
        btnFrase = view.findViewById(R.id.btn_frase)
        txtFrase = view.findViewById(R.id.txt_frase)
    }

    private fun setListeners() {
        btnFrase.setOnClickListener {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val call = Retrofit.getRetrofit().create(BitwareService::class.java).getJoke()

                    CoroutineScope(Dispatchers.Main).launch {
                        if (call.isSuccessful) {
                            txtFrase.text = call.body()?.value ?: ""
                        } else {
                            Toast.makeText(requireContext(), "Hubo un error a la hora de consumir el servicio.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("ERROR",
                    "Error al consumir el servicio: ${e.message}"
                )
                Toast.makeText(
                    requireContext(),
                    "Error al consumir el servicio. Revisar Logs.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}