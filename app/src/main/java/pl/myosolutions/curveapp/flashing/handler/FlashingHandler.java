package pl.myosolutions.curveapp.flashing.handler;

import android.os.Handler;

import pl.myosolutions.curveapp.flashing.FlashingCallback;

import static pl.myosolutions.curveapp.flashing.FlashingConfig.TIME_PERIOD;

public class FlashingHandler {

    private FlashingCallback mCallback;

    private Handler handler = new Handler();
    private Runnable flashingRunnable = new Runnable() {
        @Override
        public void run() {
            if(mCallback!=null) mCallback.flipVisibility();

            handler.postDelayed(this, TIME_PERIOD);
        }
    };

    public FlashingHandler(FlashingCallback callback) {
        this.mCallback = callback;
    }

    public void onChanged(Boolean goFlashing){
        if (goFlashing) {
            handler.post(flashingRunnable);
        } else {
            handler.removeCallbacks(flashingRunnable);
        }
    }
}
