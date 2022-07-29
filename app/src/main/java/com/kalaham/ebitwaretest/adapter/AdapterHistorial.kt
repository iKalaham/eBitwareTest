package com.kalaham.ebitwaretest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kalaham.ebitwaretest.R
import com.kalaham.ebitwaretest.database.PersonasEntidad

class AdapterHistorial(val context: Context, var list: List<PersonasEntidad?>):
    RecyclerView.Adapter<AdapterHistorial.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHistorial.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_personas, parent, false)
        return AdapterHistorial.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterHistorial.ViewHolder, position: Int) {
        val persona: PersonasEntidad = list[position]!!
        holder.nombre.text = persona.name
        holder.edad.text = persona.age
        holder.nss.text = persona.nss
        holder.peso.text = persona.weight
        holder.altura.text = persona.height
        holder.legal.text = persona.legal
        holder.imc.text = persona.idealWeight
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.txt_name_db)
        val edad: TextView = itemView.findViewById(R.id.txt_age_db)
        val nss: TextView = itemView.findViewById(R.id.txt_nss_db)
        val peso: TextView = itemView.findViewById(R.id.txt_weight_db)
        val altura: TextView = itemView.findViewById(R.id.txt_height_db)
        val legal: TextView = itemView.findViewById(R.id.txt_legal_db)
        val imc: TextView = itemView.findViewById(R.id.txt_imc_db)

    }

}