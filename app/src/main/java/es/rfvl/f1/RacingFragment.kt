package es.rfvl.f1

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import es.rfvl.f1.adapters.carrerasAdapter
import es.rfvl.f1.classes.Carreras
import es.rfvl.f1.databinding.FragmentRacingBinding


class RacingFragment() : Fragment() , carrerasAdapter.OnRaceClickListener{

    private lateinit var binding: FragmentRacingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRacingBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity2).changeToolbarTitle("RACING")
        val view = inflater.inflate(R.layout.fragment_racing, container, false)
        setUpRecyclerViewRace()

        return binding.root
    }

    private fun setUpRecyclerViewRace(){
        val race = mutableListOf<Carreras>(
            Carreras("Round 1","Bahrain","Formula 1 Gulf Air Bahrain grand prix 2023"),
            Carreras("Round 2","Saudi Arabia","Formula 1 STC Saudi Arabian prix 2023"),
            Carreras("Round 3","Australia","Formula 1 Rolex Australian prix 2023"),
            Carreras("Round 4","Azerbaijan","Formula 1 Azerbaijan grand prix 2023"),
            Carreras("Round 5","United States","Formula 1 Crypto.com Miami grand prix 2023"),
            Carreras("Round 6","Italy","Formula 1 Qatar Airways Gran Premio del MADE IN ITALY grand prix 2023"),
            Carreras("Round 7","Monaco","Formula 1 grand prix de Monaco 2023"),
            Carreras("Round 8","Spain","Formula 1 AWS Gran premio de España 2023"),
            Carreras("Round 9","Canada","Formula 1 Pirelli Grand prix du Canada 2023"),
            Carreras("Round 10","Austria","Rolex Grosser Preis Von Osterreich"),
            Carreras("Round 11","Great Britain","Formula 1 Aramco british gran prix 2023"),
            Carreras("Round 12","Hungary","Formula 1 Qatar airways hungarian grand prix 2023"),
            Carreras("Round 13","Belgium","Formula 1 MSC Cruises belgian grand prix 2023"),
            Carreras("Round 14","Netherlands","Formula 1 Heineken Dutch grand prix 2023"),
            Carreras("Round 15","Italy","Formula 1 Pirelli gran premio D'Italia 2023"),
            Carreras("Round 16","Singapore","Formula 1 Singapore airlines Singapore grand prix 2023"),
            Carreras("Round 17","Japan","Formula 1 Lenovo Japanese grand prix 2023"),
            Carreras("Round 18","Qatar","Formula 1 Qatar airways Qatar grand prix 2023 "),
            Carreras("Round 19","United States","Formula 1 Lenovo United States gran prix 2023"),
            Carreras("Round 20","Mexico","Formula 1 Gran permio de la ciudad de Mexico"),
            Carreras("Round 21","Brazil","Formula 1 Rolex grande premio de Sao Paulo 2023"),
            Carreras("Round 22","United States","Formula 1 Heineken Silver Las Vegas grand prix 2023"),
            Carreras("Round 23","Abu Dhabi","Formula 1 Etihad airways Abu Dhabi grand prix 2023")


        )
        race.reverse()
        val raceAdapter = carrerasAdapter(requireContext(), race, this)
        binding.recyclerCarreras.adapter = raceAdapter
        binding.recyclerCarreras.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

    }

    override fun onRaceClick(c: Carreras) {
        val snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_INDEFINITE)

        val snackbarView = snackbar.view as Snackbar.SnackbarLayout
        val customView = layoutInflater.inflate(R.layout.custom_snackbar, null)

        customView.findViewById<Button>(R.id.btnYes).setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
            } else {
                solicitarPermisoCamara()
            }

            snackbar.dismiss()
        }

        customView.findViewById<Button>(R.id.btnNo).setOnClickListener {
            snackbar.dismiss()
        }
        snackbarView.addView(customView)
        snackbar.show()
    }
    private fun solicitarPermisoCamara() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                0
            )
        }
    }





}