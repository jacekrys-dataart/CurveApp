package pl.myosolutions.curveapp.model;

import java.util.HashMap;

public class Total {

    private static final String TAG = Total.class.getSimpleName();

    private int total;


    public Total(int total) {
        this.total = total;
    }


    public int getTotal() {
        return total;
    }

    public Total calculateTotal(HashMap<String, Integer> map) {
        total = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            total = map.values().stream().mapToInt(Number::intValue).sum();
        } else {
            if (map != null) {
                for (Integer value : map.values()) {
                    total += value.intValue();
                }
            }
        }

        return new Total(total);
    }
}
