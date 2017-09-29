package com.example.giftest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    GifImageView giv1;//由按键控制播放的gif
    GifImageView giv2;//由MediaPlayerControl控制的播放的gif
    GifDrawable gifDrawable;//资源对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGiv1();//初始化由按键控制播放的gif
        initGiv2();//初始化由MediaPlayerControl控制的播放的gif
    }

    //初始化由按键控制播放的gif
    private void initGiv1() {
        giv1 = (GifImageView) findViewById(R.id.giv1);
        //这里控制播放的对象实际是gifDrawable
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.hzw);
            giv1.setImageDrawable(gifDrawable);//这里是实际决定资源的地方，优先级高于xml文件的资源定义
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //开始/继续播放
    public void startClick(View v) {
        gifDrawable.start();
    }

    //暂停播放
    public void stopClick(View v) {
        gifDrawable.stop();
    }

    //重新开始播放
    public void resetClick(View v) {
        gifDrawable.reset();
    }

    //初始化由MediaPlayerControl控制的播放的gif
    private void initGiv2() {
        giv2 = (GifImageView) findViewById(R.id.giv2);
        final MediaController mc = new MediaController(this);
        mc.setMediaPlayer((GifDrawable) giv2.getDrawable());
        mc.setAnchorView(giv2);
        giv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mc.show();
            }
        });
    }

}
