package com.example.myapplication;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<Integer> videoResourceIds;
    private Context context;

    public VideoAdapter(List<Integer> videoResourceIds, Context context) {
        this.videoResourceIds = videoResourceIds;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        int videoResourceId = videoResourceIds.get(position);
        holder.bindVideo(videoResourceId);
    }

    @Override
    public int getItemCount() {
        return videoResourceIds.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
        }

        public void bindVideo(int videoResourceId) {
            String videoPath = "android.resource://" + context.getPackageName() + "/" + videoResourceId;
            videoView.setVideoURI(Uri.parse(videoPath));

            // Agregar controles de reproducción
            MediaController mediaController = new MediaController(context);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            videoView.start();
        }
    }
}


