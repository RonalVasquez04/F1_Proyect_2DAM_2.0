package es.rfvl.f1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import es.rfvl.f1.adapters.videosAdapter
import es.rfvl.f1.classes.Video
import es.rfvl.f1.databinding.FragmentMultimediaBinding

class MultimediaFragment : Fragment() {

    private lateinit var binding: FragmentMultimediaBinding
    private lateinit var videosAdapter: videosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMultimediaBinding.inflate(layoutInflater)

        videosAdapter = videosAdapter(getVideos())
        binding.viewPager.adapter = videosAdapter

        binding.backButtonMultimedia.setOnClickListener {
            fragmentManager?.popBackStack()
            val activity = requireActivity() as AppCompatActivity
            activity?.supportActionBar?.show()
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility = View.VISIBLE

        }

        return binding.root
    }

    private fun getVideos(): List<Video> {

        return listOf(
            Video("android.resource://${requireContext().packageName}/"+R.raw.f1_1),
            Video("android.resource://${requireContext().packageName}/"+R.raw.f1_2),
            Video("android.resource://${requireContext().packageName}/"+R.raw.f1_3),
            Video("android.resource://${requireContext().packageName}/"+R.raw.f1_4)


        )
    }

    override fun onResume() {
        val activity = requireActivity() as AppCompatActivity
        super.onResume()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.popBackStack()

                activity?.supportActionBar?.show()
                activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility = View.VISIBLE

            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

}