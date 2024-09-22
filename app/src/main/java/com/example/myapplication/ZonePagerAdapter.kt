package com.example.tabletenniszones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ZonePagerAdapter(private val layouts: List<Int>) : RecyclerView.Adapter<ZonePagerAdapter.ZoneViewHolder>() {

    // ViewHolder для хранения слоя (зоны)
    class ZoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ZoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZoneViewHolder, position: Int) {
        // Привязка данных к слою, если необходимо
    }

    override fun getItemViewType(position: Int): Int {
        // Возвращаем макет для каждого слоя (зоны)
        return layouts[position]
    }

    override fun getItemCount(): Int {
        // Возвращаем количество слоев (зон)
        return layouts.size
    }
}
