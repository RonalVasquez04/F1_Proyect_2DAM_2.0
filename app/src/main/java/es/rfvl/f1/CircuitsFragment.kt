package es.rfvl.f1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import es.rfvl.f1.adapters.CircuitsAdapter
import es.rfvl.f1.classes.Circuits
import es.rfvl.f1.databinding.FragmentCircuitsBinding


class CircuitsFragment : Fragment(), CircuitsAdapter.OnCircuitsClickListener {

    private lateinit var binding: FragmentCircuitsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCircuitsBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity2).changeToolbarTitle("CIRCUITS")
        val view = inflater.inflate(R.layout.fragment_circuits, container, false)
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView(){
        val circuits = mutableListOf<Circuits>(
            Circuits("Internacional de Bahréin",R.drawable.baherin,"57","308.2 km","Bareín","Also known as Sakhir International Circuit, it is a speedway located in the desert area of Sakhir, within the Southern Governorate of the country, approximately 31 km from the capital Manama. Since 2004 it has been the current headquarters of the Formula 1 Bahrain Grand Prix, being at the time the first race held in the Middle East within that category."),
            Circuits("Jeddah Corniche Circuit",R.drawable.jeddah,"50","308.5 km","Saudi Arabia","The Jeddah Corniche Circuit is a 6.174 km  motor racing circuit built in the Red Sea port city of Jeddah, in Saudi Arabia. The circuit staged the inaugural Saudi Arabian Grand Prix on 5 December 2021 as the penultimate race on the 2021 Formula One season calendar."),
            Circuits("Circuito de Albert Park",R.drawable.albertpark,"58","306.1 km","Australia","The Albert Park Circuit, also called Melbourne Circuit, is a racing circuit that is set up around Lake Albert Park, south of the city of Melbourne, Australia. It has hosted the Australian Formula 1 Grand Prix annually since 1996 and a non-scoring V8 Supercars date."),
            Circuits("Circuito Urbano de Bakú",R.drawable.baku,"51","306.0 km","Azerbaijan","It is an urban racing circuit built in the city of Baku, Azerbaijan, on Baku Boulevard. It hosted the 2016 European Grand Prix of Formula 1. As of 2017, the FIA, after seeing the good results obtained on the Baku street circuit, has granted the change of the name of European Grand Prix so that from 2017 will be held as the Azerbaijan Grand Prix"),
            Circuits("Miami International Autodrome",R.drawable.miami,"57","308.3 km","United States","The Miami International Speedway is an urban racing circuit located in Miami Gardens, United States. It was announced in April 2021 and has hosted the Formula 1 Miami Grand Prix from 2022."),
            Circuits("Autódromo Enzo",R.drawable.enzo,"63","309.0 km","Italy","It is a 4.9 km road course located in Imola, Emilia-Romagna region, Italy, about 30 km southeast of the city of Bologna. It is one of the main ones in its country, having hosted races of numerous world and European championships in sprint motorsport and sprint motorcycling as well as Italian. The circuit was originally named Autodromo di Castellaccio."),
            Circuits("Circuito de Mónaco",R.drawable.monaco,"78","260.3 km","Monaco","It is an urban racing circuit located between the districts of Monte Carlo and La Condamine (this being the place where the arrival and departure point is located), within the Principality of Monaco. It was inaugurated in 1920 by Antony Noghès, and annually hosts the Monaco Formula 1 Grand Prix and the Monaco Formula E E-prix."),
            Circuits("Circuit de Barcelona",R.drawable.barcelona,"66","307.2 km","Spain","Inaugurated in 1991, it has a capacity of 140,700 spectators, and hosts various competitions, including the Spanish Formula 1 Grand Prix and the Catalan Motorcycle Grand Prix of the World Motorcycle Championship. It is also one of the most used circuits for rehearsals by the different European teams during the winter break."),
            Circuits("Circuito Gilles Villeneuve",R.drawable.villeneuve,"70","305.3 km","Canada","Also called the Montreal Circuit, it is an urban racing circuit located on the Île Notre-Dame, an artificial island located in the Saint Lawrence River and which is part of the Jean-Drapeau Park in the city of Montreal, province of Quebec, Canada. The circuit originally had the name Circuit Île Notre-Dame")
        )
        val circuitsAdapt = CircuitsAdapter(requireContext(),circuits,this)
        binding.recyclerCircuits.adapter = circuitsAdapt
        binding.recyclerCircuits.layoutManager = GridLayoutManager(requireContext(),2)

    }

    override fun onCircuitClick(c: Circuits) {
        val nuevoFragmento = MyDialogCircuitsFragment()
        val args = Bundle()

        args.putString("nombreCircuitoDialog", c.nombreCircuito)
        args.putInt("imagen",c.image)
        args.putString("vueltas",c.nVueltas)
        args.putString("distancia",c.km)
        args.putString("pais",c.pais)
        args.putString("informacion",c.info)

        nuevoFragmento.arguments = args
        nuevoFragmento.show(requireActivity().supportFragmentManager, "Circuits Dialog")
    }

}