package nguyen.hoang.movierating.di;

import javax.inject.Singleton;

import dagger.Component;
import nguyen.hoang.movierating.util.ParseWrapper;

/**
 * Created by Hoang on 5/29/2016.
 */
@Singleton
@Component(modules = MockParseWrapperModule.class)
public interface MockParseWrapperComponent extends ParseWrapperComponent{
    ParseWrapper inject();
}
