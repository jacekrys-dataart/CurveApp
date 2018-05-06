package pl.myosolutions.curveapp;

import android.app.Application;
import android.content.Context;

import pl.myosolutions.curveapp.di.AppComponent;
import pl.myosolutions.curveapp.di.AppModule;
import pl.myosolutions.curveapp.di.DaggerAppComponent;

public class CurveApplication extends Application {


    private AppComponent appComponent;
    private static CurveApplication instance;

    public static synchronized CurveApplication getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initAppComponent();
    }

    public static CurveApplication get(Context context) {
        return (CurveApplication) context.getApplicationContext();
    }

    protected DaggerAppComponent.Builder prepareAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this));
    }

    private void initAppComponent() {
        appComponent = prepareAppComponent().build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }


}
