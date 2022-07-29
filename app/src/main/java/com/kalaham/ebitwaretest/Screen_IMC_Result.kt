package com.kalaham.ebitwaretest

import android.os.Bundle
import android.service.autofill.CharSequenceTransformation
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.kalaham.ebitwaretest.database.DatabaseConfig
import com.kalaham.ebitwaretest.database.PersonasEntidad
import java.lang.Exception

class Screen_IMC_Result : Fragment() {

    private lateinit var txtName: TextView
    private lateinit var txtAge: TextView
    private lateinit var txtNss: TextView
    private lateinit var txtSex: TextView
    private lateinit var txtPeso: TextView
    private lateinit var txtAlt: TextView
    private lateinit var txtIMC: TextView
    private lateinit var txtMayor: TextView
    private lateinit var btnSave: Button

    private lateinit var nameArg: String
    private var ageArg: Int = 0
    private lateinit var nssArg: String
    private var sexArg: Char = 'H'
    private var pesoArg: Double = 0.0
    private var altArg: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_screen__i_m_c__result, container, false)

        nameArg = arguments?.getString("NAME") ?: ""
        ageArg = arguments?.getInt("AGE") ?: 0
        nssArg = arguments?.getString("NSS") ?: ""
        sexArg = arguments?.getChar("SEX") ?: 'H'
        pesoArg = arguments?.getDouble("WEIGHT") ?: 0.0
        altArg = arguments?.getDouble("HEIGHT") ?: 0.0


        initViews(view)
        setListeners()

        return view
    }

    private fun initViews(view: View) {
        txtName = view.findViewById(R.id.txt_name)
        txtAge = view.findViewById(R.id.txt_age)
        txtNss = view.findViewById(R.id.txt_nss)
        txtSex = view.findViewById(R.id.txt_sex)
        txtPeso = view.findViewById(R.id.txt_peso)
        txtAlt = view.findViewById(R.id.txt_alt)
        txtIMC = view.findViewById(R.id.txt_ope)
        txtMayor = view.findViewById(R.id.txt_legal)
        btnSave = view.findViewById(R.id.btn_save)

        txtName.text = nameArg
        txtAge.text = ageArg.toString()
        txtNss.text = nssArg
        txtSex.text = sexArg.toString()
        txtPeso.text = pesoArg.toString()
        txtAlt.text = altArg.toString()

        val imc = MetodosPersona.calcularImc(
            peso = pesoArg,
            altura = altArg,
            sexo = sexArg
        )

        val edadMayor = MetodosPersona.esMayorDeEdad(
            edad = ageArg
        )

        when (imc) {
            MetodosPersona.PESO_MAYOR -> txtIMC.text = "Sobrepeso"
            MetodosPersona.PESO_IDEAL -> txtIMC.text = "Peso ideal"
            MetodosPersona.PESO_MENOR -> txtIMC.text = "Falta de peso"
        }

        if (edadMayor) txtMayor.text = "Eres mayor de edad" else "Eres menor de edad"
    }

    private fun setListeners() {
        btnSave.setOnClickListener {
            try {
                DatabaseConfig.getInstance(requireContext()).personasDAO().insert(
                    PersonasEntidad(
                        name = nameArg,
                        age = ageArg.toString(),
                        nss = nssArg,
                        weight = pesoArg.toString(),
                        height = altArg.toString(),
                        legal = txtMayor.text.toString(),
                        idealWeight = txtIMC.text.toString()
                    )
                )

                Toast.makeText(
                    requireContext(),
                    "Info guardada en la base de datos",
                    Toast.LENGTH_SHORT
                ).show()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_container, HomeFrag()).commit()
            } catch (e: Exception) {
                Log.e("ERROR", "Error al insertar en la base de datos")
            }
        }
    }

}