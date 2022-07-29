package com.kalaham.ebitwaretest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment


class Screen_IMC : Fragment() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchBtn: Switch
    private lateinit var btnCalculate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_screen__i_m_c, container, false)

        initViews(view)
        setListener()

        return view
    }

    private fun setListener() {
        var name: String
        var age: String
        var weight: String
        var height: String
        btnCalculate.setOnClickListener {
            name = etName.text.toString()
            age = etAge.text.toString()
            weight = etWeight.text.toString()
            height = etHeight.text.toString()

            if (
                name.isNotEmpty()
                && age.isNotEmpty()
                && weight.isNotEmpty()
                && height.isNotEmpty()
            ) {
                val mSex: Char = if (switchBtn.isChecked) 'M' else 'H'

                val bundle = Bundle()
                bundle.putString("NAME", name)
                bundle.putInt("AGE", age.toInt())
                bundle.putString("NSS", MetodosPersona.generaNSS())
                bundle.putChar("SEX", mSex)
                bundle.putDouble("WEIGHT", weight.toDouble())
                bundle.putDouble("HEIGHT", height.toDouble())
                val fragment = Screen_IMC_Result()
                fragment.arguments = bundle

                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_container, fragment).addToBackStack(null).commit()
            } else {
                Toast.makeText(requireContext(), "No dejes campos vacíos.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initViews(view: View) {
        etName = view.findViewById(R.id.bts_nombre)
        etAge = view.findViewById(R.id.et_edad)
        etWeight = view.findViewById(R.id.et_peso)
        etHeight = view.findViewById(R.id.et_altura)
        switchBtn = view.findViewById(R.id.sw_sex)
        btnCalculate = view.findViewById(R.id.btn_calcular)
    }
}