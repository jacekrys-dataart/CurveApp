package pl.myosolutions.curveapp.java.pl.myosolutions.curveapp.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mockito;

import pl.myosolutions.curveapp.model.Total;
import pl.myosolutions.curveapp.viewmodel.SummatorViewModel;

public class SummatorViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Test
    public void shouldCalculateTotalForTwoValues() {
        //given
        SummatorViewModel summatorViewModel = new SummatorViewModel();
        Observer<Total> observer = Mockito.mock(Observer.class);

        //when
        summatorViewModel.getTotal().observeForever(observer);
        summatorViewModel.onValueEdited(0, 0, 2);
        summatorViewModel.onValueEdited(0, 1, 10);

        //then
        Mockito.verify(observer).onChanged(new Total(12));
    }

    @Test
    public void shouldCalculateTotalForSixValues(){

        SummatorViewModel summatorViewModel = new SummatorViewModel();
        Observer<Total> observer = Mockito.mock(Observer.class);

        //when
        summatorViewModel.getTotal().observeForever(observer);
        summatorViewModel.onValueEdited(0, 0, 2);
        summatorViewModel.onValueEdited(0, 1, 10);
        summatorViewModel.onValueEdited(1, 0, 2);
        summatorViewModel.onValueEdited(1, 1, 10);
        summatorViewModel.onValueEdited(2, 0, 2);
        summatorViewModel.onValueEdited(2, 1, 10);


        //then
        Mockito.verify(observer).onChanged(new Total(36));

    }

    @Test
    public void shouldCalculateTotalForOneValue(){

        SummatorViewModel summatorViewModel = new SummatorViewModel();
        Observer<Total> observer = Mockito.mock(Observer.class);

        //when
        summatorViewModel.getTotal().observeForever(observer);
        summatorViewModel.onValueEdited(0, 0, 2);

        //then
        Mockito.verify(observer).onChanged(new Total(2));

    }

    @Test
    public void shouldCalculateTotalAsZeroForNoValue(){

        SummatorViewModel summatorViewModel = new SummatorViewModel();
        Observer<Total> observer = Mockito.mock(Observer.class);

        //when
        summatorViewModel.getTotal().observeForever(observer);

        //then
        Mockito.verify(observer).onChanged(new Total(0));

    }

    @Test
    public void shouldCalculateTotalForManipulationSameValue(){

        SummatorViewModel summatorViewModel = new SummatorViewModel();
        Observer<Total> observer = Mockito.mock(Observer.class);

        //when
        summatorViewModel.getTotal().observeForever(observer);
        summatorViewModel.onValueEdited(0, 1, 10);
        summatorViewModel.onValueEdited(0, 1, 3);


        //then
        Mockito.verify(observer).onChanged(new Total(3));

    }


    @Test
    public void shouldCalculateTotalForManipulationMixedValue(){

        SummatorViewModel summatorViewModel = new SummatorViewModel();
        Observer<Total> observer = Mockito.mock(Observer.class);

        //when
        summatorViewModel.getTotal().observeForever(observer);
        summatorViewModel.onValueEdited(0, 1, 10);
        summatorViewModel.onValueEdited(0, 1, 9);
        summatorViewModel.onValueEdited(0, 1, 13);
        summatorViewModel.onValueEdited(1, 1, 71);


        //then
        Mockito.verify(observer).onChanged(new Total(84));

    }

}