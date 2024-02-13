package es.rfvl.f1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import es.rfvl.f1.R
import es.rfvl.f1.classes.Carreras

class carrerasAdapter(private val context: Context, private val race: MutableList<Carreras>,private val mListener: carrerasAdapter.OnRaceClickListener): RecyclerView.Adapter<carrerasAdapter.CarrerasViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrerasViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_carreras, parent, false)
        return CarrerasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return race.size
    }

    override fun onBindViewHolder(holder: CarrerasViewHolder, position: Int) {
        val carrera = race[position]
        holder.bindItem(carrera)
        holder.itemView.setOnClickListener{mListener.onRaceClick(carrera)}
    }

    class CarrerasViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val round: TextView = view.findViewById(R.id.textRound)
        private val city: TextView = view.findViewById(R.id.textCiudad)
        private val textoCarrera: TextView = view.findViewById(R.id.textCarrera)

        private val nightMode =
            view.context.resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK

        init {
            if (nightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                textoCarrera.setTextColor(ContextCompat.getColor(view.context, R.color.black))
            }
        }

        fun bindItem(carrera: Carreras){
            round.text = carrera.round
            city.text = carrera.city
            textoCarrera.text = carrera.textoCarrera
        }
    }

    interface OnRaceClickListener{

        fun onRaceClick(c: Carreras)
    }
}