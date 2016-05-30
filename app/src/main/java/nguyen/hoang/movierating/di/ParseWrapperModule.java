package nguyen.hoang.movierating.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nguyen.hoang.movierating.util.ParseWrapper;

/**
 * Created by Hoang on 5/29/2016.
 */
@Module
public class ParseWrapperModule {
    @Provides
    @Singleton
    ParseWrapper provideParseWrapper() {
        return new ParseWrapper();
    }
}
