package es.rfvl.f1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import es.rfvl.f1.adapters.chatMessageAdapter
import es.rfvl.f1.classes.ChatMessage
import es.rfvl.f1.databinding.ActivityMain2Binding
import es.rfvl.f1.databinding.FragmentChatBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChatFragment : Fragment() {
    private var Mensajes = mutableListOf<ChatMessage>()
    private lateinit var binding: FragmentChatBinding

    private lateinit var mAdapter: chatMessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)
        val activity = requireActivity() as AppCompatActivity


        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        binding.btnBackChat.setOnClickListener {
            activity.supportActionBar?.show()
            activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE

            fragmentManager?.popBackStack()
        }

        binding.sendButton.setOnClickListener {
            val textoEnviar = binding.messageEditText.text.toString()
            val prefs = requireActivity().getSharedPreferences("es.rfvl.f1_preferences", Context.MODE_PRIVATE)
            val nombre = prefs.getString("signature", "").toString()
            lifecycleScope.launch(Dispatchers.IO) {
                AuthManagerChat().addData(textoEnviar,nombre)
                withContext(Dispatchers.Main){
                    binding.messageEditText.text = null
                    binding.chatRecyclerView.scrollToPosition(Mensajes.size -1 )
                }
            }
        }

        setUpRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpRecyclerView(){
        //lifecycleScope.launch(Dispatchers.IO) {
        //    val allMessages = AuthManagerChat().getData()
        //    withContext(Dispatchers.Main){
        //        Mensajes.addAll(allMessages)
        //        mAdapter.notifyDataSetChanged()
        //    }
        //}

        lifecycleScope.launch(Dispatchers.IO) {
            AuthManagerChat().getDataFLow().collect{ datosUpdated ->
                Mensajes.clear()
                Mensajes.addAll(datosUpdated)
                withContext(Dispatchers.Main){
                    mAdapter.notifyDataSetChanged()
                    binding.chatRecyclerView.scrollToPosition(Mensajes.size -1 )
                }
            }

        }

        mAdapter = chatMessageAdapter(requireContext(), Mensajes)
        binding.chatRecyclerView.adapter = mAdapter
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
    }

    override fun onResume() {
        val activity = requireActivity() as AppCompatActivity
        super.onResume()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                activity?.supportActionBar?.show()
                activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility = View.VISIBLE

                activity?.supportFragmentManager?.popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }


}