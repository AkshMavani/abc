package com.example.delete

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder

class ColorAdapter(
    var context: Context,
    private val colors: List<Int>,
    private val onColorSelected: (Int) -> Unit,
    private val onPickColor: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_COLOR = 0
        private const val TYPE_PICKER = 1
    }
    private var currentBackgroundColor =  0xffffffff
    private val root: View? = null

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


                ColorPickerDialogBuilder
                    .with(context)
                    .setTitle("Choose color")
                    .initialColor(currentBackgroundColor.toInt())
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener(object : OnColorSelectedListener {
                        override fun onColorSelected(selectedColor: Int) {
                            Log.e("TAG", "onColorSelected: $selectedColor", )
                            // toast("onColorSelected: 0x" + Integer.toHexString(selectedColor))
                        }
                    })
                    .setPositiveButton("ok", object : ColorPickerClickListener {
                        override fun onClick(dialog: DialogInterface?, selectedColor: Int, allColors: Array<Int?>?) {
                            changeBackgroundColor(selectedColor)
                        }
                    })
                    .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> })
                    .build()
                    .show()
            }
        }
    }
    private fun changeBackgroundColor(selectedColor: Int) {
        currentBackgroundColor = selectedColor.toLong()
        root?.setBackgroundColor(selectedColor)
    }
}
