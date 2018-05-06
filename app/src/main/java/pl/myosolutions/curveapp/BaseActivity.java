package pl.myosolutions.curveapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        adjustDependencies(savedInstanceState);
        super.onCreate(savedInstanceState);
    }


    protected abstract void adjustDependencies(Bundle savedInstanceState);

}
