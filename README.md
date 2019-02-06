Android application project for showing Thor films

Architecture.
This application has been developed following MVP architecture, 
with a DataManager class ruling into the business layer, and several
Presenter classes linking DataManager class with Activity classes.

Libraries.
Design support for RecyclerView
All secondary thread process are controlled with RxJava
Dependency injection with Dagger2
Unit testing with Mockito and jUnit, implemented for presenters testing
Instrumented testing with Cucumber and Espresso, implemented for Activities testing

View navigation
Two activities, MainActivity for show a vertical list with all Thor films,
and a automatized ViewPager for showing the most featured.
By touching any item in both list, DetailActivity is open, showing further info about the film.


  