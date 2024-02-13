package es.rfvl.f1

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import es.rfvl.f1.databinding.ActivityMain2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity2 : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_F1)
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.myToolBar)
        setUpNavigationDrawer()
        changeToolbarTitle("NEWS")
        val bottonNavigationView: BottomNavigationView = this.findViewById(R.id.bottomNavigationView)
        bottonNavigationView.visibility = View.VISIBLE

        bottonNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.news -> {


                    val nuevoFragmento = NewsFragment()
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentcontainerview, nuevoFragmento)
                        .addToBackStack(null)
                        .commit()

                    true
                }
                R.id.drivers -> {

                    val nuevoFragmento = DriversFragment()
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentcontainerview, nuevoFragmento)
                        .addToBackStack(null)
                        .commit()

                    true
                }
                R.id.racing -> {

                    val nuevoFragmento = RacingFragment()
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentcontainerview, nuevoFragmento)
                        .addToBackStack(null)
                        .commit()


                    true
                }
                R.id.circuits -> {

                    val nuevoFragmento = CircuitsFragment()
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentcontainerview, nuevoFragmento)
                        .addToBackStack(null)
                        .commit()

                    true
                }
                else -> false
            }
        }

    }





    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return when(item.itemId){
            R.id.nav_chat ->{


                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<ChatFragment>(R.id.fragmentcontainerview)
                    addToBackStack(null)
                }
                binding.bottomNavigationView.visibility = View.GONE
                binding.myToolBar.visibility = View.GONE

                true
            }
            R.id.nav_multimedia ->{

                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<MultimediaFragment>(R.id.fragmentcontainerview)
                    addToBackStack(null)
                }
                binding.bottomNavigationView.visibility = View.GONE
                binding.myToolBar.visibility = View.GONE
                true
            }
            R.id.nav_web -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.formula1.com/")))
                true
            }
            R.id.nav_clasification -> {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<ClasificationFragment>(R.id.fragmentcontainerview)
                    addToBackStack(null)
                }
                binding.bottomNavigationView.visibility = View.GONE
                binding.myToolBar.visibility = View.GONE
                true
            }
            else -> false

        }
    }

    private fun setUpNavigationDrawer(){
        val toggle = ActionBarDrawerToggle(
            this,binding.drawerLayout, binding.myToolBar, R.string.navOpen,R.string.navClose
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(this)

        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.inflateMenu(R.menu.navigation_menu)

        //val switchMenuItem = navigationView.menu.findItem(R.id.menu_switch)

        //val featureSwitch = switchMenuItem.actionView as Switch

        //featureSwitch.setOnCheckedChangeListener { _, isChecked ->
            //if (isChecked) {
            //    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            //} else {
           //     AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
          //  }
        //}

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){
        R.id.action_settings -> {
            binding.bottomNavigationView.visibility = View.GONE
            binding.myToolBar.visibility = View.GONE
            val nuevoFragmento = SettingsFragment()
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentcontainerview, nuevoFragmento)
                .addToBackStack(null)
                .commit()

            true
        }

        else -> false

    }

    fun changeToolbarTitle(newTitle: String) {
        binding.myToolBar.title = newTitle
    }




}