package com.example.beer_app
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)

        val account_icon_button = findViewById<ImageButton>(R.id.account_icon_button)
        account_icon_button.setOnClickListener {
            navController.navigate(R.id.accountLoginFragment)
        }

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_maps -> {
                    ShowTable(navigationView)
                    navController.navigate(R.id.mapsFragment)
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> toolbarTitle.text = getString(R.string.login)
                R.id.mapsFragment -> toolbarTitle.text = getString(R.string.beer_map)
                R.id.accountLoginFragment -> toolbarTitle.text = getString(R.string.account_login)
                R.id.signUpFragment -> toolbarTitle.text = getString(R.string.sign_up)
                else -> toolbarTitle.text = getString(R.string.app_name)
            }
        }
    }

    fun ShowTable(view: View) {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val navigationViewBackground = findViewById<FrameLayout>(R.id.nav_view_background)

        if (navigationView.translationX == 0f) {
            navigationView.animate().translationX(-navigationView.width.toFloat()).setDuration(300).start()
            navigationViewBackground.animate().alpha(0f).setDuration(300).start()
            navigationViewBackground.isVisible = view.isGone
        }
        else {
            navigationView.animate().translationX(0f).setDuration(300).start()
            navigationViewBackground.animate().alpha(1f).setDuration(300).start()
            navigationViewBackground.isVisible = true
        }
    }

}
