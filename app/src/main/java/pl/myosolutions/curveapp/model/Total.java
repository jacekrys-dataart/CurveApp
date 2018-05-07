package pl.myosolutions.curveapp.model;

public class Total {

    private static final String TAG = Total.class.getSimpleName();

    private int total;


    public Total(int total) {
        this.total = total;
    }


    public int getTotal() {
        return total;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Total total1 = (Total) o;
        return total == total1.total;
    }
}
