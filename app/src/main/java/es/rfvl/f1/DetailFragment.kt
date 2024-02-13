package es.rfvl.f1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import es.rfvl.f1.databinding.ActivityMain2Binding


class DetailFragment : Fragment() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityMain2Binding.inflate(layoutInflater)


        binding.myToolBar.title == "RONAL"
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val nombre = arguments?.getString("nombreDriverClass")
        val apellido = arguments?.getString("apellidoDriverClass")
        val escuderia = arguments?.getString("escuderiaDriverClass")
        val imagen = arguments?.getInt("imagenDriverClass")
        val podiumsActuales = arguments?.getString("podiumsActuales")
        val podiumsTotales = arguments?.getString("podiumsTotales")
        val gpsEnteredActual = arguments?.getString("gpsEnteredActual")
        val gpsEnteredTotales = arguments?.getString("gpsEnteredTotales")
        val worldChampion = arguments?.getString("worldChampion")
        val country = arguments?.getString("country")
        val birthday = arguments?.getString("dateOfBirth")

        val nombreDriver = view.findViewById<TextView>(R.id.textNombreDetail)
        val apellidoDriver = view.findViewById<TextView>(R.id.textApellidoDetail)
        val escuderiaDriver = view.findViewById<TextView>(R.id.textEscuderiaDetail)
        val imagenDriver = view.findViewById<ImageView>(R.id.imagenDriverDetail)
        val podiumsAct = view.findViewById<TextView>(R.id.podiumsTemporadaActual)
        val podiumsTot = view.findViewById<TextView>(R.id.podiumsTotales)
        val gpsEnteredAct = view.findViewById<TextView>(R.id.gpsEnteredActual)
        val gpsEnteredTot = view.findViewById<TextView>(R.id.gpsEnteredTotales)
        val worldChamp = view.findViewById<TextView>(R.id.worldChampions)
        val pais = view.findViewById<TextView>(R.id.pais)
        val birthDay = view.findViewById<TextView>(R.id.birthday)




        if(imagen != null){
            imagenDriver.setImageResource(imagen)
        }
        nombreDriver.text = nombre
        apellidoDriver.text = apellido
        escuderiaDriver.text = escuderia
        podiumsAct.text = podiumsActuales
        podiumsTot.text = podiumsTotales
        gpsEnteredAct.text = gpsEnteredActual
        gpsEnteredTot.text = gpsEnteredTotales
        worldChamp.text = worldChampion
        pais.text = country
        birthDay.text = birthday

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()


    }



}