package es.rfvl.f1

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.rfvl.f1.adapters.newsAdapter
import es.rfvl.f1.classes.News
import es.rfvl.f1.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private var tiempoRestanteMilis: Long = 0
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater)

        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val days: TextView = view.findViewById(R.id.textDays)
        val hours: TextView = view.findViewById(R.id.textHours)
        val minutes: TextView = view.findViewById(R.id.textMinutes)
        val seconds: TextView = view.findViewById(R.id.textSeconds)

        if (countDownTimer == null) {
            iniciarTemporizador(10,15,20,26)
        }

        setUpRecyclerView()
        return binding.root
    }
    private fun iniciarTemporizador(days: Int , hours: Int, minutes: Int ,seconds: Int){
        val totalMillis = ((days * 24 + hours) * 3600 + minutes* 60 + seconds) * 1000L
        countDownTimer = object : CountDownTimer(totalMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                tiempoRestanteMilis = millisUntilFinished
                actualizarContador()
            }
            override fun onFinish() {
                iniciarTemporizador(7,23,59,59)
            }
        }.start()

    }

    private fun actualizarContador(){
        val dias = ((tiempoRestanteMilis/ (1000 * 60 * 60 * 24))).toInt()
        val hours = ((tiempoRestanteMilis/ (1000 * 60 * 60 ))%24).toInt()
        val minutes = ((tiempoRestanteMilis/ (1000 * 60 ))%60).toInt()
        val seconds = ((tiempoRestanteMilis/ 1000) % 60 ).toInt()

        binding.textDays?.text = dias.toString()
        binding.textHours?.text = hours.toString()
        binding.textMinutes?.text = minutes.toString()
        binding.textSeconds?.text = seconds.toString()
    }

    private fun setUpRecyclerView(){
        val noticias = mutableListOf<News>(
            News(R.drawable.news1,"Red Bull unveil special fan-designed Las Vegas livery ahead of weekend's inaugural Grand Prix"),
            News(R.drawable.news2,"5 Winners and 5 losers from Las Vegas - Who hit the jackpot in Sin City?"),
            News(R.drawable.news3,"SAY WHAT?! Verstappen croons, Norris crashes and Leclerc yee-haws in the best team radio from Las Vegas"),
            News(R.drawable.news4,"Verstappen understands date change for Dutch Grand Prix"),
            News(R.drawable.news5,"These drivers will be in action during FP1 for the Abu Dhabi GP"),
            News(R.drawable.news6,"Formula 1 releases 2024 calendar with more Saturday races, return of Chinese Grand Prix"),
            News(R.drawable.news2,"5 Winners and 5 losers from Las Vegas - Who hit the jackpot in Sin City?"),
            News(R.drawable.news3,"SAY WHAT?! Verstappen croons, Norris crashes and Leclerc yee-haws in the best team radio from Las Vegas"),
            News(R.drawable.news4,"Verstappen understands date change for Dutch Grand Prix"),
            News(R.drawable.news5,"These drivers will be in action during FP1 for the Abu Dhabi GP"),
            News(R.drawable.news6,"Formula 1 releases 2024 calendar with more Saturday races, return of Chinese Grand Prix"),
            News(R.drawable.news2,"5 Winners and 5 losers from Las Vegas - Who hit the jackpot in Sin City?"),
            News(R.drawable.news3,"SAY WHAT?! Verstappen croons, Norris crashes and Leclerc yee-haws in the best team radio from Las Vegas"),
            News(R.drawable.news4,"Verstappen understands date change for Dutch Grand Prix"),
            News(R.drawable.news5,"These drivers will be in action during FP1 for the Abu Dhabi GP"),
            News(R.drawable.news6,"Formula 1 releases 2024 calendar with more Saturday races, return of Chinese Grand Prix"),
            News(R.drawable.news2,"5 Winners and 5 losers from Las Vegas - Who hit the jackpot in Sin City?"),
            News(R.drawable.news3,"SAY WHAT?! Verstappen croons, Norris crashes and Leclerc yee-haws in the best team radio from Las Vegas"),
            News(R.drawable.news4,"Verstappen understands date change for Dutch Grand Prix"),
            News(R.drawable.news5,"These drivers will be in action during FP1 for the Abu Dhabi GP"), News(R.drawable.news6,"Formula 1 releases 2024 calendar with more Saturday races, return of Chinese Grand Prix")
        )
        val newApad = newsAdapter(requireContext(),noticias)
        binding.recNews.adapter = newApad
        binding.recNews.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }

}