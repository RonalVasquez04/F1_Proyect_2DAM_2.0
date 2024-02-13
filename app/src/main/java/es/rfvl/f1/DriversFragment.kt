package es.rfvl.f1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.rfvl.f1.adapters.driversAdapter
import es.rfvl.f1.classes.Drivers
import es.rfvl.f1.databinding.FragmentDriversBinding

class DriversFragment : Fragment() , driversAdapter.OnDriversClickListener{

    private lateinit var binding: FragmentDriversBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDriversBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.fragment_drivers, container, false)
        (requireActivity() as MainActivity2).changeToolbarTitle("DRIVERS")


        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        var corredores= mutableListOf<Drivers>(
            Drivers("Max", "Verstappen", "Red Bull Racing", R.drawable.max_verstappen, "19","96", "21","183","3","Nederland","30/09/1997"),
            Drivers("Sergio", "Perez", "Red Bull Racing", R.drawable.sergioperez,"8","34","21","256","0", "Mexico","26/01/1990"),
            Drivers("Lewis", "Hamilton", "Mercedes", R.drawable.hamilton,"6","197","21","330","7","United Kingdom","07/01/1985"),
            Drivers("Fernando", "Alonso", "Aston Martin", R.drawable.alonso,"8","106","21","378","2","Spain","29/07/1981"),
            Drivers("Lando", "Norris", "McLaren", R.drawable.landonorris,"7","13","21","102","0","United Kingdom", "13/11/1999"),
            Drivers("Carlos", "Sainz", "Ferrari", R.drawable.sainz,"3","18","21","183","0","Spain","01/09/1994"),
            Drivers("Charles", "Leclerc", "Ferrari", R.drawable.leclerc,"4","28","21","123","0", "Monaco","16/10/1997"),
            Drivers("George", "Russell", "Mercedes", R.drawable.russel,"1","10","21","102","0","United KIngdom","15/02/1998"),
            Drivers("Oscar", "Piastri", "McLaren", R.drawable.piastri,"2","2","21","20","0","Australia","06/04/2001"),
            Drivers("Lance", "Stroll", "Aston Martin", R.drawable.stroll,"0","3","21","141","0","Canada","29/10/1998"),
            Drivers("Pierre", "Gasly", "Alpine", R.drawable.gasly,"1","4","21","128","0","France","07/02/1996"),
            Drivers("Esteban", "Ocon", "Alpine", R.drawable.ocon,"1","3","21","121","0","France","17/09/1996"),
            Drivers("Alexander", "Albon", "Williams", R.drawable.albon,"0","2","21","79","0","Thailand","23/03/1996"),
            Drivers("Yuki", "Tsunoda", "AlphaTauri", R.drawable.tsunoda,"0","0","21","64","0","Japan","11/05/2000"),
            Drivers("Valtteri", "Bottas", "Alfa Romeo", R.drawable.bottas,"0","67","21","120","0","Finland","28/08/1989"),
            Drivers("Nico", "Hulkenberg", "Haas F1 Team", R.drawable.hulkenberg,"0","0","21","204","0","Germany","19/08/1987"),
            Drivers("Daniel", "Ricciardo", "AlphaTauri", R.drawable.ricciardo,"0","32","21","237","0","Australia","01/07/1989"),
            Drivers("Zhou", "Guanyu", "Alfa Romeo", R.drawable.guanyu,"0","0","21","42","0","China","30/05/1999"),
            Drivers("Kevin", "Magnussen", "Haas F1 Team", R.drawable.magnussen,"0","1","21","162","0","Denmark","05/10/1992"),
            Drivers("Liam", "Lawson", "AlphaTauri", R.drawable.lawson,"0","0","21","5","0","New Zealand","11/02/2002"),
            Drivers("Logan", "Sargeant", "Williams", R.drawable.sargeant,"0","0","21","20","0","USA","31/12/2000"),
            Drivers("Nyck", "De Vries", "AlphaTauri", R.drawable.devries,"0","0","21","11","0","Netherlands","06/02/1995")

        )
        val drivAdapter = driversAdapter(requireContext(),corredores, this)
        binding.recyclerDrivers.adapter = drivAdapter
        binding.recyclerDrivers.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }

    override fun onDriverClick(d: Drivers) {
        val nuevoFragmento = DetailFragment()
        val args = Bundle()

        args.putString("nombreDriverClass", d.name)
        args.putString("apellidoDriverClass", d.apellido)
        args.putString("escuderiaDriverClass",d.escuderia)
        args.putInt("imagenDriverClass",d.imagenDriver)
        args.putString("podiumsActuales", d.podiumsActuales)
        args.putString("podiumsTotales",d.podiumsTotales)
        args.putString("gpsEnteredActual",d.gpsEnteredActual)
        args.putString("gpsEnteredTotales",d.gpsEnteredTotales)
        args.putString("worldChampion",d.worldChampionships)
        args.putString("country",d.country)
        args.putString("dateOfBirth",d.dateOfBirth)
        nuevoFragmento.arguments = args

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentcontainerview, nuevoFragmento)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()

    }

}