package es.rfvl.f1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import es.rfvl.f1.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var mListener: FragmentLoginListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        binding.textoUsuario.text = null

        binding.btnIniciarSesion.setOnClickListener {
            val email = binding.textoUsuario.text.toString()
            val pass = binding.textoContraseA.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                if (!email.isNullOrBlank() && !pass.isNullOrBlank()){
                    lifecycleScope.launch(){
                        val userLogged = AuthManager().login(email,pass)
                        withContext(Dispatchers.Main){
                            if (userLogged != null){
                                val prefs = requireActivity().getSharedPreferences("es.rfvl.f1_preferences", Context.MODE_PRIVATE);
                                val nombre = prefs.getString("signature","");
                                with(prefs.edit()){
                                    putString("signature",email)
                                    //putBoolean("sync",false)
                                    //putBoolean("syncCheck", false)
                                    apply()
                                }
                                mListener.onLoginClicked()
                            }else{
                                Toast.makeText(requireContext(), "Bad credentials", Toast.LENGTH_LONG).show()
                                Snackbar.make(binding.root, "Registrese si no tiene una cuenta creada", Snackbar.LENGTH_SHORT).show()

                            }
                        }

                    }
                }
            }else{
                Snackbar.make(binding.root, "Debe rellenar el usuario y la contraseña", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnRegistrarse.setOnClickListener {
            val nuevoFragmento = RegisterFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentcontainerviewLogin, nuevoFragmento)
            fragmentTransaction.addToBackStack(null)

            fragmentTransaction.commit()
        }


        binding.recuperarContrasenya.setOnClickListener {
            val nuevoFragmento = ResetPasswordFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentcontainerviewLogin, nuevoFragmento)
            fragmentTransaction.addToBackStack(null)

            fragmentTransaction.commit()
        }


        return binding.root
    }


    interface FragmentLoginListener{
        fun onLoginClicked()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentLoginListener){
            mListener = context
        }else{
            throw Exception("EEROR")
        }
    }


}