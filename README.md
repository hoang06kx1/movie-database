
Movie database
----------------------
Movie database is an app that helps user view movies information. User can mark a film as favorite and continue to view movies in offline mode even when there is no internet available.

This personal project aim at experimenting unit test and integration test in Android by using [Robolectric](www.robolectric.org) instead of [Instrumentation test](https://developer.android.com/training/testing/unit-testing/instrumented-unit-tests.html).

**Spec**

* Back-end: Parse.
* Material design supported.
* API lib: [Volley](https://developer.android.com/training/volley/index.html) backed by [okHttp](http://square.github.io/okhttp/).
* Test framework: [Robolectric](www.robolectric.org).
* DI tool: [Dagger 2](https://github.com/google/dagger).

**Test**

* I only finished tests for authentication part.
* Robolectric is not mature enough, sometimes it produces weird bugs which hard to track down.
* Code injection by using Annotation can reduce the code coverage unexpectedly (i.e., [Butter Knife](https://github.com/JakeWharton/butterknife)).
* Static methods can not be mocked, PowerMock is unstable and hard to config to run with Robolectric. Work around is using a wrapper class, you can look at [SignInFragmentTest](https://github.com/hoang06kx1/movie-database/blob/master/app/src/test/java/nguyen/hoang/movierating/SignInFragmentTest.java) as an example, a wrapper class [ParseWrapper](https://github.com/hoang06kx1/movie-database/blob/master/app/src/main/java/nguyen/hoang/movierating/util/ParseWrapper.java) is used to wrap Parse static methods and later we mock that [ParseWrapper](https://github.com/hoang06kx1/movie-database/blob/master/app/src/main/java/nguyen/hoang/movierating/util/ParseWrapper.java) to test.
* 100% code coverage is not a reality target, which require much additional effort to refactor the code or apply some design patterns to make the code testable. I removed all Butter knife inject code in [LaunchFragment](https://github.com/hoang06kx1/movie-database/blob/master/app/src/main/java/nguyen/hoang/movierating/ui/authentication/fragment/LaunchFragment.java) and it can reach 100% code coverage.


