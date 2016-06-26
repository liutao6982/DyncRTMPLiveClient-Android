package org.xxx.livertmp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.avxxx.RendererCommon;
import org.avxxx.VideoRenderer;
import org.avxxx.api.DyncLivePublisher;
import org.avxxx.beautify.widget.BeautifyCameraView;
import org.avxxx.core.AvApp;
import org.avxxx.core.AvStreamer;
import org.avxxx.core.StreamHelper;
import org.avxxx.core.VideoView;
import org.avxxx.event.VideoSurfaceEvent;
import org.xxx.livertmp.R;

/**
 * 推流demo
 * 
 * Created by Skyline on 2016/6/24.
 */
public class PushActivity extends AppCompatActivity implements VideoSurfaceEvent, StreamHelper {

    private RelativeLayout rl_videos;
    private TextView txt_staus;
    private VideoView videoView;

    private String mUrl = "rtmp://www.teameeting.cn/live/f001";
    private boolean mBeauty = false;
    private boolean mRtmp = false;
    private boolean mAudioMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        {
            rl_videos = (RelativeLayout) findViewById(R.id.rl_videos);
            txt_staus = (TextView) findViewById(R.id.txt_status);

            if(!AvStreamer.IsSupportBeauty()) {
                ((Button)findViewById(R.id.btn_click)).setEnabled(false);
            }
        }

        mUrl = getIntent().getExtras().getString("url");
        DyncLivePublisher.Instance().init(this, 1, this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DyncLivePublisher.Instance().destroy();
    }

    public void OnBtnClick(View view) {
        Button btn = (Button) view;
        if(btn.getId() == R.id.btn_click) {
            mBeauty = !mBeauty;
            if(mBeauty) {
                btn.setText("正常");
            } else {
                btn.setText("美顏");
            }
            DyncLivePublisher.Instance().setFaceBeauty(mBeauty);
        } else if(btn.getId() == R.id.btn_rtmp) {
            mRtmp = !mRtmp;
            if(mRtmp) {
                DyncLivePublisher.Instance().startPushRTMP(mUrl);
                btn.setText("停止");
            }
            else {
                DyncLivePublisher.Instance().stopPushRTMP();
                btn.setText("推流");
            }
        } else if(btn.getId() == R.id.btn_switch) {
            DyncLivePublisher.Instance().switchCamera();
        } else if(btn.getId() == R.id.btn_mute) {
            mAudioMuted = !mAudioMuted;
            DyncLivePublisher.Instance().setAudioEnable(mAudioMuted);
        }
    }

    /**
     *
     */
    @Override
    public VideoRenderer OnGetLocalRender() {
        if(videoView == null) {
        	/**
             * 初始化 VideoView 时
             * RendererCommon.ScalingType.SCALE_ASPECT_FIT（适应屏幕大小填充）,
             * RendererCommon.ScalingType.SCALE_ASPECT_FILL（根据图像大小填充）,
             * RendererCommon.ScalingType.SCALE_ASPECT_BALANCED（平衡FIT和FILL）
             */
            videoView = new VideoView(this, AvApp.Inst().Egl(),0, 0,0,100,100, RendererCommon.ScalingType.SCALE_ASPECT_FILL);
            rl_videos.addView(videoView.mLayout);
        }

        return videoView.mRenderer;
    }

    @Override
    public BeautifyCameraView OnGetBeautifyRender() {
        BeautifyCameraView beautyView = null;
        beautyView = new BeautifyCameraView(this);
        beautyView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        rl_videos.removeAllViews();
        rl_videos.addView(beautyView);
        return beautyView;
    }


    @Override
    public void OnCloseLocalRender() {

    }

    /**
     * Implements for StreamHelper
     */
    @Override
    public void OnStreamOk() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_staus.setText("连接成功！");
            }
        });
    }

    @Override
    public void OnStreamReconnecting(int times)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_staus.setText("重连服务器...");
            }
        });
    }

    @Override
    public void OnStreamFailed(int code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_staus.setText("连接失败！");
            }
        });
    }

    @Override
    public void OnStreamClosed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_staus.setText("连接中断！");
            }
        });
    }

    @Override
    public void OnStreamStatus(final int delayMs, final int netBand) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_staus.setText("连接成功, delayms:" + delayMs +" net:" + netBand);
            }
        });
    }
}