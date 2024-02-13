package es.rfvl.f1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class SettingsFragment : PreferenceFragmentCompat(),SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val usernamePreference = findPreference<EditTextPreference>("signature")
        val prefs = requireActivity().getSharedPreferences("es.rfvl.f1_preferences", Context.MODE_PRIVATE)
        val nombre = prefs.getString("signature", "")

        val defaultValue = nombre
        usernamePreference?.text = defaultValue
    }

    override fun onStart() {
        super.onStart()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "email") {
            val usernamePreference = findPreference<EditTextPreference>("signature")
            val nuevoNombre = sharedPreferences?.getString("email", "")
            usernamePreference?.text = nuevoNombre
        }
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