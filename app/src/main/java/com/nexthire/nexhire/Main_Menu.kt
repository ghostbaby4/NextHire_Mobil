package com.nexthire.nexhire

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.nexthire.nexhire.ui.fragments.CargoFragment
import com.nexthire.nexhire.ui.fragments.DepartamentoFragment
import com.nexthire.nexhire.ui.fragments.HabilidadesFragment
import com.nexthire.nexhire.ui.fragments.ProfesionFragment
import androidx.appcompat.widget.Toolbar
import com.nexthire.nexhire.ui.fragments.DashboardFragment
import androidx.compose.ui.platform.ComposeView
import android.view.View

class Main_Menu : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var composeContainer: ComposeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navigation_drawer)
        bottomNav = findViewById(R.id.btn_navigation)
        composeContainer = findViewById(R.id.composeContainer)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btn_home -> {
                    composeContainer.visibility = View.GONE
                    replaceFragment(DashboardFragment2(), "Dashboard")
                }
                R.id.btn_profile -> {
                    composeContainer.visibility = View.GONE
                    replaceFragment(ProfileFragment(), "Perfil")
                }
                R.id.btn_settings -> {
                    composeContainer.visibility = View.GONE
                    replaceFragment(SettingsFragment(), "Configuración")
                }
            }
            true
        }

        navView.setNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_cargo -> {
                    composeContainer.visibility = View.GONE
                    replaceFragment(CargoFragment(), "Cargos")
                }
                R.id.nav_profesion -> {
                    composeContainer.visibility = View.GONE
                    replaceFragment(ProfesionFragment(), "Profesiones")
                }
                R.id.nav_habilidades -> {
                    composeContainer.visibility = View.GONE
                    replaceFragment(HabilidadesFragment(), "Habilidades")
                }
                R.id.nav_departament -> {
                    composeContainer.visibility = View.GONE
                    replaceFragment(DepartamentoFragment(), "Departamentos")
                }
                R.id.nav_apiscreen -> {

                    composeContainer.visibility = View.VISIBLE
                    composeContainer.setContent {
                    }

                    supportFragmentManager.beginTransaction()
                        .remove(supportFragmentManager.findFragmentById(R.id.fragment_container) ?: return@setNavigationItemSelectedListener true)
                        .commit()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


        replaceFragment(DashboardFragment2(), "Dashboard", addToBackStack = false)

        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            when(currentFragment) {
                is DashboardFragment2 -> supportActionBar?.title = "Dashboard"
                is ProfileFragment -> supportActionBar?.title = "Perfil"
                is SettingsFragment -> supportActionBar?.title = "Configuración"
                is CargoFragment -> supportActionBar?.title = "Cargos"
                is ProfesionFragment -> supportActionBar?.title = "Profesiones"
                is HabilidadesFragment -> supportActionBar?.title = "Habilidades"
                is DepartamentoFragment -> supportActionBar?.title = "Departamentos"
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
    }
    private fun replaceFragment(fragment: Fragment, title: String = "", addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
        supportActionBar?.title = title
    }
}