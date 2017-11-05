package com.inkubator.adryan.learnarabic.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.config.ServerConfig;
import com.inkubator.adryan.learnarabic.model.Video;

import java.util.List;

/**
 * Created by adryanev on 05/11/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoInfoHolder> {
    //these ids are the unique id for each video
    List<Video> videoList;;
    String[] VideoID = {"P3mAtvs5Elc", "nCgQDjiotG0", "P3mAtvs5Elc"};
    Context ctx;

    public VideoAdapter(Context context, List<Video>videos) {
        this.ctx = context;
        this.videoList = videos;

    }

    @Override
    public VideoInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card_view, parent, false);
        return new VideoInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoInfoHolder holder, final int position) {


        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };

        holder.youTubeThumbnailView.initialize(ServerConfig.YOUTUBE_API, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(videoList.get(position).getIdYoutubeVideo());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
                holder.judul.setText(videoList.get(position).getNamaVideo());
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("VideoAdapter","Size: "+String.valueOf(this.videoList.size()));
        return this.videoList.size();

    }

    public class VideoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;
        TextView judul;

        public VideoInfoHolder(View itemView) {
            super(itemView);
            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
            playButton.setOnClickListener(this);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
            judul = (TextView)itemView.findViewById(R.id.judulVideo);
        }

        @Override
        public void onClick(View v) {

            Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) ctx, ServerConfig.YOUTUBE_API, videoList.get(getLayoutPosition()).getIdYoutubeVideo());
            ctx.startActivity(intent);

        }
    }
    /*

    private Context context;
    private List<Video> videoList;
    public class VideoViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        TextView judul;
        ImageView playButton;


        public VideoViewHolder(View itemView) {
            super(itemView);
            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
            playButton.setOnClickListener(this);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
            judul = (TextView) itemView.findViewById(R.id.judulVideo);
        }

        @Override
        public void onClick(View v) {
            Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, ServerConfig.YOUTUBE_API, videoList.get(getLayoutPosition()).getIdYoutubeVideo());
            context.startActivity(intent);
        }
    }
    public VideoAdapter(Context context, List<Video> videos){
        this.context = context;
        this.videoList = videos;
    }
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_card_view,parent,false);
        Log.d("VideoAdapter","Sukses Memuat Layout");
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, final int position) {

        final Video video = videoList.get(position);
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
                Log.d("VideoAdapter","Sukses Memasang Thumbnail: "+s);
            }


            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                Log.d("VideoAdapter","Gagal Memasang Thumbnail: "+errorReason.toString());
            }
        };
        holder.youTubeThumbnailView.initialize(ServerConfig.YOUTUBE_API, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(video.getIdYoutubeVideo());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
                youTubeThumbnailLoader.release();
                Log.d("VideoAdapter","Sukses Memasang Video");
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e("VideoAdapter","Gagal Memasang Video");
            }
        });
        holder.judul.setText(videoList.get(position).getNamaVideo());

        Log.d("VideoAdapter","Sukses memasang cardView Video");
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    */


}
