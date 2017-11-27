package com.example.lijinduo.mydemo.redbag.yanhua;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.MediaPlayer;
/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class GameSoundMedia {
    public static final int GAME_SOUND_MEDIA_COMPLETION = 1;
    private Context mContext;

    public GameSoundMedia(Context context) {
        mContext = context;
    }

    private List<MediaPlayer> mList = new ArrayList<MediaPlayer>();

    public void stopAll() {
        for (MediaPlayer i : mList) {
            if (i != null) {
                try {
                    if (i.isPlaying()) {
                        i.stop();
                        i.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        mList.clear();
    }

    public MediaPlayer playInMediaPlayer(int resid, final GameSoundEvent event) {
        MediaPlayer player = MediaPlayer.create(mContext, resid);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (event != null)
                    event.onEvent(GAME_SOUND_MEDIA_COMPLETION);
                mp.release();
                mList.remove(mp);
            }
        });
        mList.add(player);
        return player;
    }

    public interface GameSoundEvent {
        public void onEvent(int what);
    }
}