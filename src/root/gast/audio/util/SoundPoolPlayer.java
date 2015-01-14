package root.gast.audio.util;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.util.Log;

public abstract class SoundPoolPlayer {
	
	private static final String TAG = "SoundPoolPlayer";
	private SoundPool mShortPlayer = null;
	private HashMap mSounds = new HashMap();
	private Context mContext;

	public SoundPoolPlayer(Context pContext)
    {
        // setup Soundpool
		Log.d(TAG, "set sound pool");
        this.mShortPlayer = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        mContext = pContext;
    }
	
	public void loadShortResource(int resId) {
		mSounds.put(resId, this.mShortPlayer.load(mContext, resId, 1));
	}

	public void playShortResource(int piResource) {
		if (mSounds.size() != 0) {
			
			Log.d(TAG, "number of sounds: " + mSounds.size());
			final int iSoundId = (Integer) mSounds.get(piResource);

			this.mShortPlayer.play(iSoundId, 0.99f, 0.99f, 0, 0, 1);
			
			/*
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					SoundPoolPlayer.this.mShortPlayer.play(iSoundId, 0.99f, 0.99f, 0, 0, 1);
				}
			}, 20);
			*/
			
		}
	}

	// Cleanup
	public void release() {
		// Cleanup
		this.mShortPlayer.release();
		this.mShortPlayer = null;
	}
	
	public abstract void playSound();
}
