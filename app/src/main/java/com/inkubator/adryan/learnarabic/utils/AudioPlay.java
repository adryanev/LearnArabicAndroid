package com.inkubator.adryan.learnarabic.utils;

/**
 * Created by adryanev on 06/11/17.
 */
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;

import java.io.IOException;

public class AudioPlay {
    public static MediaPlayer mediaPlayer;
    private static SoundPool soundPool;
    public static boolean isplayingAudio=false;
    public static void playAudio(Context c,String path){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
        if(!mediaPlayer.isPlaying()){
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
                        mediaPlayer.pause();
                    }
                }
            });
        }
    }
    public static void stopAudio(){
        isplayingAudio=false;
        mediaPlayer.stop();
    }
    public static void pauseAudio(){
        isplayingAudio=false;
        mediaPlayer.stop();
    }
}
