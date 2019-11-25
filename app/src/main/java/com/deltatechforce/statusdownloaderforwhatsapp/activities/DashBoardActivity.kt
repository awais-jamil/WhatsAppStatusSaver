package com.deltatechforce.statusdownloaderforwhatsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.deltatechforce.statusdownloaderforwhatsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashBoardActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var menu: ImageButton
    lateinit var appBar: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val navController = findNavController(R.id.nav_host_fragment)

        appBar = findViewById(R.id.app_bar_layout)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.isItemHorizontalTranslationEnabled = false

        bottomNavigationView.setupWithNavController(navController)

        menu = findViewById(R.id.menu)
        menu.setOnClickListener {
            showPopup(menu)
        }
    }

    fun hideTopBar(){
        appBar.visibility = View.GONE
    }

    fun showTopBar(){
        appBar.visibility = View.VISIBLE
    }

    private fun showPopup(view: View) {

        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.popup_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.exit -> {
                    this.finish()
                }
                R.id.setting -> {
                    val intent = Intent(applicationContext, SettingActivity::class.java)
                    startActivity(intent)
                }
            }

            true
        })

        popup.show()
    }

}
