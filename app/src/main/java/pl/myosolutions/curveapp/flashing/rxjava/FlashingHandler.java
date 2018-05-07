package pl.myosolutions.curveapp.flashing.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import pl.myosolutions.curveapp.flashing.FlashingCallback;

import static pl.myosolutions.curveapp.flashing.FlashingConfig.TIME_PERIOD;

public class FlashingHandler {

    private FlashingCallback mCallback;

    private Disposable disposable;

    public FlashingHandler(FlashingCallback callback) {
        this.mCallback = callback;
    }

    public void onChanged(Boolean goFlashing){
        if (goFlashing) {

            /*HACK: instead of Observable.interval it is also possible to use Schedulers to fulfil the requirement
            disposable = Schedulers.newThread().schedulePeriodicallyDirect(() -> mCallback.flipVisibility(), TIME_PERIOD, TIME_PERIOD, TimeUnit.MILLISECONDS);
            */

            disposable = Observable.interval(TIME_PERIOD, TimeUnit.MILLISECONDS)
                    .subscribe(interval -> mCallback.flipVisibility());

        } else if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }




    }



}
