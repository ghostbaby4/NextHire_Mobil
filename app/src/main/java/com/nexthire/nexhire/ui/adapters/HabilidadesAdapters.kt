package com.nexthire.nexhire.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexthire.nexhire.models.Habilidades
import com.nexthire.nexhire.R

class HabilidadesAdapters(private var items: List<Habilidades> = emptyList())
    : RecyclerView.Adapter<HabilidadesAdapters.VH>() {

    fun submit(list: List<Habilidades>) {
        items = list
        notifyDataSetChanged()
    }

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvCodigo: TextView = v.findViewById(R.id.tvCodigo)
        val tvNombre: TextView = v.findViewById(R.id.tvNombre_Habilidad)
    }

    override fun onCreateViewHolder(p: ViewGroup, vt: Int): VH {
        val v = LayoutInflater.from(p.context).inflate(R.layout.item_habilidades, p, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, pos: Int) {
        val it = items[pos]
        h.tvCodigo.text = it.codigo
        h.tvNombre.text = it.nombre_Habilidad
    }

    override fun getItemCount() = items.size
}