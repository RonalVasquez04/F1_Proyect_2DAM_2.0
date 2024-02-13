package es.rfvl.f1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.rfvl.f1.R
import es.rfvl.f1.classes.Drivers

class driversAdapter(private val context: Context, private val DRIVERS: MutableList<Drivers>, private val mListener: OnDriversClickListener) : RecyclerView.Adapter<driversAdapter.DriversViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_drivers, parent, false)
        return DriversViewHolder(view)
    }

    override fun getItemCount(): Int {
        return DRIVERS.size
    }

    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        val corredor = DRIVERS[position]
        holder.bindItem(corredor)
        holder.itemView.setOnClickListener{ mListener.onDriverClick(corredor)}
    }

    class DriversViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val nameDriver: TextView = view.findViewById(R.id.textNombre)
        private val escuderia: TextView = view.findViewById(R.id.textEscuderia)
        private val lastName: TextView = view.findViewById(R.id.textApellido)
        private val photoDriver: ImageView = view.findViewById(R.id.imagenDriver)

        fun bindItem(drivers: Drivers){
            photoDriver.setImageResource(drivers.imagenDriver)
            nameDriver.text = drivers.name
            lastName.text = drivers.apellido
            escuderia.text = drivers.escuderia
        }
    }

    interface OnDriversClickListener{

        fun onDriverClick(d: Drivers)
    }
}