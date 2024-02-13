package es.rfvl.f1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import es.rfvl.f1.databinding.FragmentResetPasswordBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentResetPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater)
        val view = inflater.inflate(R.layout.fragment_reset_password, container, false)

        binding.btnSubmit.setOnClickListener {
            val email = binding.textoEmailReset.text.toString()

            if (!email.isNullOrBlank()){
                lifecycleScope.launch(Dispatchers.IO) {
                    AuthManager().resetPassword(email)
                    withContext(Dispatchers.Main){

                            Toast.makeText(requireContext(),"Se enviara el email", Toast.LENGTH_LONG).show()
                            fragmentManager?.popBackStack()

                    }
                }
            }
        }

        binding.btnGoBackResetPassword.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        return binding.root
    }


}