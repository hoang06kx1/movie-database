package nguyen.hoang.movierating;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Hoang on 5/29/2016.
 */
@Singleton
@Component(modules = MockParseWrapperModule.class)
public interface MockParseWrapperComponent {
    void inject();
}
