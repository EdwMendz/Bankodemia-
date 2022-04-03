package mx.kodemia.bankodemiaapp.modules.home.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.internet.NetworkChangeListener
import mx.kodemia.bankodemiaapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    //View Binding
    private lateinit var binding: ActivityHomeBinding

    //Internet Monitor
    val networkChangeListener: NetworkChangeListener = NetworkChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_inicio, R.id.navigation_tarjeta, R.id.navigation_servicios
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onStart() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeListener,filter)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(networkChangeListener)
        super.onStop()
    }

}