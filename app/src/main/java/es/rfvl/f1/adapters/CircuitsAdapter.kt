package es.rfvl.f1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import es.rfvl.f1.R
import es.rfvl.f1.classes.Circuits

class CircuitsAdapter(private val context: Context, private val CIRCUITS: MutableList<Circuits>, private val mListener: OnCircuitsClickListener): RecyclerView.Adapter<CircuitsAdapter.CircuitsViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircuitsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_circuits,parent,false)
        return CircuitsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return CIRCUITS.size
    }

    override fun onBindViewHolder(holder: CircuitsViewHolder, position: Int) {
        val circuitos = CIRCUITS[position]
        holder.bindItem(circuitos)
        holder.itemView.setOnClickListener{ mListener.onCircuitClick(circuitos)}
    }

    class CircuitsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val nombreCircuito: TextView = view.findViewById(R.id.textNombreCircuito)
        private val nVueltas: TextView = view.findViewById(R.id.textNvueltas)
        private val distancia: TextView = view.findViewById(R.id.textDistancia)
        private val textoVueltas: TextView = view.findViewById(R.id.textView4)
        private val textoDistancia: TextView = view.findViewById(R.id.textView11)
        private val image: ImageView = view.findViewById(R.id.imagenCircuito)

        init {
            val nightMode =
                view.context.resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
            val textColor = if (nightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                ContextCompat.getColor(view.context, R.color.white)
            } else {
                ContextCompat.getColor(view.context, R.color.black)
            }
            changeTextColorForAllTextViews(itemView, textColor)
        }




        fun bindItem(circuits: Circuits){
            nombreCircuito.text = circuits.nombreCircuito
            nVueltas.text = circuits.nVueltas
            distancia.text = circuits.km
            image.setImageResource(circuits.image)
        }
        private fun changeTextColorForAllTextViews(view: View, color: Int) {
            if (view is TextView) {
                view.setTextColor(color)
            } else if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    changeTextColorForAllTextViews(view.getChildAt(i), color)
                }
            }
        }
    }

    interface OnCircuitsClickListener{
        fun onCircuitClick(c: Circuits)
    }

}