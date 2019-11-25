package com.deltatechforce.statusdownloaderforwhatsapp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.deltatechforce.statusdownloaderforwhatsapp.R
import com.deltatechforce.statusdownloaderforwhatsapp.activities.DashBoardActivity
import com.deltatechforce.statusdownloaderforwhatsapp.baseControls.BaseFragment
import com.deltatechforce.statusdownloaderforwhatsapp.viewmodels.DashboardViewModel
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 *
 */
class BigImageFragment : BaseFragment() {

    val dashboardViewModel by activityViewModels<DashboardViewModel>()
    lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.big_image_layout, container, false)

        imageView = view.findViewById(R.id.image_view)

        return view
    }

    override fun onResume() {
        super.onResume()

        (activity as DashBoardActivity).hideTopBar()

        if(dashboardViewModel.selectedImage != null){
            Picasso.get().load(dashboardViewModel.selectedImage.file).fit().centerInside().into(imageView)
        }
    }

    override fun onPause() {
        super.onPause()

        (activity as DashBoardActivity).showTopBar()
    }
}
