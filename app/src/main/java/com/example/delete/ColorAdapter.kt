package com.example.delete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(
    private val colors: List<Int>,
    private val onColorSelected: (Int) -> Unit,
    private val onPickColor: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_COLOR = 0
        private const val TYPE_PICKER = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == colors.size) TYPE_PICKER else TYPE_COLOR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_COLOR) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
            ColorViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color_picker, parent, false)
            ColorPickerViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ColorViewHolder) {
            holder.bind(colors[position])
        } else if (holder is ColorPickerViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return colors.size + 1 // +1 for the color picker item
    }

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val colorView: View = itemView.findViewById(R.id.color_view)

        fun bind(color: Int) {
            colorView.setBackgroundColor(color)
            itemView.setOnClickListener {
                onColorSelected(color)
            }
        }
    }

    inner class ColorPickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pickerButton: Button = itemView.findViewById(R.id.picker_button)

        fun bind() {
            pickerButton.setOnClickListener {
                onPickColor()
            }
        }
    }
}
