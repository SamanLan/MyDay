package com.samanlan.lib_ui;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.dd.CircularProgressButton;
import com.flyco.systembar.SystemBarHelper;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private SurfaceView surfaceview;
    private MediaPlayer mediaPlayer;
    private CircularProgressButton btnLogin;

    private int postion = 0;

    public void click(View view) {
        int id = view.getId();
        if (id == R.id.login) {
            btnLogin.setIndeterminateProgressMode(true);
            btnLogin.setProgress(50);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnLogin.setProgress(100);
                    btnLogin.setProgress(0);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }, 3000);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        postion = 1;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (postion == 1) {
            postion = 0;
            initView();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        SystemBarHelper.immersiveStatusBar(this, 0);
        SystemBarHelper.setHeightAndPadding(this, findViewById(R.id.toolBar));
    }

    protected void initView() {
        surfaceview = (SurfaceView) findViewById(R.id.surfaceView);
        btnLogin = (CircularProgressButton) findViewById(R.id.login);
        mediaPlayer = new MediaPlayer();
        surfaceview.getHolder().setKeepScreenOn(true);
        surfaceview.getHolder().addCallback(new SurfaceViewLis());
    }

    private class SurfaceViewLis implements SurfaceHolder.Callback {

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (postion == 0) {
                try {
                    play();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }

    public void play() throws IllegalArgumentException, SecurityException,
            IllegalStateException, IOException {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        AssetFileDescriptor fd = this.getAssets().openFd("start.mp4");
        mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),
                fd.getLength());
        mediaPlayer.setLooping(true);
        mediaPlayer.setDisplay(surfaceview.getHolder());
        // 通过异步的方式装载媒体资源
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 装载完毕回调
                mediaPlayer.start();
            }
        });
    }
}
