package pl.myosolutions.curveapp.flashing.timer;

import java.util.Timer;
import java.util.TimerTask;

import pl.myosolutions.curveapp.flashing.FlashingCallback;

import static pl.myosolutions.curveapp.flashing.FlashingConfig.TIME_PERIOD;

public class FlashingHandler {

    private FlashingCallback mCallback;

    private Timer timer;

    public FlashingHandler(FlashingCallback callback) {
        this.mCallback = callback;
    }

    public void onChanged(Boolean goFlashing){
        if (goFlashing) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new FlashingTask(), TIME_PERIOD, TIME_PERIOD);
        } else if (timer != null) {
            timer.cancel();
        }
    }


    private class FlashingTask extends TimerTask {
        @Override
        public void run() {
            if(mCallback!=null) mCallback.flipVisibility();
        }
    }
}
