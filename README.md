# CurveApp
Curve Challenge implementation.

Task

● Create an app that shows six numbers (fields) on screen, laid out in two columns. 
- Done

● Below these fields, a seventh field should show the sum of these six numbers. 
- Done

● The user can tap on any of the six numbers and edit the number. 
- Done

● Editing the number should cause the sum to be updated. 
- Done

● Tapping on the sum should toggle flashing of this field, defined as follows: when
flashing, the value should be shown for 500ms, then hidden for 500ms, repeated
indefinitely; when not flashing, the value should be visible.  
- Done

● Please include unit tests. 
- Implemented tests for Model. Done


Bonus points:

● Demonstrate at least two different ways of implementing the flashing total, including at
least one method not using RxJava.

For this bonus points I created three different implementation of handling flashing total. 
Timer, Handler and RxJava. You can test it with  three different .apk:
app-timer-debug.apk
app-handler-debug.apk
app-rxjava-debug.apk

Or build it locally choosing appropriate build variant.
I used flavors to distinguish those approaches.

● Use Dependency Injection to create the ViewModel for the Activity.

Implemented. However I decided to use ViewModelProviders.of(..) approach as it holds the ViewModel.class even when app is recreating.
To use injected ViewModel, just comment line SummatorActivity.java#94/


Happy Reviewing! :)
