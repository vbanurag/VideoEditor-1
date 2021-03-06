package com.github.xch168.videoeditor.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.github.xch168.videoeditor.R;
import com.github.xch168.videoeditor.adapter.VideoThumbnailAdapter;
import com.github.xch168.videoeditor.media.FrameExtractor;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VideoThumbProgressBar extends FrameLayout {
    private RecyclerView mVideoThumbnailGallery;
    private SeekBar mSeekBar;

    private VideoThumbnailAdapter mThumbnailAdapter;

    private FrameExtractor mFrameExtractor;

    private long mTotalTime;

    public VideoThumbProgressBar(Context context) {
        super(context);

        initView();
    }

    public VideoThumbProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public VideoThumbProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.video_thumb_progress_bar, this);

        mFrameExtractor = new FrameExtractor();

        mVideoThumbnailGallery = findViewById(R.id.video_thumbnails);
        mVideoThumbnailGallery.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mThumbnailAdapter = new VideoThumbnailAdapter(getContext(), mFrameExtractor);
        mVideoThumbnailGallery.setAdapter(mThumbnailAdapter);

        mSeekBar = findViewById(R.id.seek_bar);
        mSeekBar.setMax(1000);
        mSeekBar.setPadding(0, 0, 0 , 0);
    }

    public void setDataSource(String path) {
        mFrameExtractor.setDataSource(path);

        mTotalTime = mThumbnailAdapter.fetchDuration();
    }

    public long getTotalTime() {
        return mTotalTime;
    }

    public FrameExtractor getFrameExtractor() {
        return mFrameExtractor;
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        mSeekBar.setOnSeekBarChangeListener(listener);
    }
}
