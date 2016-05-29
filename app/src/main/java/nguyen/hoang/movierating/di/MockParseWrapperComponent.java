package nguyen.hoang.movierating.di;

import javax.inject.Singleton;

import dagger.Component;
import nguyen.hoang.movierating.Utils.ParseWrapper;

/**
 * Created by Hoang on 5/29/2016.
 */
@Singleton
@Component(modules = MockParseWrapperModule.class)
public interface MockParseWrapperComponent extends ParseWrapperComponent{
    ParseWrapper inject();
}
