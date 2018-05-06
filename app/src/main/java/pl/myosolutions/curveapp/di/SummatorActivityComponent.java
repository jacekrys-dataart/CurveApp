package pl.myosolutions.curveapp.di;

import dagger.Component;
import pl.myosolutions.curveapp.view.SummatorActivity;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SummatorActivityModule.class)
public interface SummatorActivityComponent {

    void inject(SummatorActivity activity);

}
