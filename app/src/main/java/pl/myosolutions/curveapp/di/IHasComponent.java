package pl.myosolutions.curveapp.di;

/**
 * Class that implements this interface returns a Dagger component.
 * @param <T> a class of the component.
 */
public interface IHasComponent<T> {
    T getComponent();
}
