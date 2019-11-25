package com.deltatechforce.statusdownloaderforwhatsapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.deltatechforce.statusdownloaderforwhatsapp.R
import com.deltatechforce.statusdownloaderforwhatsapp.baseControls.BaseFragment
import com.deltatechforce.statusdownloaderforwhatsapp.models.StatusModel
import com.deltatechforce.statusdownloaderforwhatsapp.viewmodels.DashboardViewModel
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class PhotoFragment : BaseFragment() {

    val dashboardViewModel by activityViewModels<DashboardViewModel>()

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: StaggeredGridLayoutManager

    var statusList: ArrayList<StatusModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_photo, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = Adapter()

        dashboardViewModel.dataLoaded.observe(
            viewLifecycleOwner,
            Observer { loaded ->

                if(loaded){

                    statusList = dashboardViewModel.imageStatusList

                    activity?.runOnUiThread {

                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                } else {
                    statusList = ArrayList()

                    activity?.runOnUiThread {

                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                }
            })


        return view
    }


    inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>()  {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
            val inflatedView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_for_status, parent, false)
            return ViewHolder(inflatedView)

        }

        override fun getItemCount(): Int {
            return statusList.size
        }

        override fun onBindViewHolder(holder:Adapter.ViewHolder, position: Int) {

            holder.bindView(position)
            var status = statusList.get(position)

            holder.image.setOnClickListener {
                dashboardViewModel.selectedImage = status
                findNavController().navigate(R.id.action_dashBoardFragment_to_bigImageFragment)
            }

            holder.downloadButton.setOnClickListener {
                dashboardViewModel.downloadStatus(status)
            }
        }

        inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

            private var view: View = v
            lateinit var name: TextView
            lateinit var image: ImageView
            lateinit var downloadButton: ImageButton
            lateinit var playButton: ImageButton

            fun bindView(position: Int) {

                view.tag = position
                view.setOnClickListener(this)

                playButton = view.findViewById(R.id.play_button)
                playButton.visibility = View.GONE
                downloadButton = view.findViewById(R.id.download)
                name = view.findViewById(R.id.name)
                image = view.findViewById(R.id.image)

                var status = statusList.get(position)

//                image.setImageBitmap(status.thumbnail)
                Picasso.get().load(status.file).fit().centerInside().into(image)


            }

            override fun onClick(v: View) {

            }
        }
    }

}
