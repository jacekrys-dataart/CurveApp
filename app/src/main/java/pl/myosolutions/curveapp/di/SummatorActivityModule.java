package pl.myosolutions.curveapp.di;

import dagger.Module;
import dagger.Provides;
import pl.myosolutions.curveapp.viewmodel.SummatorViewModel;

@Module
public class SummatorActivityModule {

    public SummatorActivityModule() {
    }


    @ActivityScope
    @Provides
    public SummatorViewModel viewModel() {
        return new SummatorViewModel();
    }
}
