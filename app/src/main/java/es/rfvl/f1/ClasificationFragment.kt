package es.rfvl.f1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.rfvl.f1.adapters.clasificationAdapter
import es.rfvl.f1.adapters.driversAdapter
import es.rfvl.f1.classes.Clasification
import es.rfvl.f1.databinding.FragmentClasificationBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClasificationFragment : Fragment() {
    private lateinit var mAdapter: clasificationAdapter
    private lateinit var binding: FragmentClasificationBinding
    private lateinit var listClasification: MutableList<Clasification>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as AppCompatActivity
        binding = FragmentClasificationBinding.inflate(layoutInflater)
        binding.btnBackClasification.setOnClickListener {
            activity.supportActionBar?.show()
            activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE

            fragmentManager?.popBackStack()
        }

        binding.btnSearchClasification.setOnClickListener {
            listClasification.clear()

            searchRound(binding.textYearSearch.text.toString(),binding.textRoundSearch.text.toString())
        }
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView(){

        listClasification = emptyList<Clasification>().toMutableList()
        mAdapter = clasificationAdapter(listClasification)
        binding.recViewClasification.adapter = mAdapter
        binding.recViewClasification.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
    }

    private fun searchRound(year: String, round: String){
        if (!year.isNullOrEmpty() && !round.isNullOrEmpty()){
            lifecycleScope.launch(Dispatchers.IO) {
                val call = RetrofitObject.getInstance().create(F1Service::class.java).getQualifyingResults(year,round)
                val response = call.body()

                withContext(Dispatchers.Main){

                    if (response != null){
                        val listaDatos = response.mrData.raceTable.races.firstOrNull()?.qualifyingResults

                        if (listaDatos != null) {
                            for (result in listaDatos){
                                listClasification.add(Clasification(result.position,result.number,result.driver.givenName+" "+result.driver.familyName,result.constructor.name,result.q1))
                                mAdapter.notifyDataSetChanged()
                            }

                        }
                    }
                }
            }
        }else{
            Toast.makeText(requireContext(), "Error, You must type a season and round", Toast.LENGTH_SHORT).show()

        }
    }
}