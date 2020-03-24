package com.example.android.bakingapp


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_video.*

/**
 * A simple [Fragment] subclass.
 */
class VideoFragment : Fragment() {

    private lateinit var playerView: PlayerView
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private val playbackPosition = 0L

    private val bandwidthMeter by lazy {
        DefaultBandwidthMeter()
    }

    private val adaptiveTrackSelectionFactory by lazy {
        AdaptiveTrackSelection.Factory(bandwidthMeter)
    }

    val mainHandler = Handler()

    val args: VideoFragmentArgs by navArgs()
    var videoUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as MainActivity).title = "Procedure"
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description.text = args.thisStep.description
        videoUrl = args.thisStep.videoURL

        if (videoUrl.isNotEmpty()) {
            prepareExoPlayer()
        }
    }

    private fun prepareExoPlayer() {
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(activity!!,
            DefaultTrackSelector(adaptiveTrackSelectionFactory))
        video_exo_player.player = simpleExoPlayer

        val dataSourceFactory = DefaultDataSourceFactory(
            activity!!, Util.getUserAgent(activity!!, "BakingApp"), bandwidthMeter)

        val extractorsFactory = DefaultExtractorsFactory()

        val videoSource = ExtractorMediaSource(
            videoUrl.toUri(),
            dataSourceFactory, extractorsFactory, mainHandler, null
        )

        simpleExoPlayer.prepare(videoSource)

        simpleExoPlayer.playWhenReady = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("Playback Position", simpleExoPlayer.currentPosition)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).title = "Procedure"
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.stop()
        simpleExoPlayer.release()
    }
}
