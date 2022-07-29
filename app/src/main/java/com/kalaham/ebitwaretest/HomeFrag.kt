package com.kalaham.ebitwaretest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class HomeFrag : Fragment() {

    private lateinit var btnPeso: Button
    private lateinit var btnData: Button
    private lateinit var btnClient: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initViews(view)
        initViews2(view)
        initViews3(view)
        setListeners()
        setListeners2()
        setListeners3()

        return view
    }

    private fun initViews(view: View) {
        btnPeso = view.findViewById(R.id.btn_peso)
    }

    private fun setListeners() {
        btnPeso.setOnClickListener {
            val fragment = Screen_IMC()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.addToBackStack(null)?.commit()
        }
    }

    private fun initViews2(view: View) {
        btnData = view.findViewById(R.id.btn_database)
    }

    private fun setListeners2() {
        btnData.setOnClickListener {
            val fragment = Screen_Data_Base()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.addToBackStack(null)?.commit()
        }
    }

    private fun initViews3(view: View) {
        btnClient = view.findViewById(R.id.btn_alta)
    }

    private fun setListeners3() {
        btnClient.setOnClickListener {
            val fragment = Screen_Clients()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.addToBackStack(null)?.commit()
        }
    }


}