package com.deltatechforce.statusdownloaderforwhatsapp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.deltatechforce.statusdownloaderforwhatsapp.R
import com.deltatechforce.statusdownloaderforwhatsapp.activities.DashBoardActivity
import com.deltatechforce.statusdownloaderforwhatsapp.baseControls.BaseFragment
import com.deltatechforce.statusdownloaderforwhatsapp.viewmodels.DashboardViewModel
import hb.xvideoplayer.MxVideoPlayer
import hb.xvideoplayer.MxVideoPlayerWidget


/**
 * A simple [Fragment] subclass.
 */
class VideoPlayFragment : BaseFragment() {

    val dashboardViewModel by activityViewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video_play, container, false)

        val videoPlayerWidget =
            view.findViewById(R.id.mpw_video_player) as MxVideoPlayerWidget
        videoPlayerWidget.startPlay(dashboardViewModel.selectedVideo.path, MxVideoPlayer.SCREEN_WINDOW_FULLSCREEN, dashboardViewModel.selectedVideo.title)

        return view
    }

    override fun onPause() {
        super.onPause()
        MxVideoPlayer.releaseAllVideos()

        (activity as DashBoardActivity).showTopBar()
    }

    override fun onStart() {
        super.onStart()

        (activity as DashBoardActivity).hideTopBar()

    }
}
