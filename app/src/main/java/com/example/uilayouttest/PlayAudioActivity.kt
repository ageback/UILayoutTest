package com.example.uilayouttest

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_play_audio.*

class PlayAudioActivity : AppCompatActivity() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_audio)
        initMediaPlayer()
        btnPlayAudio.setOnClickListener {
            if(!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        btnPauseAudio.setOnClickListener {
            if(mediaPlayer.isPlaying)
            {
                mediaPlayer.pause()
            }
        }

        btnStopAudio.setOnClickListener {
            if(mediaPlayer.isPlaying)
            {
                mediaPlayer.reset()
                initMediaPlayer()
            }
        }
    }

    private fun initMediaPlayer()
    {
        val assetManager = assets
        val fd = assetManager.openFd("music.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}