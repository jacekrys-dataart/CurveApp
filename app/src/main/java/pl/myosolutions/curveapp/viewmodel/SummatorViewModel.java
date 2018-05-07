package pl.myosolutions.curveapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.Editable;
import android.text.TextWatcher;

import com.annimon.stream.Stream;

import java.util.HashMap;

import pl.myosolutions.curveapp.StringUtility;
import pl.myosolutions.curveapp.model.Total;

public class SummatorViewModel extends ViewModel {

    private MutableLiveData<Total> totalObservable;
    public HashMap<String, Integer> values = new HashMap<>();
    public Total totalModel;
    public MutableLiveData<Boolean> isFlashingOn = new MutableLiveData<>();

    public SummatorViewModel() {
        totalModel = new Total(0);
        isFlashingOn.setValue(false);
    }

    public LiveData<Total> getTotal() {
        if (totalObservable == null) {
            totalObservable = new MutableLiveData<>();
            totalObservable.setValue(calculateTotal());
        }
        return totalObservable;
    }

    public TextWatcher getTextWatcher(final int row, final int column) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int newValue = 0;
                    if (editable != null && !editable.toString().isEmpty()) {
                        newValue = Integer.parseInt(editable.toString());
                    }
                    onValueEdited(row, column, newValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }


    public Total calculateTotal() {
        int total = Stream.of(values).mapToInt(stringIntegerEntry -> stringIntegerEntry.getValue()).sum();

        return new Total(total);
    }


    public void onStartFlashing() {
        isFlashingOn.setValue(!isFlashingOn.getValue());
    }

    public void onValueEdited(int row, int column, int value) {
        values.put(StringUtility.stringFromNumbers(row, column), value);
        totalObservable.setValue(calculateTotal());
    }

}

