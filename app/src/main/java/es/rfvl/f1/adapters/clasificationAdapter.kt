package es.rfvl.f1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.rfvl.f1.R
import es.rfvl.f1.classes.Clasification

class clasificationAdapter(private val datos: MutableList<Clasification>): RecyclerView.Adapter<clasificationAdapter.ClasificationViewFolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClasificationViewFolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_clasification, parent, false)
        return ClasificationViewFolder(view)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    override fun onBindViewHolder(holder: ClasificationViewFolder, position: Int) {
        val dato = datos[position]
        holder.bindItem(dato)
    }

    class ClasificationViewFolder(view: View): RecyclerView.ViewHolder(view){
        private val positionDriver: TextView = view.findViewById(R.id.textPos)
        private val numberDriver: TextView = view.findViewById(R.id.textNumberDriver)
        private val nameDriver: TextView = view.findViewById(R.id.textNomDriver)
        private val constructorDriver: TextView = view.findViewById(R.id.textConstructorClasfication)
        private val timeDriver: TextView = view.findViewById(R.id.textTimeDriver)

        fun bindItem(i: Clasification){
            positionDriver.text = i.pos
            numberDriver.text = i.num
            nameDriver.text = i.driver
            constructorDriver.text = i.constructor
            timeDriver.text = i.time

        }
    }
}