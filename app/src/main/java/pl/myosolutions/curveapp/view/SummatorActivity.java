package pl.myosolutions.curveapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import pl.myosolutions.curveapp.BaseActivity;
import pl.myosolutions.curveapp.CurveApplication;
import pl.myosolutions.curveapp.R;
import pl.myosolutions.curveapp.databinding.ActivitySummatorBinding;
import pl.myosolutions.curveapp.di.DaggerSummatorActivityComponent;
import pl.myosolutions.curveapp.di.IHasComponent;
import pl.myosolutions.curveapp.di.SummatorActivityComponent;
import pl.myosolutions.curveapp.di.SummatorActivityModule;
import pl.myosolutions.curveapp.flashing.FlashingCallback;
import pl.myosolutions.curveapp.flashing.handler.FlashingHandler;
import pl.myosolutions.curveapp.viewmodel.SummatorViewModel;

import static android.view.View.VISIBLE;

/***
 *
 * Implemented flavors for three different options of implementing flashing
 *
 * There are three ways of implementing Flashing total
 * Option 1:
 * Using simple Timer
 *
 * Option 2:
 * Using Handler and Runnable
 *
 * Option 3:
 * Using RxJava's Disposable
 */
public class SummatorActivity extends BaseActivity implements IHasComponent<SummatorActivityComponent>, FlashingCallback{

    //Binding
    private ActivitySummatorBinding binding;

    //Flashing total functionality
    private FlashingHandler flashingHandler;

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

        flashingHandler = new FlashingHandler(this);

    }



    private void onChanged(Boolean goFlashing) {
        if (!goFlashing) binding.total.setVisibility(VISIBLE);

        if (flashingHandler!=null) flashingHandler.onChanged(goFlashing);

    }


    @Override
    public void flipVisibility() {
        runOnUiThread(() -> binding.total.setVisibility(binding.total.getVisibility() == VISIBLE ? View.INVISIBLE : VISIBLE));
    }




}
