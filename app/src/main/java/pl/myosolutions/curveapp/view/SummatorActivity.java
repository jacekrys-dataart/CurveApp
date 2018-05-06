package pl.myosolutions.curveapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.myosolutions.curveapp.BaseActivity;
import pl.myosolutions.curveapp.CurveApplication;
import pl.myosolutions.curveapp.R;
import pl.myosolutions.curveapp.databinding.ActivitySummatorBinding;
import pl.myosolutions.curveapp.di.DaggerSummatorActivityComponent;
import pl.myosolutions.curveapp.di.IHasComponent;
import pl.myosolutions.curveapp.di.SummatorActivityComponent;
import pl.myosolutions.curveapp.di.SummatorActivityModule;
import pl.myosolutions.curveapp.viewmodel.SummatorViewModel;

import static android.view.View.VISIBLE;

/***
 * There are three ways of implementing Flashing total
 * Option 1:
 * Using simple Timer
 *
 * Option 2:
 * Using Handler and Runnable
 *
 * Option 3:
 * Using RxJava's Disposable, Scheduler
 */
public class SummatorActivity extends BaseActivity implements IHasComponent<SummatorActivityComponent> {

    private long TIME_PERIOD = 500L;

    //Binding
    private ActivitySummatorBinding binding;

    //Flashing total functionality
    private Timer timer;

    private Handler handler = new Handler();
    private Runnable flashingRunnable = new Runnable() {
        @Override
        public void run() {
            flipVisibility();
            handler.postDelayed(this, TIME_PERIOD);
        }
    };

    private Disposable disposable;



    //DI
    private SummatorActivityComponent mComponent;

    @Inject
    SummatorViewModel viewModel;

    @Override
    protected void adjustDependencies(Bundle savedInstanceState) {
        mComponent = DaggerSummatorActivityComponent.builder()
                .appComponent(CurveApplication.getApplication().getAppComponent())
                .summatorActivityModule(new SummatorActivityModule())
                .build();
        mComponent.inject(this);

    }


    @Override
    public SummatorActivityComponent getComponent() {
        return mComponent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_summator);

        /**DI
         *Here we could use injected viewModel. However I prefer to use ViewModel from ViewModelProviders as it keeps the object even when app is recreating
         */
        SummatorViewModel viewModel = ViewModelProviders.of(this).get(SummatorViewModel.class);
        binding.setVm(viewModel);

        viewModel.getTotal().observe(this, total ->
                binding.setTotalObject(total));

        viewModel.isFlashingOn.observe(this, this::onChanged);

    }

    private void onChanged(Boolean goFlashing) {
        if (!goFlashing) binding.total.setVisibility(VISIBLE);


        /**
         * Option 1.
         * To try this option uncomment this code and comment Option 2 and Option 3.
         */
/*        if (goFlashing) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new FlashingTask(), TIME_PERIOD, TIME_PERIOD);
        } else if (timer != null) {
            timer.cancel();
        }*/

        /**
         * Option 2.
         * To try this option uncomment this code and comment Option 1 and Option 3.
         */

      /*  if (goFlashing) {
            handler.post(flashingRunnable);
        } else {
            handler.removeCallbacks(flashingRunnable);
        }
*/
        /**
         * Option 3.
         * To try this option uncomment this code and comment Option 1 and Option 2.
         */

        if (goFlashing) {
            disposable = Schedulers.newThread().schedulePeriodicallyDirect(() -> flipVisibility(), TIME_PERIOD, TIME_PERIOD, TimeUnit.MILLISECONDS);
        } else if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private class FlashingTask extends TimerTask {
        @Override
        public void run() {
            flipVisibility();
        }
    }

    private void flipVisibility() {
        runOnUiThread(() -> binding.total.setVisibility(binding.total.getVisibility() == VISIBLE ? View.INVISIBLE : VISIBLE));
    }

}
