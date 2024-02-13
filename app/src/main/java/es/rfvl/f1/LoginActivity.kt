package es.rfvl.f1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() , LoginFragment.FragmentLoginListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("es.rfvl.f1_preferences", MODE_PRIVATE)
        val loginSave = prefs.getBoolean("syncLogin", false);

        if (loginSave){
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }else{
            setContentView(R.layout.activity_login)
        }
    }

    override fun onLoginClicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}