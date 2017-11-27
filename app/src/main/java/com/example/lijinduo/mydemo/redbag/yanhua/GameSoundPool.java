package com.example.lijinduo.mydemo.redbag.yanhua;
import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class GameSoundPool {
    private Context mContext;

    public GameSoundPool(Context context) {
        mContext = context;
        initSounds();
    }

    // 音效的音量
    int streamVolume;

    // 定义SoundPool对象
    private SoundPool soundPool;

    // 定义HASH表
    private HashMap<Integer, Integer> soundPoolMap;

    /***************************************************************
     * Function:initSounds(); Parameters:null Returns:None. Description:初始化声音系统
     * Notes:none.
     ***************************************************************/
    public void initSounds() {
        // 初始化soundPool对象,第一个参数是允许有多少个声音流同时播放,第2个参数是声音类型,第三个参数是声音的品质
        soundPool = new SoundPool(100, AudioManager.STREAM_MUSIC, 100);

        // 初始化HASH表
        soundPoolMap = new HashMap<Integer, Integer>();

        // 获得声音设备和设备音量
        AudioManager mgr = (AudioManager) mContext
                .getSystemService(Context.AUDIO_SERVICE);
        streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /***************************************************************
     * Function:loadSfx(); Parameters:null Returns:None. Description:加载音效资源
     * Notes:none.
     ***************************************************************/
    public void loadSfx(int raw, int ID) {
        // 把资源中的音效加载到指定的ID(播放的时候就对应到这个ID播放就行了)
        soundPoolMap.put(ID, soundPool.load(mContext, raw, ID));
    }

    /***************************************************************
     * Function:play(); Parameters:sound:要播放的音效的ID,loop:循环次数 Returns:None.
     * Description:播放声音 Notes:none.
     ***************************************************************/
    public void play(int sound, int uLoop) {
        soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1,
                uLoop, 1f);

    }
}