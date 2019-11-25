package com.deltatechforce.statusdownloaderforwhatsapp.fragments


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.deltatechforce.statusdownloaderforwhatsapp.R
import com.deltatechforce.statusdownloaderforwhatsapp.baseControls.BaseFragment
import com.deltatechforce.statusdownloaderforwhatsapp.support.SharedPreferences
import com.deltatechforce.statusdownloaderforwhatsapp.viewmodels.DashboardViewModel

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : BaseFragment() {

    val dashboardViewModel by activityViewModels<DashboardViewModel>()

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = Adapter()

        return view
    }

    inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>()  {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
            val inflatedView = LayoutInflater.from(context).inflate(R.layout.setting_recyclerview_item, parent, false)
            return ViewHolder(inflatedView)

        }

        override fun getItemCount(): Int {
            return 1
        }

        override fun onBindViewHolder(holder:Adapter.ViewHolder, position: Int) {

            holder.bindView(position)
        }

        inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

            private var view: View = v
            lateinit var name: TextView

            fun bindView(position: Int) {

                view.tag = position
                view.setOnClickListener(this)
                name = view.findViewById(R.id.name)

                name.text = "Switch WhatsApp"

            }

            override fun onClick(v: View) {
                showShareDialog()
            }
        }
    }

    fun showShareDialog(){

        val dialogBuilder = AlertDialog.Builder(context)

        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog, null)
        dialogBuilder.setView(dialogView)

        val whatsapp = dialogView.findViewById(R.id.one) as Switch
//        val bWhatsapp = dialogView.findViewById(R.id.two) as Switch
        val gbWhatsapp = dialogView.findViewById(R.id.three) as Switch

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        when(SharedPreferences.getType()){
            "WhatsApp" -> {
                whatsapp.isChecked = true
            }
            "GBWhatsApp" -> {
                gbWhatsapp.isChecked = true
            }
        }

        whatsapp.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked)
                {
                    gbWhatsapp.isChecked = false
//                    bWhatsapp.isChecked = false

                    SharedPreferences.setType( "WhatsApp")

                    activity?.runOnUiThread {

                        dashboardViewModel.getStatus()
                    }
                }
            }
        })
//        bWhatsapp.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
//            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//                if(isChecked)
//                {
//                    gbWhatsapp.isChecked = false
//                    whatsapp.isChecked = false
//
//                    SharedPreferences.setType( "BWhatsApp")
//
//
//                    activity?.runOnUiThread {
//
//                        dashboardViewModel.getStatus()
//                    }
//                }
//            }
//        })
        gbWhatsapp.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked)
                {
                    whatsapp.isChecked = false
//                    bWhatsapp.isChecked = false

                    SharedPreferences.setType( "GBWhatsApp")


                    activity?.runOnUiThread {

                        dashboardViewModel.getStatus()
                    }
                }
            }
        })
   }

}
