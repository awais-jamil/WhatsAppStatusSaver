package com.deltatechforce.statusdownloaderforwhatsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.deltatechforce.statusdownloaderforwhatsapp.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        backButton = findViewById(R.id.back)
        backButton.setOnClickListener {
            this.finish()
        }
    }
}
