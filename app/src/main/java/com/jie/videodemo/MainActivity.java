package com.jie.videodemo;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.player.media.IjkPlayerView;
import com.dl7.player.widgets.ShareDialog;
import com.jie.videodemo.danmu.DanmakuConverter;
import com.jie.videodemo.danmu.DanmakuLoader;
import com.jie.videodemo.danmu.DanmakuParser;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String mVideoUrl1 = "http://app.ahcftv.com/res/video/201803/24/ff71323a65e43281.mp4";
    String mVideoUrl2 = "http://app.ahcftv.com/res/video/201803/24/ff71323a65e43281.mp4";
    String mVideoUrl3 = "http://flv2.bn.netease.com/videolib3/1611/28/nNTov5571/SD/nNTov5571-mobile.mp4";
    private static final String IMAGE_URL = "http://vimg3.ws.126.net/image/snapshot/2015/5/J/M/VAPRJCSJM.jpg";
    ImageView iv_share, iv_share_window;
    private IjkPlayerView mPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mPlayerView = (IjkPlayerView) findViewById(R.id.player_view);

        Glide.with(this).load(IMAGE_URL).fitCenter().into(mPlayerView.mPlayerThumb);
        InputStream stream = null;
        try {
            stream = getAssets().open("custom.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayerView.init(false, true)
                .setTitle("标题要足够长才会跑。-(゜ -゜)つロ 乾杯~aaaaaaaaaaaaaaaaaaaaaa")
                .setDialogClickListener(new ShareDialog.OnDialogClickListener() {
                    @Override
                    public void onShare(Bitmap bitmap, Uri uri) {
                        Toast.makeText(MainActivity.this, "分享图片bitmap地址：" + bitmap.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .enableDanmaku()
                .setDanmakuCustomParser(new DanmakuParser(), DanmakuLoader.instance(), DanmakuConverter.instance())
                .setDanmakuSource(stream)
                .setVideoSource(null, mVideoUrl1, mVideoUrl2, mVideoUrl3, null)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH);
        iv_share = mPlayerView.findViewById(R.id.iv_share);
        iv_share_window = mPlayerView.findViewById(R.id.iv_share_window);
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "外面的分享", Toast.LENGTH_SHORT).show();
            }
        });
        iv_share_window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "外面的分享", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        findViewById(R.id.b1).setOnClickListener(this);
        findViewById(R.id.b2).setOnClickListener(this);
        findViewById(R.id.b3).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                mPlayerView.switchVideoSource(null, mVideoUrl1, mVideoUrl2, mVideoUrl3, null)
                        .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                        .enableDanmaku()
                        .setTitle("标题要足够长才会跑。-(゜ -゜)つロ ~aaaaaaaaaaaaaaaaaaaaaa")
                        .start();
                break;
            case R.id.b2:
                mPlayerView.switchVideoSource(null, mVideoUrl2, mVideoUrl1, mVideoUrl3, null)
                        .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                        .enableDanmaku()
                        .setTitle("标题要足够长才会跑。-(゜ -゜)つロ ~aaaaaaaaaaaaaaaaaaaaaa")
                        .start();
                break;
            case R.id.b3:
                mPlayerView.switchVideoSource(null, mVideoUrl1, mVideoUrl2, mVideoUrl3, null)
                        .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                        .setTitle("标题要足够长才会跑。-(゜ -゜)つロ ~aaaaaaaaaaaaaaaaaaaaaa")
                        .start();
                break;
        }
    }
}
