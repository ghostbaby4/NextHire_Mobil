package com.nexthire.nexhire.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexthire.nexhire.models.Departamento
import com.nexthire.nexhire.R

class DepartamentoAdapters(private var items: List<Departamento> = emptyList())
    : RecyclerView.Adapter<DepartamentoAdapters.VH>() {

    fun submit(list: List<Departamento>) {
        items = list
        notifyDataSetChanged()
    }

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvCodigo: TextView = v.findViewById(R.id.tvCodigo)
        val tvNombre: TextView = v.findViewById(R.id.tvNombre)
    }

    override fun onCreateViewHolder(p: ViewGroup, vt: Int): VH {
        val v = LayoutInflater.from(p.context).inflate(R.layout.item_departamento, p, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, pos: Int) {
        val it = items[pos]
        h.tvCodigo.text = it.codigo
        h.tvNombre.text = it.nombre
    }

    override fun getItemCount() = items.size
}
