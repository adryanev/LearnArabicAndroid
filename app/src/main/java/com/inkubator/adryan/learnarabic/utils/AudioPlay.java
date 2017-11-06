package com.inkubator.adryan.learnarabic.utils;

/**
 * Created by adryanev on 06/11/17.
 */
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.youtube.player.internal.r;

import java.io.IOException;

public class AudioPlay {
    public static MediaPlayer mediaPlayer;
    private static SoundPool soundPool;
    public static boolean isplayingAudio=false;
    static boolean a = false;

    public static void playAudio(Context c,String path){
        mediaPlayer = new MediaPlayer();
        if(!mediaPlayer.isPlaying()){
            try {
                mediaPlayer.setDataSource(path);

            } catch (IOException e) {
                Toast.makeText(c,"Tidak dapat menemukan file audio.",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
        if(!isplayingAudio){
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                    if(!mediaPlayer.isPlaying())
                    {
                        isplayingAudio=true;
                        mediaPlayer.start();
                    }
                    else {
                        isplayingAudio = false;
                        mediaPlayer.pause();
                    }
                }
            });

        }
    }

    public static boolean isComplete(){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
               a = true;
            }
        });
        return a;
    }

    public static void stopAudio(){
        isplayingAudio=false;
        mediaPlayer.stop();
    }
    public static void pauseAudio(){
        isplayingAudio=false;
        mediaPlayer.pause();
    }
}
