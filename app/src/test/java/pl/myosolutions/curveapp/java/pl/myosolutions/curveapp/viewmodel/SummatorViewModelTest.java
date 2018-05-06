package pl.myosolutions.curveapp.java.pl.myosolutions.curveapp.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import pl.myosolutions.curveapp.model.Total;
import pl.myosolutions.curveapp.view.SummatorActivity;
import pl.myosolutions.curveapp.viewmodel.SummatorViewModel;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SummatorViewModelTest {

    private SummatorActivity summatorActivity;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock private ViewModelProvider viewModelProvider;
    @Mock private SummatorViewModel viewModel;
    @Mock private MutableLiveData<Total> totalObservable;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        summatorActivity = new SummatorActivity();
        viewModel = Mockito.spy(new SummatorViewModel());

        when(viewModelProvider.get(any(Class.class))).thenReturn(viewModel);
        when(viewModel.getTotal()).thenReturn(totalObservable);


    }

    @Test
    public void onValueEdited(){

     /*   totalObservable.setValue(new Total(5));
        assertEquals(totalObservable.getValue().getTotal(), 5);*/



        Observer observer = mock(Observer.class);
        totalObservable.observeForever(observer);
        viewModel.onValueEdited(0,0,5);

        verify(totalObservable).observe(eq(summatorActivity), any(Observer.class));
        assertEquals(totalObservable.getValue().getTotal(), 5);
    }



}
