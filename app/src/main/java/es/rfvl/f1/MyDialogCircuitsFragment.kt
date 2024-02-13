package es.rfvl.f1

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import es.rfvl.f1.databinding.FragmentCircuitsBinding
import es.rfvl.f1.databinding.FragmentMyDialogCircuitsBinding

class MyDialogCircuitsFragment : DialogFragment() {
    private lateinit var  binding: FragmentMyDialogCircuitsBinding
    private lateinit var mListener: MyDialogCircuitsFragment

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.fragment_my_dialog_circuits, null)

            val nom = arguments?.getString("nombreCircuitoDialog")
            val pais = arguments?.getString("pais")
            val imagePista = arguments?.getInt("imagen")
            val vueltasN = arguments?.getString("vueltas")
            val km = arguments?.getString("distancia")
            val info = arguments?.getString("informacion")



            val closeButton = view.findViewById<ImageView>(R.id.btnClose)
            val nameCircuit = view.findViewById<TextView>(R.id.textNombreCircuitoDilaog)
            val imagen = view.findViewById<ImageView>(R.id.imagenCircuitoDialog)
            val vueltas = view.findViewById<TextView>(R.id.textnVueltasDialog)
            val distancia = view.findViewById<TextView>(R.id.textKMCircuitsDialog)
            val country = view.findViewById<TextView>(R.id.textCountryCircuitDialog)
            val informacion = view.findViewById<TextView>(R.id.textinformacionCircuitDialog)

            if(imagePista != null){
                imagen.setImageResource(imagePista)
            }
            nameCircuit.text = nom
            country.text = pais
            vueltas.text = vueltasN
            distancia.text = km
            informacion.text = info
            closeButton.setOnClickListener {
                dismiss()
            }


            builder.setView(view)
            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyDialogCircuitsBinding.inflate(layoutInflater)


        val view = inflater.inflate(R.layout.fragment_my_dialog_circuits, container, false)



        return binding.root
    }






}