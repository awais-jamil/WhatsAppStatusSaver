package com.deltatechforce.statusdownloaderforwhatsapp.activities

import android.content.Intent
import android.os.Bundle
import com.deltatechforce.statusdownloaderforwhatsapp.R
import com.deltatechforce.statusdownloaderforwhatsapp.baseControls.BaseActivity


class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

//        if(true) {

            //Go to On Boarding user flow
            goToOnBoarding()
//        }

    }

    private fun goToOnBoarding() {

        val intent = Intent(applicationContext, DashBoardActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        startActivity(intent)

        finish()
    }
}
