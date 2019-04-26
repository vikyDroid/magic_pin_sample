package com.droid.magicpin


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout.VERTICAL
import java.util.*

class MainActivity : AppCompatActivity() {

    private var recyclerViewFeed: ExoPlayerRecyclerView? = null

    private val videoInfoList = ArrayList<VideoInfo>()
    private var mAdapter: VideoRecyclerViewAdapter? = null
    private var firstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareVideoList()
        recyclerViewFeed = findViewById(R.id.recyclerViewFeed)
        recyclerViewFeed!!.setVideoInfoList(videoInfoList)
        mAdapter = VideoRecyclerViewAdapter(videoInfoList)
        recyclerViewFeed!!.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        val itemDecor = DividerItemDecoration(recyclerViewFeed!!.context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_drawable)!!)
        recyclerViewFeed!!.addItemDecoration(itemDecor)
        recyclerViewFeed!!.itemAnimator = DefaultItemAnimator()
        recyclerViewFeed!!.adapter = mAdapter

        if (firstTime) {
            Handler(Looper.getMainLooper()).post {
                recyclerViewFeed!!.playVideo() }
            firstTime = false
        }
        recyclerViewFeed!!.scrollToPosition(0)

    }

    private fun prepareVideoList() {
        val videoInfo = VideoInfo()
        videoInfo.url = "https://player.vimeo.com/external/286837767.m3u8?s=42570e8c4a91b98cdec7e7bfdf0eccf54e700b69"

        val videoInfo2 = VideoInfo()
        videoInfo2.url = "https://player.vimeo.com/external/286837810.m3u8?s=610b4fee49a71c2dbf22c01752372ff1c6459b9e"

        val videoInfo3 = VideoInfo()
        videoInfo3.url = "https://player.vimeo.com/external/286837723.m3u8?s=3df60d3c1c6c7a11df4047af99c5e05cc2e7ae96"

        val videoInfo4 = VideoInfo()
        videoInfo4.url = "https://player.vimeo.com/external/286837649.m3u8?s=9e486e9b932be72a8875afc6eaae21bab124a35a"

        val videoInfo5 = VideoInfo()
        videoInfo5.url = "https://player.vimeo.com/external/286837529.m3u8?s=20f83af6ea8fbfc8ce0c2001f32bf037f8b0f65f"

        videoInfoList.add(videoInfo)
        videoInfoList.add(videoInfo2)
        videoInfoList.add(videoInfo3)
        videoInfoList.add(videoInfo4)
        videoInfoList.add(videoInfo5)

    }

    override fun onDestroy() {
        if (recyclerViewFeed != null)
            recyclerViewFeed!!.onRelease()
        super.onDestroy()
    }
}
