package pl.myosolutions.curveapp.di;

import javax.inject.Scope;

/**
 * Activity-wide scope. It means when activity is dead, so is all objects with the same scope.
 */
@Scope
public @interface ActivityScope {
}
